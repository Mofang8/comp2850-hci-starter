# Data Recording Notes — Week 9

> 本文件用于记录数据收集与数据质量相关的信息：
> - 如何在试验中手动记录数据；
> - 自动日志（`data/metrics.csv`）的使用方式；
> - 任何数据缺失、异常值或试验事故。

---

## Manual Recording（人工记录，必做）

对于每位参与者（P1、P2、P3…），建议在纸质笔记或本仓库中记录以下内容，然后在会后整理到
`pilot-notes.md` 与 `consent-log.md`：

- **Task times（任务用时）**  
  - 如服务器日志完整，可以仅用日志中的 `ms` 字段；  
  - 仍建议用秒表作为备份：  
    - 从主持人读完场景 / 参与者开始操作到参与者表示“完成了”为止。

- **Success / failure（是否完成）**  
  - 每个任务记录 `Completion`：1 / 0.5 / 0；

- **Errors（错误）**  
  - 记录触发验证错误、错误点击、明显误操作等事件；

- **Confidence ratings（自信评分）**  
  - 每个任务后都询问一次 1–5 的信心分，并记录在 notes 中；

- **Think‑aloud & quotes（思维口述与典型语句）**  
  - 用引号 `""` 标记参与者原话，例如：
    - `"我不确定刚才的编辑有没有保存成功"`。

---

## Automated Instrumentation（自动埋点，推荐）

若已按照 Week 9 Lab 1 指南实现 `utils.Logger` 与 `utils.timed`，则服务器会将关键任务的事件
写入 `data/metrics.csv`。

### Log file

- **File**: `data/metrics.csv`
- **Schema**（详见 `wk09/lab-wk9/instr/schema.md`）：
```csv
ts_iso,session_id,request_id,task_code,step,outcome,ms,http_status,js_mode
```

示例：
```csv
2025-10-13T14:23:01.832Z,7a9f2c,r0001,T3_add,success,,567,200,on
2025-10-13T14:23:45.123Z,7a9f2c,r0002,T3_add,validation_error,blank_title,0,400,on
2025-10-13T14:24:10.456Z,7a9f2c,r0003,T1_filter,success,,1847,200,on
```

### Typical usage for analysis

- **Time-on-task**  
  - 按 `session_id + task_code` 过滤 `step = success` 行，然后取 `ms` 列求 median / MAD；

- **Error rate / error count**  
  - 对 `step = validation_error` 行按任务统计数量，再除以尝试次数；

- **Mode comparison（JS-on vs JS-off）**  
  - 使用 `js_mode` 列区分 JS 打开/关闭的情况；
  - 比较相同任务在两种模式下的 median 时间与错误率差异。

---

## Observational Codes（观察标签）

建议在 `pilot-notes.md` 中的 `issue_tag` 列使用这些简写：

- `KBD`：键盘导航问题（Tab 顺序、焦点丢失等）；
- `SR`：屏幕阅读器相关问题；
- `FOC`：焦点管理问题（例如保存后焦点回到页面顶部）；
- `ERR`：验证错误（提示文案、可见性、朗读等）；
- `CONF`：参与者口头表达迷惑或不确定；
- `POS`：正向反馈（“这个设计很好用”等）；
- `PARITY`：JS-on / no‑JS 行为不一致的问题。

示例：
> `14:27 | T2 | P1 说 "不知道有没有保存" | CONF | medium`

---

## Anomalies & Data Quality（异常与数据质量）

在试验过程中，难免会遇到以下情况——请一律记录在本文件中，方便后续解释数据：

- 浏览器或服务器崩溃；
- 主持人忘记启动或停止秒表；
- 参与者被外界打断（同学对话、老师提问等）；
- 网络明显延迟或请求卡顿；
- 不可重复的奇怪行为（例如浏览器插件拦截了请求）。

### Template

```markdown
# Data Quality Notes

## Session P1 (sid=7a9f2c)
- T1_filter: metrics.csv 与秒表记录一致（~19s），无异常。

## Session P2 (sid=ab12cd)
- T2_edit: 服务器在提交后短暂重启，metrics.csv 中缺失 success 行；
  - 处理：使用秒表记录的 17s 作为该任务时间；
  - 影响：后续分析需标记该值为手动测量。

## Session P3 (sid=ef34gh)
- T3_add (no-JS): 用时 40s，明显超过 median，原因是参与者中途停下来读说明。

## Exclusions（如有）
- 暂无 / 或者说明哪些数据在分析中被排除以及原因。
```

---

## Storage & Backup（存储与备份）

- 在试验进行时，可优先使用纸质笔记记录以减少对参与者的干扰；
- 试验结束后，将纸质笔记照片安全地备份到 `wk09/evidence/notes-backup/`（如课程要求）；
- 确保任何包含潜在 PII 的信息不出现在版本控制的仓库中（只使用 P1、P2 等代号）。

---

在 Week 10 的分析中，本文件将帮助你和批改者理解 **数据的可信度** 以及
哪些结论需要带着谨慎态度去解读。 


