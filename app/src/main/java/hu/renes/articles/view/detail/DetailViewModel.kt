package hu.renes.articles.view.detail

import dagger.hilt.android.lifecycle.HiltViewModel
import hu.renes.articles.injection.IoDispatcher
import hu.renes.articles.view.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

sealed class MainEvent<out T>

@HiltViewModel
class DetailViewModel @Inject constructor(
    @IoDispatcher uiContext: CoroutineDispatcher
) : BaseViewModel<MainEvent<Nothing>>(uiContext) {


    override fun handleEvent(event: MainEvent<Nothing>) {

    }
}