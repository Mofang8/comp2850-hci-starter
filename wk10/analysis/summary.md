# Pilot Data Analysis Summary — Week 10

**Study**: Peer pilots (n=5)  
**Purpose**: Summarise quantitative metrics and qualitative findings to inform redesign priorities  
**Analysis Date**: 2025-11-22

---

## Summary Statistics

| Task | JS Mode | n_success | Median (ms) | MAD (ms) | Completion Rate | Error Rate | Notes |
|------|---------|-----------|-------------|----------|-----------------|------------|-------|
| T1 (Filter) | on | 4 | 2207 | 494 | 100% | 0% | Consistent across participants |
| T1 (Filter) | off | 1 | 3456 | 0 | 100% | 0% | 57% slower than JS-on |
| T1 (Filter) | all | 5 | 2567 | 720 | 100% | 0% | All participants successful |
| T2 (Edit) | on | 4 | 2301 | 445 | 100% | 20% | 1 validation error (P2) |
| T2 (Edit) | off | 1 | 4567 | 0 | 100% | 50% | 1 validation error (P4); 98% slower |
| T2 (Edit) | all | 5 | 2456 | 889 | 100% | 29% | **Highest error rate; highest MAD** |
| T3 (Add) | on | 4 | 678 | 167 | 100% | 0% | Fast and confident |
| T3 (Add) | off | 1 | 2134 | 0 | 100% | 0% | 3.15× slower; confidence 3/5 |
| T3 (Add) | all | 5 | 789 | 445 | 100% | 0% | No-JS lacks success feedback |
| T4 (Delete) | on | 4 | 423 | 123 | 100% | 0% | Fast; confirmation dialog helpful |
| T4 (Delete) | off | 1 | 1678 | 0 | 100% | 0% | 4× slower; no confirmation (trade-off) |
| T4 (Delete) | all | 5 | 534 | 222 | 100% | 0% | Known trade-off from wk8-01 |

---

## Task-by-Task Interpretation

### T1 Filter

**What the data shows**:
- 100% completion across all variants
- No-JS is 57% slower (expected due to full page reload)
- One participant (P2) took 75s vs 42s median—discoverability issue

**Who is affected**:
- One participant struggled to find the filter input
- No-JS users experience acceptable slowdown

**Recommendation**: Add placeholder text to improve discoverability (low priority)

---

### T2 Edit

**What the data shows**:
- **Highest error rate**: 29% overall (20% JS-on, 50% JS-off)
- **Highest variability**: MAD 889ms indicates inconsistent experiences
- **Lowest confidence**: Mean 3.4/5 (lowest of all tasks)
- **No-JS barrier**: 98% slower, error message not focusable

**Barriers identified**:
1. P4 (No-JS): Error message not focusable, had to scroll to find it
2. P1, P2, P3, P4: Uncertainty about whether save succeeded
3. P2, P4: Accidentally submitted blank title (validation error)

**WCAG violations**:
- 3.3.1 Error Identification (A): Error location not intuitive for No-JS
- 4.1.3 Status Messages (AA): Success confirmation not prominent

**Recommendation**: 
- Fix wk9-02 (enhance save confirmation)
- Fix wk9-03 (make error focusable)

---

### T3 Add

**What the data shows**:
- Fastest task for JS-on (median 678ms)
- No-JS is 3.15× slower (2134ms)
- JS-on confidence 5/5 vs JS-off confidence 3/5

**Critical inclusion issue**:
- P4 (No-JS): "I think it worked but there's no confirmation"
- PRG redirect provides no explicit success message
- Users must manually verify by scanning the task list

**WCAG reference**: Success feedback is as important as error feedback (3.3.1 principle)

**Recommendation**: Fix wk9-01 (add success message for No-JS path)

---

### T4 Delete

**What the data shows**:
- Fast across all variants (median 534ms)
- 100% completion, 0% errors
- No-JS is 4× slower but acceptable

**Known trade-off**:
- P4: "Whoa, that just deleted it without asking!"
- No confirmation in No-JS mode (documented in wk8-01)
- Confidence remained 4/5 because outcome was visible

**Recommendation**: Defer to backlog (known acceptable trade-off)

---

## Priority Findings

| ID | Issue | Task | Evidence | WCAG | Impact | Inclusion | Effort | Priority |
|----|-------|------|----------|------|--------|-----------|--------|----------|
| wk9-01 | No success feedback (No-JS add) | T3 | P4 confidence 3/5; quote L331-334 | 3.3.1 | 5 | 5 | 2 | **8** |
| wk9-03 | Error not focusable (No-JS edit) | T2 | P4 L311-313; 98% slower | 3.3.1, 2.4.3 | 4 | 5 | 2 | **7** |
| wk9-02 | Save confirmation not prominent | T2 | 4/5 uncertain; confidence 3.4/5 | 4.1.3 | 4 | 4 | 2 | **6** |
| wk9-04 | Filter discoverability | T1 | P2 took 30s to find input | 2.4.6 | 3 | 2 | 1 | **4** |
| wk9-05 | Focus after delete | T4 | P3 disoriented | 2.4.3 | 2 | 3 | 3 | **2** |

**Week 10 Lab 2 Candidates**: wk9-01, wk9-02, wk9-03 (scores ≥6, all WCAG violations)

---

## Data Quality Notes

- [x] All 5 pilots logged in `data/metrics.csv`
- [x] Session IDs match consent log
- [ ] **Note**: JS-off sample n=1 (P4 only) — findings indicative but require follow-up
- [x] No anomalies or corrupt data detected
- [x] All referenced pilot notes verified against source file

---

## Confidence Scores by Participant and Task

| Participant | Mode | T1 | T2 | T3 | T4 | Mean |
|-------------|------|----|----|----|----|------|
| P1 | HTMX | 4 | 4 | 5 | 5 | 4.5 |
| P2 | HTMX | 3 | 3 | 5 | 5 | 4.0 |
| P3 | KB-only | 4 | 3 | 5 | 4 | 4.0 |
| P4 | No-JS | 3 | **2** | **3** | 4 | **3.0** |
| P5 | HTMX | 5 | 5 | 5 | 5 | 5.0 |
| **Mean** | | 3.8 | **3.4** | 4.6 | 4.6 | 4.1 |

**Key insight**: P4 (No-JS) has lowest confidence across all tasks, particularly T2 (2/5) and T3 (3/5). This validates the prioritization of wk9-01 and wk9-03.

---

_Last updated: 2025-11-22_

