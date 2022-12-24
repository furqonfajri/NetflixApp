package id.fajri.netflixapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import id.fajri.netflixapp.domain.model.Movie
import id.fajri.netflixapp.ui.screen.MovieDetailScreen
import id.fajri.netflixapp.ui.theme.NetflixAppTheme

@ExperimentalMaterial3Api
class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetflixAppTheme {
                intent.extras?.getParcelable<Movie>(EXTRA_MOVIE)?.let { movie ->
                    setContent{
                        MovieDetailScreen(movie = movie) {
                            finish()
                        }
                    }
                }

            }
        }
    }

    companion object{
        const val EXTRA_MOVIE ="extra_movie"
    }
}