package hu.renes.articles.view.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<T>(protected val uiContext: CoroutineContext) : ViewModel(),
    CoroutineScope {

    internal val isLoading = MutableLiveData(false)
    internal val errorMessage = MutableLiveData<String>()
    internal val showMessage = MutableLiveData<String>()

    abstract fun handleEvent(event: T)

    protected var jobTracker: Job = Job()

    protected val errorState = MutableLiveData<String>()
    val error: LiveData<String> get() = errorState

    protected val loadingState = MutableLiveData<Unit>()
    val loading: LiveData<Unit> get() = loadingState

    override val coroutineContext: CoroutineContext
        get() = uiContext + jobTracker

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorMessage.value = throwable.localizedMessage
    }
}