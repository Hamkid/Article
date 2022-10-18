package hu.renes.articles.domain.service

import hu.renes.articles.domain.model.ArticleResponse
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {

    @GET("/v2/top-headlines")
    suspend fun articles(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String
    ): ArticleResponse

    object Factory {
        fun createService(retrofit: Retrofit): ArticleService {
            return retrofit.create(ArticleService::class.java)
        }
    }
}