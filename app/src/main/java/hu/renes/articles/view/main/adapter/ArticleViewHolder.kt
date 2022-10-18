package hu.renes.articles.view.main.adapter

import android.content.Context
import hu.renes.articles.databinding.ArticleItemBinding
import hu.renes.articles.databinding.BigArticleItemBinding
import hu.renes.articles.utility.extension.setImage
import hu.renes.articles.view.base.adapter.BaseViewHolder

class ArticleViewHolder(val binding: ArticleItemBinding, context: Context) : BaseViewHolder<ArticleVM>(binding, context) {

    var listener: ClickListener? = null

    override fun bind(data: ArticleVM) {
        binding.dateTextView.text = data.date
        binding.descriptionTextView.text = data.description
        binding.photoImageView.setImage(context, data.imageUrl)
        binding.root.setOnClickListener {
            listener?.onClicked(data)
        }
    }
}

interface ClickListener {
    fun onClicked(data: ArticleVM)
}

class BigArticleViewHolder(val binding: BigArticleItemBinding, context: Context) : BaseViewHolder<ArticleVM>(binding, context) {

    var listener: ClickListener? = null

    override fun bind(data: ArticleVM) {
        binding.dateTextView.text = data.date
        binding.descriptionTextView.text = data.description
        binding.photoImageView.setImage(context, data.imageUrl)
        binding.root.setOnClickListener {
            listener?.onClicked(data)
        }
    }
}