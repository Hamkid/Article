package hu.renes.articles.utility

import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


sealed class ApiResponse<out T> {
    class Success<out T>(val data: T) : ApiResponse<T>()
    class Error(
        val exception: Throwable,
        val localizedMessage: String? = exception.localizedMessage
    ) : ApiResponse<Nothing>()

    object Pending : ApiResponse<Nothing>()
    object Complete : ApiResponse<Nothing>()
}

inline fun <T : Any> ApiResponse<T>.onSuccess(action: (T) -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Success) action(data)
    return this
}

inline fun <T : Any> ApiResponse<T>.onError(action: (ApiResponse.Error) -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Error) action(this)
    return this
}

suspend inline fun <T> safeApiCall(
    context: CoroutineContext,
    crossinline body: suspend () -> T,
): ApiResponse<T> {
    return try {
        val result = withContext(context) { body() }
        ApiResponse.Success(result)
    } catch (ex: Exception) {
        ApiResponse.Error(ex)
    }
}

