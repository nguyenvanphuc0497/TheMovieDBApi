package vn.nvp.themoviedbapi.data.vo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Create by Nguyen Van Phuc on 3/11/21
 */
@Parcelize
data class Movie(
    val adult: String,
    val original_language: String,
    val original_title: String,
    val popularity: String,
    val vote_average: Float,
    val vote_count: String,
    val title: String,
    val poster_path: String,
    val overview: String,
    val release_date: String,
) : Parcelable

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: String
)
