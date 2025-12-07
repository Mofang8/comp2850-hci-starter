# Evidence Directory — Week 10 Assessment

This directory contains screenshots and evidence supporting the Week 10 redesign.

## Structure

```
05-evidence/
├── README.md                    # This file
├── screenshots/
│   ├── annotations.md           # Alt text and context for each screenshot
│   ├── before-nojs-add.png      # Before: No-JS add task (no confirmation)
│   ├── after-nojs-add.png       # After: No-JS add task (success message)
│   ├── before-nojs-error.png    # Before: Error not focused
│   ├── after-nojs-error.png     # After: Error summary focused
│   ├── before-status-style.png  # Before: Subtle status message
│   ├── after-status-style.png   # After: Prominent green success
│   └── axe-after-scan.png       # axe DevTools scan (0 violations)
```

## Screenshot Requirements

When capturing screenshots:

1. **Resolution**: Minimum 1280×720 pixels
2. **Format**: PNG (lossless)
3. **Crop**: Remove browser chrome except when showing DevTools
4. **Annotations**: Add arrows/boxes to highlight changes (optional)
5. **No PII**: Ensure no personal information visible

## Screenshot Instructions

### before-nojs-add.png

1. Disable JavaScript (DevTools → Settings → Disable JavaScript)
2. Add a task
3. Capture the page after redirect (no success message visible)

### after-nojs-add.png

1. Disable JavaScript
2. Add a task
3. Capture the page showing "Task added successfully." message

### before-nojs-error.png

1. Disable JavaScript
2. Submit blank title
3. Capture the page (error at top, focus not on it)

### after-nojs-error.png

1. Disable JavaScript
2. Submit blank title
3. Capture the page with error summary focused (visible outline)

### before-status-style.png

1. Enable JavaScript
2. Edit a task successfully
3. Capture the subtle blue status message

### after-status-style.png

1. Enable JavaScript
2. Edit a task successfully
3. Capture the prominent green success message

### axe-after-scan.png

1. Open axe DevTools
2. Run scan on /tasks page
3. Capture results showing 0 critical/serious violations

## Annotations Template

For `annotations.md`:

```markdown
## before-nojs-add.png

**Alt text**: "Task list page after adding a task in No-JS mode. The task 'Test task' appears in the list but no success confirmation message is displayed."
**Context**: Week 9 baseline. User had to scroll through list to verify task was added.

## after-nojs-add.png

**Alt text**: "Task list page with green success banner reading 'Task added successfully.' displayed at the top of the page after adding a task in No-JS mode."
**Context**: Week 10 fix. Explicit confirmation eliminates uncertainty.
```

---

_Note: Populate this directory with actual screenshots before submission._
