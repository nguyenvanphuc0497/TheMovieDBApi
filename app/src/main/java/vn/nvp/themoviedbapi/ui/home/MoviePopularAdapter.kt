package vn.nvp.themoviedbapi.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_home_movie_popular.view.*
import vn.nvp.themoviedbapi.BuildConfig
import vn.nvp.themoviedbapi.R
import vn.nvp.themoviedbapi.data.vo.Movie
import vn.nvp.themoviedbapi.ui.base.BaseListAdapter

/**
 * Create by Nguyen Van Phuc on 3/13/21
 */
class MoviePopularAdapter : BaseListAdapter<Movie>(ItemDiffUntil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_movie_popular, parent, false)
        )

    inner class ItemViewHolder(view: View) : BaseViewHolder(view) {
        override fun onBind() {
            getItem(adapterPosition).let {
                itemView.run {
                    tvTitleMovie.text = it.original_title
                    tvDate.text = it.release_date
                    tvOverview.text = it.overview
                    Glide.with(this)
                        .load("${BuildConfig.IMAGE_BASE_URL}${it.poster_path}")
                        .centerInside()
                        .into(imgPosterMovie)
                    progressBarRating.setProgress(it.vote_average / 10 * 100)
                }
            }
        }
    }

    private class ItemDiffUntil : BaseDiffUtilItemCallback<Movie>()
}
