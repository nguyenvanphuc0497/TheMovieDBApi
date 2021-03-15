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
    protected var currentPage = 0
    protected var totalPage = 0
    protected var isLoading = false

    internal fun getApiException(): LiveData<Event<String>> = apiException

    internal fun getLoadingApiException(): LiveData<Event<Boolean>> = loadingProgress

    protected fun postApiException(msg: String) = apiException.postValue(Event(msg))

    protected fun postStateLoadingProgress(isLoading: Boolean) {
        loadingProgress.postValue(Event(isLoading))
    }

    /**
     * Handle Load More RecyclerView
     *
     * @param isLoadMore condition to reload from recycler view
     */
    protected fun handleLoadMore(isLoadMore: Boolean, onLoadMore: () -> Unit) {
        if (isHasLoadMore(isLoadMore)) {
            isLoading = true
            currentPage++
            onLoadMore()
        }
    }

    /**
     * Detect change data and post data to UI using LiveData
     *
     * @param listOfResponseServer Data get from server
     * @param listOfDataViewModel Data is declared on the view model
     * @param sender LiveData using post data to recycler view on UI using thread != [Main Thread]
     */
    protected fun <T> handleDataChange(
        listOfResponseServer: MutableList<T>,
        listOfDataViewModel: MutableList<T>,
        sender: MutableLiveData<List<T>>
    ) {
        listOfResponseServer.also {
            it.addAll(0, listOfDataViewModel)
            sender.postValue(it)
            listOfDataViewModel.run {
                clear()
                addAll(it)
            }
        }
    }

    /**
     * Load more if there are has pages to load
     *
     * @return Boolean
     */
    protected open fun isHasLoadMore(isLoadMore: Boolean) =
        isLoadMore && !isLoading && currentPage < totalPage
}
