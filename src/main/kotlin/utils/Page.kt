package utils

/**
 * Simple pagination container for server-rendered lists.
 *
 * @param items Items on the current page
 * @param currentPage 1-based index of the current page
 * @param pageSize Number of items per page
 * @param totalItems Total number of items across all pages
 */
data class Page<T>(
    val items: List<T>,
    val currentPage: Int,
    val pageSize: Int,
    val totalItems: Int,
) {
    val totalPages: Int = if (totalItems == 0) 1 else ((totalItems + pageSize - 1) / pageSize)
    val hasPrevious: Boolean get() = currentPage > 1
    val hasNext: Boolean get() = currentPage < totalPages
    val previousPage: Int? get() = if (hasPrevious) currentPage - 1 else null
    val nextPage: Int? get() = if (hasNext) currentPage + 1 else null
}
