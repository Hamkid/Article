package hu.renes.articles.domain.repository

import hu.renes.articles.BuildConfig
import hu.renes.articles.domain.service.ArticleService

class ArticleRepository constructor(
    private val articleService: ArticleService
) {
    suspend fun articles() = articleService.articles(apiKey = BuildConfig.API_KEY)

}