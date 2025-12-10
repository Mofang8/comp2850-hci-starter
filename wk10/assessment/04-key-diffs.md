# Key Code Diffs — Week 10 Redesign

**Module**: COMP2850 Human-Computer Interaction  
**Student**: [Tianqi Wang]  
**Date**: 2025-11-22

---

## Overview

This document shows annotated before/after code changes for the three priority fixes implemented in Week 10 Lab 2.

---

## Fix 1: No-JS Success Feedback (wk9-01)

### File: `src/main/kotlin/routes/TaskRoutes.kt`

#### POST /tasks (Add Task) — Lines 234-237

**Before**:
```kotlin
// No-JS: POST-Redirect-GET pattern (303 See Other)
call.response.headers.append("Location", "/tasks")
call.respond(HttpStatusCode.SeeOther)
```

**After**:
```kotlin
// No-JS: POST-Redirect-GET pattern with success message (303 See Other)
// Week 10 Fix (wk9-01): Add success feedback for No-JS users
call.response.headers.append("Location", "/tasks?msg=task_added")
call.respond(HttpStatusCode.SeeOther)
```

**Rationale**: Query parameter `?msg=task_added` passes success state across the PRG redirect. This is a stateless approach that doesn't require session storage.

**WCAG**: Addresses principle behind 3.3.1 (success feedback as important as error feedback).

---

#### POST /tasks/{id}/edit (Edit Task) — Lines 399-401

**Before**:
```kotlin
call.response.headers.append("Location", "/tasks")
call.respond(HttpStatusCode.SeeOther)
```

**After**:
```kotlin
// No-JS: POST-Redirect-GET pattern with success message
// Week 10 Fix (wk9-01): Add success feedback for No-JS users
call.response.headers.append("Location", "/tasks?msg=task_updated")
call.respond(HttpStatusCode.SeeOther)
```

**Rationale**: Same pattern as add task. Provides confirmation that edit was saved.

---

#### POST /tasks/{id}/delete (Delete Task) — Lines 296-297

**Before**:
```kotlin
// POST-Redirect-GET pattern (303 See Other)
call.response.headers.append("Location", "/tasks")
call.respond(HttpStatusCode.SeeOther)
```

**After**:
```kotlin
// POST-Redirect-GET pattern with success message (303 See Other)
// Week 10 Fix: Add success feedback for No-JS users (consistency)
call.response.headers.append("Location", "/tasks?msg=task_deleted")
call.respond(HttpStatusCode.SeeOther)
```

**Rationale**: Consistent feedback across all CRUD operations.

---

### File: `src/main/resources/templates/tasks/index.peb`

#### Success Message Display — Lines 6-19

**Before**: (No success message section)

**After**:
```twig
{# Week 10 Fix (wk9-01): Success message for No-JS users after add/edit/delete #}
{% if msg is not empty %}
<div role="status"
     aria-live="polite"
     class="success-message"
     id="success-message">
  {% if msg == "task_added" %}
  Task added successfully.
  {% elseif msg == "task_updated" %}
  Task updated successfully.
  {% elseif msg == "task_deleted" %}
  Task deleted successfully.
  {% endif %}
</div>
{% endif %}
```

**Rationale**:
- `role="status"` + `aria-live="polite"`: Screen readers announce message without interrupting
- `class="success-message"`: Applies enhanced green styling
- Conditional messages for each action type

**WCAG**: 4.1.3 Status Messages (AA) — status announced without receiving focus.

---

## Fix 2: Enhanced Status Message Visibility (wk9-02)

### File: `src/main/resources/static/css/custom.css`

#### Status Styling — Lines 69-96

**Before**:
```css
#status {
  padding: 1rem;
  margin: 1rem 0;
  border-radius: 4px;
  border-left: 4px solid #2196F3;
  background: #e3f2fd;
  color: #0d47a1;
}
```

**After**:
```css
#status {
  padding: 1rem 1.25rem;
  margin: 1rem 0;
  border-radius: 4px;
  border-left: 4px solid #2196F3;
  background: #e3f2fd;
  color: #0d47a1;
  font-size: 1rem;
  font-weight: 500;
}

/* Week 10 Fix (wk9-02): Success styling - more prominent */
#status.success,
.success-message {
  background: #d4edda;
  border: 2px solid #28a745;
  border-left: 4px solid #28a745;
  color: #155724;
  padding: 1rem 1.25rem;
  margin: 1rem 0;
  border-radius: 4px;
  font-size: 1.05rem;
  font-weight: 600;
}
```

**Rationale**:
- Green background (#d4edda) clearly indicates success (colour psychology)
- Larger font (1.05rem) and bold weight (600) increase visibility
- Border provides additional visual emphasis
- Contrast ratio 7.2:1 exceeds WCAG AA requirement (4.5:1)

**WCAG**: 1.4.3 Contrast (AA) — verified with Colour Contrast Analyser.

---

#### HTMX Status Class Addition

**File**: `src/main/kotlin/routes/TaskRoutes.kt` — Multiple locations

**Before** (example from POST /tasks success):
```kotlin
val statusHtml =
    """
    <div id="status"
         hx-swap-oob="true"
         role="status"
         aria-live="polite">
        Task added successfully.
    </div>
    """.trimIndent()
```

**After**:
```kotlin
val statusHtml =
    """
    <div id="status"
         hx-swap-oob="true"
         role="status"
         aria-live="polite"
         class="success">
        Task added successfully.
    </div>
    """.trimIndent()
```

**Rationale**: Adding `class="success"` applies the enhanced green styling to HTMX responses.

---

## Fix 3: Auto-Focus Error Summary (wk9-03)

### File: `src/main/resources/templates/tasks/index.peb`

#### Error Summary with Auto-Focus — Lines 22-45

**Before**:
```twig
{% if error is not empty %}
<div role="alert"
     aria-live="assertive"
     class="error-summary"
     id="error-summary"
     tabindex="-1">
  <h2>There is a problem</h2>
  <ul>
    {% if error == "title" %}
    <li>
      <a href="#title">
        {% if msg == "too_long" %}
        Title is too long (maximum 200 characters)
        {% else %}
        Title is required. Please enter at least one character.
        {% endif %}
      </a>
    </li>
    {% endif %}
  </ul>
</div>
{% endif %}
```

**After**:
```twig
{% if error is not empty %}
<div role="alert"
     aria-live="assertive"
     class="error-summary"
     id="error-summary"
     tabindex="-1">
  <h2>There is a problem</h2>
  <ul>
    {% if error == "title" %}
    <li>
      <a href="#title">
        {% if msg == "too_long" %}
        Title is too long (maximum 200 characters)
        {% else %}
        Title is required. Please enter at least one character.
        {% endif %}
      </a>
    </li>
    {% endif %}
  </ul>
</div>
{# Week 10 Fix (wk9-03): Auto-focus error summary for keyboard/SR users (progressive enhancement) #}
<script>
  (function() {
    var errorSummary = document.getElementById('error-summary');
    if (errorSummary) {
      errorSummary.focus();
    }
  })();
</script>
{% endif %}
```

**Rationale**:
- `tabindex="-1"`: Makes div focusable programmatically (not in natural tab order)
- IIFE (Immediately Invoked Function Expression): Runs once on page load
- `?.focus()` or explicit check: Graceful handling if element doesn't exist
- Progressive enhancement: If script fails, error is still visible and tabindex allows Tab navigation

**WCAG**: 
- 2.4.3 Focus Order (A): Focus moves to error summary immediately
- 3.3.1 Error Identification (A): Error is brought to user's attention
- 4.1.3 Status Messages (AA): `role="alert"` ensures SR announcement

---

## Summary of Changes

| File | Lines Added | Lines Modified | Purpose |
|------|-------------|----------------|---------|
| `TaskRoutes.kt` | 6 | 6 | Add `?msg=` params and `class="success"` |
| `tasks/index.peb` | 18 | 0 | Success message display, auto-focus script |
| `custom.css` | 15 | 3 | Enhanced success styling |
| **Total** | **39** | **9** | |

---

## Git Commits

```bash
# Suggested commit message
git commit -m "feat(wk10-lab2): implement accessible feedback fixes (wk9-01, wk9-02, wk9-03)

- Add success message for No-JS add/edit/delete via query param (wk9-01)
- Enhance status message styling with green background and bold text (wk9-02)
- Auto-focus error summary on page load for keyboard/SR users (wk9-03)

WCAG compliance:
- 3.3.1 Error Identification (A): Error focused and linked
- 2.4.3 Focus Order (A): Focus managed after error
- 4.1.3 Status Messages (AA): Success/error announced
- 1.4.3 Contrast (AA): 7.2:1 for success message

Closes wk9-01, wk9-02, wk9-03"
```

---

_Last updated: 2025-11-22_

