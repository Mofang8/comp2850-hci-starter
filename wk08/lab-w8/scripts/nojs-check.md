# No-JS Parity Verification Script — Week 8

**Purpose**: Verify all task flows work identically with JavaScript disabled.

**Setup**:

1. Open browser DevTools settings and **disable JavaScript**.
2. Hard refresh (Ctrl+Shift+R / Cmd+Shift+R) to clear cached JS.
3. Ensure the server is running at `/tasks`.

---

## Test 1: Add Task (Happy Path)

**Steps**:

1. Navigate to `/tasks`.
2. Enter title `No-JS test task`.
3. Click `Add Task`.

**Expected**:

- Full page reload (only HTML request in Network tab).
- New task appears in list.
- URL remains `/tasks` (PRG redirect).
- No error messages.

**Evidence**: Screenshot of task list with new task visible.

**Result**: [ ] Pass [ ] Fail

---

## Test 2: Add Task (Validation Error)

**Steps**:

1. Leave title field empty.
2. Click `Add Task`.

**Expected**:

- Full page reload.
- URL shows `/tasks?error=title`.
- Error summary appears at top: `There is a problem`.
- Summary contains a link to `#title` input.
- Input has red border, error message below.

**Evidence**: Screenshot of error summary and highlighted input.

**Result**: [ ] Pass [ ] Fail

---

## Test 3: Add Task (Title Too Long)

**Steps**:

1. Paste a title longer than 200 characters.
2. Click `Add Task`.

**Expected**:

- Full page reload.
- URL shows `/tasks?error=title&msg=too_long`.
- Error summary text: `Title is too long (maximum 200 characters)`.
- Inline error message under the title field with the same text.

**Evidence**: Screenshot showing long-title error.

**Result**: [ ] Pass [ ] Fail

---

## Test 4: Filter Tasks

**Steps**:

1. Add tasks `Alpha`, `Bravo`, `Charlie` if needed.
2. In filter box, enter `ra`.
3. Click `Apply` to submit.

**Expected**:

- Full page reload.
- URL shows `/tasks?q=ra&page=1`.
- Only `Bravo` and `Charlie` visible (partial match).
- Result count text reflects the filtered total.

**Evidence**: Screenshot of filtered results with URL visible.

**Result**: [ ] Pass [ ] Fail

---

## Test 5: Pagination

**Steps**:

1. Ensure there are at least 15 tasks (page size = 10).
2. Use `Next` link to go to page 2.

**Expected**:

- Full page reload.
- URL shows `/tasks?page=2` (or `/tasks?q=...&page=2` if filtered).
- Tasks 11–15 visible.
- `Previous` link appears; `Next` link disabled or hidden on last page.

**Evidence**: Screenshot of page 2 with navigation controls.

**Result**: [ ] Pass [ ] Fail

---

## Test 6: Edit Task (Inline)

**Steps**:

1. Click `Edit` on a task.
2. Change title to `Updated via no-JS`.
3. Submit.

**Expected**:

- Full page reload (or server-rendered inline form then updated view).
- URL remains `/tasks`.
- Updated title visible in list.

**Evidence**: Screenshot of updated task.

**Result**: [ ] Pass [ ] Fail

---

## Test 7: Delete Task

**Steps**:

1. Click `Delete` on a task.

**Expected**:

- Form submits to `POST /tasks/{id}/delete`.
- Full page reload.
- Task removed from list.
- URL redirects to `/tasks`.
- **No confirmation dialog** (trade-off documented in constraints).

**Evidence**: Screenshot of task list with item removed.

**Result**: [ ] Pass [ ] Fail

---

## Test 8: Keyboard Navigation

**Steps**:

1. With JS disabled, Tab through the page: skip link → add form → filter form → task list → pagination.
2. Activate `Add Task` with Enter.
3. On validation error, Tab to error summary link and press Enter.

**Expected**:

- Focus order matches visual order, focus is always visible.
- Error link moves focus to `#title` input when activated.
- All interactive elements reachable and operable with keyboard.

**Evidence**: Notes on tab order and a screenshot showing visible focus.

**Result**: [ ] Pass [ ] Fail

---

## Test 9: Browser History

**Steps**:

1. Start at `/tasks`.
2. Apply a filter (e.g., `test`).
3. Go to page 2.
4. Use browser Back button multiple times.

**Expected**:

- Back 1 → page 1 of filtered results.
- Back 2 → unfiltered `/tasks`.
- History stack matches participant expectations.

**Evidence**: Notes on history behaviour.

**Result**: [ ] Pass [ ] Fail

---

## Summary

**Passed**: **\_** / 9  
**Failed**: **\_** / 9

**Issues found**: Log any failures in `backlog/backlog.csv` with IDs like `wk8-01`.

**Notes**:

- Capture screenshots in an `evidence/wk8/nojs-parity/` folder (if you choose to create one).
- Re-run this script after any route or template changes that affect tasks.
