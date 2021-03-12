package vn.nvp.themoviedbapi.data.network

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val code: Int? = null, val msg: String? = null) : Result<Nothing>()

    object NetworkError : Result<Nothing>()
}
