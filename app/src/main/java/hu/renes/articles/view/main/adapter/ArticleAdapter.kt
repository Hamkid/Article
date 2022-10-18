package hu.renes.articles.view.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.renes.articles.databinding.ArticleItemBinding
import hu.renes.articles.databinding.BigArticleItemBinding
import hu.renes.articles.view.base.adapter.BaseAdapter
import hu.renes.articles.view.base.adapter.BaseViewHolder
import javax.inject.Inject

class ArticleAdapter @Inject constructor(@ApplicationContext context: Context) : BaseAdapter<ArticleVM>(context) {

    companion object {
        enum class ArticleType(val index: Int) {
            BIG(0),
            SMALL(1)
        }
    }

    var clickListener: ClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ArticleVM> {
        if (viewType == ArticleType.BIG.index) {
            val view = BigArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return BigArticleViewHolder(view, context)
        }
        val view = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ArticleVM>, position: Int) {
        super.onBindViewHolder(holder, position)
        if (holder is ArticleViewHolder) {
            holder.listener = clickListener
        }
        if (holder is BigArticleViewHolder) {
            holder.listener = clickListener
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) ArticleType.BIG.index else ArticleType.SMALL.index
    }
}