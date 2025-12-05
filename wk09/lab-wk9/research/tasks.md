# Evaluation Tasks — Week 9

> 这些任务基于你当前的任务管理器，实现对 **筛选、编辑、添加、删除** 四个关键流程的评估，
> 与 `wk9-lab1-eval-plan-instrumentation.md` 模板保持一致，并为后续日志中的
> `T1_filter / T2_edit / T3_add / T4_delete` 提供明确含义。

## Task T1: Filter Tasks (`T1_filter`)

**Scenario（场景）**  
“你在准备实验报告，需要快速找到所有和会议相关的事项。请使用任务列表顶部的过滤框，只显示标题里包含『meet』的任务，然后告诉我一共有多少条。”

**Setup（前置条件）**
- 在试验前预置至少 8–10 条任务，其中 **3 条** 标题中包含单词 `meet`，例如：
  - `Meet supervisor`
  - `Prepare meeting slides`
  - `Book meeting room`
- 确保这些任务在第一页可见，避免分页干扰。

**Success criteria（成功标准）**
- 参与者找到并聚焦到过滤输入框；
- 正确输入 `meet`（大小写不限）并触发过滤（HTMX 自动或 Enter / 提交按钮）；
- 报告正确数量的匹配任务（3 条）；
- 在 **120 秒** 内完成；
- 未出现验证错误。

**Metrics（度量）**
- **Time-on-task**：从主持人读完场景、参与者开始操作，到说出最终数量的毫秒数；
- **Completion**：  
  - `1` = 在时限内给出正确数量；  
  - `0.5` = 找到过滤功能但数量错误；  
  - `0` = 放弃或超时；
- **Error count**：错误点击、反复清空搜索、输入错误关键词（如 `meeting` 导致结果与预期不符）；
- **Confidence (1–5)**：完成后询问 “你有多确定自己找到了所有包含『meet』的任务？”。

**Accessibility checks（无障碍检查）**
- 仅用键盘（Tab / Shift+Tab / Enter）是否能完成整个任务；
- 屏幕阅读器是否会朗读过滤结果数量（如 “Found 3 tasks.” live region）；
- 在禁用 JS（no-JS）模式下，过滤是否仍然可用，并通过重新加载后的摘要文本反馈结果。

---

## Task T2: Edit Task Title (`T2_edit`)

**Scenario**  
“列表中有一个任务标题写错了：`draft report`。请把它改成 `submit report`，确保修改成功保存。”

**Setup**
- 在种子数据中预置一条任务，标题为 `draft report`，在第一页可见；
- 其他任务标题不要包含完全相同的字样，以免混淆。

**Success criteria**
- 参与者找到 `draft report` 这一条任务；
- 激活编辑模式（点击 Edit / 键盘触发等）；
- 将标题改为 `submit report` 并保存；
- 保存后在列表中看到更新后的标题，刷新页面后仍然保持；
- 在 **90 秒** 内完成；
- 未出现验证错误（例如提交空标题）。

**Metrics**
- **Time-on-task**：从第一次点击 Edit 到看到成功提示或更新后的标题的毫秒数；
- **Completion**：`1` = 正确修改并持久化；`0.5` = 修改但标题不完全正确；`0` = 放弃或恢复原样；
- **Validation errors**：如提交空标题、超长标题时触发的错误次数；
- **Confidence (1–5)**：完成后询问 “你有多确定标题已经成功修改？”。

**Accessibility checks**
- 屏幕阅读器是否会朗读 “Task \"…\" updated successfully.” 等状态信息；
- 键盘用户是否可以顺利找到 Edit 按钮、进入编辑框、保存或取消；
- 保存后焦点是否回到该任务附近（避免跳到页面顶部而迷失）；
- JS 禁用时，是否仍然可以通过完整页面编辑流程完成该操作，并在错误时能看见可达的错误信息。

---

## Task T3: Add New Task (`T3_add`)

**Scenario**  
“你刚想起需要和导师约一次会，请添加一个新的任务 `Meet supervisor about project`。”

**Setup**
- 当前任务列表可以为空或已有若干条任务，但不包含完全相同的标题；
- 添加任务的表单位于页面顶部，进入页面就可见，无需滚动。

**Success criteria**
- 参与者找到添加任务的输入框；
- 正确输入 `Meet supervisor about project`（或接近的变体）；
- 提交表单后，新任务立即出现在任务列表中；
- 在 **60 秒** 内完成；
- 如果发生一次验证错误（如误提交空标题），能够恢复并成功完成。

**Metrics**
- **Time-on-task**：从输入框获得焦点，到看到新任务出现在列表中的毫秒数；
- **Completion**：`1` = 添加成功且标题合理；`0` = 放弃或标题明显不符；
- **Validation error count**：因空标题或超长标题导致的错误次数；
- **Confidence (1–5)**：完成后询问 “你觉得自己刚才添加的任务有多确定是成功了？”。

**Accessibility checks**
- 成功消息（如 “Task added successfully.”）是否对屏幕阅读器可见并被朗读；
- 发生验证错误时，错误信息是否通过 `role="alert"` 等方式被朗读，且焦点不会丢失；
- JS 禁用时，PRG 流程是否仍然把用户带回一个清晰的状态（新任务可见或有适当错误提示）。

---

## Task T4: Delete Task (`T4_delete`)

**Scenario**  
“有一个临时任务 `tmp` 已经不需要了。请把这个任务从列表里删除。”

**Setup**
- 在种子数据中预置一条任务，标题为 `tmp`，并提供明显的 Delete 按钮；
- 列表中还有其他任务，方便参与者对比删除前后差异。

**Success criteria**
- 参与者在列表中找到标题为 `tmp` 的任务；
- 使用 Delete 按钮或等价操作请求删除；
- HTMX 模式下：如有确认对话框，参与者能理解并确认删除；
- 删除之后，`tmp` 任务不再出现在列表中；
- 在 **45 秒** 内完成。

**Metrics**
- **Time-on-task**：从第一次与该任务相关的操作（例如点击 Delete）到任务从列表中消失或成功消息出现的毫秒数；
- **Completion**：`1` = 成功删除并确认；`0` = 放弃或误删其他条目；
- **Hesitation / errors**：例如取消删除后又重试、点击错误行的 Delete 按钮；
- **Confidence (1–5)**：完成后询问 “你有多确定刚刚删除的任务真的被删除了？”。

**Accessibility checks**
- Delete 按钮的可访问名称是否清楚（如 “Delete task: tmp”），便于屏幕阅读器用户理解删除的是哪一条；
- 删除后的状态消息（如 “Deleted \"tmp\".”）是否使用 `role="status"` 并且可被屏幕阅读器朗读；
- 键盘-only 用户能否安全触发删除，并在没有鼠标的情况下避免误删；
- JS 禁用时，虽然缺少确认对话框，但删除流程是否仍然清晰，且这一 trade‑off 已在 Week 8 原型约束文档中记录。

---

## Task Order & Variants

**推荐顺序**
1. **Warm‑up（不计时）**：自由浏览任务列表；
2. **T3（Add）** — 认知负荷低，帮助熟悉界面；
3. **T1（Filter）** — 中等复杂度，测试搜索和结果反馈；
4. **T2（Edit）** — 测试内联编辑、验证和状态提示；
5. **T4（Delete）** — 破坏性操作，测试确认和反馈。

**变体设计**
- 至少一位参与者使用 **键盘-only** 变体；
- 至少一位参与者在 **禁用 JS（no-JS）** 的情况下完成任务；
- 若条件允许，可安排一位使用屏幕阅读器的参与者，重点记录无障碍观察。


