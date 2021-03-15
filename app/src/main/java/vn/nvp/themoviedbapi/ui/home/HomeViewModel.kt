package vn.nvp.themoviedbapi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import vn.nvp.themoviedbapi.data.network.ResultWrapper
import vn.nvp.themoviedbapi.data.repository.MovieRepository
import vn.nvp.themoviedbapi.data.vo.Movie
import vn.nvp.themoviedbapi.data.vo.MovieResponse
import vn.nvp.themoviedbapi.ui.base.BaseViewModel

/**
 * Create by Nguyen Van Phuc on 3/11/21
 */
class HomeViewModel : BaseViewModel(), KoinComponent {
    private val movieRepository by inject<MovieRepository>()
    private val moviePlayingNowResult = MutableLiveData<MovieResponse?>()
    private val moviePopularResult = MutableLiveData<List<Movie>>()
    private var listMoviePopularResult = mutableListOf<Movie>()
    internal val sizeListMoviePopular: Int
        get() = listMoviePopularResult.size

    init {
        callApi()
    }

    internal fun getMoviePlayingNowResult(): LiveData<MovieResponse?> = moviePlayingNowResult

    internal fun getMoviePopularResult(): MutableLiveData<List<Movie>> = moviePopularResult

    internal fun isHasLoadMore() = !isLoading && currentPage < totalPage

    internal fun loadMoreListMoviePopular() {
        callApiGetMoviePopular(currentPage + 1)
    }

    private fun callApi() {
        callApiGetMovieNowPlaying()
        callApiGetMoviePopular()
    }

    private fun callApiGetMoviePopular(page: Int = 1) {
        viewModelScope.launch {
            isLoading = true
            when (val movie = movieRepository.getListMoviePopular(page)) {
                is ResultWrapper.Success -> {
                    handleDataChange(
                        movie.data.results.toMutableList(),
                        listMoviePopularResult,
                        moviePopularResult
                    )
                    currentPage = movie.data.page
                    totalPage = movie.data.total_pages
                }
                is ResultWrapper.NetworkError -> {
                    postApiException("Network Error")
                }
                is ResultWrapper.Error -> {
                    postApiException(movie.msg ?: "Generic Error")
                }
            }
            isLoading = false
        }
    }

    private fun callApiGetMovieNowPlaying() {
        viewModelScope.launch {
            when (val movie = movieRepository.getListMovieNowPlaying()) {
                is ResultWrapper.Success -> {
                    moviePlayingNowResult.postValue(movie.data)
                }
                is ResultWrapper.NetworkError -> {
                    postApiException("Network Error")
                }
                is ResultWrapper.Error -> {
                    postApiException(movie.msg ?: "Generic Error")
                }
            }
        }
    }
}
