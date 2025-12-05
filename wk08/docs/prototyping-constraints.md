# Prototyping Constraints & Trade-offs — Week 8

## Rendering splits

- **Full page**: `/tasks` returns layout + add form + filter form + list + pager.
- **Fragment**: `/tasks/fragment` returns list + pager + OOB status only.

**Benefits**:

- Baseline HTML works with JS disabled.
- HTMX path avoids re-sending layout and chrome, saving bandwidth.

**Costs / Risks**:

- Need to keep full-page and fragment responses in sync.
- If `_list` or `_pager` diverge between routes, parity breaks.

---

## Dual-Path Architecture

### Design Decision

Every route that changes task state handles both **HTMX (enhanced)** and **no-JS (baseline)** requests:

- Detect HTMX with `HX-Request: true` (`call.isHtmxRequest()`).
- HTMX path returns HTML fragments + out-of-band status.
- No-JS path uses POST-Redirect-GET to full pages.

### Benefits

- **Inclusion**: Works for people regardless of JavaScript availability.
- **Resilience**: If HTMX fails to load, baseline still works.
- **State in URL**: Filter (`q`) and `page` are query params for both paths.
- **Testing**: Easy to verify parity with `curl` or by disabling JS.

### Costs

- **Extra branching**: `if (call.isHtmxRequest())` in several routes.
- **More templates to think about**: partials + full page.
- **Testing burden**: Each feature must be verified twice (JS on/off).

### Risks

- **Drift**: HTMX fragment route and full-page route could start behaving differently.
- **Error handling blind spot**: Easy to fix HTMX error states and forget no-JS.

### Mitigations

- ✅ Shared partials: `_list.peb` and `_pager.peb` are used for both `/tasks` and `/tasks/fragment`.
- ✅ Central `renderTemplate` helper always injects `sessionId` and `isHtmx`.
- ✅ No-JS script `wk08/lab-w8/scripts/nojs-check.md` documents how to re-test parity.
- ✅ Explicit backlog items for parity issues (see `backlog/backlog.csv`).

---

## Validation Strategy

### Design Decision

All validation for creating tasks happens on the **server**, not in the browser:

- `POST /tasks` reads and trims `title`.
- Rejects blank titles.
- Rejects titles longer than 200 characters.

### Benefits

- **Security**: Client-side checks can be bypassed; server checks cannot.
- **Consistency**: Same rules apply for HTMX and no-JS flows.
- **Accessibility**: Server can return tailored responses:
  - HTMX: OOB status message inside `#status` live region.
  - No-JS: Full-page reload with error summary and inline error text.

### Costs

- Extra round-trip to see validation errors (slower than instant JS checks).
- People may not see errors until after page reload.

### Risks

- **Frustration** if error messages are vague.
- **Network dependency**: No feedback when offline.

### Mitigations

- ✅ Clear, specific messages: e.g., `Title is required. Please enter at least one character.`
- ✅ Accessible error identification:
  - Error summary with `role="alert"` and link to `#title`.
  - `aria-invalid="true"` and `aria-describedby="title-hint title-error"` on the field.
  - Inline error text under the field.
- 🔮 Future enhancement: optional client-side hints (e.g., live character count) as progressive enhancement, not a replacement.

---

## Delete Confirmation

### Design Decision

- HTMX-enhanced delete uses `hx-confirm` on the Delete button.
- No-JS fallback uses a standard `<form method="post" action="/tasks/{id}/delete">` with **no extra confirmation page**.

### Benefits

- **HTMX**: Reduces accidental deletions with a simple confirmation dialog.
- **No-JS**: Implementation stays simple, no extra route or template needed.

### Costs

- **Inconsistent UX**: JS users see a confirmation dialog; no-JS users do not.
- **Limited control**: Browser confirm dialogs are not fully customisable.

### Risks

- **Accidental deletes** in no-JS mode (mis-clicks cannot be undone).

### Mitigations

- ✅ Clearly labelled delete buttons (`aria-label="Delete task: {{ task.title }}"`).
- ✅ Status message after delete in both modes (`Task deleted.` via live region / full page).
- ✅ Backlog item for exploring a dedicated confirmation page (`wk8-01`).
- 🔮 Possible future enhancement: soft delete + undo window instead of hard delete.

---

## State Management (Filter + Pagination)

### Design Decision

Use query parameters for state:

- `q` for search query.
- `page` for current page (1-based).

### Benefits

- **Shareable URLs**: Filtered + paged views can be bookmarked or shared.
- **Predictable history**: Back/forward navigates through prior filter/page states.
- **Stateless server**: No session storage for list state.

### Costs

- Slightly more complex URLs.
- Need to validate and clamp `page` to valid bounds.

### Risks

- **Invalid page numbers** (e.g., `page=9999`) could lead to empty pages.

### Mitigations

- ✅ `TaskRepository.search` clamps `page` into `[1, totalPages]`.
- ✅ `Page` helper exposes `hasPrevious`, `hasNext`, `previousPage`, `nextPage`.
- ✅ `_pager.peb` hides `Previous`/`Next` links when not applicable.

---

## Accessibility Hooks

### Design Decision

- Global live region `#status` in the header with `role="status"` and `aria-live="polite"`.
- Result count associated with list via `aria-describedby` / screen-reader-only text.
- Error states announced via `role="alert"` where appropriate.

### Benefits

- Screen reader users are informed when lists update (filter, paginate, add, edit, delete).
- Validation errors are clearly identified and associated with the offending field.

### Costs

- Need to be careful that HTMX out-of-band updates do not strip ARIA attributes.

### Risks

- Replacing `#status` via HTMX could remove `role` / `aria-live` if not re-added.

### Mitigations

- ✅ All HTMX status snippets now include `role="status"` or `role="alert"` with proper `aria-live`.
- ✅ Result count lives in `_list.peb` so both full-page and fragment updates share the same text.

---

## Performance Notes

### Page Size

- **Decision**: 10 tasks per page.
- **Benefit**: Keeps pages small and reduces scrolling.
- **Trade-off**: More paging for people with many tasks.

### Fragments vs Full Page

- **Decision**: Use `/tasks/fragment` for HTMX filter/paging.
- **Benefit**: Only list + pager + status are re-rendered.
- **Trade-off**: Slightly more complexity in routing and templates.

### Active Search Debounce

- **Decision**: 300ms debounce on `keyup` for HTMX filter.
- **Benefit**: Reduces server load and flicker.
- **Trade-off**: 300ms perceived delay before results change.

---

## Known Trade-offs to Revisit

- `wk8-01`: No-JS delete has no confirmation; accept for now, but consider a confirmation page or undo pattern.
- `wk8-02`: Filter feedback in no-JS mode is only via full page reload and result text; could add more visual emphasis around the result count.

These are recorded in `backlog/backlog.csv` and should be revisited before a production deployment or summative assessment.
