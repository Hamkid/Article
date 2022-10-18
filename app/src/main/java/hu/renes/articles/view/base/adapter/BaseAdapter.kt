package hu.renes.articles.view.base.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : IListTypeProvider> : RecyclerView.Adapter<BaseViewHolder<T>> {
    val context: Context
    private var items: List<T>

    constructor(context: Context) {
        this.context = context
        items = ArrayList()
    }

    constructor(context: Context, items: MutableList<T>) {
        this.context = context
        this.items = items
    }

    override fun getItemViewType(position: Int): Int = getItem(position).getLayoutType()

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = items.size

    fun getItem(position: Int): T = items[position]

    fun getItemPosition(item: T): Int {
        return if (items.contains(item)) {
            items.indexOf(item)
        } else {
            -1
        }
    }

    fun getItems(): List<T> = items

    fun setItems(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun notifyItemChanged(item: T) {
        if (items.contains(item)) {
            notifyItemChanged(items.indexOf(item))
        }
    }
}
