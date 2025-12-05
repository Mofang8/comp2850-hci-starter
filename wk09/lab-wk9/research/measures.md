# Metrics Definitions — Week 9

Reference: `wk9-lab1-eval-plan-instrumentation.md`、`references/evaluation-metrics-quickref.md`

---

## Objective Metrics

### 1. Completion Rate

**Definition**  
某个任务成功完成的比例，衡量 **有效性（effectiveness）**。

**Calculation**
```text
Completion rate = (# 成功完成该任务的参与者) / (# 尝试该任务的参与者)
```

**Data source**
- 观察记录（是否完成任务目标）；
- 结合 `data/metrics.csv` 中该任务是否有 `step=success` 行。

**Reporting**
- 按任务给出百分比，例如：`T1_filter: 4/5 = 80%`；
- 可按 JS-on / JS-off、键盘-only / 鼠标 分组对比。

---

### 2. Time-on-Task

**Definition**  
从参与者开始执行某一任务，到完成或放弃该任务的时长（毫秒），衡量 **效率（efficiency）**。

**Calculation**
- **服务器计时**（首选）：  
  使用 `data/metrics.csv` 中某个 `session_id + task_code` 的 `success` 事件的 `ms` 字段；
- **人工计时备份**：  
  主持人使用秒表，从读完任务场景/参与者开始操作，到参与者说 “完成了”。

统计时：
```text
对每个任务：
  使用成功样本的 median（中位数）作为代表值；
  使用 MAD（Median Absolute Deviation）描述离散程度；
  记录最小值和最大值（range）作为上下文。
```

**Reporting**
- 例如：
  - `T1_filter: median = 19s, MAD = 6s, range = 12s–32s (n=5)`
- 尽量按 JS-on / JS-off 分组，验证 no-JS 预期会更慢。

---

### 3. Error Rate

**Definition**  
触发验证错误（例如空标题、超长标题）的尝试在所有尝试中的比例，衡量 **错误易发程度**。

**Calculation**
```text
Error rate = (# validation_error events) / (# attempts)
```
其中：
- `validation_error` 来自 `data/metrics.csv` 中 `step = "validation_error"` 的行；
- `attempts` = 相同 `session_id + task_code` 下 `validation_error + success + fail` 等尝试总数。

**Reporting**
- 按任务给出百分比，并简述错误类型，例如：
  - `T3_add: 2/5 = 40%（1× 空标题，1× 超长标题）`

---

### 4. Validation Error Count

**Definition**  
每位参与者在每个任务上的验证错误次数。

**Calculation**
- 对每位参与者、每个 `task_code` 统计 `step = "validation_error"` 行数；
- 计算平均值：
```text
Mean errors per participant for task T = (该任务所有 validation_error 次数) / (参与该任务的参与者人数)
```

**Reporting**
- 例如：
  - `T2_edit: 平均错误次数 0.4（总共 2 次错误，5 位参与者）`

**HCI insight**  
错误次数偏高通常意味着：控件提示不清晰、约束不明显或无障碍问题（例如错误信息未被朗读）。

---

## Subjective Metrics

### 5. Confidence Rating (1–5)

**Definition**  
参与者对自己是否正确完成某个任务的自信程度，衡量 **主观满意度/确定性**。

**Scale**
```text
1 = 完全不确定
2 = 比较不确定
3 = 一般
4 = 比较确定
5 = 非常确定
```

**Collection method**
- 每个任务完成后立即询问：
> “在 1 到 5 分之间，你有多确定自己刚才正确完成了这个任务？”

**Reporting**
- 记录每位参与者的评分；
- 按任务汇总 **平均值 ± 标准差**，并列出分布（多少人选了 1、2、3、4、5）。

**Insight**  
若任务的 Completion rate 很高，但 Confidence 较低，说明界面反馈可能不够清晰（用户虽然完成了任务，但心理上不确定）。

---

### 6. Difficulty Rating (1–7, 可选)

**Definition**  
参与者主观感受到的任务难度。

**Scale**
```text
1 = 非常容易
4 = 一般
7 = 非常困难
```

**Collection method**
- 每个任务完成后可选询问：
> “在 1 到 7 分之间，这个任务对你来说有多难？”

**Reporting**
- 按任务给出平均值 ± 标准差；
- 与 Time-on-task 和 Error rate 对照，判断是 “客观难” 还是 “主观觉得难”。

---

### 7. Post-Session Satisfaction (UMUX-Lite, 可选)

**Definition**  
两道题简化版满意度量表，作为 SUS 的近似替代，衡量整体可用性。

**Questions (1–7, strongly disagree → strongly agree)**
1. “This system's capabilities meet my requirements.”
2. “This system is easy to use.”

**Calculation**
- 对每位参与者，取两题的平均分作为该会话的 UMUX-Lite 得分；
- 再对所有参与者的得分取平均。

**Reporting**
- 例如：`UMUX-Lite 平均分 = 5.6/7 (n=5)`。

---

## Qualitative & Accessibility Metrics

### 8. Facilitator Notes（质性观察）

**Definition**  
主持人在试验过程中记录的时间戳、行为、犹豫、直接引用等，用于后续主题分析。

**Capture**
- 行为：长时间停顿、来回点击、明显迷失；
- 言语：如 “我不确定它是不是保存成功了”；
- 无障碍问题：如 “屏幕阅读器没有朗读错误信息”；
- 替代行为：如 用浏览器 `Ctrl+F` 搜索而不是内置过滤。

**Format**
- 记录在 `wk09/lab-wk9/research/pilot-notes.md` 中，使用表格形式：
  - `Time | Task | Observation | issue_tag | severity`
- 并在试后补充自由文本小结和参与者原话引用。

**Analysis**
- Week 10 中对这些 notes 进行轻量主题编码（thematic coding），并把主题映射到 backlog 条目。

---

### 9. Keyboard-Only Completion

**Definition**  
是否可以只用键盘（Tab、Shift+Tab、Enter、Space 等）完成任务。

**Measurement**
- 为至少一位参与者安排键盘-only 变体；
- 对每个任务标记：
  - ✅ = 完全可以仅用键盘完成；
  - ✗ = 某些步骤必须用鼠标或存在键盘陷阱。

**Reporting**
- 在分析和 evidence chains 中，以“任务级别的可键盘完成性”形式呈现，例如：
  - `T2_edit: Keyboard-only ✅`
  - `T4_delete: Keyboard-only ✗（无法用键盘触发确认对话框）`

---

### 10. Screen Reader Announcement Quality

**Definition**  
状态消息（成功/错误）、过滤结果数量等是否被屏幕阅读器正确朗读。

**Measurement**
- 在至少一次屏幕阅读器或演示会话中，观察：
  - 状态区域是否使用 `role="status"` 或 `role="alert"`；
  - 过滤结果数量是否在更新后被朗读；
  - 错误消息是否与对应输入框关联（`aria-describedby` 或内联文本）。

**Reporting**
- 在 notes 中收集具体例子；
- 在 Week 10 分析时，将问题映射到 WCAG（尤其是 4.1.3 Status Messages）。

---

## Data Integrity Checks

在正式分析数据（Week 10）之前，应检查：

- **Completeness**  
  - 所有任务对于每位参与者至少有一条 `session_id + task_code + step` 记录；
  - 主观评分（Confidence 等）没有缺失。

- **Plausibility**  
  - 各任务的时间在合理范围内（例如：T3_add 一般不应超过 60–90 秒）；
  - 无明显异常值（如 0ms 或 999999ms）。

- **Consistency**  
  - `js_mode` 与试验变体记录一致（Keyboard-only 也应为 `on`，No-JS 试验应为 `off`）；
  - 参与者 notes 中描述的错误，与 `metrics.csv` 中的 `validation_error` 行相互印证。

- **Outliers**  
  - 对于超出 median 3 倍以上的时长，记为异常并在 `data-notes.md` 中解释原因
    （例如：被其他同学打断、浏览器崩溃或主持人忘记停表）。

所有数据质量备注，应记录在 `wk09/lab-wk9/research/data-notes.md` 中，以便评估者了解分析的局限性。


