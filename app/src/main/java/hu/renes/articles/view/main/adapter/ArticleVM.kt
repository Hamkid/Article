package hu.renes.articles.view.main.adapter

import hu.renes.articles.R
import hu.renes.articles.domain.model.Article
import hu.renes.articles.utility.extension.hoursFrom
import hu.renes.articles.view.base.adapter.IListTypeProvider

open class ArticleVM(val article: Article) : IListTypeProvider {

    val imageUrl = article.urlToImage
    val date = "${article.publishedAt.hoursFrom()}H"
    val description = article.description

    override fun getLayoutType(): Int = R.layout.article_item
}

class BigArticleVM(article: Article): ArticleVM(article) {
    override fun getLayoutType(): Int = R.layout.big_article_item
}