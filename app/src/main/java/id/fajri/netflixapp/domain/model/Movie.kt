package id.fajri.netflixapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String,
    val description: String,
    val rating: Float,
    val backdropResourceId: Int,
    val posterResourceId: Int
): Parcelable
