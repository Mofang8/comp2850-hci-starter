# Peer Pilot Protocol — Week 9

> 本协议基于 `wk9-lab1-eval-plan-instrumentation.md` 和 `wk9-lab2-pilots-debrief-draft.md`，
> 目标是为 Week 9 同伴试验提供一套 **可重复、低风险、符合隐私与伦理要求** 的流程。

---

## Study Overview

**Purpose（目的）**  
评估你在本课程中实现的任务管理器在 **可用性（usability）** 和 **无障碍（accessibility）** 方面的表现，
为 Week 10 的重构与修复提供证据。

**Type（类型）**
- 低风险（low‑risk）同伴可用性评估；
- 形成性（formative）试验：目的是发现问题、改进设计，而不是给出最终评分。

**Scope（范围）**
- 3–5 名同学作为参与者（建议覆盖不同使用方式：鼠标、键盘-only、No‑JS 等）；
- 每次会话包含 4 个任务（T1–T4）与短暂访谈，总时长约 15–20 分钟；
- 不进行任何音视频录制；
- 不收集任何可识别个人身份的信息（PII）。

**Ethical approval（伦理）**
- 属于课程设计的同伴活动，纳入模块的低风险教学研究框架；
- 遵循学校与课程的隐私政策及 UK GDPR / Data Protection Act 2018 要求。

**Data retention（数据保存）**
- 原始日志（`data/metrics.csv`）和手动记录仅保存在本地私有仓库；
- 数据只用于本模块学习与评估目的；
- 课程结束并完成评分后，按模块要求删除或匿名化处理。

---

## Participant Requirements

**Inclusion（纳入标准）**
- 参与者为本课程同学（COMP2850 学生）；
- 熟悉基本的网页浏览；
- 能够理解并口头确认知情同意。

**Exclusion（排除标准）**
- 无强制排除条款，但参与者可以自由选择不参与或随时退出；
- 若参与者不适合使用特定变体（例如强烈晕动症，无法长时间使用屏幕阅读器），可跳过对应变体。

**Accessibility accommodations（无障碍安排）**
- 屏幕阅读器用户：允许更长时间完成任务，记录 SR 专属观察；
- 键盘-only 用户：明确说明可以不用鼠标，只需使用 Tab / Shift+Tab / Enter / Space；
- No‑JS 用户：至少安排一次完全禁用 JS 的会话，验证无 JS 路径的功能等价性（parity）。

---

## Consent Process（知情同意流程）

**Before starting（开始前朗读）**

> “谢谢你愿意帮我试用这个原型，大概需要 15 分钟左右。  
> 我会请你完成 4 个和任务列表相关的小任务，同时我会计时并做笔记。  
>  
> 我要强调的是：**我们是在测试界面，不是在测试你**，所以没有‘做错’这一说法。  
>  
> **我们会收集的内容包括**：  
> - 你完成每个任务所用的大致时间（来自服务器日志）；  
> - 你是否完成了任务（成功/失败）；  
> - 过程中出现的错误（例如验证错误）；  
> - 你对每个任务的自信评分（1–5 分）；  
> - 我对你的操作和任何无障碍问题的观察笔记。  
>  
> **我们不会收集的内容**：  
> - 你的姓名、邮箱或学号；  
> - 音频或视频录制；  
> - 任何与本练习无关的个人信息。  
>  
> 为了在日志里区分不同参与者，我会给你分配一个随机的会话编号，比如 `sid=7a9f2c`。  
> 如果你之后任何时候想要我删除与你这个编号相关的所有数据，我都会照做。  
>  
> 在整个过程中，你可以随时选择暂停或停止，这不会对你的成绩产生不利影响。  
>  
> 你有什么问题吗？如果没有，我可以开始吗？”  

**Verbal consent（口头同意）**
- 等待对方明确回答 “可以” 或等价表述；
- 如对方犹豫或拒绝，应表示感谢并不再追问理由。

**Recording consent（记录同意）**
- 在 `wk09/lab-wk9/research/consent-log.md` 中添加一条记录：
```markdown
Date: 2025-10-xx
Participant code: P1
Session ID: 7a9f2c
Variant: Standard (HTMX, mouse) / Keyboard-only / No-JS / Screen reader
Consent: Verbal consent given
Notes: （如有特别说明，例如“要求键盘-only”）
```

**Opt-out / deletion（退出与删除数据）**
- 如参与者在会后要求删除数据：
  1. 打开 `data/metrics.csv` 并删除所有 `session_id` 等于该编号的行；
  2. 在 `pilot-notes` 中用匿名方式删除或模糊化对应记录；
  3. 在 `consent-log.md` 中追加一条说明：“Data for session 7a9f2c deleted on [date] at participant request.”

---

## Session Setup（会话准备）

**Environment（环境）**
- 安静、不受打扰的教室角落；
- 参与者使用自己的或实验室电脑，浏览器打开到任务管理器；
- 主持人使用另一台设备（或同机另一窗口）记录 `pilot-notes`，尽量避免在参与者背后紧盯屏幕。

**Pre‑pilot steps（会话前步骤）**
1. 生成随机 session ID，例如在终端中运行：  
   `openssl rand -hex 3` → 例如 `7a9f2c`；
2. 在参与者浏览器控制台中设置 cookie：  
   ```javascript
   document.cookie = "sid=7a9f2c; path=/";
   ```  
   （确保与 consent log 中的 Session ID 一致）；
3. 将任务数据库预置为适合 T1–T4 的测试数据（如 `draft report`、`tmp` 等）；
4. 打开 `wk09/lab-wk9/research/tasks.md`，方便朗读任务场景；
5. 打开 `wk09/lab-wk9/research/pilot-notes.md` 作为记录模板；
6. 确认服务器已启动（`./gradlew run`），访问 `/tasks` 页面正常。

---

## Session Flow（会话流程）

### 0. Introduction & Warm-up（约 4 分钟）

1. 按“知情同意”一节朗读说明并记录口头同意；  
2. 解释 think‑aloud 是可选的：  
   > “如果你愿意，可以在操作时把自己在想什么说出来，如果不舒服也没关系。”  
3. Warm‑up（不计时）：  
   > “请先花 1–2 分钟随便看一下这个任务列表，随便点点按钮，让自己熟悉一下界面。觉得差不多可以开始任务时告诉我。”

### 1. Task T3: Add New Task（约 1 分钟）

- 朗读 `tasks.md` 中 T3 的场景：  
  > “你刚想起需要和导师约一次会，请添加一个新的任务 `Meet supervisor about project`。”  
- 当参与者开始操作（如光标聚焦到输入框）时，开始计时；
- 观察并在 `pilot-notes` 中记录时间戳、行为、错误（如空标题）、任何犹豫；
- 任务结束后询问 Confidence (1–5)，并记录。

### 2. Task T1: Filter Tasks（约 2 分钟）

- 朗读 T1 场景：  
  > “请只显示标题里包含『meet』的任务，然后告诉我一共有多少条。”  
- 观察是否能找到过滤框、是否理解结果数量反馈；
- 记录完成时间、是否成功、错误点击等；
- 询问信心分并记录。

### 3. Task T2: Edit Task Title（约 2 分钟）

- 朗读 T2 场景：  
  > “请把 `draft report` 这条任务改成 `submit report`，并确保修改成功保存。”  
- 观察参与者如何找到 Edit 按钮、是否触发验证错误；
- 记录完成情况、错误次数及相关言语（如 “不知道保存了没有”）；
- 询问信心分并记录。

### 4. Task T4: Delete Task（约 1 分钟）

- 朗读 T4 场景：  
  > “请把 `tmp` 这条临时任务删除掉。”  
- 观察 Delete 按钮的可发现性、确认对话框（若有）、删除后的反馈信息；
- 记录完成时间、成功与否以及犹豫情况；
- 询问信心分并记录。

### 5. Debrief（约 3 分钟）

向参与者提出开放式问题，并记录原话（尽量逐字）：
1. “哪一个任务对你来说最难？为什么？”  
2. “有没有哪个地方的表现和你预期的不一样？”  
3. “有没有哪一刻你不确定系统有没有成功完成你的操作？”  
4. 若参与了无障碍变体（键盘-only / SR / no‑JS）：
   - “在这个变体下，你有遇到任何无障碍相关的问题吗？”  

最后感谢参与者：
> “非常感谢，你的反馈会直接帮助我们改进这个原型。”  

如参与者感到困扰或不适，应主动给予肯定与安慰，并强调问题在界面而不在个人能力。

---

## Facilitator Guidelines（主持人守则）

**Do（应该做）**
- 保持中立的语气和表情，避免暗示“对/错”；
- 记录关键事件的时间戳和参与者原话；
- 在参与者思考时保持安静，不要急于提示；
- 如确实需要帮助（超过约 3 分钟完全卡住），记录“已介入”并简要说明原因。

**Don’t（不应做）**
- 在任务开始前详细解释界面如何使用；
- 提示控件位置（如 “过滤框在左上角”）；
- 为参与者做出设计辩解（如 “其实是故意这样设计的”）；
- 表现出不耐烦或评价性用语。

**If participant is stuck（参与者完全卡住时）**
1. 等待约 3 分钟；
2. 问一句中立问题：“你现在在找什么？”；  
3. 若仍然无法继续，建议跳过该任务：“我们先跳过这个任务吧，做下一个。” 并将 Completion 记为 0，在 notes 里写明原因。

---

## Data Recording（数据记录）

**Automated（自动日志：`data/metrics.csv`）**
- 由 Week 9 的服务器端埋点生成，字段包括：
  - `ts_iso, session_id, request_id, task_code, step, outcome, ms, http_status, js_mode`
- 每个 `session_id + task_code` 至少应有一条 `success` 或 `validation_error` 记录。

**Manual（人工记录：`wk09/lab-wk9/research/pilot-notes.md`）**

```markdown
Session: P1 (sid=7a9f2c)
Date: 2025-10-xx
Variant: Keyboard-only, JS-on

| Time | Task | Observation | issue_tag | severity |
|------|------|-------------|-----------|----------|
| 14:23 | T3  | Participant hesitated before submitting—unsure if Enter or button | ux-feedback | medium |
| 14:25 | T3  | Success message not noticed initially | a11y-status | high |
| 14:26 | T1  | Typed "meet" slowly, checking results update | ux-expectation | low |
| 14:27 | T1  | SR announced "Found 3 tasks" | a11y-pass | info |
| 14:29 | T2  | Blank submission triggered validation error | error-handling | high |
| 14:30 | T2  | Recovered from error and completed successfully | resilience | info |

Debrief notes:
- "I liked that the filter reacted immediately when I typed."
- "I wasn't sure the edit saved—maybe make the message more obvious?"
- Screen reader announced status messages correctly throughout.

Confidence ratings (1–5):
- T3: 5
- T1: 4
- T2: 3 ("not sure it saved")
- T4: 5
```

**Data-notes（数据质量备注）**
- 对任何日志缺失、异常时间值、试验中断等，记录在 `wk09/lab-wk9/research/data-notes.md` 中；
- 例如：“P3 在 T2 期间服务器重启，metrics.csv 中缺少一条 success 记录，改用秒表时间 17s。”

---

## Accessibility Variants（无障碍变体）

为提升包容性，还需要至少尝试以下变体：

### Keyboard-Only Session
- 参与者仅使用键盘（Tab / Shift+Tab / Enter / Space）完成全部任务；
- 重点观察：
  - Tab 顺序是否合理；
  - 是否有键盘陷阱（陷入某个控件无法出来）；
  - 焦点样式是否始终可见；
  - 任何必须靠鼠标才能触发的功能。

### Screen Reader Session（如条件允许）
- 使用 NVDA / Orca 等屏幕阅读器；
- 允许更长时间完成任务；
- 记录：
  - 控件标签是否被正确朗读；
  - 状态和错误信息是否被即时朗读（尤其是 `role="status"` / `role="alert"` 区域）；
  - 是否有视觉上明显、但对 SR 用户“沉默”的信息。

### No‑JS Session
- 在浏览器中完全禁用 JavaScript；
- 重复至少一个关键任务（推荐 T3_add 或 T1_filter）；
- 观察：
  - 是否仍然可以完成任务（功能等价性）；
  - 时间和错误率与 JS-on 情况的差异；
  - 有哪些反馈在 no‑JS 下缺失或不明显。

---

## Risk Mitigation（风险与缓解）

**Participant distress（参与者烦躁或挫败）**
- 若参与者表现出沮丧：
  - 说明：“这些问题对我们很有价值，说明界面还有改进空间，这不是你的问题。”  
  - 提供跳过当前任务或直接结束会话的选项；
  - 不记录任何让其身份可被识别的细节。

**Technical failure（技术故障）**
- 若服务器在会话中崩溃或前端出现严重错误：
  - 重启服务器并记录事件（在 `data-notes.md` 中注明）；
  - 不要求参与者重复已经完成的任务；
  - 如某个任务完全无法执行，必须在分析时标记该任务数据缺失。

**Data loss / quality issues（数据丢失或质量问题）**
- 若 `metrics.csv` 中缺失部分日志：
  - 使用手动计时作为备份；
  - 在 `data-notes.md` 中清楚标明哪些任务的时间来自秒表，不能与自动日志混同；
  - 若数据严重不完整，应在 Week 10 报告中诚实说明，而不是“补造数据”。

---

## Summary Checklist（会前/会中/会后自检）

**Before each session**
- [ ] 生成 session ID，并写入 cookie；
- [ ] 预置任务数据（包含 `draft report`、`tmp` 等关键条目）；
- [ ] 打开 `tasks.md`、`pilot-notes.md`、本协议；
- [ ] 确认服务器运行正常，`/tasks` 可访问。

**During session**
- [ ] 按脚本朗读知情同意，并记录 verbal consent；
- [ ] 记录 Participant code、session ID、变体类型和日期；
- [ ] 为每个任务计时（自动日志 + 秒表备份）；
- [ ] 收集每个任务的 Confidence 评分；
- [ ] 记录带时间戳的观察与参与者原话；
- [ ] 在结尾做简短 debrief。

**After session**
- [ ] 将当次 notes 存档到 `pilot-notes.md` 或单独文件；
- [ ] 打开 `data/metrics.csv` 检查本次 session 是否有完整记录；
- [ ] 如发现异常，立即补充到 `data-notes.md`；
- [ ] 再次提醒参与者可在之后任何时间要求删除数据。


