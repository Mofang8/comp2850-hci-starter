# Quantitative Analysis — Week 10

**Study**: Peer pilots (n=5)  
**Period**: 2025-11-17 to 2025-11-22  
**Data Source**: `data/metrics.csv`  
**Analysis Date**: 2025-11-22

---

## Summary Statistics

### Task Completion Times (Median ± MAD)

| Task        | JS-On (n=4)    | JS-Off (n=1) | All (n=5)      | Notes                         |
| ----------- | -------------- | ------------ | -------------- | ----------------------------- |
| T1 (Filter) | 2207ms ± 494ms | 3456ms ± 0ms | 2567ms ± 720ms | No-JS 57% slower              |
| T2 (Edit)   | 2301ms ± 445ms | 4567ms ± 0ms | 2456ms ± 889ms | No-JS 98% slower; highest MAD |
| T3 (Add)    | 678ms ± 167ms  | 2134ms ± 0ms | 789ms ± 445ms  | No-JS 3.15× slower            |
| T4 (Delete) | 423ms ± 123ms  | 1678ms ± 0ms | 534ms ± 222ms  | No-JS 4× slower               |

### Completion & Error Rates

| Task        | JS-On Completion | JS-Off Completion | JS-On Errors | JS-Off Errors | Overall Errors |
| ----------- | ---------------- | ----------------- | ------------ | ------------- | -------------- |
| T1 (Filter) | 4/4 (100%)       | 1/1 (100%)        | 0%           | 0%            | 0%             |
| T2 (Edit)   | 4/4 (100%)       | 1/1 (100%)        | 20% (1/5)    | 50% (1/2)     | 29% (2/7)      |
| T3 (Add)    | 4/4 (100%)       | 1/1 (100%)        | 0%           | 0%            | 0%             |
| T4 (Delete) | 4/4 (100%)       | 1/1 (100%)        | 0%           | 0%            | 0%             |

### Confidence Ratings (from pilot notes)

| Task        | JS-On Mean | JS-Off | Overall Mean |
| ----------- | ---------- | ------ | ------------ |
| T1 (Filter) | 4.0/5      | 3.0/5  | 3.8/5        |
| T2 (Edit)   | 3.75/5     | 2.0/5  | 3.4/5        |
| T3 (Add)    | 5.0/5      | 3.0/5  | 4.6/5        |
| T4 (Delete) | 4.75/5     | 4.0/5  | 4.6/5        |

---

## Task-by-Task Interpretation

### Task T1: Filter Tasks

**Quantitative findings**:

- JS-on: 100% completion, median 2207ms, 0% error rate
- JS-off: 100% completion, median 3456ms, 0% error rate
- MAD 494ms (JS-on) suggests moderate variability

**Interpretation**:  
All participants successfully completed the filter task. No-JS users were 57% slower (expected due to full page reload). The No-JS participant's lower confidence (3/5 vs 4.0/5 average) reflects uncertainty about whether the filter worked, despite success. One HTMX participant (P2) took 75 seconds due to difficulty finding the filter input—a discoverability issue.

**Inclusion impact**: Minimal. Task completable across all variants. Full-page reload is acceptable trade-off for no-JS parity.

---

### Task T2: Edit Task Title

**Quantitative findings**:

- JS-on: 100% completion, median 2301ms, 20% error rate (1 validation error)
- JS-off: 100% completion, median 4567ms, 50% error rate (1 validation error)
- Overall error rate: 29% (2 validation errors across 7 attempts)
- MAD 889ms (all) is the highest of all tasks—significant variability

**Interpretation**:  
T2 had the **highest error rate** (29%) and **lowest confidence scores** (overall 3.4/5). Two participants accidentally submitted blank titles. The No-JS participant had:

- Lowest confidence (2/5)
- Longest time (4567ms vs 2301ms median for JS-on)
- 50% error rate
- Had to scroll up to find error message after validation failure

The high MAD (889ms) indicates inconsistent experiences—some participants struggled significantly while others completed quickly.

**Inclusion impact**: **Critical**. No-JS users face compounded barriers:

1. Error message not keyboard-focusable
2. No clear success confirmation after save
3. Must manually verify changes by scrolling

**WCAG violations identified**:

- 3.3.1 Error Identification (A): Error message location not intuitive
- 4.1.3 Status Messages (AA): Success confirmation not prominent

---

### Task T3: Add New Task

**Quantitative findings**:

- JS-on: 100% completion, median 678ms, 0% error rate, confidence 5.0/5
- JS-off: 100% completion, median 2134ms, 0% error rate, confidence 3.0/5
- No-JS is 3.15× slower than JS-on

**Interpretation**:  
Adding tasks was the **fastest and most confident task** for HTMX users. However, the No-JS participant:

- Took 3× longer (2134ms vs 678ms)
- Had significantly lower confidence (3/5 vs 5/5)
- Explicitly noted: _"I think it worked but there's no confirmation"_

This is a **critical inclusion issue**—the PRG redirect provides no success feedback, requiring users to manually verify their action by scanning the task list.

**Inclusion impact**: **High**. Users without JavaScript cannot confirm success, creating uncertainty and potential redundant submissions.

**WCAG reference**: While 3.3.1 focuses on errors, the principle of clear feedback applies equally to success states.

---

### Task T4: Delete Task

**Quantitative findings**:

- JS-on: 100% completion, median 423ms, 0% error rate, confidence 4.75/5
- JS-off: 100% completion, median 1678ms, 0% error rate, confidence 4.0/5
- No-JS is 4× slower

**Interpretation**:  
Delete was fast and successful across all modes. HTMX users benefited from the confirmation dialog (`hx-confirm`). The No-JS participant noted surprise at immediate deletion (_"Whoa, that just deleted it without asking!"_)—this aligns with the documented trade-off from wk8-01.

Despite the surprise, confidence remained acceptable (4/5) because the outcome was immediately visible. This is a **known trade-off**, not a priority fix.

**Inclusion impact**: Low. Documented trade-off. Consider soft-delete with undo for future enhancement.

---

## Statistical Notes

### Median vs Mean

We use **median** instead of mean because:

- Small sample size (n=5) is sensitive to outliers
- One slow participant (e.g., P4 in no-JS mode) would skew the mean
- Median represents the "typical" user experience

### MAD (Median Absolute Deviation)

MAD measures consistency of user experiences:

- **Low MAD** (e.g., T4: 123ms): Consistent experiences across participants
- **High MAD** (e.g., T2: 889ms): Variable experiences—some struggled, others succeeded quickly

High MAD often signals **inclusion barriers**—certain users face obstacles that others don't.

### Sample Size Limitations

- **n=5 total**, with only **n=1 for no-JS mode**
- Cannot perform statistical significance tests (Mann-Whitney U requires n≥3 per group)
- No-JS findings are indicative but should be verified with additional pilots

---

## Key Findings Summary

| Finding                             | Metric Evidence                                         | Confidence | Priority        |
| ----------------------------------- | ------------------------------------------------------- | ---------- | --------------- |
| No success feedback in No-JS add    | T3 confidence 3/5 (JS-off) vs 5/5 (JS-on)               | High       | **HIGH**        |
| Edit save confirmation unclear      | T2 confidence 3.4/5 overall; 80% participants uncertain | High       | **HIGH**        |
| No-JS error message not focusable   | T2 JS-off time 98% slower; 50% error rate               | Medium     | **MEDIUM**      |
| Filter input discoverability        | P2 took 75s (vs 42s median)                             | Low        | LOW             |
| Delete without confirmation (No-JS) | P4 surprised but confidence 4/5                         | Low        | Known trade-off |

---

## Data Quality Notes

- [x] All 5 pilots logged in `data/metrics.csv`
- [x] Session IDs match consent log (`wk09/lab-wk9/research/consent-log.md`)
- [x] JS-off sample size is n=1 (P4 only) — findings indicative but not statistically significant
- [x] No data anomalies or corrupt rows detected
- [x] All tasks completed successfully (100% completion rate)

---

_Last updated: 2025-11-22_
