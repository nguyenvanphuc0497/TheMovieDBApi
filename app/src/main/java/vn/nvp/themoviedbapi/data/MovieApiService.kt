package vn.nvp.themoviedbapi.data

import retrofit2.http.GET
import vn.nvp.themoviedbapi.data.vo.MovieResponse

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getListMoviePopular(): MovieResponse

    @GET("movie/now_playing?language=en-US&page=undefined")
    suspend fun getListMovieNowPlaying(): MovieResponse
}
