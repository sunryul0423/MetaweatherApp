package com.metaweather.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.metaweather.GlideApp
import com.metaweather.R


/**
 * Glide 이미지 세팅
 * @param view ImageView
 * @param url 이미지 Url
 */
@BindingAdapter("imgUrl")
fun setImageUrl(view: ImageView, url: String?) {
    url?.let {
        GlideApp.with(view.context.applicationContext)
            .load(url)
            .fitCenter()
            .error(R.drawable.error_placeholder)
            .into(view)
    }
}

