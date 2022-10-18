package hu.renes.articles.utility.extension

import android.app.Dialog
import android.content.Context
import android.widget.Toast.*
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Context.toast(@StringRes resId: Int, duration: Int = LENGTH_SHORT) {
    makeText(this, getString(resId), duration).show()
}

fun Context.toast(message: String, duration: Int = LENGTH_SHORT) {
    makeText(this, message, duration).show()
}

fun Fragment.toast(message: String) {
    makeText(this.context, message, LENGTH_SHORT).show()
}

fun Fragment.toast(@StringRes resId: Int) {
    makeText(this.context, getString(resId), LENGTH_SHORT).show()
}

fun Dialog.toast(message: String) {
    makeText(this.context, message, LENGTH_LONG).show()
}

fun Dialog.toast(@StringRes resId: Int) {
    makeText(this.context, this.context.getString(resId), LENGTH_LONG).show()
}