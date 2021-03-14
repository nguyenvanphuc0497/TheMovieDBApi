package vn.nvp.themoviedbapi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import vn.nvp.themoviedbapi.data.network.ResultWrapper
import vn.nvp.themoviedbapi.data.repository.MovieRepository
import vn.nvp.themoviedbapi.data.vo.MovieResponse
import vn.nvp.themoviedbapi.ui.base.BaseViewModel

/**
 * Create by Nguyen Van Phuc on 3/11/21
 */
class HomeViewModel : BaseViewModel(), KoinComponent {
    private val movieRepository by inject<MovieRepository>()
    private val moviePlayingNowResult = MutableLiveData<MovieResponse?>()
    private val moviePopularResult = MutableLiveData<MovieResponse?>()

    init {
        callApi()
    }

    fun getMoviePlayingNowResult(): LiveData<MovieResponse?> = moviePlayingNowResult

    fun getMoviePopularResult(): LiveData<MovieResponse?> = moviePopularResult

    private fun callApi() {
        viewModelScope.launch {
            postStateLoadingProgress(isLoading = true)
            when (val movie = movieRepository.getListMoviePopular()) {
                is ResultWrapper.Success -> {
                    moviePopularResult.postValue(movie.data)
                }
                is ResultWrapper.NetworkError -> {
                    postApiException("Network Error")
                }
                is ResultWrapper.Error -> {
                    postApiException(movie.msg ?: "Generic Error")
                }
            }
            postStateLoadingProgress(isLoading = false)
        }
        viewModelScope.launch {
            postStateLoadingProgress(isLoading = true)
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
            postStateLoadingProgress(isLoading = false)
        }
    }
}
