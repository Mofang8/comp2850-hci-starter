# Pilot Notes Template — Week 9

> 该文件用于记录 Week 9 同伴试验的 **质性观察** 和 **主观评分**。
> 请在每次会话后立即补全，以免遗忘细节。

---

## Coding Scheme（标签约定）

为便于后续主题分析（thematic coding），建议使用以下 `issue_tag`：

- `ux-feedback`：一般可用性反馈（布局、命名、视觉层次等）；
- `ux-expectation`：与预期不符的行为或界面（如“以为要按按钮才会过滤”）；
- `error-handling`：与错误提示、验证逻辑相关的问题；
- `a11y-status`：状态提示（成功/错误）在可见性或朗读上的问题；
- `a11y-pass`：明确观察到的无障碍成功案例；
- `focus-management`：焦点丢失或移动不合理；
- `parity-nojs`：JS-on 与 no‑JS 行为不一致的问题；
- `resilience`：从错误中恢复的过程（如第二次尝试成功）；
- `other`：暂不归类的观察（可在备注中解释）。

严重程度 `severity` 建议使用：
- `high`：严重影响任务完成或造成明显排斥/混淆；
- `medium`：有明显负面影响但仍可完成任务；
- `low`：小问题或细节；
- `info`：正面观察或无直接问题的记录。

---

## Session P1

```markdown
Session: P1 (sid=XXXXXX)
Date: YYYY-MM-DD
Variant: Standard (HTMX, mouse) / Keyboard-only / No-JS / Screen reader

| Time | Task | Observation | issue_tag | severity |
|------|------|-------------|-----------|----------|
| 14:23 | T3  | ... | ... | ... |
| 14:25 | T1  | ... | ... | ... |
| 14:28 | T2  | ... | ... | ... |
| 14:31 | T4  | ... | ... | ... |

Debrief notes:
- ...
- ...

Confidence ratings (1–5):
- T3 (Add): ...
- T1 (Filter): ...
- T2 (Edit): ...
- T4 (Delete): ...
```

---

## Session P2

（复制上方结构，根据实际情况替换 Variant，例如 Keyboard-only、No-JS 等）

```markdown
Session: P2 (sid=XXXXXX)
Date: YYYY-MM-DD
Variant: Keyboard-only, JS-on

| Time | Task | Observation | issue_tag | severity |
|------|------|-------------|-----------|----------|
| ...  | ...  | ...         | ...       | ...      |

Debrief notes:
- ...

Confidence ratings (1–5):
- T3: ...
- T1: ...
- T2: ...
- T4: ...
```

---

## Session P3 …

按需要继续添加 P3、P4、P5 等会话的记录。  
在 Week 10 的分析中，这些笔记将与 `data/metrics.csv` 结合，用于提炼主题并构建 evidence chains。


