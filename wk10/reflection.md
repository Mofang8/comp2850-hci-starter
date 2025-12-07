# Week 10 Reflection

**Module**: COMP2850 Human-Computer Interaction  
**Student**: [Tianqi Wang]  
**Date**: 2025-12-04

---

## 1. Data Interpretation

### What surprised you most in the numbers?

The most surprising finding was the magnitude of the confidence gap between No-JS and HTMX users. P4 (No-JS) had a mean confidence of 3.0/5 compared to 4.6/5 for HTMX users—a gap of 1.6 points. I expected No-JS to be slower (which it was, 2-4× slower), but I didn't anticipate such a dramatic difference in user confidence.

The T2 (Edit) task data was particularly revealing: 80% of participants expressed uncertainty about whether their edit was saved, even though the success rate was 100%. This showed that task completion alone doesn't measure user experience—confidence and perceived feedback are equally important.

### Were any findings unexpected?

Yes, I was surprised that P5 was the only participant who noticed the status message. The status message was present and had `role="status"`, but it was too subtle visually. This highlighted that WCAG compliance (having the right ARIA roles) is necessary but not sufficient—usability requires perceptual prominence too.

---

## 2. Inclusion Lens

### How did the prioritisation framework change which issues you focused on?

Without the Inclusion dimension, I would have prioritised issues purely by frequency and impact. The (Impact + Inclusion) – Effort framework elevated wk9-03 (error not focusable) from medium to high priority because it disproportionately affected keyboard and screen reader users.

For example, wk9-04 (filter discoverability) affected 20% of participants, while wk9-03 affected 100% of No-JS users who triggered errors. Traditional prioritisation might rank them similarly, but the Inclusion dimension correctly identified wk9-03 as more critical for equity.

### Would you have chosen differently without the Inclusion dimension?

Yes. Without Inclusion, I might have focused on wk9-04 first because it affected a mainstream user (P2, HTMX mode) rather than a minority mode (No-JS). The framework reminded me that inclusive design means prioritising barriers that exclude specific user groups, not just optimising for the majority.

---

## 3. Trade-offs

### Which issues did you deprioritise and why?

I deferred two issues to the backlog:

1. **wk9-04 (Filter discoverability)**: Score 4, affected 1/5 participants. The fix (adding placeholder text) is trivial, but the impact is low since most users found the filter quickly. Acceptable to defer.

2. **wk9-05 (Focus after delete)**: Score 2, low impact (task still completed), moderate effort (requires focus management logic). The disorientation was minor and only affected keyboard-only users in a specific scenario.

I also accepted **wk8-01 (No-JS delete confirmation)** as a documented trade-off. Implementing confirmation would require a separate page (`/tasks/{id}/delete/confirm`), adding significant complexity. The current behavior (immediate delete with success message) is acceptable because the outcome is immediately visible.

### How comfortable are you with those decisions?

Reasonably comfortable. The deferred issues don't block task completion, and the priority issues (wk9-01, wk9-02, wk9-03) had the highest equity impact. However, I would want to revisit wk9-05 in Semester 2, as focus management is important for keyboard users navigating long lists.

---

## 4. Redesign Confidence

### How confident are you that the proposed fix will achieve ≥90% improvement?

Moderately confident (70-80%). The fixes directly address the root causes:
- wk9-01: No feedback → explicit success message
- wk9-02: Subtle styling → prominent green banner
- wk9-03: Focus at form → auto-focus on error

The logic is sound, and verification testing confirmed the changes work as expected. However, with n=1 for No-JS in Week 9, I can't be statistically certain the improvements will generalise. Ideally, I would conduct 2-3 additional No-JS pilots to validate.

### What uncertainties remain?

1. **Query parameter visibility**: The URL shows `?msg=task_added` after success. Some users might find this ugly or confusing. I accepted this trade-off for stateless feedback.

2. **Auto-focus script reliability**: If JavaScript fails to load after the HTML renders, the error summary won't be focused. However, `tabindex="-1"` ensures it's still Tab-navigable.

3. **Screen reader announcement timing**: I haven't tested with multiple SR configurations. NVDA announced correctly, but VoiceOver/JAWS behavior may differ.

---

## 5. Evidence Chains

### Can you trace wk9-01 from raw metrics → analysis → prioritisation → redesign-brief?

Yes, the evidence chain is complete:

1. **Raw data**: `data/metrics.csv` → P4_f5a2, T3_add, success, 2134ms, js_mode=off
2. **Pilot notes**: `wk09/data/pilot-notes.md` L331-334 → "I think it worked but there's no confirmation"
3. **Analysis**: `wk10/analysis/quantitative-summary.md` → T3 No-JS confidence 3.0/5 vs 5.0/5 JS-on
4. **Themes**: `wk10/analysis/qualitative-themes.md` → Theme 1: Success Feedback Insufficient
5. **Prioritisation**: `wk10/analysis/prioritisation.csv` → wk9-01, score=8, candidate_fix=true
6. **Redesign brief**: `wk10/redesign/redesign-brief.md` → Change 1: Add Success Message
7. **Implementation**: `TaskRoutes.kt` L234-237 → `/tasks?msg=task_added`
8. **Verification**: `wk10/evidence/reverification.md` → No-JS add shows success message

### Is anything missing?

The chain is complete for documentation. The only gap is quantitative verification data—I haven't re-run formal pilots with metrics collection after the fix. The verification was manual testing rather than instrumented measurement.

---

## 6. Week 10 Lab 2 Readiness

### What could go wrong during implementation?

1. **Regression in HTMX mode**: Changing the redirect URL could break something if HTMX requests also hit the No-JS path. Mitigated by checking `call.isHtmxRequest()` before redirect.

2. **CSS specificity conflicts**: New `.success-message` styles could conflict with existing rules. Mitigated by using specific selectors and testing both modes.

3. **Template syntax errors**: Pebble template conditionals (`{% if msg == "task_added" %}`) could fail if msg is undefined. Mitigated by using `{% if msg is not empty %}` guard.

### How will you mitigate?

- Test both HTMX and No-JS paths after every change
- Use browser DevTools to verify CSS applied correctly
- Check Ktor logs for any template rendering errors
- Run axe DevTools to catch any accessibility regressions

---

## Key Learnings

1. **Confidence matters as much as completion**: 100% success rate doesn't mean good UX if users are uncertain about outcomes.

2. **Inclusion prioritisation changes decisions**: Without the Inclusion dimension, I would have missed that No-JS barriers are more critical than mainstream annoyances.

3. **Server-first simplifies accessibility**: The fix was ~45 lines across 3 files. A client-side SPA would require complex state management and ARIA handling.

4. **Evidence chains enable traceability**: Being able to trace wk9-01 from raw data to implementation gives confidence that the fix addresses the actual problem.

5. **Trade-offs are acceptable when documented**: Accepting wk8-01 (no delete confirmation) is fine because it's documented, low risk, and the alternative is high effort.

---

_Completed: 2025-12-04_

