package vn.nvp.themoviedbapi.data.repository

import vn.nvp.themoviedbapi.data.MovieApiService
import vn.nvp.themoviedbapi.data.network.ResultWrapper
import vn.nvp.themoviedbapi.data.network.SafeApi
import vn.nvp.themoviedbapi.data.vo.MovieResponse

class MovieRepository(private val movieApi: MovieApiService) : SafeApi() {
    suspend fun getListMoviePopular(): ResultWrapper<MovieResponse> = safeApiCall {
        movieApi.getListMoviePopular()
    }
}
