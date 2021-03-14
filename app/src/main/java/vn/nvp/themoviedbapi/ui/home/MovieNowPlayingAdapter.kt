package vn.nvp.themoviedbapi.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_home_movie_playing_now.view.*
import vn.nvp.themoviedbapi.BuildConfig
import vn.nvp.themoviedbapi.R
import vn.nvp.themoviedbapi.data.vo.Movie
import vn.nvp.themoviedbapi.ui.base.BaseListAdapter

/**
 * Create by Nguyen Van Phuc on 3/13/21
 */
class MovieNowPlayingAdapter : BaseListAdapter<Movie>(ItemDiffUntil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_movie_playing_now, parent, false)
        )

    inner class ItemViewHolder(view: View) : BaseViewHolder(view) {
        override fun onBind() {
            Glide
                .with(itemView)
                .load("${BuildConfig.IMAGE_BASE_URL}${getItem(adapterPosition).poster_path}")
                .into(itemView.imgPoster)
        }
    }

    private class ItemDiffUntil : BaseDiffUtilItemCallback<Movie>()
}
