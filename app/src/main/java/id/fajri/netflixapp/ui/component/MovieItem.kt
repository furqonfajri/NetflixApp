package id.fajri.netflixapp.ui.component

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.fajri.netflixapp.DetailActivity
import id.fajri.netflixapp.R
import id.fajri.netflixapp.data.MovieDatasource
import id.fajri.netflixapp.domain.model.Movie
import id.fajri.netflixapp.ui.theme.NetflixAppTheme

@ExperimentalMaterial3Api
@Composable
fun MovieItem(
    isGrid: Boolean,
    movie: Movie,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = {
            context.startActivity(
                Intent(context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_MOVIE, movie)
                }
            )
        }
        ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(if (isGrid) movie.posterResourceId else movie.backdropResourceId)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.movie_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(220.dp)
            )


            Text(
                text = movie.rating.toString(),
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(12.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.Black)
                    .padding(9.dp)
                    .align(Alignment.TopEnd)
            )
        }
        Text(
            text = movie.title,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun MovieItemPreview() {
    NetflixAppTheme {
        MovieItem(isGrid = false, movie = MovieDatasource.getNowPlayingMovie()[0])
    }
}