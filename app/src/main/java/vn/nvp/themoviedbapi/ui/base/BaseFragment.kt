package vn.nvp.themoviedbapi.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.view.*
import vn.nvp.themoviedbapi.extentions.getStatusBarHeight
import vn.nvp.themoviedbapi.util.EventObserver

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

    open fun createViewForFragment(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayoutResource(), container, false)
        view.content.run {
            setPadding(left, top + context.getStatusBarHeight(), right, bottom)
        }
        return view
    }

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
