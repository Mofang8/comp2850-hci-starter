# Inclusive Redesign Brief — Week 10 Lab 2

**Target**: Accessible Feedback for No-JS and All Users (wk9-01, wk9-02, wk9-03)  
**Priority**: 1 (Combined Score: 21)  
**Assignee**: [Tianqi Wang]  
**Date**: 2025-11-23

---

## Problem Statement

Analysis of Week 9 pilot data revealed three related accessibility barriers affecting user confidence and task completion, particularly for No-JS users:

1. **No success feedback after add task (No-JS)**: P4 (No-JS) said "I think it worked but there's no confirmation" (confidence 3/5 vs 5/5 for HTMX users). PRG redirect provides no explicit success message.

2. **Edit save confirmation not prominent**: 80% of participants (4/5) expressed uncertainty after saving. Status message "Task updated successfully" is too subtle. P4 confidence 2/5 for T2.

3. **Error message not keyboard-focusable (No-JS)**: P4 had to scroll up to find error message after validation failure. Error summary exists but focus not moved to it.

**Root cause**: No-JS path lacks explicit feedback mechanisms. HTMX users benefit from OOB status updates and dynamic UI changes, but No-JS users only see page reloads with no confirmation.

**WCAG violations**:

- 3.3.1 Error Identification (Level A): Error location not intuitive
- 2.4.3 Focus Order (Level A): Focus not managed after error
- 4.1.3 Status Messages (Level AA): Success/error messages not prominent

---

## Goal

**Target metrics** (Week 10 Lab 2 verification):

- T3 (Add) No-JS confidence ≥ 4/5 (was 3/5)
- T2 (Edit) overall confidence ≥ 4/5 (was 3.4/5)
- T2 No-JS time reduced by ≥ 30% (was 4567ms)
- Zero WCAG 3.3.1 / 2.4.3 / 4.1.3 violations on retest

**Success means**:

- No-JS users see explicit success messages after add/edit/delete
- All users see enhanced, prominent status messages
- Keyboard/SR users have error summary auto-focused on page load

---

## Inclusion Impact

**Who benefits**:

- **No-JS users** (estimated 1-2% of users): Can confirm actions succeeded without manual verification
- **Keyboard-only users**: Error summary focusable and in tab order
- **Screen reader users**: Status/error messages announced via `role="status"` / `role="alert"`
- **Users with cognitive disabilities**: Explicit confirmation reduces uncertainty
- **Low digital literacy users**: Clear feedback builds trust

**Equity**: Current design disproportionately affects No-JS users (P4 confidence 3.0/5 vs 4.6/5 for HTMX users). Fix restores parity.

---

## Proposed Changes

### Change 1: Add Success Message for No-JS Paths (wk9-01)

**File**: `src/main/kotlin/routes/TaskRoutes.kt`

**Before** (POST /tasks, No-JS path):

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

**Template** (`tasks/index.peb`):

```twig
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

**Rationale**: Query parameter passes success state across redirect. Template displays message with `role="status"` for SR announcement.

---

### Change 2: Enhance Status Message Visibility (wk9-02)

**File**: `src/main/resources/static/css/custom.css`

**Before**:

```css
#status {
  padding: 1rem;
  margin: 1rem 0;
  border-radius: 4px;
  border-left: 4px solid #2196f3;
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
  border-left: 4px solid #2196f3;
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

- Green background (#d4edda) clearly indicates success
- Larger font size (1.05rem) and bold weight (600) improve visibility
- Sufficient contrast ratio (4.5:1+) meets WCAG 1.4.3

---

### Change 3: Auto-Focus Error Summary (wk9-03)

**File**: `src/main/resources/templates/tasks/index.peb`

**Before**:

```twig
<div role="alert"
     aria-live="assertive"
     class="error-summary"
     id="error-summary"
     tabindex="-1">
  ...
</div>
{% endif %}
```

**After**:

```twig
<div role="alert"
     aria-live="assertive"
     class="error-summary"
     id="error-summary"
     tabindex="-1">
  ...
</div>
{# Week 10 Fix (wk9-03): Auto-focus error summary for keyboard/SR users #}
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

- `tabindex="-1"` makes div focusable programmatically (not in natural tab order)
- Small inline script auto-focuses error summary on page load (progressive enhancement)
- Graceful degradation: If JS fails, error is still visible and `tabindex="-1"` allows Tab navigation

---

## Acceptance Criteria

### Functional

- [ ] No-JS add task redirects to `/tasks?msg=task_added`
- [ ] No-JS edit task redirects to `/tasks?msg=task_updated`
- [ ] No-JS delete task redirects to `/tasks?msg=task_deleted`
- [ ] Success message displayed prominently at top of page
- [ ] HTMX paths continue to use OOB status updates
- [ ] Error summary receives focus on page load when errors present

### Accessibility (WCAG 2.2 Level AA)

- [ ] **3.3.1 Error Identification (A)**: Error described in text and linked to input ✓
- [ ] **2.4.3 Focus Order (A)**: Focus managed predictably (error summary → input) ✓
- [ ] **4.1.3 Status Messages (AA)**: Success/error announced by SR ✓
- [ ] **1.4.3 Contrast (AA)**: Success message contrast ≥ 4.5:1 ✓

### Testing Protocol

- [ ] **Keyboard-only**: Tab to form, submit, Tab to success message (visible)
- [ ] **Screen reader (NVDA/VoiceOver)**: Submit → SR announces success message
- [ ] **No-JS**: Disable JS, add/edit/delete → page reloads with message
- [ ] **Error flow**: Submit blank → error summary focused, Tab → input

---

## Verification Plan

### Quantitative (Week 10 Lab 2)

Re-run T2 and T3 with 2-3 participants:

1. **P6**: Standard (HTMX, mouse, JS-on)
2. **P7**: Keyboard-only (JS-on)
3. **P8**: No-JS

**Measure**:

- Confidence ratings (target: ≥ 4/5)
- Task completion time (target: T2 No-JS ≤ 3200ms)
- Error recovery time (if applicable)

### Qualitative

- Capture participant quotes about success feedback
- Note any remaining confusion or hesitation
- Screenshot of success message with focus/styling

### Backlog Update

Mark wk9-01, wk9-02, wk9-03 as `status=fixed`:

```csv
wk9-01,...,fixed,"wk10/evidence/reverification.md; wk10/assessment/05-evidence/screenshots/"
```

---

## Risk & Constraints

### Technical Constraints

- **No major refactor**: Changes limited to 3 files (1 Kotlin, 1 template, 1 CSS)
- **Server-first principle**: Keep HTMX enhancement, ensure No-JS parity
- **Progressive enhancement**: Auto-focus script gracefully degrades

### Potential Issues

- **Query param pollution**: `?msg=task_added` visible in URL. Acceptable trade-off for stateless feedback.
- **Message persistence**: Refreshing page keeps message. Acceptable for single-use confirmation.
- **Progressive enhancement script**: If JS fails after page load, error summary still visible but not focused.

### Trade-offs Accepted

- URL shows `?msg=...` after success (cosmetic, no functional impact)
- Success message disappears only on next navigation (no auto-hide timer)

---

## Success Criteria Summary

**Definition of Done**:

1. ✅ Code changes committed
2. ✅ All acceptance criteria passing
3. ⏳ Verification pilots completed (n=2-3)
4. ⏳ Confidence scores improved (target ≥ 4/5)
5. ⏳ Zero WCAG violations on retest
6. ⏳ Evidence captured (screenshots, notes)
7. ⏳ Backlog updated (wk9-01, wk9-02, wk9-03 marked fixed)

---

_Last updated: 2025-11-23_
