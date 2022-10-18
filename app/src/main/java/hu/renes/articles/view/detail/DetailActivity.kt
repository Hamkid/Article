package hu.renes.articles.view.detail

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hu.renes.articles.databinding.DetailActivityBinding
import hu.renes.articles.domain.model.Article
import hu.renes.articles.utility.extension.setImage
import hu.renes.articles.utility.extension.toast
import hu.renes.articles.view.base.BaseActivity

@AndroidEntryPoint
class DetailActivity : BaseActivity<DetailActivityBinding>() {

    companion object {
        private const val EXTRA_ARTICLE = "hu.renes.articles.view.detail.article"

        fun create(context: Context, article: Article): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_ARTICLE, article)
            return intent
        }
    }

    override val viewModel: DetailViewModel by viewModels()

    override val bindingInflater: (LayoutInflater) -> DetailActivityBinding = DetailActivityBinding::inflate

    override fun initialized() {
        val article = intent.getParcelableExtra<Article>(EXTRA_ARTICLE) ?: return
        binding.photoImageView.setImage(this, article.urlToImage)
        binding.sourceTextView.text = article.source.name
        binding.titleTextView.text = article.title
        binding.contentTextView.text = article.content

        binding.backButton.setOnClickListener {
            onBackPressed()
        }
        binding.fullButton.setOnClickListener {
            toast("Full button clicked")
        }
        binding.shareButton.setOnClickListener {
            toast("Share button clicked")
        }
    }
}