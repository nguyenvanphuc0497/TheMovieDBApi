package vn.nvp.themoviedbapi.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import vn.nvp.themoviedbapi.util.EventObserver

/**
 * Create by Nguyen Van Phuc on 3/11/21
 */
abstract class BaseFragment<VM : BaseViewModel> : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return createViewForFragment(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Handle Api exception
        handelApiException()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initEvents()
    }

    open fun createViewForFragment(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayoutResource(), container, false)
//        view.content.run {
//            setPadding(left, top + context.getStatusBarHeight(), right, bottom)
//        }
        return view
    }

    protected open fun initViews() = Unit

    protected open fun initEvents() = Unit

    abstract fun viewModel(): VM

    @LayoutRes
    abstract fun getLayoutResource(): Int

    open fun handelApiException() {
        viewModel().getApiException().observe(viewLifecycleOwner, EventObserver { msg ->
            showMessageGenericError(msg)
        })
    }

    open fun handleNetworkError() {
        showMessageNetworkError()
    }

    private fun showMessageNetworkError() {
        val dialog = AlertDialog.Builder(this.requireContext())
            .setMessage("Network Error")
            .create()
        dialog.show()
    }

    private fun showMessageGenericError(msg: String?) {
        AlertDialog.Builder(this.requireContext())
            .setMessage(msg)
            .create()
            .show()
    }
}
