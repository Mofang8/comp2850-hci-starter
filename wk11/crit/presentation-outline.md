# Studio Crit Presentation Outline

## Slide 1: Overview (30s)

**What I built**: Server-first task manager (Ktor + Pebble) with HTMX enhancement and no-JS parity.  
**Key features**: Add, edit, delete, toggle complete; search/pagination; WCAG 2.2 AA focus on status/error handling.  
**Architecture**: Server-rendered baseline, HTMX fragments, CSV storage, anonymous sessions/metrics.

---

## Slide 2: Needs-Finding (Week 6) (1m)

**Process**: Peer interviews → 5 job stories → inclusive backlog.  
**Example insight**: “When I submit a form, I want confirmation it worked so I don’t re-submit” (4/5 participants).  
**Backlog link**: `backlog/backlog.csv` (wk8-01, wk9-01, wk9-03).

---

## Slide 3: Implementation Journey (Weeks 7-8) (1.5m)

**Week 7**: Inline edit, validation messaging, initial a11y audit.  
**Week 8**: Pagination/search, template partials, HTMX vs no-JS parity.  
**Key challenge**: Keep fragments and full-page rendering consistent (IDs, live regions, PRG).

---

## Slide 4: Evaluation (Week 9) (1.5m)

**Method**: 5 pilots (3 HTMX, 2 no-JS).  
**Quant**: T3 add success 100% (HTMX/no-JS); T2 edit 67%→? still 25% errors remain.  
**Qual**: “Did it save? I don’t hear status” (2 participants).  
**A11y**: Error summary focus missing (WCAG 3.3.1/2.4.3).

---

## Slide 5: Redesign (Week 10) (1.5m)

**Fixes**:

1. No-JS success banners (wk9-01)
2. Error summary focus + links (wk9-03)
3. Status visibility for edit (wk9-02)  
   **Before/After**: Status styling, error summary focus, PRG success.

---

## Slide 6: Week 11 & Questions (1m)

**New in wk11**: Session consistency for metrics; delete focus return; toggle parity; backlog updates.  
**Known gaps**: VoiceOver coverage, focus contrast 3:1, T2 residual errors, no-JS delete confirmation.  
**Ask peers**: Have you balanced `aria-live` politeness vs. speed for status? Any no-JS confirm patterns you prefer?

---

**Total**: ~7 minutes (+Q&A)

**Artifacts to show**: screenshots (before/after), metrics table, SR transcript, code diff for status/error handling.
