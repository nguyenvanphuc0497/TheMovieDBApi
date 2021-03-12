package vn.nvp.themoviedbapi.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.nvp.themoviedbapi.BuildConfig
import vn.nvp.themoviedbapi.data.MovieApiService
import vn.nvp.themoviedbapi.data.network.AuthInterceptor

val networkModule = module {
    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    factory {
        provideWeatherApi(get())
    }
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    val httpLogging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val interceptor = Interceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .method(original.method, original.body)
            .build()
        chain.proceed(request)
    }

    return OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(httpLogging)
        .addInterceptor(interceptor)
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideWeatherApi(retrofit: Retrofit): MovieApiService {
    return retrofit.create(MovieApiService::class.java)
}
