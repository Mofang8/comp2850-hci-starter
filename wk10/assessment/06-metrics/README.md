# Metrics Directory — Week 10 Assessment

This directory contains quantitative data from Week 9 (before) and Week 10 (after) pilots.

## Structure

```
06-metrics/
├── README.md              # This file
├── pre/
│   └── analysis.csv       # Week 9 analysis (copy from wk10/analysis/)
└── post/
    └── postchange.csv     # Week 10 verification pilot data (if collected)
```

## Pre-Change Metrics (Week 9)

Copy the analysis file:
```bash
cp wk10/analysis/analysis.csv wk10/assessment/06-metrics/pre/
```

### Key Metrics Summary (Before)

| Task | JS Mode | Median (ms) | Confidence | Error Rate |
|------|---------|-------------|------------|------------|
| T3 (Add) | on | 678 | 5.0/5 | 0% |
| T3 (Add) | off | 2134 | 3.0/5 | 0% |
| T2 (Edit) | on | 2301 | 3.75/5 | 20% |
| T2 (Edit) | off | 4567 | 2.0/5 | 50% |

## Post-Change Metrics (Week 10)

If conducting verification pilots, record data in `post/postchange.csv`:

```csv
ts_iso,session_id,task_code,js_mode,time_ms,errors,confidence,notes
2025-12-04T15:00:00Z,P6_post,T3_add,off,1800,0,5,"Saw success message immediately"
2025-12-04T15:05:00Z,P6_post,T2_edit,off,3000,0,4,"Clear confirmation displayed"
```

### Expected Improvements (After)

| Task | JS Mode | Target Median | Target Confidence | Target Error Rate |
|------|---------|---------------|-------------------|-------------------|
| T3 (Add) | off | ≤1800ms | ≥4.0/5 | 0% |
| T2 (Edit) | off | ≤3200ms | ≥4.0/5 | ≤30% |

## Comparison Template

| Metric | Before | After | Δ | Meets Target? |
|--------|--------|-------|---|---------------|
| T3 No-JS confidence | 3.0/5 | [measure] | | ≥4.0/5 |
| T2 No-JS confidence | 2.0/5 | [measure] | | ≥4.0/5 |
| T2 No-JS time | 4567ms | [measure] | | ≤3200ms |

---

_Populate this directory with actual data after conducting verification pilots._

