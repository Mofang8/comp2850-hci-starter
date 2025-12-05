# Pilot Data — Week 9 (Lab 2)

**Study Period**: 2025-12-02 to 2025-12-04  
**Total Participants**: 5 (3 HTMX standard, 1 keyboard-only, 1 no-JS)  
**Facilitator**: Student researcher  
**Data Sources**: Server logs (`data/metrics.csv`) + manual observations below

---

## Participant P1

**Mode**: HTMX (standard, mouse + keyboard)  
**Date**: 2025-12-02 14:15  
**Session ID**: `P1_7a9f`  
**Consent**: ✅ Verbal consent confirmed  
**Duration**: 16 minutes

---

### Task T1: Filter Tasks

**Start**: 14:17:32 → **End**: 14:18:14 = **42 seconds**  
**Success**: 1 (completed correctly)  
**Errors**: 0  
**Confidence**: 4/5  
**Observations**:

- Found the filter input immediately (top of page)
- Typed "meet" and waited briefly for results
- Said "Oh, it filters as I type—nice"
- Correctly reported 2 matching tasks
- Did not notice the status message "Found 2 tasks" initially; looked at list instead

---

### Task T2: Edit Task Title

**Start**: 14:19:05 → **End**: 14:19:52 = **47 seconds**  
**Success**: 1  
**Errors**: 0  
**Confidence**: 4/5  
**Observations**:

- Located "draft report" task quickly
- Clicked Edit button, inline form appeared
- Changed title to "submit report" and clicked Save
- Paused 2 seconds after saving, then said "I think it worked?"
- Status message "Task updated successfully" displayed but participant didn't read it aloud

---

### Task T3: Add New Task

**Start**: 14:20:30 → **End**: 14:20:48 = **18 seconds**  
**Success**: 1  
**Errors**: 0  
**Confidence**: 5/5  
**Observations**:

- Found add form at top immediately
- Typed "Meet supervisor about project"
- Pressed Enter key to submit (didn't click button)
- New task appeared in list instantly
- Said "That was easy"

---

### Task T4: Delete Task

**Start**: 14:21:20 → **End**: 14:21:35 = **15 seconds**  
**Success**: 1  
**Errors**: 0  
**Confidence**: 5/5  
**Observations**:

- Found "tmp" task
- Clicked Delete button
- Browser confirmation dialog appeared (hx-confirm)
- Confirmed deletion
- Task removed from list immediately
- Status message displayed "Deleted tmp"

---

### Debrief Notes

- **Most difficult task**: "Editing was slightly confusing—I wasn't 100% sure the save worked until I saw the title change"
- **What worked well**: "Adding tasks is really smooth, I like the instant feedback"
- **Uncertainty moments**: "After editing, maybe the success message could be more prominent"

### Accessibility Notes

- N/A (standard mouse + keyboard session)

---

## Participant P2

**Mode**: HTMX (standard, mouse)  
**Date**: 2025-12-02 15:00  
**Session ID**: `P2_b3c8`  
**Consent**: ✅ Verbal consent confirmed  
**Duration**: 19 minutes

---

### Task T1: Filter Tasks

**Start**: 15:02:10 → **End**: 15:03:25 = **75 seconds**  
**Success**: 1  
**Errors**: 0  
**Confidence**: 3/5  
**Observations**:

- Scrolled down first looking for a "Search" button
- Took 30 seconds to notice the filter input at top
- Typed "meet" correctly
- Counted tasks manually instead of reading status message
- Said "I think there are 2... let me check again"

---

### Task T2: Edit Task Title

**Start**: 15:04:15 → **End**: 15:05:42 = **87 seconds**  
**Success**: 1  
**Errors**: 1 (submitted blank title by accident)  
**Confidence**: 3/5  
**Observations**:

- Found Edit button
- Accidentally cleared the input and pressed Enter
- Error message appeared: "Title is required"
- Said "Oh, I deleted it by mistake"
- Re-entered "submit report" and saved successfully
- Hesitated before leaving, unsure if change was saved

---

### Task T3: Add New Task

**Start**: 15:06:30 → **End**: 15:06:55 = **25 seconds**  
**Success**: 1  
**Errors**: 0  
**Confidence**: 5/5  
**Observations**:

- Found form quickly this time
- Typed task title and clicked Add button
- Task appeared in list
- Scrolled down to verify it was added

---

### Task T4: Delete Task

**Start**: 15:07:40 → **End**: 15:08:02 = **22 seconds**  
**Success**: 1  
**Errors**: 0  
**Confidence**: 5/5  
**Observations**:

- Found "tmp" task
- Clicked Delete, confirmed in dialog
- Task removed
- Said "Good, that's gone"

---

### Debrief Notes

- **Most difficult task**: "Editing—I accidentally cleared it and got an error. The error message was small"
- **What worked well**: "Deleting was straightforward with the confirmation"
- **Uncertainty moments**: "The filter box wasn't obvious to me at first. Maybe add a label or placeholder?"

### Accessibility Notes

- N/A (standard session)

---

## Participant P3

**Mode**: Keyboard-only (Tab, Enter, Space only; JS enabled)  
**Date**: 2025-12-03 10:30  
**Session ID**: `P3_d4e9`  
**Consent**: ✅ Verbal consent confirmed  
**Duration**: 22 minutes

---

### Task T1: Filter Tasks

**Start**: 10:32:45 → **End**: 10:33:52 = **67 seconds**  
**Success**: 1  
**Errors**: 0  
**Confidence**: 4/5  
**Observations**:

- [KBD] Tab order reached filter input after 4 Tab presses
- Typed "meet" successfully
- Results updated automatically
- [KBD] Focus remained in input after filtering—good
- Counted results visually (2 tasks)

---

### Task T2: Edit Task Title

**Start**: 10:35:00 → **End**: 10:36:48 = **108 seconds**  
**Success**: 1  
**Errors**: 0  
**Confidence**: 3/5  
**Observations**:

- [KBD] Tabbed through task list to find "draft report"
- Tab order: checkbox → title → Edit → Delete for each task
- Pressed Enter on Edit button
- [FOC] Focus moved to input field—good
- Changed title, pressed Enter to save
- [FOC] After save, focus returned to the task item—good
- Said "I wasn't sure if Enter would save or cancel"

---

### Task T3: Add New Task

**Start**: 10:38:10 → **End**: 10:38:45 = **35 seconds**  
**Success**: 1  
**Errors**: 0  
**Confidence**: 5/5  
**Observations**:

- [KBD] Tabbed to Add Task input
- Typed title
- Pressed Enter
- Task added successfully
- [FOC] Focus stayed in input for adding another task

---

### Task T4: Delete Task

**Start**: 10:40:00 → **End**: 10:40:55 = **55 seconds**  
**Success**: 1  
**Errors**: 0  
**Confidence**: 4/5  
**Observations**:

- [KBD] Tabbed to "tmp" task's Delete button
- Pressed Enter
- [KBD] Browser confirm dialog appeared, pressed Enter to confirm
- Task removed
- [FOC] Focus moved to next task in list—acceptable but slightly disorienting

---

### Debrief Notes

- **Most difficult task**: "Editing took a while because I had to Tab through every task to find the right one"
- **What worked well**: "Tab order is logical, focus indicators are visible"
- **Uncertainty moments**: "After deleting, I wasn't sure where focus went—maybe announce what happened?"

### Accessibility Notes

- [KBD] All tasks completable with keyboard only ✅
- [KBD] Focus indicators visible throughout ✅
- [FOC] Focus management after delete could be improved—focus jumps to next item without announcement
- [KBD] No keyboard traps detected ✅

---

## Participant P4

**Mode**: No-JS (JavaScript disabled in browser)  
**Date**: 2025-12-03 14:00  
**Session ID**: `P4_f5a2`  
**Consent**: ✅ Verbal consent confirmed  
**Duration**: 25 minutes

---

### Task T1: Filter Tasks

**Start**: 14:02:30 → **End**: 14:03:58 = **88 seconds**  
**Success**: 1  
**Errors**: 0  
**Confidence**: 3/5  
**Observations**:

- Typed "meet" in filter box
- Pressed Enter—full page reload occurred
- Results showed 2 tasks
- Said "It reloaded the whole page... did it work?"
- Checked URL, saw `?q=meet` parameter
- Confidence lower due to full page reload

---

### Task T2: Edit Task Title

**Start**: 14:05:20 → **End**: 14:07:45 = **145 seconds**  
**Success**: 1  
**Errors**: 1 (blank submission)  
**Confidence**: 2/5  
**Observations**:

- Clicked Edit on "draft report"
- Full page reload to edit form
- [NOJS] Accidentally submitted blank—page reloaded with error message
- Error message visible at top: "Title is required"
- [ERR] Error message not focusable, had to scroll up to find it
- Re-entered title, submitted
- Full page reload back to task list
- Said "I'm not sure if it saved... let me scroll down and check"
- Found task with new title, confirmed success

---

### Task T3: Add New Task

**Start**: 14:09:00 → **End**: 14:10:12 = **72 seconds**  
**Success**: 1  
**Errors**: 0  
**Confidence**: 3/5  
**Observations**:

- Typed title in add form
- Clicked Add button
- Full page reload
- [NOJS] No success message displayed after redirect
- Had to scroll through list to verify task was added
- Said "I think it worked but there's no confirmation"

---

### Task T4: Delete Task

**Start**: 14:11:30 → **End**: 14:12:25 = **55 seconds**  
**Success**: 1  
**Errors**: 0  
**Confidence**: 4/5  
**Observations**:

- Found "tmp" task
- Clicked Delete button
- [NOJS] No confirmation dialog—task deleted immediately
- Full page reload
- Said "Whoa, that just deleted it without asking!"
- Verified task was gone from list

---

### Debrief Notes

- **Most difficult task**: "Editing was confusing—the error message was far from the form, and I wasn't sure the save worked"
- **What worked well**: "Filtering worked, just slower with page reloads"
- **Uncertainty moments**: "After adding a task, there's no message saying it was added. I had to scroll and look for it. Also, delete has no confirmation which is scary."

### Accessibility Notes

- [NOJS] All tasks functional without JavaScript ✅
- [NOJS] Times significantly slower due to full page reloads (expected trade-off)
- [NOJS] **Issue**: No success feedback after adding task (PRG redirect shows nothing)
- [NOJS] **Issue**: Error message in edit flow not keyboard-focusable
- [NOJS] **Issue**: Delete has no confirmation (documented trade-off from wk8-01)

---

## Participant P5

**Mode**: HTMX (standard, mouse + keyboard)  
**Date**: 2025-12-04 11:00  
**Session ID**: `P5_c7d1`  
**Consent**: ✅ Verbal consent confirmed  
**Duration**: 15 minutes

---

### Task T1: Filter Tasks

**Start**: 11:02:15 → **End**: 11:02:48 = **33 seconds**  
**Success**: 1  
**Errors**: 0  
**Confidence**: 5/5  
**Observations**:

- Noticed filter input immediately
- Typed "meet"
- Read status message "Found 2 tasks"
- Said "Got it, there are 2"

---

### Task T2: Edit Task Title

**Start**: 11:03:30 → **End**: 11:04:08 = **38 seconds**  
**Success**: 1  
**Errors**: 0  
**Confidence**: 5/5  
**Observations**:

- Clicked Edit on "draft report"
- Changed to "submit report"
- Clicked Save
- Saw status message "Task updated successfully"
- Said "Done, I can see the message"

---

### Task T3: Add New Task

**Start**: 11:05:00 → **End**: 11:05:15 = **15 seconds**  
**Success**: 1  
**Errors**: 0  
**Confidence**: 5/5  
**Observations**:

- Typed title quickly
- Pressed Enter
- Task appeared
- Said "Super fast"

---

### Task T4: Delete Task

**Start**: 11:06:00 → **End**: 11:06:12 = **12 seconds**  
**Success**: 1  
**Errors**: 0  
**Confidence**: 5/5  
**Observations**:

- Found and deleted "tmp" task
- Confirmed in dialog
- Task removed

---

### Debrief Notes

- **Most difficult task**: "None really, everything was intuitive"
- **What worked well**: "The instant updates are great, I always knew what was happening"
- **Uncertainty moments**: "None"

### Accessibility Notes

- N/A (standard session)

---

## Summary Statistics

| Participant | Mode    | T1 Time | T2 Time | T3 Time | T4 Time | Total Errors | Mean Confidence |
| ----------- | ------- | ------- | ------- | ------- | ------- | ------------ | --------------- |
| P1          | HTMX    | 42s     | 47s     | 18s     | 15s     | 0            | 4.5             |
| P2          | HTMX    | 75s     | 87s     | 25s     | 22s     | 1            | 4.0             |
| P3          | KB-only | 67s     | 108s    | 35s     | 55s     | 0            | 4.0             |
| P4          | No-JS   | 88s     | 145s    | 72s     | 55s     | 1            | 3.0             |
| P5          | HTMX    | 33s     | 38s     | 15s     | 12s     | 0            | 5.0             |

**Overall Completion Rate**: 100% (20/20 tasks completed successfully)  
**Overall Error Rate**: 10% (2 validation errors across 20 task attempts)  
**Mean Confidence**: 4.1/5
