# Self-Reflection (≈400–600 words)

## What changed and why

- Targeted problems: missing/noisy status feedback, inaccessible error focus, no parity for success banners, incomplete toggle.
- Redesign: added clear status banners (HTMX + PRG), focused error summary, toggle parity with CSV persistence, session consistency for metrics, and delete focus return for keyboard users.

## Evidence of impact

- Before: T2 edit success 67% overall, 0% in no-JS; pilots reported “did it save?” and could not focus error summary.
- After: No-JS add/edit show banners; error summary autofocus; HTMX status more visible. T2 errors still ~25%, so not fully resolved (logged wk11-03).
- Inclusion impact: keyboard and screen reader users get explicit status/error cues; no-JS path regains confidence via PRG banners.

## Inclusion

- Beneficiaries: keyboard users, SR users, JS-off users.
- Trade-offs: No-JS delete still lacks confirmation (risk of accidental delete, documented in wk8-01); aria-live assertive kept for errors, polite for status—will reassess with VoiceOver.
- Remaining risks: Focus contrast slightly under 3:1; T2 residual errors need root-cause analysis; VoiceOver parity unverified.

## Process

- Worked well: dual-path thinking (HTMX + PRG) early; backlog-driven fixes; live SR/error demos.
- To change: run contrast/SR checks earlier; add small pilots after each UX change; keep parity checklists per route.
- Collaboration: peer crit surfaced SR/contrast gaps we overlooked.

## References

- Code: `TaskRoutes.kt`, `TaskRepository.kt`, `tasks/index.peb`, `tasks/partials/view.peb`, `utils/SessionUtils.kt`.
- Evidence: `wk11/crit/demo-script.md`, `wk11/refinements.md`, `wk11/crit/feedback-received.md`.
- Backlog links: `backlog/backlog.csv` (wk11-01..03; wk8-01).
