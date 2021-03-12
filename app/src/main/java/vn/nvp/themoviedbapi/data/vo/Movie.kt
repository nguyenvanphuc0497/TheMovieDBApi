package vn.nvp.themoviedbapi.data.vo

data class Movie(
    val adult: String,
    val original_language: String,
    val original_title: String,
    val popularity: String,
    val vote_average: String,
    val vote_count: String,
    val title: String,
)

data class MovieResponse(
    val page: String,
    val results: List<Movie>,
    val total_pages: String,
    val total_results: String
)
