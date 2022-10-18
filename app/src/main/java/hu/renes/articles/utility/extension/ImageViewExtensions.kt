package hu.renes.articles.utility.extension

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImage(context: Context, uri: Uri?) {
    Glide.with(context)
        .load(uri)
        .override(240, 240)
        .into(this)
}

fun ImageView.setImage(context: Context, path: String?) {
    Glide.with(context)
        .load(path)
        .override(500, 400)
        .into(this)
}