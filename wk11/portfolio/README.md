# COMP2850 HCI Portfolio — Week 6–11

## Overview

- Server-first task manager (Ktor + Pebble) with HTMX enhancement and full no-JS parity.
- Focus: WCAG 2.2 AA, inclusive feedback loops, evidence-led redesign.

## Week-by-Week Highlights

- **Week 6**: Baseline CRUD, HTMX intro, inclusive backlog from interviews.
- **Week 7**: Inline edit, validation messaging, initial a11y audit.
- **Week 8**: Pagination/search, partials for list/pager, parity cleanup.
- **Week 9**: Pilots (5), findings, backlog (wk9-01..05), evidence chains.
- **Week 10**: Redesign + re-verification; PRG success banners; error focus fix.
- **Week 11**: Crit, action plan, session consistency, toggle parity, delete focus return.

## Key Artefacts

- Code: `TaskRoutes.kt`, `TaskRepository.kt`, `tasks/*.peb`, `utils/*.kt`.
- Backlog: `backlog/backlog.csv` (wk6–wk11 evolution).
- Evidence: `wk09/analysis/`, `wk10/assessment/`, `wk11/crit/demo-script.md`, `wk11/refinements.md`.
- A11y references: `references/assistive-testing-checklist.md`, `custom.css`.

## Reflection (short)

- Biggest gain: treating HTMX/no-JS as equal citizens improved reliability and inclusion.
- Persistent gap: VoiceOver coverage + focus contrast; delete confirm in no-JS.
- Process lesson: run micro-pilots after each UX change; keep parity checklist per route.

## Future Enhancements (Sem 2)

- Add no-JS delete confirmation page or undo pattern.
- Raise focus contrast to ≥3:1 and re-verify.
- Cross-SR parity (VoiceOver/NVDA/Orca) with transcripts.
- Automated no-JS regression (Playwright).
