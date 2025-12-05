# Interview Notes — Week 6

## Participant A
**Date**: 2025-11-28 14:10
**Context**: Uses keyboard frequently due to wrist pain, prefers dark mode
**Consent**: ✅ Confirmed verbally
**Duration**: 12 minutes

### Q1: Last time you used a task manager
**Response**: "I use Todoist. Yesterday I was trying to find tasks for my 'HCI' project. I filtered by tag, but every time I added a task, the page reloaded and I lost my filter. It was super annoying to re-select 'HCI' every single time."

**Observations**:
- **Pain point**: State loss on reload (filter resets)
- **Impact**: Frustration, wasted time, cognitive load
- **Context**: Project-specific workflow

**Themes**: `filter_persistence`, `state_loss`, `cognitive_load`

---

### Q2: What frustrates you?
**Response**: "Sometimes I click 'Add' and I'm not sure if it actually worked, especially on slow wifi. I end up clicking it twice and getting duplicates. I wish it would just tell me 'Saved' clearly."

**Observations**:
- **Pain point**: Lack of clear feedback
- **Consequence**: Duplicate data, uncertainty
- **Need**: Explicit confirmation

**Themes**: `status_feedback`, `confirmation`, `uncertainty`

---

### Q3: Work without a mouse?
**Response**: "Yeah, my wrist hurts if I mouse too much. I tried tabbing through this new app. The 'Delete' buttons are hard to get to—I have to tab through every single edit button first. And I can't tell which 'Delete' button belongs to which task easily when using a screen reader sometimes (I tested it once)."

**Observations**:
- **Pain point**: Inefficient tab order / excessive tabbing
- **Accessibility**: Keyboard navigation friction
- **Context**: RSI / Motor impairment

**Themes**: `keyboard_nav`, `tab_order`, `efficiency`

---

## Participant B
**Date**: 2025-11-28 14:30
**Context**: Uses screen reader (NVDA) for testing, sensitive to bright light
**Consent**: ✅ Confirmed verbally
**Duration**: 10 minutes

### Q1: Task manager experience
**Response**: "I tried to use a simple to-do app on my phone in the park. The sun was so bright I couldn't see the gray text on the white background. I literally couldn't read my tasks."

**Observations**:
- **Pain point**: Low contrast text
- **Context**: Situational disability (bright sunlight)
- **Impact**: Unreadable content

**Themes**: `contrast`, `visibility`, `situational_disability`

---

### Q2: Features to add
**Response**: "I want to know when I've messed up. If I try to add an empty task, some apps just do nothing. I need a big red error message that my screen reader reads out immediately."

**Observations**:
- **Pain point**: Silent failure
- **Need**: Assertive error reporting
- **Context**: Screen reader user needs immediate feedback

**Themes**: `error_handling`, `screen_reader`, `feedback`

---

### Q3: Lost track of tasks?
**Response**: "I have like 50 tasks. I scroll and scroll. I wish I could just see 'Work' tasks. Pagination would be nice too, so I don't have a mile-long page."

**Observations**:
- **Pain point**: List length management
- **Need**: Filtering / Pagination
- **Context**: High volume of tasks

**Themes**: `pagination`, `filtering`, `information_overload`

---

## Summary
**Top pain points identified**:
1.  **Filter/State Loss**: Re-filtering after reload is tedious (P-A).
2.  **Feedback Uncertainty**: Not knowing if add/delete succeeded (P-A, P-B).
3.  **Keyboard Efficiency**: Tabbing through long lists is slow (P-A).
4.  **Contrast**: Gray text is hard to read in sunlight (P-B).
5.  **Error Feedback**: Silent failures for invalid input (P-B).
