package hu.renes.articles.view.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB
    abstract val viewModel: (ViewModel)

    companion object {
        const val MIN_BUFFER = 100
        const val MAX_BUFFER = 500
        const val PLAYBACK_BUFFER = 0
        const val PLAYBACK_REBUFFER = 0
    }


    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        initialized()
    }

    abstract fun initialized()

    fun clearAndStartActivity(intent: Intent, bundle: Bundle? = null) {
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}