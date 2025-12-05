package utils

import io.ktor.server.application.*
import io.ktor.util.*

/**
 * Timing helpers and JS-mode detection for Week 9 instrumentation.
 *
 * Usage pattern in routes:
 *
 * ```kotlin
 * val reqId = newReqId()
 * call.attributes.put(RequestIdKey, reqId)
 * val jsMode = call.jsMode()
 *
 * call.timed(taskCode = "T3_add", jsMode = jsMode) {
 *     // route logic (validation + repository calls + responses)
 * }
 * ```
 *
 * On success, a `success` row is logged via `Logger`.
 * On exception, a `server_error` row is logged and the exception rethrown.
 */

// AttributeKey for request ID so multiple helpers can share it
val RequestIdKey: AttributeKey<String> = AttributeKey("RequestId")

/**
 * Time a block of work for a given task code and log outcome.
 */
suspend fun ApplicationCall.timed(
    taskCode: String,
    jsMode: String,
    block: suspend ApplicationCall.() -> Unit,
) {
    val start = System.currentTimeMillis()
    val sessionId = request.cookies["sid"] ?: "anon"
    val reqId = attributes.getOrNull(RequestIdKey) ?: newReqId()

    try {
        block()
        val duration = System.currentTimeMillis() - start
        Logger.success(sessionId, reqId, taskCode, duration, jsMode)
    } catch (e: Exception) {
        val duration = System.currentTimeMillis() - start
        Logger.serverError(sessionId, reqId, taskCode, duration, e.message ?: "unknown", jsMode)
        throw e
    }
}

/**
 * Detect whether this call came from HTMX (JS-on) or full page (no-JS).
 */
fun ApplicationCall.isHtmx(): Boolean =
    request.headers["HX-Request"]?.equals("true", ignoreCase = true) == true

fun ApplicationCall.jsMode(): String =
    if (isHtmx()) "on" else "off"

/**
 * Simple in-memory request ID generator.
 *
 * For Week 9 scale this is sufficient; for production you'd typically
 * use UUIDs or a distributed trace ID.
 */
private var requestCounter: Long = 0

@Synchronized
fun newReqId(): String {
    requestCounter += 1
    return "r${String.format("%04d", requestCounter)}"
}


