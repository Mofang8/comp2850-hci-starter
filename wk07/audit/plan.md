# Week 7 Audit Plan

## Scope

- **Application**: COMP2850 task manager (`/tasks` feature)
- **Pages / routes**:
  - `GET /tasks` — list + add form + inline edit
  - `GET /tasks/{id}/edit` — full-page edit (no‑JS path)
  - `POST /tasks/{id}/edit` — save with validation
- **Components**:
  - Add-task form (title + priority)
  - Task list items (view + inline edit modes)
  - Status / feedback messages (live region)

## Assistive Technologies & Modes

- **Keyboard only**:
  - Tab / Shift+Tab through all focusable elements
  - Activate buttons/links via Enter / Space
- **Screen reader (VoiceOver on macOS)**:
  - Navigate by headings, landmarks, form controls
  - Verify labels, hints, errors, status messages are announced
- **No‑JS mode**:
  - Disable JavaScript in browser
  - Verify add / edit / delete flows still work (full page reloads)
- **Zoom / viewport** (optional if time):
  - Browser zoom at 200%+ to check layout and wrapping

## Criteria

- **WCAG 2.2 A / AA** (sample focus):
  - 1.3.1 Info and Relationships
  - 1.4.3 Contrast (Minimum)
  - 2.1.1 Keyboard
  - 2.4.1 Bypass Blocks
  - 2.4.3 Focus Order
  - 2.4.7 Focus Visible
  - 3.3.1 Error Identification
  - 3.3.2 Labels or Instructions
  - 4.1.2 Name, Role, Value
  - 4.1.3 Status Messages
- **Heuristics**:
  - Nielsen’s 10 usability heuristics
  - Shneiderman’s Golden Rules (as supporting lens)

## Evidence Capture Plan

- **Automated tools**:
  - Run axe DevTools on `http://localhost:8080/tasks`
  - Save findings to `wk07/audit/axe-report.md`
- **Manual WCAG checks**:
  - Record results in `wk07/audit/wcag-checklist.md`
  - Include Pass/Fail/N/A, evidence, and notes
- **Heuristic evaluation**:
  - Document in `wk07/audit/heuristics.md`
  - For each issue, link to screenshot or observed behaviour
- **Screenshots / assets** (no PII):
  - Store in `wk07/evidence/`
  - Use descriptive names, e.g.:
    - `contrast-before.png`, `contrast-after.png`
    - `axe-after.png`
    - `focus-outline.png`

## Backlog Integration

- Use findings from:
  - `axe-report.md`
  - `wcag-checklist.md`
  - `heuristics.md`
- Update `wk06/backlog/backlog.csv` with:
  - Severity (High/Medium/Low)
  - Inclusion risk tags (SR, Keyboard, Cognitive, etc.)
  - Evidence path (e.g., `wk07/audit/axe-report.md#Issue2`)
  - `candidate_fix=true` for 1–2 high‑priority items to tackle in Lab 2


