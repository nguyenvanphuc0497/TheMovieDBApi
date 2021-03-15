package vn.nvp.themoviedbapi.data

import retrofit2.http.GET
import retrofit2.http.Query
import vn.nvp.themoviedbapi.data.vo.MovieResponse

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getListMoviePopular(@Query("page") page: Int = 1): MovieResponse

    @GET("movie/now_playing?language=en-US&page=undefined")
    suspend fun getListMovieNowPlaying(): MovieResponse
}
