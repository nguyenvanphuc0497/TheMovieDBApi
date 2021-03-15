package vn.nvp.themoviedbapi.data.repository

import vn.nvp.themoviedbapi.data.MovieApiService
import vn.nvp.themoviedbapi.data.network.ResultWrapper
import vn.nvp.themoviedbapi.data.network.SafeApi
import vn.nvp.themoviedbapi.data.vo.MovieResponse

/**
 * Create by Nguyen Van Phuc on 3/11/21
 */
class MovieRepository(private val movieApi: MovieApiService) : SafeApi() {
    suspend fun getListMoviePopular(page: Int = 1): ResultWrapper<MovieResponse> = safeApiCall {
        movieApi.getListMoviePopular(page = page)
    }

    suspend fun getListMovieNowPlaying(): ResultWrapper<MovieResponse> = safeApiCall {
        movieApi.getListMovieNowPlaying()
    }
}
