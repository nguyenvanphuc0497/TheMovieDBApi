package vn.nvp.themoviedbapi.data.network

/**
 * Create by Nguyen Van Phuc on 3/11/21
 */
sealed class ResultWrapper<out T> {
    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data class Error(val code: Int? = null, val msg: String? = null) : ResultWrapper<Nothing>()

    object NetworkError : ResultWrapper<Nothing>()
}
