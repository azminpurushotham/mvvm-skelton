package core.bindingadapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.azmin.skelton.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import timber.log.Timber

object ImageViewBA {
    private var options: RequestOptions? = null
    @JvmStatic
    @BindingAdapter("android:src")
    fun setSrc(view: ImageView, imageUri: String?) {
        Timber.v(" " + imageUri);
        if (options == null) {
            options = RequestOptions()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        }


        if (imageUri == null || imageUri.equals("", ignoreCase = true)) {
            view.setImageDrawable(
                ContextCompat.getDrawable(
                    view.context,
                    R.drawable.placeholder
                )
            )
        } else if (imageUri.contains("http")) {
            Glide.with(view).applyDefaultRequestOptions(options!!).load(imageUri).into(view)
        } else {
//            Glide.with(view).applyDefaultRequestOptions(options!!).load(ImageUrl+imageUri).into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageDrawable(view: ImageView, drawable: Drawable) {
        view.setImageDrawable(drawable)
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageResource(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource)
    }
}