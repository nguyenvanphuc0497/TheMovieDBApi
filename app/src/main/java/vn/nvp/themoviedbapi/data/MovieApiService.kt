package vn.nvp.themoviedbapi.data

import retrofit2.http.GET
import vn.nvp.themoviedbapi.data.vo.MovieResponse

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getListMoviePopular(): MovieResponse
}
