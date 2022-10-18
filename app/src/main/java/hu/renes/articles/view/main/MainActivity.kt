package hu.renes.articles.view.main

import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import hu.renes.articles.R
import hu.renes.articles.databinding.MainActivityBinding
import hu.renes.articles.utility.DividerItemDecorator
import hu.renes.articles.utility.extension.toast
import hu.renes.articles.view.base.BaseActivity
import hu.renes.articles.view.detail.DetailActivity
import hu.renes.articles.view.main.adapter.ArticleAdapter
import hu.renes.articles.view.main.adapter.ArticleVM
import hu.renes.articles.view.main.adapter.ArticleViewHolder
import hu.renes.articles.view.main.adapter.ClickListener
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding>() {

    @Inject
    lateinit var adapter: ArticleAdapter

    override val viewModel: MainViewModel by viewModels()

    override val bindingInflater: (LayoutInflater) -> MainActivityBinding = MainActivityBinding::inflate

    override fun initialized() {
        val divider = DividerItemDecorator(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        binding.recyclerView.addItemDecoration(divider)
        adapter.clickListener = object : ClickListener {
            override fun onClicked(data: ArticleVM) {
                startActivity(DetailActivity.create(this@MainActivity, data.article))
            }
        }
        binding.recyclerView.adapter = adapter
        viewModel.handleEvent(MainEvent.OnStart)
        viewModel.articles.observe(this) {
            adapter.setItems(it)
        }
        viewModel.errorMessage.observe(this) {
            toast(it)
        }
        viewModel.showMessage.observe(this) {
            toast(it)
        }
    }
}