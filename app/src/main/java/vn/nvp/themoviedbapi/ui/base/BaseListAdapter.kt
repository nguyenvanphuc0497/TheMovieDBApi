package vn.nvp.themoviedbapi.ui.base

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Create by Nguyen Van Phuc on 3/13/21
 */
abstract class BaseListAdapter<T>(diffCallBack: BaseDiffUtilItemCallback<T>) :
    ListAdapter<T, BaseListAdapter.BaseViewHolder>(diffCallBack) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind()
    }

    /**
     * ViewHolder Abstract
     */
    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        open fun onBind() = Unit
    }


    /**
     * ItemCallback Abstract
     */
    @SuppressLint("DiffUtilEquals")
    abstract class BaseDiffUtilItemCallback<T> : DiffUtil.ItemCallback<T>() {
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem == newItem
    }
}
