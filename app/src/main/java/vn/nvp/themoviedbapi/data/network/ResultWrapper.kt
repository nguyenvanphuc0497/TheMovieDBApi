package vn.nvp.themoviedbapi.data.network

sealed class ResultWrapper<out T> {
    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data class Error(val code: Int? = null, val msg: String? = null) : ResultWrapper<Nothing>()

    object NetworkError : ResultWrapper<Nothing>()
}
