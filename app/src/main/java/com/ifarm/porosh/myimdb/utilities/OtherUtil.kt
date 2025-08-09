package com.ifarm.porosh.myimdb.utilities

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.text.HtmlCompat


class OtherUtil {

    object Util {

        fun makeStyle(title: String, value: String): Spanned? {
            val makeStyle = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(
                    "<b>$title:</b> $value",
                    HtmlCompat.FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml("<b>$title:</b> $value")
            }

            return makeStyle
        }

        fun makeStyleWithoutColon(title: String, value: String): Spanned? {
            val makeStyle = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(
                    "$title <big><b>$value</b></big>",
                    HtmlCompat.FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml("$title <b>$value</b>")
            }

            return makeStyle
        }

        fun hideKeyboard(view: View?) {
            if (view != null) {
                val inputMethod = view.context
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethod.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

    }

}