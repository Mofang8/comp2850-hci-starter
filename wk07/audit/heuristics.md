# Heuristic Evaluation — Week 7

**Evaluator**: [Your Name]  
**Date**: 2025-11-30  
**Method**: Nielsen’s 10 Usability Heuristics + Shneiderman’s Golden Rules

---

## 1. Visibility of System Status

**Rating**: 4 / 5 (Good)

- ✅ Status messages announce add/edit/delete actions via live region (`#status`, `role="status"`, `aria-live="polite"`).
- ✅ Inline edit “Save” updates the status text (e.g. “Task 'X' updated successfully.”).
- ⚠ No explicit loading indicator for slower HTMX requests (currently fast on localhost, but may be slow on poor networks).

**Accessibility implication**:  
Screen reader users get confirmation via live region (supports WCAG 4.1.3 Status Messages).

**Issue identified**: None (meets WCAG); optional enhancement: add `hx-indicator` for long‑running requests.

---

## 2. Match Between System and Real World

**Rating**: 5 / 5 (Excellent)

- ✅ Plain language labels: “Add Task”, “Edit”, “Delete” (no jargon).
- ✅ Error messages use clear, specific wording: “Title is required. Please enter at least one character.”
- ✅ Layout follows familiar task‑list metaphor (heading, add form, list of items).

**Accessibility implication**:  
Simple language benefits people with cognitive disabilities and low digital literacy.

**Issue identified**: None.

---

## 3. User Control and Freedom

**Rating**: 3 / 5 (Fair)

- ✅ Inline edit provides a “Cancel” action to discard changes and return to view mode.
- ❌ Delete is immediate and irreversible (no confirmation, no undo).

**Accessibility implication**:  
People with motor impairments or tremor may accidentally trigger “Delete” and have no easy way to recover.

**Issues identified**:

1. **Delete has no confirmation dialog**

   - Severity: **Medium**
   - Inclusion risk: **Motor, Cognitive**
   - Backlog candidate: add confirmation step or “undo” pattern.

2. **No undo for delete**
   - Severity: **Low/Medium** (depends on task importance)
   - Inclusion risk: **Motor, Cognitive**
   - Backlog candidate: out of scope for Week 7, but important for future redesign.

---

## 4. Consistency and Standards

**Rating**: 5 / 5 (Excellent)

- ✅ Semantic HTML elements (`<button>`, `<form>`, `<ul>`, `<section>`).
- ✅ ARIA patterns used as per examples (`role="status"`, `role="alert"`, `aria-describedby`).
- ✅ Layout consistent across view and edit modes (task appears in same position).

**Accessibility implication**:  
Familiar patterns reduce learning effort for assistive technology users.

**Issue identified**: None.

---

## 5. Error Prevention

**Rating**: 3 / 5 (Fair)

- ✅ Server‑side validation prevents saving blank titles.
- ⚠ No prevention for accidental deletion (no confirmation or soft delete).

**Accessibility implication**:  
Error prevention is especially important for users with cognitive impairments or limited fine motor control.

**Issue identified**:

- **Delete action too easy to trigger accidentally**
  - Severity: **Medium**
  - Inclusion risk: **Motor, Cognitive**
  - Backlog candidate: add confirmation or “are you sure?” step for delete.

---

## 6. Recognition Rather Than Recall

**Rating**: 4 / 5 (Good)

- ✅ Labels are always visible (not only as placeholders).
- ✅ Hint text (“Keep it short and specific.”) stays visible under the input.
- ✅ Inline edit shows the current title value in the input, reducing memory load.

**Accessibility implication**:  
Supports users with memory difficulties or attention issues (e.g., ADHD, dyslexia).

**Issue identified**: None (meets WCAG and good practice).

---

## 7. Flexibility and Efficiency of Use

**Rating**: 3 / 5 (Fair)

- ✅ Keyboard shortcuts inherent to HTML (Enter submits button, Esc cancels in some browsers).
- ❌ No custom accelerators (e.g., Ctrl+E to edit first task).

**Accessibility implication**:  
Keyboard power users and some motor‑impaired users could benefit from shortcuts, but not critical for Week 7.

**Issue identified**:

- **No additional keyboard shortcuts for frequent actions**
  - Severity: **Low**
  - Inclusion risk: **Keyboard (power users)**
  - Backlog: low‑priority enhancement for later iterations.

---

## 8. Aesthetic and Minimalist Design

**Rating**: 5 / 5 (Excellent)

- ✅ UI is uncluttered: only essential controls shown.
- ✅ Inline edit appears only when needed (progressive disclosure).
- ✅ Status messages do not accumulate; they reuse the same live region.

**Accessibility implication**:  
Minimalism reduces cognitive load and speeds up screen reader navigation.

**Issue identified**: None.

---

## 9. Help Users Recognize, Diagnose, and Recover from Errors

**Rating**: 5 / 5 (Excellent)

- ✅ Error message clearly states cause and remedy: “Title is required. Please enter at least one character.”
- ✅ Error is programmatically tied to the input via `aria-describedby`.
- ✅ Screen readers announce the error using `role="alert"` and live region.

**Accessibility implication**:  
Supports WCAG 3.3.1 (Error Identification) and 3.3.3 (Error Suggestion).

**Issue identified**: None.

---

## 10. Help and Documentation

**Rating**: 2 / 5 (Poor)

- ❌ No dedicated help page or inline “What is this?” links.
- ❌ No guidance for keyboard shortcuts or screen reader usage within the UI itself.

**Accessibility implication**:  
First‑time users and people with cognitive disabilities may struggle to discover all capabilities without documentation.

**Issue identified**:

- **Lack of in‑app help or documentation**
  - Severity: **Low**
  - Inclusion risk: **Cognitive, Low digital literacy**
  - Backlog: candidate for future enhancement (outside Week 7 scope).

---

## Summary of Heuristic Issues for Backlog

| Heuristic                   | Issue                         | Severity | Inclusion Risk                  |
| --------------------------- | ----------------------------- | -------- | ------------------------------- |
| 3. User control & freedom   | No confirmation before delete | Medium   | Motor, Cognitive                |
| 3. User control & freedom   | No undo for delete            | Low/Med  | Motor, Cognitive                |
| 5. Error prevention         | Delete too easy to trigger    | Medium   | Motor, Cognitive                |
| 7. Flexibility & efficiency | No extra keyboard shortcuts   | Low      | Keyboard (power users)          |
| 10. Help & documentation    | No in‑app help/documentation  | Low      | Cognitive, Low digital literacy |

These items should be merged into `wk06/backlog/backlog.csv` with:

- **Severity** (High/Medium/Low)
- **Inclusion risk** tags
- **Evidence** pointing to this file (`wk07/audit/heuristics.md`) and any screenshots or notes.
