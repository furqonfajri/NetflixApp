package id.fajri.netflixapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.fajri.netflixapp.data.MovieDatasource
import id.fajri.netflixapp.domain.model.Movie
import id.fajri.netflixapp.ui.component.MovieAppBar
import id.fajri.netflixapp.ui.component.MovieItem

@ExperimentalMaterial3Api
@Composable
fun MovieListScreen(paddingValues: PaddingValues, movies: List<Movie>) {

    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(movies) { movie ->
            MovieItem(
                isGrid = false,
                movie = movie,
                modifier = Modifier.padding(16.dp)
            )
        }
    }


}