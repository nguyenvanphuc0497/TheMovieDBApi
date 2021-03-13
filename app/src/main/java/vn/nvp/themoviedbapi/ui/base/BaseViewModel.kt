package vn.nvp.themoviedbapi.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vn.nvp.themoviedbapi.util.Event

/**
 * Create by Nguyen Van Phuc on 3/11/21
 */
abstract class BaseViewModel : ViewModel() {
    private val loadingProgress = MutableLiveData<Event<Boolean>>()
    private val apiException = MutableLiveData<Event<String>>()

    internal fun getApiException(): LiveData<Event<String>> = apiException

    internal fun getLoadingApiException(): LiveData<Event<Boolean>> = loadingProgress

    protected fun postApiException(msg: String) = apiException.postValue(Event(msg))

    protected fun postStateLoadingProgress(isLoading: Boolean) =
        loadingProgress.postValue(Event(isLoading))
}
