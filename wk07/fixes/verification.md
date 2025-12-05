# Verification Log — Fix 09 (Button Text Contrast)

**Date**: 2025-11-30  
**Fix**: Button text contrast (WCAG 1.4.3)

---

## Before State

- **CSS**:
  ```css
  button[type="submit"],
  button {
    color: #6c757d;
  }
  ```
- **Computed contrast (axe DevTools)**:
  - Foreground: `#6c757d`
  - Background: `#0172ad` (Pico primary button background)
  - Ratio: **1.11:1** → **Fail (AA requires ≥ 4.5:1)**.
- **axe summary**:
  - Rule: `color-contrast` (serious)
  - Count: 7 failing elements (Add, Edit, Delete buttons across tasks).
- **Evidence source**:
  - `wk07/audit/localhost_8080-COMP2850-Task-Manager-2025-11-30.json`
  - `wk07/audit/axe-report.md`

---

## After State

- **CSS** (custom override):
  ```css
  /* Issue (fixed): Button color contrast failed WCAG 1.4.3 (AA)
     Previously: #6c757d text on Pico primary background (#0172ad) ≈ 1.11:1
     Fix: Use white text on primary background to meet contrast requirements */
  button[type="submit"],
  button {
    color: #ffffff;
  }
  ```
- **Contrast (WebAIM calculation)**:
  - Foreground: `#ffffff`
  - Background: Pico primary blue (e.g., `#0172ad`)
  - Approximate contrast ratio: **≈ 5.25:1** → **Pass (AA)** for normal text.

---

## Tests Performed

### Test 1: Visual Inspection

- **Action**: Reloaded `http://localhost:8080/tasks` in Firefox on macOS.
- **Result**:
  - “Add Task” button shows **white text on blue background**.
  - All “Edit” and “Delete” buttons show **white text on blue background**.
  - Text is clearly readable at 100% and 200% zoom.

### Test 2: Contrast Calculation (WebAIM)

- **Tool**: WebAIM Contrast Checker.
- **Input**:
  - Foreground: `#ffffff`
  - Background: Pico primary blue (sampled from button in DevTools).
- **Result**:
  - Approx. **5.25:1** contrast ratio.
  - ✅ Meets **WCAG 2.1 / 2.2 AA** minimum (≥ 4.5:1) for normal text.

### Test 3: axe DevTools Re-scan

- **Action**:
  - Ran axe DevTools again on `http://localhost:8080/tasks` after CSS change.
- **Result** (see `wk07/audit/localhost_8080-2025-11-30.json`):
  - `color-contrast` is **no longer listed** under `failedRules`.
  - Remaining issues:
    - `image-alt` (1×) — missing `alt` on icon image.
    - `link-name` (1×) — footer `/about` link with no text.

### Test 4: Regression Check (Functional)

- **Actions**:
  - Add a new task.
  - Edit an existing task (HTMX inline path).
  - Edit with JavaScript disabled (no‑JS path).
  - Delete a task.
- **Result**:
  - All flows continue to work as before.
  - No unintended changes to behaviour; only visual contrast improved.

---

## WCAG Compliance

- **Criterion**: 1.4.3 Contrast (Minimum, Level AA)
- **Status (after fix)**: **Pass** — contrast ≥ 4.5:1 and axe no longer reports `color-contrast` failures.

---

## Evidence

**Before (failing contrast)**  
![Buttons before contrast fix — grey text on blue background](before_screenshot.png)

**After (improved contrast)**  
![Buttons after contrast fix — white text on blue background](fixed_screenshot.png)

**axe DevTools after fix**  
Results recorded in `wk07/audit/localhost_8080-2025-11-30.json`, confirming that `color-contrast` no longer fails.
