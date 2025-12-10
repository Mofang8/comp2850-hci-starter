# Qualitative Themes — Week 10

**Study**: Peer pilots (n=5)  
**Period**: 2025-11-17 to 2025-11-22  
**Data Source**: `wk09/data/pilot-notes.md`  
**Analysis Date**: 2025-11-22

---

## Overview

This document presents thematic analysis of qualitative observations from Week 9 peer pilots. Themes were identified by reviewing participant quotes, behavioral observations, and debrief comments, then grouped by recurring patterns.

---

## Theme 1: Success Feedback Insufficient in No-JS Mode

**Frequency**: 1/1 No-JS participants (100% of No-JS sessions)  
**Severity**: **HIGH**  
**Related Tasks**: T2 (Edit), T3 (Add)

### Evidence

**P4 (No-JS), Task T3 (Add Task)**:

> "I think it worked but there's no confirmation"  
> — `wk09/data/pilot-notes.md`, lines 331-334

**P4 (No-JS), Task T2 (Edit Task)**:

> "I'm not sure if it saved... let me scroll down and check"  
> — `wk09/data/pilot-notes.md`, lines 314-316

**Behavioral observation**:

- P4 had to scroll through task list to verify actions succeeded
- Confidence ratings: T2 = 2/5, T3 = 3/5 (lowest of all participants)

### Design Implication

The No-JS path relies on POST-Redirect-Get (PRG) which provides no explicit success message. Users must manually verify their actions by scanning the task list. This creates uncertainty and reduces confidence.

**Impact on inclusion**: Particularly problematic for:

- Users with cognitive disabilities who benefit from explicit confirmation
- Users with low digital literacy who may not trust implicit feedback
- Users in low-bandwidth environments where page loads may be slow

### Recommendation

Add flash message or success banner to No-JS add/edit flows:

```
GET /tasks?msg=task_added → display "Task added successfully"
GET /tasks?msg=task_updated → display "Task updated successfully"
```

**Backlog reference**: `wk9-01`

---

## Theme 2: Edit Flow Causes Uncertainty About Save State

**Frequency**: 4/5 participants (80%)  
**Severity**: **HIGH**  
**Related Tasks**: T2 (Edit)

### Evidence

**P1 (HTMX)**, after saving:

> "I think it worked?"  
> — `wk09/data/pilot-notes.md`, lines 47-48

**P2 (HTMX)**, after saving:

> "Hesitated before leaving, unsure if change was saved"  
> — `wk09/data/pilot-notes.md`, line 136

**P3 (Keyboard-only)**, during edit:

> "I wasn't sure if Enter would save or cancel"  
> — `wk09/data/pilot-notes.md`, line 222

**P4 (No-JS)**, after saving:

> "I'm not sure if it saved... let me scroll down and check"  
> — Found task with new title, confirmed success after manual verification  
> — `wk09/data/pilot-notes.md`, lines 314-317

**Only P5** expressed confidence after saving:

> "Done, I can see the message"  
> — P5 was the only participant who noticed the status message

### Design Implication

Even when the save succeeds, most participants lack confidence that their edit was persisted. The current status message has issues:

1. **Too subtle**: Small text, not visually prominent
2. **Positioned away from focus**: User's attention is on the edited task, not the status region
3. **Easily missed**: Only 1/5 participants (P5) explicitly mentioned seeing it

### Recommendation

Enhance success message visibility:

- Larger text or visual highlight (background color, icon)
- Consider inline confirmation near the edited task
- Ensure `role="status"` and `aria-live="polite"` for SR announcement

**Backlog reference**: `wk9-02`

---

## Theme 3: Error Message Not Keyboard-Focusable (No-JS)

**Frequency**: 1/1 No-JS participants who triggered validation error  
**Severity**: **MEDIUM-HIGH**  
**Related Tasks**: T2 (Edit)

### Evidence

**P4 (No-JS)**, after validation error:

> "Error message visible at top: 'Title is required'"  
> "[ERR] Error message not focusable, had to scroll up to find it"  
> — `wk09/data/pilot-notes.md`, lines 311-313

**Behavioral observation**:

- P4 accidentally submitted blank title
- Page reloaded with error message at top
- Focus remained at form, not at error message
- P4 had to scroll up to discover the error
- Added significant time to task (145s vs 47-87s for HTMX users)

### Design Implication

When validation errors occur in No-JS edit mode, the error message appears but:

1. Focus is not moved to the error summary
2. Keyboard users must Tab/scroll to find it
3. Screen reader users may not hear the error announcement

This violates WCAG accessibility requirements for error handling.

### Recommendation

1. Add `tabindex="-1"` to error summary div
2. Auto-focus error summary on page load (small JS script for progressive enhancement)
3. Ensure `role="alert"` and `aria-live="assertive"` for immediate SR announcement

**WCAG references**:

- 3.3.1 Error Identification (Level A)
- 2.4.3 Focus Order (Level A)
- 4.1.3 Status Messages (Level AA)

**Backlog reference**: `wk9-03`

---

## Theme 4: Filter Input Not Immediately Discoverable

**Frequency**: 1/5 participants (20%)  
**Severity**: **MEDIUM**  
**Related Tasks**: T1 (Filter)

### Evidence

**P2 (HTMX)**, during filter task:

> "Scrolled down first looking for a 'Search' button"  
> "Took 30 seconds to notice the filter input at top"  
> — `wk09/data/pilot-notes.md`, lines 115-117

**P2 (Debrief)**:

> "The filter box wasn't obvious to me at first. Maybe add a label or placeholder?"  
> — `wk09/data/pilot-notes.md`, line 174

**Quantitative impact**:

- P2 filter time: 75s (vs median 42s for other HTMX users)
- Additional 33 seconds spent discovering the input

### Design Implication

While 4/5 participants found the filter input quickly, one participant expected a button-based search interaction. The current design relies on type-to-filter behavior which may not be immediately obvious.

### Recommendation

Add visible placeholder text: `"Type to filter tasks..."` to improve discoverability.

**WCAG reference**: 2.4.6 Headings and Labels (Level AA)

**Backlog reference**: `wk9-04` (candidate_fix = false; lower priority)

---

## Theme 5: Focus Management After Delete Could Be Improved

**Frequency**: 1/5 participants (keyboard-only user)  
**Severity**: **LOW**  
**Related Tasks**: T4 (Delete)

### Evidence

**P3 (Keyboard-only)**, after deleting:

> "[FOC] Focus moved to next task in list—acceptable but slightly disorienting"  
> — `wk09/data/pilot-notes.md`, line 254

**P3 (Debrief)**:

> "After deleting, I wasn't sure where focus went—maybe announce what happened?"  
> — `wk09/data/pilot-notes.md`, line 262

### Design Implication

After deleting a task via keyboard, focus moves to the next task item. While technically correct (focus doesn't get lost), it can be disorienting because:

1. No announcement of the deletion
2. User may not immediately realize which task now has focus
3. The focus shift happens silently

### Recommendation

Consider announcing deletion and new focus location via `aria-live` region:

> "Deleted 'task name'. Focus moved to 'next task name'."

**WCAG reference**: 2.4.3 Focus Order (Level A), 4.1.3 Status Messages (Level AA)

**Backlog reference**: `wk9-05` (candidate_fix = false; lower priority)

---

## Theme Summary

| Theme                             | Frequency         | Severity    | Backlog | Priority     |
| --------------------------------- | ----------------- | ----------- | ------- | ------------ |
| 1. No success feedback (No-JS)    | 100% No-JS        | HIGH        | wk9-01  | **MUST FIX** |
| 2. Edit save confirmation unclear | 80% all           | HIGH        | wk9-02  | **MUST FIX** |
| 3. Error not keyboard-focusable   | 100% No-JS errors | MEDIUM-HIGH | wk9-03  | **MUST FIX** |
| 4. Filter input discoverability   | 20% all           | MEDIUM      | wk9-04  | SHOULD FIX   |
| 5. Focus after delete             | 100% KB-only      | LOW         | wk9-05  | NICE TO HAVE |

---

## Accessibility-Specific Observations

### Keyboard Navigation (P3)

- **All tasks completable with keyboard only** ✅
- **Focus indicators visible throughout** ✅
- **No keyboard traps detected** ✅
- **Tab order logical**: form fields → task list (checkbox → title → Edit → Delete per item)
- **Area for improvement**: Focus management after delete (Theme 5)

### No-JS Parity (P4)

- **All tasks functional without JavaScript** ✅
- **Performance**: Times 2-4× slower than HTMX (expected trade-off)
- **Issues identified**:
  1. No success feedback (Theme 1) — **Priority fix**
  2. Error message not focusable (Theme 3) — **Priority fix**
  3. No delete confirmation (known trade-off from wk8-01)

### Screen Reader Testing

- **Not conducted in Week 9** due to participant availability
- **Recommendation for Week 10 Lab 2**: Conduct at least one SR session to verify:
  - Status messages announced via `role="status"` / `aria-live`
  - Error messages linked to inputs via `aria-describedby`
  - Filter result count announced

---

_Last updated: 2025-11-22_
