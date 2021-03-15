package vn.nvp.themoviedbapi.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.nvp.themoviedbapi.R
import vn.nvp.themoviedbapi.extentions.replaceFragment
import vn.nvp.themoviedbapi.ui.base.BaseFragment

/**
 * Create by Nguyen Van Phuc on 3/11/21
 */
class HomeFragment : BaseFragment<HomeViewModel>() {
    companion object {
        private const val OFFSET_LOAD_MORE = 2 + 1
    }

    override fun viewModel(): HomeViewModel = viewModel<HomeViewModel>().value
    private val movieNowPlayingAdapter: MovieNowPlayingAdapter = MovieNowPlayingAdapter()
    private val moviePopularAdapter: MoviePopularAdapter = MoviePopularAdapter()

    override fun getLayoutResource(): Int = R.layout.fragment_home

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel().getMoviePopularResult().observe(viewLifecycleOwner, { result ->
            moviePopularAdapter.submitList(result)
        })

        viewModel().getMoviePlayingNowResult().observe(viewLifecycleOwner, { result ->
            movieNowPlayingAdapter.submitList(result?.results)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnDetail?.setOnClickListener {
            activity?.replaceFragment(HomeFragment())
        }

        rvPlayingNow?.run {
            adapter = this@HomeFragment.movieNowPlayingAdapter
            layoutManager = LinearLayoutManager(
                this@HomeFragment.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.HORIZONTAL
                ).apply {
                    setDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.divider_item_movie_color_black,
                            null
                        )!!
                    )
                })
        }

        rvMostPopular?.run {
            adapter = this@HomeFragment.moviePopularAdapter
            val linearLayoutManager = LinearLayoutManager(this@HomeFragment.context)
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                setDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.divider_item_movie_color_gray,
                        null
                    )!!
                )
            })
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (viewModel().isHasLoadMore() && (viewModel().sizeListMoviePopular - linearLayoutManager.findLastCompletelyVisibleItemPosition()) == OFFSET_LOAD_MORE) {
                        viewModel().loadMoreListMoviePopular()
                    }
                }
            })
        }
    }
}
