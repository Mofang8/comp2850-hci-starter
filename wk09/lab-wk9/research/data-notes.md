# Data Recording Notes — Week 9

**Study Period**: 2025-12-02 to 2025-12-04  
**Data Files**: `data/metrics.csv`, `wk09/data/pilot-notes.md`

---

## Manual Recording

For each participant (P1–P5), the following were recorded during and immediately after each session:

- **Task times**: Primary source was server logs (`ms` column in metrics.csv). Backup stopwatch times were not needed as all server logs were complete.
- **Success/failure**: Binary completion status (1 = success, 0 = failure). All participants completed all tasks successfully.
- **Errors**: Validation errors tracked in metrics.csv (`step=validation_error`). Additional error observations noted in pilot-notes.md.
- **Confidence ratings**: Asked after each task: "On a scale 1-5, how confident are you that you completed correctly?"
- **Think-aloud quotes**: Captured verbatim in pilot-notes.md with timestamps.

---

## Automated Instrumentation

Server-side logging via `utils/Logger.kt` successfully captured all task events to `data/metrics.csv`.

### Log File Summary

- **File**: `data/metrics.csv`
- **Total rows**: 22 (excluding header)
- **Sessions**: 5 (P1_7a9f, P2_b3c8, P3_d4e9, P4_f5a2, P5_c7d1)
- **Schema**:

```csv
ts_iso,session_id,request_id,task_code,step,outcome,ms,http_status,js_mode
```

### Data Completeness Check

| Session | T1_filter | T2_edit                  | T3_add | T4_delete | Complete? |
| ------- | --------- | ------------------------ | ------ | --------- | --------- |
| P1_7a9f | ✅        | ✅                       | ✅     | ✅        | Yes       |
| P2_b3c8 | ✅        | ✅ (1 error + 1 success) | ✅     | ✅        | Yes       |
| P3_d4e9 | ✅        | ✅                       | ✅     | ✅        | Yes       |
| P4_f5a2 | ✅        | ✅ (1 error + 1 success) | ✅     | ✅        | Yes       |
| P5_c7d1 | ✅        | ✅                       | ✅     | ✅        | Yes       |

All expected log entries are present. No missing data.

---

## Observational Codes Used

The following tags were used in `pilot-notes.md`:

| Code | Meaning                                     | Count              |
| ---- | ------------------------------------------- | ------------------ |
| KBD  | Keyboard navigation observation             | 5                  |
| SR   | Screen reader issue                         | 0 (no SR sessions) |
| FOC  | Focus management issue                      | 3                  |
| ERR  | Validation error triggered                  | 2                  |
| CONF | Participant expressed confusion/uncertainty | 4                  |
| POS  | Positive feedback                           | 3                  |
| NOJS | No-JS specific observation                  | 5                  |

---

## Data Quality Notes

### Session P1 (sid=P1_7a9f)

- All metrics captured correctly
- No anomalies
- Stopwatch backup not needed

### Session P2 (sid=P2_b3c8)

- T2_edit: One validation error logged (`blank_title`) followed by successful retry
- Both events captured in metrics.csv (rows 6-7)
- No anomalies

### Session P3 (sid=P3_d4e9)

- Keyboard-only session
- All tasks took longer than standard sessions (expected for keyboard navigation)
- Focus observations recorded manually (not captured in server logs)
- No anomalies in automated data

### Session P4 (sid=P4_f5a2)

- No-JS session (js_mode=off for all rows)
- T2_edit: One validation error followed by success
- Times significantly longer than HTMX sessions (expected due to full page reloads)
- Confidence ratings notably lower (captured manually, not in server logs)
- No anomalies in automated data

### Session P5 (sid=P5_c7d1)

- Fastest session overall
- All metrics captured correctly
- No anomalies

---

## Outlier Analysis

| Metric         | Outlier Check                       | Result                                                              |
| -------------- | ----------------------------------- | ------------------------------------------------------------------- |
| T1_filter time | P2: 2834ms (75s manually measured)  | Within acceptable range; participant was searching for filter input |
| T2_edit time   | P4: 4567ms (145s manually measured) | Expected for No-JS mode with error recovery                         |
| T3_add time    | P4: 2134ms (72s manually measured)  | Expected for No-JS mode                                             |
| T4_delete time | P3/P4: ~1600ms                      | Expected for keyboard-only and No-JS                                |

No data points were excluded. All outliers have documented explanations.

---

## Mode Comparison Data

### js_mode = on (HTMX, n=4 sessions)

- Sessions: P1_7a9f, P2_b3c8, P3_d4e9, P5_c7d1
- Note: P3 was keyboard-only but JS was enabled

### js_mode = off (No-JS, n=1 session)

- Session: P4_f5a2
- All 4 tasks logged with js_mode=off

---

## Storage & Backup

- **metrics.csv**: Stored in `data/` directory, tracked in git
- **pilot-notes.md**: Stored in `wk09/data/`, tracked in git
- **consent-log.md**: Stored in `wk09/lab-wk9/research/`, tracked in git
- **No PII**: All files use anonymous participant codes only

---

## Data Integrity Verification

Before analysis, the following checks were performed:

- [x] All columns present in metrics.csv
- [x] Timestamps in ISO 8601 format
- [x] Session IDs consistent across related rows
- [x] Task codes match evaluation protocol (T1_filter, T2_edit, T3_add, T4_delete)
- [x] js_mode values correct (on for P1-P3/P5, off for P4)
- [x] Durations plausible (no negative values, no extreme outliers >10× median)
- [x] HTTP status codes correct (200 for success, 400 for validation_error)

**Data quality assessment**: HIGH — All automated logs complete, manual observations comprehensive, no exclusions necessary.
