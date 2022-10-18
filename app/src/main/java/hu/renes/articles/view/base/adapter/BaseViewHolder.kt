package hu.renes.articles.view.base.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T>(viewBinding: ViewBinding, context: Context) : RecyclerView.ViewHolder(viewBinding.root) {
    protected val context: Context
    private var clickableView: View

    abstract fun bind(data: T)

    init {
        clickableView = viewBinding.root
        this.context = context
    }
}