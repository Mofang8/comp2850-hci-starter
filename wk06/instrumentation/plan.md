# Instrumentation Plan — Week 6

**Purpose**: Capture objective metrics for Week 9 task-based pilots and Week 10 analysis.

---

## Events to Log

### 1. Task Created

**Trigger**: POST /tasks (success)
**Fields**:

- `ts_iso`: ISO 8601 timestamp (e.g., 2025-01-15T14:23:45Z)
- `session_id`: Anonymous 6-char hex (e.g., `P1_a3f7`)
- `request_id`: Unique per request (for tracing)
- `task_code`: `T3_add` (pilot task identifier)
- `step`: `submit`
- `outcome`: `success` | `validation_error`
- `ms`: Time from request start to response (server-side)
- `http_status`: 200 (success) | 400 (validation error)
- `js_mode`: `js-on` | `js-off`

**Why**: Measure task completion time, compare HTMX vs. no-JS.

---

### 2. Validation Error

**Trigger**: POST /tasks (blank title)
**Fields**: Same as Task Created, but `outcome=validation_error`, `http_status=400`

**Why**: Count errors as usability metric; high error rate = poor UX.

---

### 3. Task Deleted

**Trigger**: POST /tasks/{id}/delete (success)
**Fields**: Same structure as Task Created, but `task_code=T4_delete`

**Why**: Measure delete task time; verify live region announcement.

---

## Data Storage

- **Format**: CSV (human-readable, easy to analyse in Excel/Google Sheets)
- **Location**: `data/metrics.csv` (local file, not cloud)
- **Schema**:
  ```csv
  ts_iso,session_id,request_id,task_code,step,outcome,ms,http_status,js_mode
  ```

**Example rows**:

```csv
2025-01-15T14:23:45Z,P1_a3f7,req_001,T3_add,submit,success,120,200,js-on
2025-01-15T14:24:10Z,P1_a3f7,req_002,T4_delete,submit,success,85,200,js-on
2025-01-15T14:25:00Z,P2_b8c4,req_003,T3_add,submit,validation_error,0,400,js-off
```

---

## Ethics & Privacy

- **No PII**: Session IDs are random hex (not linked to names/emails)
- **Anonymisation**: Use `P1`, `P2` pseudonyms (consistent with interview notes)
- **Consent**: Covered by Week 6 consent protocol (low-risk research)
- **Retention**: Delete after Semester 1 or anonymise for portfolio

---

## Implementation (Week 9)

Create `src/main/kotlin/utils/Logger.kt`:

```kotlin
object Logger {
    private val file = File("data/metrics.csv").apply {
        parentFile?.mkdirs()
        if (!exists()) writeText("ts_iso,session_id,request_id,task_code,step,outcome,ms,http_status,js_mode\n")
    }

    @Synchronized
    fun write(session: String, req: String, task: String, step: String, outcome: String, ms: Long, status: Int, js: String) {
        val ts = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
        file.appendText("$ts,$session,$req,$task,$step,$outcome,$ms,$status,$js\n")
    }
}
```

---

**Next steps**: In Week 9 Lab 1, integrate Logger into routes and test with manual pilots.
