# Pilot Findings Analysis — Week 9

**Study Period**: 2025-11-17 to 2025-11-22  
**Module**: COMP2850 Human-Computer Interaction

---

## Overview

- **Participants**: 5 total
  - 3 × HTMX (standard mouse + keyboard)
  - 1 × Keyboard-only (JS enabled)
  - 1 × No-JS (JavaScript disabled)
- **Date range**: 2025-11-17 to 2025-11-22
- **Data sources**:
  - Automated logs: `data/metrics.csv`
  - Qualitative notes: `wk09/data/pilot-notes.md`

---

## Quantitative Summary

### Task T1: Filter Tasks (`T1_filter`)

| Metric          | HTMX (n=3) | KB-only (n=1) | No-JS (n=1) | Overall (n=5) |
| --------------- | ---------- | ------------- | ----------- | ------------- |
| Median time (s) | 42         | 67            | 88          | 67            |
| Mean time (s)   | 50         | 67            | 88          | 61            |
| Success rate    | 100%       | 100%          | 100%        | 100%          |
| Mean errors     | 0          | 0             | 0           | 0             |
| Mean confidence | 4.0/5      | 4.0/5         | 3.0/5       | 3.8/5         |

**Interpretation**  
All participants successfully completed the filter task. HTMX users were fastest (median 42s), while the No-JS participant took longest (88s) due to full page reload. The No-JS participant's lower confidence (3/5) reflects uncertainty about whether the filter actually worked, despite success. One HTMX participant (P2) initially couldn't find the filter input, suggesting the form could be more prominent.

---

### Task T2: Edit Task Title (`T2_edit`)

| Metric          | HTMX (n=3) | KB-only (n=1) | No-JS (n=1) | Overall (n=5) |
| --------------- | ---------- | ------------- | ----------- | ------------- |
| Median time (s) | 47         | 108           | 145         | 87            |
| Mean time (s)   | 57         | 108           | 145         | 85            |
| Success rate    | 100%       | 100%          | 100%        | 100%          |
| Error rate      | 33% (1/3)  | 0%            | 100% (1/1)  | 40% (2/5)     |
| Mean confidence | 4.0/5      | 3.0/5         | 2.0/5       | 3.4/5         |

**Interpretation**  
Task T2 (Edit) had the highest error rate (40%) and lowest confidence scores. Two participants accidentally submitted blank titles, triggering validation errors. The No-JS participant reported the lowest confidence (2/5) despite successful completion—they were unsure whether the edit had saved and had to manually verify. The keyboard-only participant noted uncertainty about whether Enter would save or cancel. These findings suggest the edit flow needs clearer feedback mechanisms.

---

### Task T3: Add New Task (`T3_add`)

| Metric          | HTMX (n=3) | KB-only (n=1) | No-JS (n=1) | Overall (n=5) |
| --------------- | ---------- | ------------- | ----------- | ------------- |
| Median time (s) | 18         | 35            | 72          | 25            |
| Mean time (s)   | 19         | 35            | 72          | 33            |
| Success rate    | 100%       | 100%          | 100%        | 100%          |
| Mean errors     | 0          | 0             | 0           | 0             |
| Mean confidence | 5.0/5      | 5.0/5         | 3.0/5       | 4.6/5         |

**Interpretation**  
Adding tasks was the fastest and most confidently completed task for HTMX users (median 18s, confidence 5/5). However, the No-JS participant took 4× longer (72s) and had significantly lower confidence (3/5). They explicitly noted the lack of success feedback after the PRG redirect: "I think it worked but there's no confirmation." This is a critical inclusion issue for users without JavaScript.

---

### Task T4: Delete Task (`T4_delete`)

| Metric          | HTMX (n=3) | KB-only (n=1) | No-JS (n=1) | Overall (n=5) |
| --------------- | ---------- | ------------- | ----------- | ------------- |
| Median time (s) | 15         | 55            | 55          | 22            |
| Mean time (s)   | 16         | 55            | 55          | 32            |
| Success rate    | 100%       | 100%          | 100%        | 100%          |
| Mean errors     | 0          | 0             | 0           | 0             |
| Mean confidence | 5.0/5      | 4.0/5         | 4.0/5       | 4.6/5         |

**Interpretation**  
Delete was fast and successful across all modes. HTMX users benefited from the confirmation dialog and instant removal. The No-JS participant noted surprise at the lack of confirmation ("Whoa, that just deleted it without asking!")—this aligns with the documented trade-off from wk8-01. Despite this, confidence remained acceptable (4/5) because the outcome was immediately visible.

---

## Qualitative Themes

### Theme 1: Success Feedback Insufficient in No-JS Mode

**Evidence**

- P4 (No-JS): "I think it worked but there's no confirmation" (after adding task)
- P4 (No-JS): "I'm not sure if it saved... let me scroll down and check" (after editing)
- P4 (No-JS): Confidence rating 3/5 for T3, 2/5 for T2

**Design Implication**  
The No-JS path relies on PRG (Post-Redirect-Get) which provides no explicit success message. Users must manually verify their actions succeeded by looking at the task list. This creates uncertainty and reduces confidence, particularly for users who depend on explicit feedback (e.g., users with cognitive disabilities, low digital literacy). **Recommendation**: Add a flash message or visible success banner after successful form submissions in No-JS mode.

---

### Theme 2: Edit Flow Causes Uncertainty About Save State

**Evidence**

- P1 (HTMX): "I think it worked?" (pause after saving)
- P2 (HTMX): Hesitated before leaving edit mode, "wasn't sure if change was saved"
- P3 (KB-only): "I wasn't sure if Enter would save or cancel"
- P4 (No-JS): Had to scroll down to verify title change

**Design Implication**  
Even when the save succeeds, participants lack confidence that their edit was persisted. The current status message "Task updated successfully" may be too subtle or positioned away from the user's focus. **Recommendation**: Make the success message more prominent (larger text, colour highlight, or animation) and consider keeping focus near the edited task with the updated title visible.

---

### Theme 3: Filter Input Not Immediately Discoverable

**Evidence**

- P2 (HTMX): Scrolled down first looking for a "Search" button, took 30 seconds to notice filter input
- P2 (HTMX): "The filter box wasn't obvious to me at first. Maybe add a label or placeholder?"

**Design Implication**  
One participant expected a button-based search rather than the current type-to-filter interaction. While other participants found it quickly, this suggests the affordance could be strengthened. **Recommendation**: Add a visible label or placeholder text like "Type to filter tasks..." to improve discoverability.

---

### Theme 4: Keyboard Navigation Functional but Verbose

**Evidence**

- P3 (KB-only): "Editing took a while because I had to Tab through every task to find the right one"
- P3 (KB-only): Task completion times were 60-100% longer than HTMX users
- P3 (KB-only): All tasks completable with keyboard ✅

**Design Implication**  
Keyboard navigation works correctly (no traps, visible focus), but efficiency suffers when the task list is long. Users must Tab through multiple elements per task (checkbox, title, Edit, Delete). **Recommendation**: Consider adding skip links, landmark roles, or search-then-edit patterns to reduce keystrokes for keyboard users.

---

### Theme 5: Delete Without Confirmation is Concerning (No-JS)

**Evidence**

- P4 (No-JS): "Whoa, that just deleted it without asking!"
- Pre-existing backlog item wk8-01: "No-JS delete has no confirmation"

**Design Implication**  
This is a known trade-off documented in Week 8. The lack of `hx-confirm` in No-JS mode means destructive actions happen immediately. While P4's confidence remained acceptable (4/5), the surprise reaction indicates this could cause real problems with accidental deletions. **Recommendation**: Consider implementing a two-step delete flow or soft-delete with undo for No-JS users.

---

## Accessibility Observations

### Keyboard Navigation

- **All tasks completable with keyboard only** ✅
- **Focus indicators visible throughout** ✅
- **No keyboard traps detected** ✅
- **Tab order logical**: form fields → task list (checkbox → title → Edit → Delete per item)
- **Area for improvement**: After delete, focus jumps to next task without announcement. P3 noted: "After deleting, I wasn't sure where focus went."

### Screen Reader

- Screen reader testing was not conducted in this pilot round due to participant availability.
- **Recommendation for Week 10**: Conduct at least one session with NVDA/VoiceOver to verify:
  - Status messages announced via `role="status"` / `aria-live`
  - Error messages linked to inputs via `aria-describedby`
  - Filter result count announced

### No-JS Parity

- **All tasks functional without JavaScript** ✅
- **Performance**: Times 2-4× slower than HTMX due to full page reloads (expected)
- **Issues identified**:
  1. **No success feedback after adding task** (PRG redirect shows nothing)
  2. **Error message not keyboard-focusable** in edit flow
  3. **No delete confirmation** (known trade-off from wk8-01)
- **Acceptable trade-offs**: Slower performance is expected and documented. Delete without confirmation is documented but should be revisited.

---

## Prioritised Issues

Based on frequency, severity, and inclusion impact:

### 1. **HIGH**: No success feedback in No-JS add task flow

- **Evidence**: P4 T3 confidence = 3/5, quote: "I think it worked but there's no confirmation"
- **Frequency**: Affects all No-JS users (100% of No-JS sessions)
- **Impact**: Users cannot confirm task was added without manually scanning the list
- **WCAG**: Related to 3.3.1 Error Identification (success feedback equally important)
- **Backlog**: → wk9-01

### 2. **HIGH**: Edit save confirmation not prominent enough

- **Evidence**: P1, P2, P3, P4 all expressed uncertainty after saving; P4 confidence = 2/5
- **Frequency**: 80% of participants (4/5) mentioned uncertainty
- **Impact**: Reduces user confidence, may cause redundant save attempts
- **Backlog**: → wk9-02

### 3. **MEDIUM**: Error message in No-JS edit not focusable

- **Evidence**: P4 had to scroll up to find error message after validation failure
- **Frequency**: Affects all No-JS users who trigger validation errors
- **Impact**: Error recovery is harder for keyboard/screen reader users
- **WCAG**: 3.3.1 Error Identification, 2.4.3 Focus Order
- **Backlog**: → wk9-03

### 4. **MEDIUM**: Filter input discoverability

- **Evidence**: P2 took 30 seconds to find filter, suggested adding label/placeholder
- **Frequency**: 1/5 participants affected
- **Impact**: Delays task completion, reduces efficiency
- **Backlog**: → wk9-04

### 5. **LOW**: Focus management after delete

- **Evidence**: P3 (KB-only) noted focus jumped without announcement
- **Frequency**: Affects keyboard-only users
- **Impact**: Minor disorientation, but task completed successfully
- **WCAG**: 2.4.3 Focus Order
- **Backlog**: → wk9-05

---

## Recommendations for Week 10

Based on the prioritised issues above:

1. **Implement flash message for No-JS success feedback** (wk9-01) — HIGH priority
2. **Enhance edit save confirmation visibility** (wk9-02) — HIGH priority
3. **Make No-JS error messages focusable** (wk9-03) — MEDIUM priority

These three fixes address the most impactful issues and are feasible within the Week 10 timeframe.
