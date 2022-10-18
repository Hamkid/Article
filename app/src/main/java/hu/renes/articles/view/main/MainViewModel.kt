package hu.renes.articles.view.main

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.renes.articles.domain.repository.ArticleRepository
import hu.renes.articles.injection.IoDispatcher
import hu.renes.articles.utility.onError
import hu.renes.articles.utility.onSuccess
import hu.renes.articles.utility.safeApiCall
import hu.renes.articles.view.base.BaseViewModel
import hu.renes.articles.view.main.adapter.ArticleVM
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class MainEvent<out T> {
    object OnStart : MainEvent<Nothing>()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    @IoDispatcher uiContext: CoroutineDispatcher,
    private val articleRepository: ArticleRepository
) : BaseViewModel<MainEvent<Nothing>>(uiContext) {

    internal val articles = MutableLiveData<List<ArticleVM>>()

    override fun handleEvent(event: MainEvent<Nothing>) {
        when (event) {
            is MainEvent.OnStart -> loadArticles()
        }
    }

    private fun loadArticles() = launch {
        safeApiCall(uiContext) { articleRepository.articles() }
            .onSuccess {
                articles.postValue(it.articles.map { article -> ArticleVM(article) })
            }.onError {
                errorMessage.postValue(it.localizedMessage)
            }
    }
}