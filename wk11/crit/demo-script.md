# Live Demo Script — Week 11 Studio Crit

**Timing**: ~5 minutes

---

## Setup (30s)

- Open two tabs: JS on (HTMX), JS off (DevTools → Disable JavaScript).
- Navigate to `/tasks`.
- Narrate that demo covers HTMX first, then no-JS, then success path.

---

## Demo 1: HTMX Error Handling (edit) — 2 min

1. Click “Edit” on an existing task.
2. Clear the title, click “Save”.
3. Observe inline error with `role=alert` + `aria-live=assertive`.
4. Show Elements panel to highlight the live region.
5. (Optional) Run NVDA/Orca/VoiceOver to hear: “Alert. Title is required…”.
6. Fix the title, click “Save” → status banner (`role=status`, polite).
7. Point out OOB swap keeps list + pager intact.

Evidence: `tasks/partials/edit.peb`, `custom.css` status styles, metrics `T2_edit`.

---

## Demo 2: No-JS Error Handling (add) — 2 min

1. In no-JS tab, clear title and submit.
2. Page reloads; focus lands on error summary (`tabindex="-1"`).
3. Press Tab → focus on error link; Enter → focus on `#title`.
4. Type a valid title, submit → success banner (PRG).

Evidence: `tasks/index.peb` error summary, `TaskRoutes.kt` PRG, backlog wk9-03 fixed.

---

## Demo 3: Toggle Completion (parity) — 45s

1. In HTMX tab, click “Mark done” → row updates in place, status banner updates.
2. In no-JS tab, click “Mark done” → PRG with success banner.
3. Repeat “Mark to do” to show bidirectional parity.

Evidence: `TaskRoutes.kt` `/tasks/{id}/toggle`, `view.peb` button, CSV completed flag.

---

## Demo 4: Delete Focus Return (HTMX) — 30s

1. Click Delete (HTMX).
2. After removal, note status banner + focus returns to “Add task” input.
3. Mention no-JS delete still lacks confirmation (logged backlog).

Evidence: `TaskRoutes.kt` delete HTMX script, backlog wk8-01, wk11-03 outstanding.

---

## Backup Plan (30s)

- If live SR fails: use prepared transcript + screenshot of status region.
- If HTMX fails: show no-JS flow only; explain parity intent.

---

## Close (15s)

- Restate improvements: status clarity, parity, toggle, focus return.
- Call out open items: VoiceOver testing, focus contrast, delete confirm, T2 residual errors.
