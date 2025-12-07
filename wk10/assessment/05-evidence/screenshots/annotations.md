# Screenshot Annotations — Week 10 Evidence

**Purpose**: Provide alt text and context for each screenshot to ensure accessibility and traceability.

---

## Before/After Comparison: No-JS Success Feedback (wk9-01)

### before-nojs-add.png

**Alt text**: "Task list page after adding a task in No-JS mode. The page shows the task list with a newly added task 'Test task' visible, but no success confirmation message is displayed anywhere on the page. The URL bar shows '/tasks' without any query parameters."

**Context**: Week 9 baseline. P4 said "I think it worked but there's no confirmation." User had to scroll through the list to verify the task was added, reducing confidence to 3/5.

**Highlight**: Note the absence of any success banner at the top of the page. The only indication of success is the task appearing in the list.

**Evidence for**: wk9-01 (No success feedback after add task in No-JS mode)

---

### after-nojs-add.png

**Alt text**: "Task list page with a prominent green success banner reading 'Task added successfully.' displayed at the top of the page. The banner has a green background (#d4edda), dark green text (#155724), and a visible border. The URL bar shows '/tasks?msg=task_added'. Below the banner, the task list is visible with the newly added task."

**Context**: Week 10 fix (wk9-01). Query parameter `?msg=task_added` triggers the success message display. Explicit confirmation eliminates uncertainty.

**Highlight**:

- Green success message prominently displayed
- `role="status"` ensures screen reader announcement
- URL shows `?msg=task_added` parameter

**Evidence for**: wk9-01 fix verification

---

## Before/After Comparison: Error Focus Management (wk9-03)

### before-nojs-error.png

**Alt text**: "Task page after submitting a blank title in No-JS mode. An error summary appears at the top of the page with the heading 'There is a problem' and the message 'Title is required.' The page has scrolled to show the form area, and the error summary is partially or fully above the visible viewport. No focus indicator (blue outline) is visible on the error summary, indicating focus is elsewhere on the page."

**Context**: Week 9 baseline. P4 "had to scroll up to find error." Focus remained on the form after page reload, requiring manual navigation to discover the error. This added significant time (4567ms vs 2301ms for JS-on users).

**Highlight**:

- Error summary exists but is not focused
- User must scroll or Tab to find the error
- WCAG 2.4.3 (Focus Order) violation

**Evidence for**: wk9-03 (No-JS edit error message not keyboard-focusable)

---

### after-nojs-error.png

**Alt text**: "Task page after submitting a blank title in No-JS mode. The error summary at the top of the page has a visible blue focus outline (3px solid #4A90E2) indicating it has received keyboard focus automatically. The error summary contains a 'There is a problem' heading and a clickable link 'Title is required. Please enter at least one character.' that navigates to the title input field."

**Context**: Week 10 fix (wk9-03). Auto-focus script moves focus to error summary on page load. `tabindex="-1"` makes it programmatically focusable. User immediately sees the error without scrolling.

**Highlight**:

- Blue focus outline on error-summary div confirms auto-focus is working
- Error link provides direct navigation to the problem field
- `role="alert"` ensures immediate screen reader announcement

**Evidence for**: wk9-03 fix verification

---

## Before/After Comparison: Status Message Visibility (wk9-02)

### before-status-style.png

**Alt text**: "Task list after successful edit in HTMX mode. A status message reads 'Task updated successfully.' The message has a light blue background (#e3f2fd) with a blue left border, regular font weight, and appears below the main heading. The message is present but not particularly prominent against the page background."

**Context**: Week 9 baseline. P1 said "I think it worked?" after pausing 2 seconds. Only P5 explicitly mentioned seeing the status message. 4/5 participants (80%) were uncertain if their edit was saved.

**Highlight**:

- Status message is present but subtle
- Light blue styling doesn't strongly signal success
- Easily overlooked by users focused on the edited task

**Evidence for**: wk9-02 (Edit save confirmation not prominent enough)

---

### after-status-style.png

**Alt text**: "Task list after successful edit in HTMX mode. A prominent status message reads 'Task updated successfully.' The message has a bright green background (#d4edda), dark green text (#155724), bold font weight (600), larger font size (1.05rem), and a 2px green border. It stands out clearly from the rest of the page content."

**Context**: Week 10 fix (wk9-02). Enhanced CSS styling makes success messages unmissable. Class `success` applied to status div triggers the prominent green styling.

**Highlight**:

- Green background clearly signals success
- Bold text and larger font improve visibility
- Contrast ratio 7.2:1 exceeds WCAG AA requirement (4.5:1)

**Evidence for**: wk9-02 fix verification

---

## Accessibility Verification

### axe-after-scan.png

**Alt text**: "axe DevTools accessibility scan results for the /tasks page after Week 10 fixes. The results panel shows '0 issues' with expandable sections for Critical (0), Serious (0), Moderate (0), and Minor (0) violations. The page passes all automated accessibility checks."

**Context**: Week 10 regression testing. Confirms that the three fixes (wk9-01, wk9-02, wk9-03) did not introduce new accessibility issues and the page remains WCAG 2.2 AA compliant.

**Highlight**:

- 0 violations in all severity categories
- Automated testing confirms no regressions
- Supplements manual testing (keyboard, screen reader, No-JS)

**Evidence for**: Regression testing, WCAG compliance verification

---
