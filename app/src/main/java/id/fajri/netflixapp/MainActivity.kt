package id.fajri.netflixapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.fajri.netflixapp.data.MovieDatasource
import id.fajri.netflixapp.domain.model.Movie
import id.fajri.netflixapp.ui.component.MovieAppBar
import id.fajri.netflixapp.ui.component.MovieItem
import id.fajri.netflixapp.ui.screen.MovieGridScreen
import id.fajri.netflixapp.ui.screen.MovieListScreen
import id.fajri.netflixapp.ui.theme.NetflixAppTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetflixAppTheme {
                NetflixApp()
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun NetflixApp() {
    val movies: List<Movie> by rememberSaveable {
        mutableStateOf(MovieDatasource.getNowPlayingMovie())
    }

    var isGrid by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MovieAppBar(
                onViewChange = {isGrid = it}
            )
        }
    ) { contentPadding ->
        if (isGrid) MovieGridScreen(paddingValues = contentPadding, movies = movies)
        else MovieListScreen(paddingValues = contentPadding, movies = movies)
    }

}
