package vn.nvp.themoviedbapi.data.network

import okhttp3.Interceptor
import okhttp3.Response
import vn.nvp.themoviedbapi.BuildConfig

/**
 * Create by Nguyen Van Phuc on 3/11/21
 */
class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val url = req.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_THEMOVIEDB_KEY).build()

        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}
