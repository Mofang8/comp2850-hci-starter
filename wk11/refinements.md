# Post-Crit Refinements (Week 11)

## 1) Session consistency for metrics

- **Issue**: Routes sometimes logged `anon` because session cookie wasn’t ensured.
- **Fix**: Added `ensureSession()` and used it in timing/logging and routes.
- **Evidence**: `utils/SessionUtils.kt`, `utils/Timing.kt`, `TaskRoutes.kt` (add/edit).
- **Status**: Done.

## 2) Delete focus return (HTMX path)

- **Issue**: After delete, focus could land unpredictably.
- **Fix**: HTMX delete response now returns status + script to focus `#title`.
- **Evidence**: `TaskRoutes.kt` delete response; manual test (HTMX).
- **Status**: Done (HTMX). No-JS confirm still pending (backlog wk8-01).

## 3) Toggle parity (HTMX + no-JS)

- **Issue**: README promised completion toggle; not implemented.
- **Fix**: Added `/tasks/{id}/toggle`, completed flag in CSV, UI buttons, success banners for both modes.
- **Evidence**: `TaskRepository.kt`, `TaskRoutes.kt`, `tasks/partials/view.peb`, `tasks/index.peb`.
- **Status**: Done.

## 4) Status visibility and messaging

- **Issue**: Status OOB messages lacked consistent success styling.
- **Fix**: Mark success class on delete/toggle responses; ensured banners for completed/reopened.
- **Evidence**: `TaskRoutes.kt`, `tasks/index.peb`.
- **Status**: Done.

## Pending from action plan

- VoiceOver coverage (wk11-01) — pending.
- Focus contrast 3:1 (wk11-02) — pending.
- T2 residual errors (wk11-03) — pending.
- No-JS delete confirmation (ties to wk8-01) — pending.
