package core.bindingadapters

import android.os.Build
import android.text.Html
import android.text.TextUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:spanntext")
fun TextView.setSpanntext(text: String?) {
    if (text != null) {
        if (TextUtils.isEmpty(text) == false) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                setText(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY))
            } else {
                setText(Html.fromHtml(text))
            }
        }
    }
}