# Evidence Chains — Week 9

**Purpose**: Link raw pilot data → findings → backlog items → WCAG criteria (where applicable)

---

## Chain 1: No Success Feedback in No-JS Add Task Flow

### Raw Data

**Quantitative (metrics.csv)**:

- Session `P4_f5a2`, task `T3_add`, step `success`, ms `~2100`, js_mode `off`
- Task completed successfully (HTTP 200) but confidence score was 3/5

**Qualitative (pilot-notes.md, P4 section)**:

- P4 (No-JS): "I think it worked but there's no confirmation"
- P4 had to scroll through list to verify task was added
- Time: 72 seconds (vs. 18-25s for HTMX users)

### Finding

In No-JS mode, the POST-Redirect-Get (PRG) pattern successfully adds tasks to the database, but the redirect to `/tasks` provides no explicit success message. Users must manually verify their action by scanning the task list. This creates uncertainty and reduces confidence, particularly problematic for users who rely on explicit feedback.

### Backlog Item

```csv
wk9-01,No success feedback after add task in No-JS mode,,usability,need,ux,high,high,"wk09/data/pilot-notes.md P4; wk09/analysis/findings.md Theme 1","PRG redirect shows no confirmation. Users must manually verify task was added.",Add flash message or success banner to No-JS add flow,true
```

### WCAG Reference

- **3.3.1 Error Identification (Level A)**: While this criterion focuses on errors, the principle of providing clear feedback applies equally to success states. Users should not have to guess whether their action succeeded.

### Evidence Files

- `wk09/data/pilot-notes.md` — P4 Task T3 section, lines 180-195
- `data/metrics.csv` — Rows with `session_id=P4_f5a2, task_code=T3_add`
- `wk09/analysis/findings.md` — Theme 1, Prioritised Issue #1

---

## Chain 2: Edit Save Confirmation Not Prominent Enough

### Raw Data

**Quantitative (metrics.csv)**:

- Multiple sessions show successful T2_edit operations, but confidence scores varied:
  - P1: 4/5 (said "I think it worked?")
  - P2: 3/5 (hesitated, unsure if saved)
  - P3: 3/5 (uncertain about Enter key behavior)
  - P4: 2/5 (had to scroll to verify)
  - P5: 5/5 (only participant who noticed status message)

**Qualitative (pilot-notes.md)**:

- P1: Paused 2 seconds after saving, then said "I think it worked?"
- P2: "Hesitated before leaving, unsure if change was saved"
- P3 (KB-only): "I wasn't sure if Enter would save or cancel"
- P4 (No-JS): "I'm not sure if it saved... let me scroll down and check"

### Finding

80% of participants (4/5) expressed uncertainty about whether their edit was saved successfully. The current status message "Task updated successfully" is:

1. Too subtle (small text, not highlighted)
2. Positioned in a status region that users don't naturally look at
3. Requires users to shift attention away from the edited task

This affects user confidence and may lead to redundant save attempts.

### Backlog Item

```csv
wk9-02,Edit save confirmation not prominent enough,,usability,need,ux,high,medium,"wk09/data/pilot-notes.md P1-P4; wk09/analysis/findings.md Theme 2","4/5 participants uncertain after saving. Status message too subtle.",Enhance status message visibility - larger text or visual highlight or inline confirmation,true
```

### WCAG Reference

- **4.1.3 Status Messages (Level AA)**: Status messages should be programmatically determinable and presented to the user without receiving focus. However, the _perceptual prominence_ of the message is also important for usability.

### Evidence Files

- `wk09/data/pilot-notes.md` — P1, P2, P3, P4 Task T2 sections
- `data/metrics.csv` — T2_edit success rows with timing data
- `wk09/analysis/findings.md` — Theme 2, Prioritised Issue #2

---

## Chain 3: No-JS Error Message Not Keyboard-Focusable

### Raw Data

**Quantitative (metrics.csv)**:

- Session `P4_f5a2`, task `T2_edit`, step `validation_error`, outcome `blank_title`
- Followed by successful retry

**Qualitative (pilot-notes.md, P4 section)**:

- P4 (No-JS): Accidentally submitted blank title
- "Error message visible at top: 'Title is required'"
- "[ERR] Error message not focusable, had to scroll up to find it"
- Added significant time to task completion (145s vs. 47-87s for HTMX users)

### Finding

When a validation error occurs in No-JS edit mode, the page reloads with an error message displayed at the top. However:

1. Focus is not automatically moved to the error message
2. Users must scroll/Tab to find the error
3. This is particularly problematic for keyboard-only and screen reader users

### Backlog Item

```csv
wk9-03,No-JS edit error message not keyboard-focusable,,a11y,need,a11y,medium,high,"wk09/data/pilot-notes.md P4; wk09/analysis/findings.md","Error message appears but focus not moved to it after page reload.",Add tabindex=-1 to error summary and focus on page load,true
```

### WCAG Reference

- **3.3.1 Error Identification (Level A)**: Errors must be identified and described to the user.
- **2.4.3 Focus Order (Level A)**: Focus should move in a sequence that preserves meaning and operability.
- **4.1.3 Status Messages (Level AA)**: Important messages should be announced to assistive technology.

### Evidence Files

- `wk09/data/pilot-notes.md` — P4 Task T2 section
- `data/metrics.csv` — `session_id=P4_f5a2, task_code=T2_edit, step=validation_error`
- `wk09/analysis/findings.md` — Accessibility Observations, Prioritised Issue #3

---

## Chain 4: Filter Input Discoverability

### Raw Data

**Quantitative (metrics.csv)**:

- Session `P2_b3c8`, task `T1_filter`, ms `~2800` (75 seconds — highest among HTMX users)
- Compared to P1: 42s, P5: 33s

**Qualitative (pilot-notes.md, P2 section)**:

- P2: "Scrolled down first looking for a 'Search' button"
- P2: "Took 30 seconds to notice the filter input at top"
- P2 Debrief: "The filter box wasn't obvious to me at first. Maybe add a label or placeholder?"

### Finding

While 4/5 participants found the filter input quickly, one participant (P2) took 30 seconds to discover it because they expected a button-based search interaction. This suggests the filter's affordance could be strengthened with better labeling.

### Backlog Item

```csv
wk9-04,Filter input not immediately discoverable,,usability,need,ux,medium,low,"wk09/data/pilot-notes.md P2; wk09/analysis/findings.md Theme 3","1/5 participants struggled to find filter. Suggested adding label.",Add placeholder text or visible label to filter input,false
```

### WCAG Reference

- **2.4.6 Headings and Labels (Level AA)**: Labels describe topic or purpose. A visible label like "Filter tasks" would improve discoverability.

### Evidence Files

- `wk09/data/pilot-notes.md` — P2 Task T1 section, P2 Debrief
- `data/metrics.csv` — `session_id=P2_b3c8, task_code=T1_filter`
- `wk09/analysis/findings.md` — Theme 3, Prioritised Issue #4

---

## Chain 5: Focus Management After Delete

### Raw Data

**Qualitative (pilot-notes.md, P3 section)**:

- P3 (KB-only): "[FOC] Focus moved to next task in list—acceptable but slightly disorienting"
- P3 Debrief: "After deleting, I wasn't sure where focus went—maybe announce what happened?"

### Finding

After deleting a task via keyboard, focus moves to the next task item in the list. While this behavior is technically correct (focus doesn't get lost), it can be disorienting because:

1. No announcement is made about the deletion
2. Users may not immediately realize which task now has focus
3. The focus shift happens silently

### Backlog Item

```csv
wk9-05,Focus management after delete could be improved,,a11y,nice,a11y,low,medium,"wk09/data/pilot-notes.md P3","Focus moves to next item without announcement after delete.",Consider announcing deletion and new focus location via aria-live,false
```

### WCAG Reference

- **2.4.3 Focus Order (Level A)**: Focus sequence should be logical and predictable.
- **4.1.3 Status Messages (Level AA)**: The deletion confirmation is shown visually but could be announced more clearly.

### Evidence Files

- `wk09/data/pilot-notes.md` — P3 Task T4 section, P3 Accessibility Notes
- `wk09/analysis/findings.md` — Keyboard Navigation observations, Prioritised Issue #5

---

## Summary of Evidence Chains

| Chain | Issue                                | Priority | WCAG         | Backlog ID | Candidate Fix? |
| ----- | ------------------------------------ | -------- | ------------ | ---------- | -------------- |
| 1     | No success feedback in No-JS add     | HIGH     | 3.3.1        | wk9-01     | ✅ Yes         |
| 2     | Edit save confirmation not prominent | HIGH     | 4.1.3        | wk9-02     | ✅ Yes         |
| 3     | No-JS error not focusable            | MEDIUM   | 3.3.1, 2.4.3 | wk9-03     | ✅ Yes         |
| 4     | Filter input discoverability         | MEDIUM   | 2.4.6        | wk9-04     | No             |
| 5     | Focus after delete                   | LOW      | 2.4.3, 4.1.3 | wk9-05     | No             |

**Week 10 Candidate Fixes**: wk9-01, wk9-02, wk9-03 (3 items targeting the highest-impact issues)
