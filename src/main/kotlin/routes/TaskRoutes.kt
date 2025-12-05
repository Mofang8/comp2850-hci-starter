package routes

import data.TaskRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.pebbletemplates.pebble.PebbleEngine
import java.io.StringWriter
import utils.Page
import isHtmxRequest
import renderTemplate

/**
 * NOTE FOR NON-INTELLIJ IDEs (VSCode, Eclipse, etc.):
 * IntelliJ IDEA automatically adds imports as you type. If using a different IDE,
 * you may need to manually add imports. The commented imports below show what you'll need
 * for future weeks. Uncomment them as needed when following the lab instructions.
 *
 * When using IntelliJ: You can ignore the commented imports below - your IDE will handle them.
 */

// Week 7+ imports (inline edit, toggle completion):
// import model.Task               // When Task becomes separate model class
// import model.ValidationResult   // For validation errors
// import renderTemplate            // Extension function from Main.kt
// import isHtmxRequest             // Extension function from Main.kt

// Week 8+ imports (pagination, search, URL encoding):
// import io.ktor.http.encodeURLParameter  // For query parameter encoding
// import utils.Page                       // Pagination helper class

// Week 9+ imports (metrics logging, instrumentation):
// import utils.jsMode              // Detect JS mode (htmx/nojs)
// import utils.logValidationError  // Log validation failures
// import utils.timed               // Measure request timing

// Note: Solution repo uses storage.TaskStore instead of data.TaskRepository
// You may refactor to this in Week 10 for production readiness

/**
 * Week 6 Lab 1: Simple task routes with HTMX progressive enhancement.
 *
 * **Teaching approach**: Start simple, evolve incrementally
 * - Week 6: Basic CRUD with Int IDs
 * - Week 7: Add toggle, inline edit
 * - Week 8: Add pagination, search
 */

fun Route.taskRoutes() {
    val pebble =
        PebbleEngine
            .Builder()
            .loader(
                io.pebbletemplates.pebble.loader.ClasspathLoader().apply {
                    prefix = "templates/"
                },
            ).build()

    /**
     * GET /tasks - List tasks with optional filtering and pagination.
     *
     * Also reads any validation error query params for the no-JS path:
     * - error: which field has an error (e.g., "title")
     * - msg: optional error code (e.g., "too_long")
     */
    get("/tasks") {
        val query = call.request.queryParameters["q"].orEmpty()
        val pageParam = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
        val pageSize = 10

        val page: Page<data.Task> = TaskRepository.search(query = query, page = pageParam, size = pageSize)

        val error = call.request.queryParameters["error"]
        val msg = call.request.queryParameters["msg"]
        val titleValue = call.request.queryParameters["title"].orEmpty()

        val model =
            mapOf(
                "title" to "Tasks",
                "page" to page,
                "query" to query,
                "error" to (error ?: ""),
                "msg" to (msg ?: ""),
                "titleValue" to titleValue,
            )

        val html = call.renderTemplate("tasks/index.peb", model)
        call.respondText(html, ContentType.Text.Html)
    }

    /**
     * GET /tasks/fragment - Return list + pager fragments for HTMX.
     */
    get("/tasks/fragment") {
        val query = call.request.queryParameters["q"].orEmpty()
        val pageParam = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
        val pageSize = 10

        val page: Page<data.Task> = TaskRepository.search(query = query, page = pageParam, size = pageSize)

        val fragmentModel =
            mapOf(
                "page" to page,
                "query" to query,
            )

        val listHtml = call.renderTemplate("tasks/_list.peb", fragmentModel)
        val pagerHtml = call.renderTemplate("tasks/_pager.peb", fragmentModel)
        val statusHtml =
            """
            <div id="status"
                 hx-swap-oob="true"
                 role="status"
                 aria-live="polite">
                Found ${page.totalItems} tasks.
            </div>
            """.trimIndent()

        call.respondText(listHtml + pagerHtml + statusHtml, ContentType.Text.Html)
    }

    /**
     * POST /tasks - Add new task
     * Dual-mode: HTMX fragment or PRG redirect
     */
    post("/tasks") {
        val title = call.receiveParameters()["title"].orEmpty().trim()

        // Server-side validation: always validate on server for both HTMX and no-JS paths.
        if (title.isBlank()) {
            if (call.isHtmxRequest()) {
                // HTMX error path: keep task list + pager visible, update status via OOB.
                val query = call.request.queryParameters["q"].orEmpty()
                val page: Page<data.Task> = TaskRepository.search(query = query, page = 1, size = 10)
                val fragmentModel =
                    mapOf(
                        "page" to page,
                        "query" to query,
                    )
                val listHtml = call.renderTemplate("tasks/_list.peb", fragmentModel)
                val pagerHtml = call.renderTemplate("tasks/_pager.peb", fragmentModel)

                val errorHtml =
                    """
                    <div id="status" hx-swap-oob="true" role="alert" aria-live="assertive" class="error">
                        Title is required. Please enter at least one character.
                    </div>
                    """.trimIndent()
                return@post call.respondText(listHtml + pagerHtml + errorHtml, ContentType.Text.Html, HttpStatusCode.BadRequest)
            } else {
                // No-JS: redirect with error query param so full page can show accessible summary.
                val redirectUrl = "/tasks?error=title&title=${title.encodeURLParameter()}"
                call.response.headers.append("Location", redirectUrl)
                return@post call.respond(HttpStatusCode.SeeOther)
            }
        }

        if (title.length > 200) {
            if (call.isHtmxRequest()) {
                val query = call.request.queryParameters["q"].orEmpty()
                val page: Page<data.Task> = TaskRepository.search(query = query, page = 1, size = 10)
                val fragmentModel =
                    mapOf(
                        "page" to page,
                        "query" to query,
                    )
                val listHtml = call.renderTemplate("tasks/_list.peb", fragmentModel)
                val pagerHtml = call.renderTemplate("tasks/_pager.peb", fragmentModel)

                val errorHtml =
                    """
                    <div id="status" hx-swap-oob="true" role="alert" aria-live="assertive" class="error">
                        Title is too long (maximum 200 characters).
                    </div>
                    """.trimIndent()
                return@post call.respondText(listHtml + pagerHtml + errorHtml, ContentType.Text.Html, HttpStatusCode.BadRequest)
            } else {
                val redirectUrl = "/tasks?error=title&msg=too_long&title=${title.encodeURLParameter()}"
                call.response.headers.append("Location", redirectUrl)
                return@post call.respond(HttpStatusCode.SeeOther)
            }
        }

        TaskRepository.add(title)

        if (call.isHtmxRequest()) {
            // After adding, re-render the current page of results into the task area.
            val query = call.request.queryParameters["q"].orEmpty()
            val page: Page<data.Task> = TaskRepository.search(query = query, page = 1, size = 10)

            val fragmentModel =
                mapOf(
                    "page" to page,
                    "query" to query,
                )

            val listHtml = call.renderTemplate("tasks/_list.peb", fragmentModel)
            val pagerHtml = call.renderTemplate("tasks/_pager.peb", fragmentModel)
            val statusHtml =
                """
                <div id="status"
                     hx-swap-oob="true"
                     role="status"
                     aria-live="polite">
                    Task added successfully.
                </div>
                """.trimIndent()

            return@post call.respondText(listHtml + pagerHtml + statusHtml, ContentType.Text.Html)
        }

        // No-JS: POST-Redirect-GET pattern (303 See Other)
        call.response.headers.append("Location", "/tasks")
        call.respond(HttpStatusCode.SeeOther)
    }

    /**
     * DELETE /tasks/{id} - Delete task (HTMX path)
     *
     * Used by hx-delete on the Delete button. Returns only an OOB status message,
     * relying on outerHTML swap on the <li> to remove the task from the DOM.
     */
    delete("/tasks/{id}") {
        val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(HttpStatusCode.BadRequest)
        val task = TaskRepository.find(id)
        val removed = TaskRepository.delete(id)

        val message =
            if (removed) {
                """Deleted "${task?.title ?: "task"}"."""
            } else {
                "Could not delete task."
            }

        val status =
            """
            <div id="status"
                 hx-swap-oob="true"
                 role="status"
                 aria-live="polite">
                $message
            </div>
            """.trimIndent()

        // Empty body for the element itself; OOB status updates the live region.
        call.respondText(status, ContentType.Text.Html)
    }

    /**
     * POST /tasks/{id}/delete - Delete task (no-JS fallback)
     *
     * HTML form posts here when JavaScript is disabled.
     */
    post("/tasks/{id}/delete") {
        val id = call.parameters["id"]?.toIntOrNull()
        val removed = id?.let { TaskRepository.delete(it) } ?: false

        // For now we accept that there is no confirmation in no-JS mode (documented trade-off).
        // POST-Redirect-GET pattern (303 See Other)
        call.response.headers.append("Location", "/tasks")
        call.respond(HttpStatusCode.SeeOther)
    }

    /**
     * GET /tasks/{id}/edit - Show edit form
     */
    get("/tasks/{id}/edit") {
        val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.NotFound)
        val task = TaskRepository.find(id) ?: return@get call.respond(HttpStatusCode.NotFound)
        val errorParam = call.request.queryParameters["error"]

        val errorMessage = when (errorParam) {
            "blank" -> "Title is required. Please enter at least one character."
            else -> null
        }

        if (call.isHtmxRequest()) {
            val template = pebble.getTemplate("tasks/partials/edit.peb")
            val model = mapOf("task" to task, "error" to errorMessage)
            val writer = StringWriter()
            template.evaluate(writer, model)
            call.respondText(writer.toString(), ContentType.Text.Html)
        } else {
            // No-JS: render full page with the selected task in edit mode.
            val allTasks = TaskRepository.all()
            val page =
                Page(
                    items = allTasks,
                    currentPage = 1,
                    pageSize = allTasks.size.coerceAtLeast(1),
                    totalItems = allTasks.size,
                )

            val model =
                mutableMapOf<String, Any>(
                    "title" to "Tasks",
                    "page" to page,
                    "editingId" to id,
                ).apply {
                    if (errorMessage != null) {
                        this["errorMessage"] = errorMessage
                    }
                }

            val html = call.renderTemplate("tasks/index.peb", model)
            call.respondText(html, ContentType.Text.Html)
        }
    }

    /**
     * POST /tasks/{id}/edit - Save task
     */
    post("/tasks/{id}/edit") {
        val id = call.parameters["id"]?.toIntOrNull() ?: return@post call.respond(HttpStatusCode.NotFound)
        val task = TaskRepository.find(id) ?: return@post call.respond(HttpStatusCode.NotFound)

        val newTitle = call.receiveParameters()["title"].orEmpty().trim()

        if (newTitle.isBlank()) {
            if (call.isHtmxRequest()) {
                val html =
                    call.renderTemplate(
                        "tasks/partials/edit.peb",
                        mapOf(
                            "task" to task,
                            "error" to "Title is required. Please enter at least one character.",
                        ),
                    )
                return@post call.respondText(html, ContentType.Text.Html)
            } else {
                call.response.headers.append("Location", "/tasks/${id}/edit?error=blank")
                return@post call.respond(HttpStatusCode.SeeOther)
            }
        }

        task.title = newTitle
        TaskRepository.update(task)

        if (call.isHtmxRequest()) {
            val viewHtml = call.renderTemplate("tasks/partials/view.peb", mapOf("task" to task))

            val status =
                """
                <div id="status"
                     hx-swap-oob="true"
                     role="status"
                     aria-live="polite">
                    Task "${task.title}" updated successfully.
                </div>
                """.trimIndent()

            return@post call.respondText(viewHtml + status, ContentType.Text.Html)
        }

        call.response.headers.append("Location", "/tasks")
        call.respond(HttpStatusCode.SeeOther)
    }

    /**
     * GET /tasks/{id}/view - Cancel edit
     */
    get("/tasks/{id}/view") {
        val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.NotFound)
        val task = TaskRepository.find(id) ?: return@get call.respond(HttpStatusCode.NotFound)

        val html = call.renderTemplate("tasks/partials/view.peb", mapOf("task" to task))
        call.respondText(html, ContentType.Text.Html)
    }
}
