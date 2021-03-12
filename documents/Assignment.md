
# Mobile assignment CS
**Technical Info**
Build an application using **TheMovieDB API**, following the mocks attached separately. We expect you to
implement following functionality in the app
# Layout Sample (Mock UI)
Here are links to the layouts you need to follow, you can inspect each view to get the dimensions.
Refer Android - (screen.png)
# ListScreen
1. List horizontally currently playing movies.
Only display poster images in horizontal scrolling list view, No pagination necessary.
2. Display the most popular movies in vertical listview, as this list will contain multiple pages, Pagination
support will be required.
3. When a user clicks on any movie list item, it will navigate to a detailed screen, with more information
about the movie.
**API:**
https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=undefined&api_key={YOUR_KEY}
https://api.themoviedb.org/3/movie/popular?api_key={YOUR_KEY }

Each list item will contain the following:
 - List item
 - Poster image
 - Title
 - Rating
 - Release date
 - Duration
# Detail Screen
Detail screen should contain following information:
-	Poster image
-	Duration
-	Title
-	Overview
-	Release date
-	List of genres
**API**
https://api.themoviedb.org/3/movie/{MOVIE_ID}?api_key={YOUR_KEY
# Image API
Image API Details can be available here:
https://developers.themoviedb.org/3/getting-started/images


# Requirements
1. Write network implementation to fetch and parse movie json response and render on the UI.
-	Implement paging mechanism to load a list of movies as the user scrolls down the list.
-	Cache movie images, in order to make smooth scrolling.
2. Implement custom progress view to show ratings of the movie
-   **No 3rd party library should be used.**
-	Animation is not necessary.
3. Provide UI/unit tests for the information screen code we provided you.
4. Implementation of the Details screen.

## Additional requirements/restrictions:
1. Provide a README.md explaining your approach to solve the image caching also custom
progress bar implementation and any other important decision you took or assumptions you
made during the implementation.
2. The code of the assignment has to be delivered along with the git repository (.git folder). We
want to see the progress of evolution. If using a cloud hosted repository, it **MUST** be a private
repository.
3. Prefer use Kotlin/Coroutine in project
4. Prefer use clean architecture
