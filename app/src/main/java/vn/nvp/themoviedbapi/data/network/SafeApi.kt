package vn.nvp.themoviedbapi.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.HttpsURLConnection

abstract class SafeApi {

    suspend inline fun <T> safeApiCall(crossinline callFunction: suspend () -> T): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                Result.Success(callFunction.invoke())
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        val response: Response<*>? = e.response()
                        when (response?.code()) {
                            HttpsURLConnection.HTTP_BAD_REQUEST -> {
                                Result.Error()
                            }
                            HttpsURLConnection.HTTP_INTERNAL_ERROR -> {
                                Result.Error()
                            }
                            else -> {
                                Result.Error()
                            }
                        }

                    }
                    is IOException -> {
                        when (e) {
                            is UnknownHostException -> {
                                Result.NetworkError
                            }
                            is SocketTimeoutException -> {
                                Result.NetworkError
                            }
                            else -> {
                                Result.NetworkError
                            }
                        }
                    }
                    else -> Result.Error(null, null)
                }
            }
        }
    }
}
