package hu.renes.articles.view.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
    abstract val viewModel: (ViewModel)

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialized()
    }

    fun clearAndStartActivity(intent: Intent, bundle: Bundle? = null) {
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent, bundle)
    }

    fun removeChildFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.remove(fragment)
        transaction?.commitAllowingStateLoss()
    }

    fun addFragment(fragment: Fragment, @IdRes containerId: Int) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.add(containerId, fragment, fragment::class.simpleName)
        transaction?.addToBackStack(fragment::class.simpleName)
        transaction?.commit()
    }

    abstract fun initialized()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}