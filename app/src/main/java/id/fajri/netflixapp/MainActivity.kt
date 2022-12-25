package id.fajri.netflixapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.fajri.netflixapp.data.MovieDatasource
import id.fajri.netflixapp.domain.model.Movie
import id.fajri.netflixapp.ui.MovieViewModel
import id.fajri.netflixapp.ui.component.MovieAppBar
import id.fajri.netflixapp.ui.component.MovieItem
import id.fajri.netflixapp.ui.component.MovieSearchField
import id.fajri.netflixapp.ui.screen.MovieGridScreen
import id.fajri.netflixapp.ui.screen.MovieListScreen
import id.fajri.netflixapp.ui.theme.NetflixAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel

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
fun NetflixApp(
    viewModel: MovieViewModel = viewModel(factory = MovieViewModel.Factory)
) {
    val movies by viewModel.movies.observeAsState(arrayListOf())
    var isGrid by remember { mutableStateOf(false) }
    var keyword by remember { mutableStateOf("") }

    LaunchedEffect(keyword) {
        viewModel.getMovies(keyword)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                MovieAppBar(
                    onViewChange = {isGrid = it}
                )
                MovieSearchField(
                    keyword,
                    onTextChange = {
                        keyword = it
                    },
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .padding(horizontal = 16.dp)
                )
            }

        }
    ) { contentPadding ->
        if (isGrid) MovieGridScreen(paddingValues = contentPadding, movies = movies)
        else MovieListScreen(paddingValues = contentPadding, movies = movies)
    }

}
