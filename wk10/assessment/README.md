# COMP2850 HCI — Week 10 Assessment Submission

**Student**: [Tianqi Wang]  
**Date**: 2025-12-05
**Module**: COMP2850 Human-Computer Interaction

---

## Contents

1. **01-redesign-brief.md** — Problem statement, proposed changes, acceptance criteria
2. **02-regression-checklist.csv** — Accessibility verification (keyboard, SR, No-JS)
3. **03-before-after-summary.md** — Quantitative metrics comparison (Week 9 vs Week 10)
4. **04-key-diffs.md** — Annotated code changes with WCAG rationale
5. **05-evidence/** — Screenshots, annotations
6. **06-metrics/** — Raw analysis CSVs (pre/post)

---

## Summary

### Problem

Week 9 pilot data revealed three accessibility barriers affecting No-JS users:

1. **wk9-01**: No success feedback after add task (confidence 3/5 vs 5/5)
2. **wk9-02**: Edit save confirmation not prominent (80% users uncertain)
3. **wk9-03**: Error message not keyboard-focusable (scroll required)

### Solution

Implemented accessible feedback system:
- Success messages via query parameter for No-JS paths
- Enhanced CSS styling for prominent status display
- Auto-focus error summary on page load

### Result

| Metric | Before | After | Improvement |
|--------|--------|-------|-------------|
| T3 No-JS confidence | 3.0/5 | 5.0/5 | +2.0 |
| T2 No-JS confidence | 2.0/5 | 4.0/5 | +2.0 |
| WCAG violations | 3 | 0 | -100% |

### Impact

- **No-JS users**: Can now confirm all actions succeeded
- **Keyboard users**: Error recovery reduced from 15+ seconds to instant
- **Screen reader users**: Status messages clearly announced
- **All users**: More confident in edit save state

---

## Evidence Chain

```
1. Raw data      → data/metrics.csv, wk09/data/pilot-notes.md
                   ↓
2. Analysis      → wk10/analysis/quantitative-summary.md
                   wk10/analysis/qualitative-themes.md
                   ↓
3. Prioritisation → wk10/analysis/prioritisation.csv
                    wk10/redesign/priorities.md
                    ↓
4. Implementation → TaskRoutes.kt, index.peb, custom.css
                    wk10/assessment/04-key-diffs.md
                    ↓
5. Verification  → wk10/assessment/02-regression-checklist.csv
                   wk10/evidence/reverification.md
                   ↓
6. Measurement   → wk10/assessment/03-before-after-summary.md
                   wk10/assessment/06-metrics/
```

All files reference each other for full traceability.

---

## WCAG Compliance

| Criterion | Status | Evidence |
|-----------|--------|----------|
| 3.3.1 Error Identification (A) | ✅ Pass | Error focused and linked to input |
| 2.4.3 Focus Order (A) | ✅ Pass | Auto-focus on error summary |
| 4.1.3 Status Messages (AA) | ✅ Pass | role="status/alert" announced |
| 1.4.3 Contrast (AA) | ✅ Pass | 7.2:1 for success message |

---

## Files Modified

| File | Purpose |
|------|---------|
| `src/main/kotlin/routes/TaskRoutes.kt` | Add `?msg=` to No-JS redirects |
| `src/main/resources/templates/tasks/index.peb` | Display success message, auto-focus script |
| `src/main/resources/static/css/custom.css` | Enhanced success styling |

---

## Verification Checklist

- [x] All regression checks pass (02-regression-checklist.csv)
- [x] axe DevTools: 0 violations
- [x] Keyboard navigation: All elements reachable
- [x] Screen reader: Messages announced
- [x] No-JS: Success messages displayed
- [x] Error focus: Auto-focused on load

---

## Submission Notes

- All files sanitized (no PII)
- Screenshots to be captured following 05-evidence/README.md instructions
- Commit history clean and descriptive

---

_Last updated: 2025-12-05_

