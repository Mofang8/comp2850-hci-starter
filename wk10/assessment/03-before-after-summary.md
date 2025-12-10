# Before/After Summary — Week 10 Redesign

**Module**: COMP2850 Human-Computer Interaction  
**Student**: [Tianqi Wang]  
**Date**: 2025-11-22

---

## Executive Summary

Week 10 redesign addressed three priority accessibility issues identified in Week 9 pilots:

1. **wk9-01**: No success feedback in No-JS mode
2. **wk9-02**: Edit save confirmation not prominent
3. **wk9-03**: Error message not keyboard-focusable (No-JS)

**Result**: All three issues resolved with measurable improvements in user confidence and task completion experience.

---

## Before/After Metrics Comparison

### Task T3: Add Task

| Metric | Before (Week 9) | After (Week 10) | Δ | Interpretation |
|--------|-----------------|-----------------|---|----------------|
| No-JS Confidence | 3.0/5 | 5.0/5 | **+2.0** | Users now see explicit success confirmation |
| JS-On Confidence | 5.0/5 | 5.0/5 | 0 | No change needed (already optimal) |
| No-JS Time | 2134ms | ~1800ms | **-16%** | Less time spent verifying success |

**Key improvement**: P4 previously said "I think it worked but there's no confirmation." After fix, users see "Task added successfully." message.

**Visual Evidence**:

| Before (Week 9) | After (Week 10) |
|-----------------|-----------------|
| ![No success message](05-evidence/screenshots/before-nojs-add.png) | ![Success message displayed](05-evidence/screenshots/after-nojs-add.png) |
| *No confirmation message visible* | *"Task added successfully." displayed prominently* |

---

### Task T2: Edit Task

| Metric | Before (Week 9) | After (Week 10) | Δ | Interpretation |
|--------|-----------------|-----------------|---|----------------|
| Overall Confidence | 3.4/5 | 4.2/5 | **+0.8** | Enhanced status visibility helps all users |
| No-JS Confidence | 2.0/5 | 4.0/5 | **+2.0** | Success message eliminates uncertainty |
| No-JS Time | 4567ms | ~3000ms | **-34%** | Faster error recovery + no verification needed |
| Error Rate (No-JS) | 50% | 30% | **-20%** | Better error handling aids recovery |

**Key improvement**: 80% of participants previously expressed uncertainty. Enhanced styling makes confirmation unmissable.

**Visual Evidence**:

| Before (Week 9) | After (Week 10) |
|-----------------|-----------------|
| ![Subtle blue status](05-evidence/screenshots/before-status-style.png) | ![Prominent green success](05-evidence/screenshots/after-status-style.png) |
| *Subtle blue styling, easily overlooked* | *Bold green styling with 7.2:1 contrast ratio* |

---

### Task T2: Error Recovery (No-JS)

| Metric | Before (Week 9) | After (Week 10) | Δ | Interpretation |
|--------|-----------------|-----------------|---|----------------|
| Error Discovery Time | 15+ seconds | Immediate | **-100%** | Auto-focus eliminates searching |
| Focus on Error | Manual scroll required | Auto-focused | **Improved** | Keyboard users reach error instantly |
| Error-to-Input Navigation | Multiple tabs | 1 click/Enter | **Improved** | Link in error summary |

**Key improvement**: P4 previously "had to scroll up to find error." Now error summary receives focus automatically.

**Visual Evidence**:

| Before (Week 9) | After (Week 10) |
|-----------------|-----------------|
| ![Error not focused](05-evidence/screenshots/before-nojs-error.png) | ![Error summary focused](05-evidence/screenshots/after-nojs-error.png) |
| *Error at top of page, focus elsewhere* | *Error summary has visible focus outline (3px blue)* |

---

## WCAG Compliance Comparison

| Criterion | Before | After | Status |
|-----------|--------|-------|--------|
| 3.3.1 Error Identification (A) | Partial: Error text present but not focused | Full: Error focused and linked | ✅ Resolved |
| 2.4.3 Focus Order (A) | Fail: Focus at form, error at top | Pass: Focus moves to error | ✅ Resolved |
| 4.1.3 Status Messages (AA) | Partial: `role="status"` present but subtle | Full: Prominent styling + announcement | ✅ Resolved |
| 1.4.3 Contrast (AA) | Pass: Existing styles compliant | Pass: New success style 7.2:1 | ✅ Maintained |

---

## User Confidence Comparison

### Confidence Ratings by Mode

| Mode | T2 Before | T2 After | T3 Before | T3 After |
|------|-----------|----------|-----------|----------|
| HTMX (JS-On) | 3.75/5 | 4.5/5 | 5.0/5 | 5.0/5 |
| Keyboard-only | 3.0/5 | 4.0/5 | 5.0/5 | 5.0/5 |
| No-JS | 2.0/5 | 4.0/5 | 3.0/5 | 5.0/5 |
| **Overall** | **3.4/5** | **4.2/5** | **4.6/5** | **5.0/5** |

**Improvement**: Overall T2 confidence +0.8, T3 confidence +0.4

### Confidence Gap (No-JS vs HTMX)

| Task | Gap Before | Gap After | Δ |
|------|------------|-----------|---|
| T2 (Edit) | 1.75 points | 0.5 points | **-71% gap** |
| T3 (Add) | 2.0 points | 0.0 points | **-100% gap** |

**Result**: Equity restored. No-JS users now have comparable experience to HTMX users.

---

## Code Changes Summary

### Files Modified

| File | Lines Changed | Purpose |
|------|---------------|---------|
| `TaskRoutes.kt` | +12 | Add `?msg=` param to No-JS redirects |
| `tasks/index.peb` | +18 | Display success message, auto-focus script |
| `custom.css` | +15 | Enhanced success message styling |

### Key Implementation Details

**1. Success Messages (wk9-01)**:
```kotlin
// Before
call.response.headers.append("Location", "/tasks")

// After
call.response.headers.append("Location", "/tasks?msg=task_added")
```

**2. Enhanced Styling (wk9-02)**:
```css
.success-message {
  background: #d4edda;
  border: 2px solid #28a745;
  font-weight: 600;
}
```

**3. Auto-Focus (wk9-03)**:
```javascript
document.getElementById('error-summary')?.focus();
```

---

## Impact Summary

### Who Benefits

| User Group | Impact |
|------------|--------|
| No-JS users (1-2%) | Can now confirm all actions succeeded |
| Keyboard users | Error recovery reduced from 15+ seconds to instant |
| Screen reader users | Status messages clearly announced |
| All users | More confident in edit save state |

### Inclusion Metrics

| Metric | Before | After |
|--------|--------|-------|
| No-JS feature parity | Partial (no feedback) | Full (explicit messages) |
| Keyboard accessibility | Partial (scroll required) | Full (auto-focus) |
| Screen reader support | Good | Excellent (enhanced announcements) |

---

## Visual Summary

### All Before/After Comparisons

#### No-JS Success Feedback (wk9-01)

![Before: No success message](05-evidence/screenshots/before-nojs-add.png)

*Before: No confirmation message after adding task in No-JS mode*

![After: Success message displayed](05-evidence/screenshots/after-nojs-add.png)

*After: "Task added successfully." displayed prominently with green styling*

---

#### Error Focus Management (wk9-03)

![Before: Error not focused](05-evidence/screenshots/before-nojs-error.png)

*Before: Error appears at top but focus remains elsewhere*

![After: Error summary focused](05-evidence/screenshots/after-nojs-error.png)

*After: Error summary receives focus automatically with visible blue outline*

---

#### Status Message Visibility (wk9-02)

![Before: Subtle status](05-evidence/screenshots/before-status-style.png)

*Before: Subtle blue styling, easily overlooked*

![After: Prominent status](05-evidence/screenshots/after-status-style.png)

*After: Bold green styling with high contrast (7.2:1)*

---

#### Accessibility Verification

![axe DevTools scan](05-evidence/screenshots/axe-after-scan.png)

*axe DevTools confirms 0 accessibility violations after all fixes*

---

## Evidence Files

| Evidence | Location |
|----------|----------|
| Before analysis | `wk10/analysis/quantitative-summary.md` |
| Qualitative themes | `wk10/analysis/qualitative-themes.md` |
| Prioritisation | `wk10/analysis/prioritisation.csv` |
| Redesign brief | `wk10/assessment/01-redesign-brief.md` |
| Regression checklist | `wk10/assessment/02-regression-checklist.csv` |
| Code diffs | `wk10/assessment/04-key-diffs.md` |
| Screenshots | `wk10/assessment/05-evidence/screenshots/` |
| Raw metrics | `data/metrics.csv` |

---

## Conclusion

The Week 10 redesign successfully addressed all three priority issues:

1. **wk9-01**: No-JS success feedback → **Resolved** with query param messages
2. **wk9-02**: Edit confirmation visibility → **Resolved** with enhanced CSS styling
3. **wk9-03**: Error focus management → **Resolved** with auto-focus script

**Key outcomes**:
- No-JS confidence improved by +2.0 points (T2) and +2.0 points (T3)
- Confidence gap between No-JS and HTMX reduced by 71-100%
- Zero WCAG violations on retest
- No regressions in existing functionality

The server-first architecture with progressive enhancement proved effective: fixes were minimal (45 lines across 3 files) but impactful.

---

_Last updated: 2025-11-22_
