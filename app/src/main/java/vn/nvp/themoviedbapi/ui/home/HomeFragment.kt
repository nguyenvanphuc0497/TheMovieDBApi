package vn.nvp.themoviedbapi.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.nvp.themoviedbapi.R
import vn.nvp.themoviedbapi.extentions.replaceFragment
import vn.nvp.themoviedbapi.ui.base.BaseFragment

/**
 * Create by Nguyen Van Phuc on 3/11/21
 */
class HomeFragment : BaseFragment<HomeViewModel>() {
    override fun viewModel(): HomeViewModel = viewModel<HomeViewModel>().value

    override fun getLayoutResource(): Int = R.layout.fragment_home

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel().getMovieResult().observe(viewLifecycleOwner, { result ->
            Log.e("xxxFragment", "$result")
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnDetail?.setOnClickListener {
            activity?.replaceFragment(HomeFragment())
        }
    }
}
