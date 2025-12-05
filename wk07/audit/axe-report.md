# axe DevTools Audit Report — Week 7

**Date**: 2025-11-30  
**URL**: http://localhost:8080/tasks  
**Tool**: axe DevTools HTML 4.117.0 (axe-core 4.10.3)  
**Standard**: WCAG 2.1 AA (sufficient for course target WCAG 2.2 AA)

---

## Summary

- **Critical**: 1
- **Serious**: 8
- **Moderate**: 0
- **Minor**: 0
- **Total**: 9 issues

### Failed rules

| Rule             | WCAG ref   | Impact   | Count | Notes                                    |
| ---------------- | ---------- | -------- | ----- | ---------------------------------------- |
| `color-contrast` | 1.4.3 (AA) | serious  | 7     | Text on primary buttons too low contrast |
| `image-alt`      | 1.1.1 (A)  | critical | 1     | Icon image missing alternative text      |
| `link-name`      | 2.4.4 (A)  | serious  | 1     | Footer `/about` link has no link text    |

Raw export: `wk07/audit/localhost_8080-COMP2850-Task-Manager-2025-11-30.json`

---

## Critical Issues

### Issue C1: Icon image missing alt text

- **Rule**: `image-alt` (WCAG 1.1.1 Non-text Content, Level A)
- **Element**: `<img src="/static/img/icon.png" width="16" height="16">`
- **Location**: `src/main/resources/templates/tasks/index.peb` (task list heading icon)
- **Description**: Image has no `alt` attribute and no ARIA override.
- **Impact**:
  - Screen readers only announce “image” with no description.
  - People using screen readers lose context for the icon.
- **Suggested fix**:
  - If decorative: add `alt=""` or `role="presentation"`.
  - If meaningful: add `alt="Task list"` (or similar accurate text).
- **Backlog mapping**:
  - Existing item: **ID 7 — Icon image missing alt text**
  - Evidence: update to include this report (`wk07/audit/axe-report.md#issue-c1`).

---

## Serious Issues

### Issue S1–S7: Button text contrast too low (multiple buttons)

- **Rule**: `color-contrast` (WCAG 1.4.3 Contrast (Minimum), Level AA)
- **Elements** (examples from report):
  - `form[action="/tasks"] > button` — “Add Task” button
  - `button[aria-label="Edit task: Test Task"]` — Edit button
  - `button[aria-label="Delete task: Test Task"]` — Delete button
  - Similar patterns for other tasks (e.g., “Kotlin 2.2.21 works”, “HTMX Updated”)
- **Computed contrast (axe)**:
  - Foreground color: `#6c757d`
  - Background color: `#0172ad` (Pico primary button background)
  - **Contrast ratio**: **1.11:1** (expected ≥ 4.5:1 for normal text)
- **Impact**:
  - People with low vision and color‑blindness cannot reliably read button text.
  - Situational impairments: glare, low‑quality screens, or outdoor use.
- **Cause**:
  - Custom CSS overrides button text color:
    - `src/main/resources/static/css/custom.css`:
      ```css
      button[type="submit"],
      button {
        color: #6c757d;
      }
      ```
  - This override is layered on top of Pico’s blue primary button background.
- **Suggested fix (course recommendation)**:
  - Override Pico button text color with a high‑contrast color against the primary blue:
    - Option A: `#ffffff` (white) on `#0172ad` — high contrast (AA/AAA, to be confirmed)
    - Option B: change button variant to lighter background + dark text
- **Backlog mapping**:
  - Existing item: **ID 4 — Button text contrast too low** (job story from Week 6)
  - For Week 7:
    - Update **evidence** for ID 4 to include this axe report.
    - Mark as high‑priority candidate fix for Lab 2.

---

### Issue S8: Footer link has no accessible name

- **Rule**: `link-name` (WCAG 2.4.4 Link Purpose, 4.1.2 Name, Role, Value, Level A)
- **Element**: `<a href="/about"></a>`
- **Location**: `src/main/resources/templates/_layout/base.peb` (footer)
- **Description**:
  - Link is in tab order but:
    - Has no text content.
    - No `aria-label` or `aria-labelledby`.
    - No `title`.
  - Screen readers announce this as a nameless link, which is confusing and unusable.
- **Impact**:
  - Screen reader and keyboard users encounter a focusable link with no purpose.
- **Suggested fix**:
  - Option 1 (recommended): add visible text, e.g.:
    - `<a href="/about">About</a>`
  - Option 2: if the link is not needed, remove it entirely.
- **Backlog mapping**:
  - Existing item: **ID 8 — Empty link in footer**
  - Evidence: update to point to `wk07/audit/axe-report.md#issue-s8`.

---

## Actions Summary

1. **Confirm and prioritise**:
   - Treat all 7 `color-contrast` findings as one **high‑priority** issue (WCAG 1.4.3 AA failure).
   - Treat `image-alt` and `link-name` as additional A‑level failures.
2. **Update backlog**:
   - Link backlog items 4, 7, 8 to this report as primary evidence.
   - Mark the contrast issue as `candidate_fix=true` for Week 7 Lab 2.
3. **Plan fix**:
   - Implement a CSS override for button text contrast (Fix 09).
   - Add alt text to the icon image.
   - Add text (or remove) the footer `/about` link.
4. **Re‑scan after fixes**:
   - Save a new axe export (e.g., `localhost_8080-...-after.json`).
   - Update verification log with “0 critical, 0 serious” for these rules.
