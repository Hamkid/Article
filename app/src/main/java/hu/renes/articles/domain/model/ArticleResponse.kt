package hu.renes.articles.domain.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

data class ArticleResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)

@Parcelize
data class Article(
    val author: String?,
    val content: String?,
    val description: String,
    val publishedAt: Date,
    val title: String,
    val url: String,
    val source: Source,
    val urlToImage: String
) : Parcelable

@Parcelize
data class Source(
    val id: String?,
    val name: String
) : Parcelable