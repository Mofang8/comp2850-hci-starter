# WCAG 2.2 AA Checklist — Week 7

**Date**: 2025-11-30  
**Scope**: Task manager (`/tasks` — add, inline edit, delete)  
**Tester**: [Tianqi Wang]

---

## Perceivable (Principle 1)

### 1.1 Text Alternatives

| Criterion              | Level | Status  | Evidence                                        | Notes                                                 |
| ---------------------- | ----- | ------- | ----------------------------------------------- | ----------------------------------------------------- |
| 1.1.1 Non-text Content | A     | ❌ Fail | `wk07/audit/axe-report.md#issue-c1` (image-alt) | Icon `<img src="/static/img/icon.png">` missing `alt` |

### 1.3 Adaptable

| Criterion                    | Level | Status  | Evidence                                               | Notes                                                  |
| ---------------------------- | ----- | ------- | ------------------------------------------------------ | ------------------------------------------------------ |
| 1.3.1 Info and Relationships | A     | ✅ Pass | Templates use `<label>`, `<section>`, `<ul>`           | Labels associated with inputs; list structure semantic |
| 1.3.2 Meaningful Sequence    | A     | ✅ Pass | Keyboard test path in `wk07/evidence/testing-notes.md` | Reading/tab order matches visual order                 |

### 1.4 Distinguishable

| Criterion                | Level | Status  | Evidence                                        | Notes                                                       |
| ------------------------ | ----- | ------- | ----------------------------------------------- | ----------------------------------------------------------- |
| 1.4.3 Contrast (Minimum) | AA    | ❌ Fail | `wk07/audit/axe-report.md` (7× color-contrast)  | Buttons use `#6c757d` text on `#0172ad` background (1.11:1) |
| 1.4.11 Non-text Contrast | AA    | ✅ Pass | Custom focus outline in `static/css/custom.css` | 3px blue outline clearly visible around focused elements    |

---

## Operable (Principle 2)

### 2.1 Keyboard Accessible

| Criterion              | Level | Status  | Evidence                                            | Notes                                             |
| ---------------------- | ----- | ------- | --------------------------------------------------- | ------------------------------------------------- |
| 2.1.1 Keyboard         | A     | ✅ Pass | `wk07/evidence/testing-notes.md` (Keyboard Testing) | Add, edit, save, delete, cancel all via Tab/Enter |
| 2.1.2 No Keyboard Trap | A     | ✅ Pass | Manual test — can Tab into and out of forms         | No traps detected in inline edit or add form      |

### 2.4 Navigable

| Criterion                       | Level | Status  | Evidence                                             | Notes                                                           |
| ------------------------------- | ----- | ------- | ---------------------------------------------------- | --------------------------------------------------------------- |
| 2.4.1 Bypass Blocks             | A     | ✅ Pass | Skip link in `base.peb`, confirmed via keyboard      | First Tab shows “Skip to main content” and jumps to `#main`     |
| 2.4.3 Focus Order               | A     | ✅ Pass | `wk07/evidence/testing-notes.md` (Tab path)          | Order: Skip → Add form → Task list → Edit → Title → Save/Cancel |
| 2.4.4 Link Purpose (In Context) | A     | ❌ Fail | `wk07/audit/axe-report.md#issue-s8` (link-name)      | Footer `<a href="/about"></a>` has no visible/accessible text   |
| 2.4.7 Focus Visible             | AA    | ✅ Pass | Custom CSS `*:focus { outline: 3px solid #4A90E2; }` | Strong outline visible on all interactive elements              |

---

## Understandable (Principle 3)

### 3.2 Predictable

| Criterion      | Level | Status  | Evidence    | Notes                                                    |
| -------------- | ----- | ------- | ----------- | -------------------------------------------------------- |
| 3.2.1 On Focus | A     | ✅ Pass | Manual test | No context change on focus; actions only on click/submit |
| 3.2.2 On Input | A     | ✅ Pass | Manual test | Forms submit only on button click, not on field change   |

### 3.3 Input Assistance

| Criterion                    | Level | Status    | Evidence                                                        | Notes                                                                 |
| ---------------------------- | ----- | --------- | --------------------------------------------------------------- | --------------------------------------------------------------------- |
| 3.3.1 Error Identification   | A     | ✅ Pass   | `wk07/evidence/testing-notes.md` (Validation error screenshots) | Error text: “Title is required. Please enter at least one character.” |
| 3.3.2 Labels or Instructions | A     | ⚠ Partial | Priority input missing `<label>` (backlog ID 6)                 | Title has proper label; priority still missing label                  |
| 3.3.3 Error Suggestion       | AA    | ✅ Pass   | Error includes suggestion (“enter at least one character”)      | Helps user recover from error                                         |

---

## Robust (Principle 4)

### 4.1 Compatible

| Criterion               | Level | Status  | Evidence                                                          | Notes                                                  |
| ----------------------- | ----- | ------- | ----------------------------------------------------------------- | ------------------------------------------------------ |
| 4.1.2 Name, Role, Value | A     | ✅ Pass | Buttons have accessible names (e.g. `aria-label="Edit task: …"`)  | Verified via DevTools accessibility tree               |
| 4.1.3 Status Messages   | AA    | ✅ Pass | Live region `#status` in `base.peb`, tested in `testing-notes.md` | HTMX OOB updates announce success without moving focus |

---

## Summary

- **Total criteria evaluated**: 15
- **Pass**: 11
- **Fail**: 3 (1.1.1, 1.4.3, 2.4.4)
- **Partial**: 1 (3.3.2 — priority field label)

### High-priority failures

1. **1.4.3 Contrast (Minimum, AA)**

   - Multiple primary buttons (`Add Task`, `Edit`, `Delete`) have contrast ratio **1.11:1**.
   - **Action**: Override button text color to meet ≥4.5:1 against primary background.

2. **1.1.1 Non-text Content (A)**

   - Icon image missing `alt` text.
   - **Action**: Add `alt=""` (if decorative) or meaningful `alt`.

3. **2.4.4 Link Purpose (In Context, A)**
   - Footer `/about` link has no accessible text.
   - **Action**: Add link text “About” or remove link if not used.

---

## Next

- Use this checklist together with:
  - `wk07/audit/axe-report.md`
  - `wk07/audit/heuristics.md`  
    to update `wk06/backlog/backlog.csv` with severity, inclusion risk, and evidence links.
