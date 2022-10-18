package hu.renes.articles.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.renes.articles.domain.repository.ArticleRepository
import hu.renes.articles.domain.service.ArticleService
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideArticleRepository(
        articleService: ArticleService
    ): ArticleRepository = ArticleRepository(articleService)
}