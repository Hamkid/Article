package hu.renes.articles.utility.extension

import java.util.*
import kotlin.math.ceil

fun Date.hoursFrom(): Int {
    val diff: Long = Date().time - this.time
    val seconds = diff / 1000
    val minutes = seconds / 60
    return ceil(minutes.toDouble() / 60).toInt()
}