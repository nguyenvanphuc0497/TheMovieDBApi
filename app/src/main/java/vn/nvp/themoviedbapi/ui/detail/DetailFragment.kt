package vn.nvp.themoviedbapi.ui.detail

import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.nvp.themoviedbapi.BuildConfig
import vn.nvp.themoviedbapi.R
import vn.nvp.themoviedbapi.data.vo.Movie
import vn.nvp.themoviedbapi.ui.base.BaseFragment

/**
 * Create by Nguyen Van Phuc on 3/15/21
 */
class DetailFragment : BaseFragment<DetailViewModel>() {
    companion object {
        private const val KEY_MOVIE_DETAIL = "key_movie_detail"

        internal fun newInstance(movie: Movie) = DetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_MOVIE_DETAIL, movie)
            }
        }
    }

    override fun viewModel(): DetailViewModel = viewModel<DetailViewModel>().value

    override fun getLayoutResource(): Int = R.layout.fragment_detail

    override fun initViews() {
        arguments?.getParcelable<Movie>(KEY_MOVIE_DETAIL)?.let { movie ->
            Glide.with(this)
                .load("${BuildConfig.IMAGE_BASE_URL}${movie.poster_path}")
                .centerInside()
                .into(imgPosterMovie)
            tvTitle?.text = movie.original_title
            tvDate?.text = movie.release_date
            tvOverviewContent?.text = movie.overview
        }
    }

    override fun initEvents() {
        imgBack?.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}
