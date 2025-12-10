# Redesign Priorities — Week 10 Lab 2

**Module**: COMP2850 Human-Computer Interaction  
**Student**: [Tianqi Wang]  
**Date**: 2025-11-23

---

## Scoring Framework

**Formula**: `Priority = (Impact + Inclusion) – Effort`

### Dimensions (1–5 scale)

| Dimension | 5 | 3 | 1 |
|-----------|---|---|---|
| **Impact** | Blocks task completion for most participants | Slows down or frustrates some participants | Minor annoyance, rare |
| **Inclusion** | SR/keyboard/cognitive disability users can't complete | Affects some disabled participants (e.g., low vision) | Affects everyone equally |
| **Effort** | Major refactor, >8 hours | Moderate, 2–4 hours | Quick fix, <1 hour |

### Score Interpretation

- **8–10**: Critical — fix immediately (Week 10 Lab 2)
- **5–7**: High priority — fix if time permits
- **<5**: Defer or document as known issue

---

## Priority 1: No Success Feedback in No-JS Add Task (MUST FIX)

**Backlog ID**: wk9-01  
**Priority Score**: (5 Impact + 5 Inclusion) – 2 Effort = **8**

### Issue

In No-JS mode, adding a task via POST-Redirect-Get (PRG) provides no explicit success message. The user is redirected to `/tasks` with no indication that their action succeeded. P4 had to scroll through the task list to verify the task was added.

### Evidence

| Source | Finding |
|--------|---------|
| `wk09/data/pilot-notes.md` L331-334 | P4: "I think it worked but there's no confirmation" |
| `data/metrics.csv` P4_f5a2, T3_add | Time: 2134ms (3.15× slower than JS-on median 678ms) |
| `wk09/analysis/findings.md` Theme 1 | Confidence 3/5 for T3 (vs 5/5 for HTMX users) |
| `wk09/analysis/evidence-chains.md` Chain 1 | Full evidence chain documented |

### WCAG Reference

- **3.3.1 Error Identification (Level A)**: The principle of providing clear feedback applies equally to success states. Users should not have to guess whether their action succeeded.

### Proposed Fix

**Server-side change** (`TaskRoutes.kt`):
```kotlin
// No-JS success path - add query param
call.response.headers.append("Location", "/tasks?msg=task_added")
call.respond(HttpStatusCode.SeeOther)
```

**Template change** (`tasks/index.peb`):
```twig
{% if msg == "task_added" %}
<div role="status" aria-live="polite" class="success-message">
  Task added successfully.
</div>
{% endif %}
```

### Effort Estimate

**1-2 hours**:
- Modify redirect URL in `TaskRoutes.kt` (15 min)
- Add template conditional in `index.peb` (15 min)
- Add CSS styling for success message (15 min)
- Test both HTMX and No-JS paths (30 min)
- Verification pilot with No-JS user (30 min)

### Acceptance Criteria

- [ ] No-JS add task redirects to `/tasks?msg=task_added`
- [ ] Success message displayed prominently at top of page
- [ ] Message has `role="status"` for SR announcement
- [ ] Message disappears on subsequent navigation (stateless)
- [ ] HTMX path unaffected (continues using OOB status)

---

## Priority 2: No-JS Error Message Not Keyboard-Focusable (MUST FIX)

**Backlog ID**: wk9-03  
**Priority Score**: (4 Impact + 5 Inclusion) – 2 Effort = **7**

### Issue

When validation errors occur in No-JS edit mode, the error message appears at the top of the page, but focus is not moved to it. P4 had to scroll up to find the error, significantly slowing down task completion.

### Evidence

| Source | Finding |
|--------|---------|
| `wk09/data/pilot-notes.md` L311-313 | P4: "[ERR] Error message not focusable, had to scroll up to find it" |
| `data/metrics.csv` P4_f5a2, T2_edit | Time: 4567ms (98% slower than JS-on median 2301ms) |
| `wk09/analysis/findings.md` | Error rate 50% for JS-off (1/2 attempts) |
| `wk09/analysis/evidence-chains.md` Chain 3 | WCAG 3.3.1, 2.4.3 violations documented |

### WCAG Reference

- **3.3.1 Error Identification (Level A)**: Errors must be identified and described to the user in text.
- **2.4.3 Focus Order (Level A)**: Focus should move in a sequence that preserves meaning and operability.
- **4.1.3 Status Messages (Level AA)**: Status messages must be programmatically determinable.

### Proposed Fix

**Template change** (`tasks/index.peb` - error summary already exists for add task, extend to edit):
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
    <li><a href="#title">Title is required.</a></li>
    {% endif %}
  </ul>
</div>
<script>
  // Progressive enhancement: auto-focus error summary
  (function() {
    var errorSummary = document.getElementById('error-summary');
    if (errorSummary) {
      errorSummary.focus();
    }
  })();
</script>
{% endif %}
```

**Note**: The error summary pattern already exists in `index.peb` for the add task flow. This fix ensures consistency and adds auto-focus for better accessibility.

### Effort Estimate

**1-2 hours**:
- Verify existing error summary covers all cases (15 min)
- Ensure `tabindex="-1"` and `role="alert"` are present (15 min)
- Add auto-focus script for error summary (15 min)
- Extend error handling to edit task flow if needed (30 min)
- Test with keyboard-only navigation (30 min)
- Verification pilot with No-JS user (30 min)

### Acceptance Criteria

- [ ] Error summary has `tabindex="-1"` for programmatic focus
- [ ] Error summary has `role="alert"` for immediate SR announcement
- [ ] Error summary receives focus on page load when errors present
- [ ] Error summary link navigates to the invalid input (`href="#title"`)
- [ ] Tab order from error summary leads to the problem field

---

## Priority 3: Edit Save Confirmation Not Prominent Enough (SHOULD FIX)

**Backlog ID**: wk9-02  
**Priority Score**: (4 Impact + 4 Inclusion) – 2 Effort = **6**

### Issue

80% of participants (4/5) expressed uncertainty about whether their edit was saved successfully. The current status message "Task updated successfully" is too subtle and positioned away from the user's focus.

### Evidence

| Source | Finding |
|--------|---------|
| `wk09/data/pilot-notes.md` L47-48 | P1: "I think it worked?" (paused 2 seconds) |
| `wk09/data/pilot-notes.md` L136 | P2: "Hesitated before leaving, unsure if change was saved" |
| `wk09/data/pilot-notes.md` L222 | P3: "I wasn't sure if Enter would save or cancel" |
| `wk09/data/pilot-notes.md` L314-316 | P4: "I'm not sure if it saved... let me scroll down and check" |
| `wk09/analysis/findings.md` Theme 2 | Only P5 noticed the status message |

### WCAG Reference

- **4.1.3 Status Messages (Level AA)**: Status messages should be presented to the user without requiring focus change. However, perceptual prominence is also important for usability.

### Proposed Fix

**CSS change** (`custom.css`):
```css
#status[role="status"] {
  padding: 12px 16px;
  margin: 16px 0;
  border-radius: 4px;
  font-weight: 500;
}

#status.success {
  background-color: #d4edda;
  border: 1px solid #28a745;
  color: #155724;
}

#status.error {
  background-color: #f8d7da;
  border: 1px solid #dc3545;
  color: #721c24;
}
```

**Template consideration**:
- Add `class="success"` to success status messages
- Consider adding a success icon for additional visual cue

### Effort Estimate

**1 hour**:
- Add CSS styling for status messages (20 min)
- Update template to include appropriate classes (15 min)
- Test visual appearance in both modes (15 min)
- Verify SR announcement still works (10 min)

### Acceptance Criteria

- [ ] Success message has visible background color (green)
- [ ] Success message has adequate contrast (WCAG 1.4.3)
- [ ] Message remains announced by SR via `role="status"`
- [ ] No regression in HTMX OOB swap behavior
- [ ] No-JS path also shows styled success message

---

## Deferred Items (Post-Assessment or Semester 2)

### wk9-04: Filter Input Discoverability

**Priority Score**: (3 + 2) – 1 = **4**

**Reason for deferral**: Only 1/5 participants affected. Current implementation works for 80% of users. Low effort but also low impact.

**Future fix**: Add placeholder text `"Type to filter tasks..."` to the filter input.

---

### wk9-05: Focus Management After Delete

**Priority Score**: (2 + 3) – 3 = **2**

**Reason for deferral**: Low impact (task still completed successfully), moderate effort (requires focus management logic), and affects only keyboard-only users in edge case.

**Future fix**: Announce deletion and new focus location via `aria-live` region.

---

### wk8-01: No-JS Delete Has No Confirmation

**Priority Score**: N/A (documented trade-off)

**Reason for acceptance**: This is a known trade-off from Week 8 prototyping constraints. Implementing confirmation for No-JS would require a separate `/tasks/{id}/delete/confirm` page, adding significant complexity. The current behavior (immediate delete) is acceptable because:
1. Outcome is immediately visible (task disappears)
2. Confidence remained 4/5 for P4
3. Recovery is possible by re-adding the task

**Future fix**: Consider soft-delete with undo window for all users.

---

## Implementation Order (Week 10 Lab 2)

| Order | Issue | Effort | Cumulative Time |
|-------|-------|--------|-----------------|
| 1 | wk9-01: No-JS success feedback | 1-2 hours | 1-2 hours |
| 2 | wk9-03: Error message focusable | 1-2 hours | 2-4 hours |
| 3 | wk9-02: Save confirmation styling | 1 hour | 3-5 hours |

**Total estimated effort**: 3-5 hours (fits within Week 10 Lab 2 session + homework)

---

## Verification Plan

After implementing fixes, re-verify with:

1. **Automated testing**: Run axe DevTools scan on `/tasks`
2. **Keyboard-only test**: Complete T2 and T3 using only Tab/Enter
3. **No-JS test**: Disable JavaScript and complete all tasks
4. **Quick pilot** (n=1-2): Re-run T2 and T3 with a peer, measure confidence

**Success metrics**:
- T3 (No-JS) confidence ≥ 4/5 (was 3/5)
- T2 (No-JS) time reduced by ≥30% (was 4567ms)
- T2 overall confidence ≥ 4/5 (was 3.4/5)
- Zero WCAG 3.3.1, 2.4.3, 4.1.3 violations on retest

---

_Last updated: 2025-11-23_

