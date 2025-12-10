# Assessment Draft — Week 9 (Task 1)

**Module**: COMP2850 Human-Computer Interaction  
**Study Period**: 2025-11-17 to 2025-11-22  
**Author**: Student Researcher

---

## Section 1: Evaluation Plan (≈15%)

### 1.1 Test Tasks (≈5%)

The evaluation comprised four task-based scenarios designed to assess critical user flows identified in the Week 6-8 backlog:

| Task | Code        | Scenario                                                | Time Limit |
| ---- | ----------- | ------------------------------------------------------- | ---------- |
| T1   | `T1_filter` | "Find all tasks containing 'meet' and report the count" | 120s       |
| T2   | `T2_edit`   | "Change 'draft report' to 'submit report'"              | 90s        |
| T3   | `T3_add`    | "Add a new task: Meet supervisor about project"         | 60s        |
| T4   | `T4_delete` | "Delete the task called 'tmp'"                          | 45s        |

Tasks were designed to be:

- **Realistic**: Scenarios mirror actual student task management activities
- **Measurable**: Clear success criteria (correct count, changed title, task added/removed)
- **Representative**: Cover CRUD operations and the filter feature
- **Inclusive**: Tested across HTMX, keyboard-only, and No-JS modes

Full task definitions with success criteria and accessibility checks are documented in `wk09/lab-wk9/research/tasks.md`.

### 1.2 Metrics (≈5%)

**Objective Metrics**:

- **Completion rate**: Binary success (1) or failure (0) per task
- **Time-on-task**: Server-measured milliseconds from request start to completion
- **Error rate**: Validation errors triggered / total attempts
- **Error count**: Number of errors per participant per task

**Subjective Metrics**:

- **Confidence rating**: Post-task question "On a scale 1-5, how confident are you that you completed correctly?"
- **Qualitative observations**: Facilitator notes on hesitations, verbalizations, and accessibility barriers

**Rationale**: Objective metrics quantify efficiency and accuracy, while subjective metrics capture user experience and confidence. Together they address ISO 9241-11's three usability dimensions: effectiveness, efficiency, and satisfaction.

Full metric definitions with calculation formulas are documented in `wk09/lab-wk9/research/measures.md`.

### 1.3 Protocol (≈5%)

The evaluation followed a low-risk peer pilot protocol:

**Participants**: 5 peers from COMP2850 module

- 3 × Standard (HTMX with mouse)
- 1 × Keyboard-only (JS enabled)
- 1 × No-JS (JavaScript disabled)

**Privacy measures**:

- Anonymous session IDs (e.g., `P1_7a9f`) — no names, emails, or student IDs collected
- No audio/video recording
- Verbal consent obtained before each session
- Participants informed of opt-out rights and data deletion procedures

**Session flow** (~20 minutes each):

1. Consent process (2 min)
2. Warm-up: Browse interface (2 min)
3. Task execution: T3 → T1 → T2 → T4 (12 min)
4. Debrief: Open questions (3 min)

Full protocol with facilitator guidelines is documented in `wk09/lab-wk9/research/protocol.md`.

---

## Section 2: Findings (≈40%)

### 2.1 Quantitative Results (≈15%)

**Overall Performance**:

- **Completion rate**: 100% (20/20 tasks completed successfully)
- **Error rate**: 10% (2 validation errors across 20 attempts)
- **Mean confidence**: 4.1/5

**Per-Task Summary**:

| Task      | Median Time (s) | Success Rate | Error Rate | Mean Confidence |
| --------- | --------------- | ------------ | ---------- | --------------- |
| T1 Filter | 67              | 100%         | 0%         | 3.8/5           |
| T2 Edit   | 87              | 100%         | 40%        | 3.4/5           |
| T3 Add    | 25              | 100%         | 0%         | 4.6/5           |
| T4 Delete | 22              | 100%         | 0%         | 4.6/5           |

**Mode Comparison (JS-on vs No-JS)**:

| Metric          | HTMX (n=4) | No-JS (n=1) |
| --------------- | ---------- | ----------- |
| Mean task time  | 45s        | 90s         |
| Mean confidence | 4.2/5      | 3.0/5       |

The No-JS participant took approximately 2× longer and reported significantly lower confidence. This performance gap was expected due to full page reloads, but the confidence gap indicates a feedback problem that should be addressed.

### 2.2 Qualitative Analysis (≈15%)

Five themes emerged from pilot observations and debrief notes:

**Theme 1: Success Feedback Insufficient in No-JS Mode**

- 100% of No-JS participants affected
- Quote: "I think it worked but there's no confirmation" (P4)
- Design implication: Add flash message after successful form submissions

**Theme 2: Edit Flow Causes Uncertainty About Save State**

- 80% of participants expressed uncertainty after saving
- Quotes: "I think it worked?" (P1), "I wasn't sure if Enter would save or cancel" (P3)
- Design implication: Make status message more prominent

**Theme 3: Filter Input Not Immediately Discoverable**

- 1 participant took 30 seconds to find filter
- Quote: "Maybe add a label or placeholder?" (P2)
- Design implication: Add visible label to filter input

**Theme 4: Keyboard Navigation Functional but Verbose**

- Keyboard-only times were 60-100% longer than mouse users
- Quote: "Editing took a while because I had to Tab through every task" (P3)
- Design implication: Consider skip links or search-then-edit patterns

**Theme 5: Delete Without Confirmation Concerning (No-JS)**

- Quote: "Whoa, that just deleted it without asking!" (P4)
- This is a known trade-off from wk8-01, but the reaction confirms user concern

### 2.3 Accessibility Observations (≈10%)

**Keyboard Navigation** ✅ Passed

- All tasks completable with keyboard only
- Focus indicators visible throughout
- No keyboard traps detected
- Tab order logical

**Screen Reader**: Not tested (participant unavailable)

- Recommendation: Test with NVDA/VoiceOver in Week 10

**No-JS Parity** ⚠️ Partial

- All tasks functional ✅
- Performance acceptable (slower but expected) ✅
- Issues identified:
  - No success feedback after adding task ❌
  - Error message not focusable in edit flow ❌
  - No delete confirmation (documented trade-off) ⚠️

---

## Section 3: Evidence Chains (≈25%)

### 3.1 High-Priority Findings (≈15%)

**Issue 1: No success feedback in No-JS add task flow** (wk9-01)

- Raw data: P4 T3 confidence = 3/5, time = 72s (vs. 18-25s HTMX)
- Finding: PRG redirect provides no confirmation; users must manually verify
- WCAG: Related to 3.3.1 Error Identification
- Candidate fix: Add flash message to No-JS success path

**Issue 2: Edit save confirmation not prominent enough** (wk9-02)

- Raw data: 4/5 participants expressed uncertainty; P4 confidence = 2/5
- Finding: Status message "Task updated successfully" is too subtle
- WCAG: 4.1.3 Status Messages
- Candidate fix: Enhance visibility with larger text or visual highlight

**Issue 3: No-JS error message not keyboard-focusable** (wk9-03)

- Raw data: P4 had to scroll up to find error after validation failure
- Finding: Error appears but focus remains at bottom of form
- WCAG: 3.3.1 Error Identification, 2.4.3 Focus Order
- Candidate fix: Add `tabindex="-1"` to error summary and focus on load

### 3.2 Backlog Integration (≈10%)

Five new backlog items were created based on pilot findings:

| ID     | Description                          | Priority | Candidate Fix? |
| ------ | ------------------------------------ | -------- | -------------- |
| wk9-01 | No success feedback in No-JS add     | HIGH     | ✅ Yes         |
| wk9-02 | Edit save confirmation not prominent | HIGH     | ✅ Yes         |
| wk9-03 | No-JS error not keyboard-focusable   | MEDIUM   | ✅ Yes         |
| wk9-04 | Filter input discoverability         | MEDIUM   | No             |
| wk9-05 | Focus management after delete        | LOW      | No             |

Updated backlog available in `backlog/backlog.csv`.

---

## Section 4: Reflection (≈20%)

### 4.1 Process Critique (≈10%)

**What went well**:

- Task design was realistic and matched actual user goals
- Mixed-mode testing (HTMX/keyboard/No-JS) revealed issues that single-mode testing would miss
- Server-side instrumentation captured accurate timing data without client-side dependencies
- Privacy-by-design approach (anonymous session IDs) worked well

**Areas for improvement**:

- Sample size (n=5) is sufficient for formative testing but limits statistical significance
- No screen reader testing conducted due to participant availability
- Peer participants may have higher technical literacy than general population
- Some facilitator bias possible (same person ran all sessions)

**Data limitations**:

- Confidence ratings are self-reported and may not fully reflect actual uncertainty
- No-JS participant (n=1) limits ability to generalize No-JS findings
- Metrics capture server-side time only; client-side render time not measured

### 4.2 Next Steps (≈10%)

**Week 10 Priority Fixes**:

1. **Implement No-JS success flash message** (wk9-01)

   - Highest impact: Affects all No-JS users
   - Implementation: Add session-based flash message after successful form POST
   - Verification: Re-test with No-JS session, confirm message appears

2. **Enhance edit save confirmation** (wk9-02)

   - Affects 80% of participants
   - Implementation: Increase font size, add checkmark icon, or brief animation
   - Verification: Ask participants if they noticed the confirmation

3. **Make No-JS error focusable** (wk9-03)
   - Improves accessibility for keyboard/SR users
   - Implementation: Add `tabindex="-1"` to error div, JavaScript focuses it on page load (graceful degradation: visually prominent even without JS)
   - Verification: Tab through form after error, confirm error is first focus

**Verification approach**: Run 2-3 follow-up pilot sessions in Week 10 after implementing fixes, comparing confidence scores and qualitative feedback to Week 9 baseline.

---

## Files Referenced

- `wk09/lab-wk9/research/tasks.md` — Task definitions
- `wk09/lab-wk9/research/measures.md` — Metric specifications
- `wk09/lab-wk9/research/protocol.md` — Evaluation protocol
- `wk09/lab-wk9/research/consent-log.md` — Consent records
- `wk09/data/pilot-notes.md` — Raw observation data
- `data/metrics.csv` — Server-side timing logs
- `wk09/analysis/findings.md` — Analysis and themes
- `wk09/analysis/evidence-chains.md` — Evidence traceability
- `backlog/backlog.csv` — Updated backlog with wk9 items

---

**Word Count**: ~1,800 words  
**Target**: 2,500-3,000 words (to be expanded in Week 10 final submission)
