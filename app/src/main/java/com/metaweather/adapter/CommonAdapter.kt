package com.metaweather.adapter

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.metaweather.GlideApp


/**
 * Glide 이미지 세팅
 * @param view xml의 SimpleDraweeView
 * @param url 이미지 Url
 */
@BindingAdapter("imgUrl")
fun setImageUrl(view: ImageView, url: String?) {
    url?.let {
        GlideApp.with(view.context.applicationContext)
            .load(url)
            .fitCenter()
            .listener(createLoggerListener("wrap_image"))
            .into(view)
    }
}

fun createLoggerListener(name: String): RequestListener<Drawable> {
    return object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: com.bumptech.glide.request.target.Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: com.bumptech.glide.request.target.Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            if (resource is BitmapDrawable) {
                val bitmap = resource.bitmap
                Log.d(
                    "srpark", String.format(
                        "Ready %s bitmap %,d bytes, size: %d x %d", name,
                        bitmap.byteCount,
                        bitmap.width,
                        bitmap.height
                    )
                )
            }
            return false
        }
    }
}
