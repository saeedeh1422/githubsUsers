package com.itoll.githubusers.common

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions

object ImageLoader {
    fun load(
        context: Context?,
        url: String?,
        imageView: ImageView,
        isCircle: Boolean = false,
        placeholder: Int? = null,
    ) {
        if (url.isNullOrEmpty()) {
            imageView.setImageDrawable(null)
            return
        }
        context?.let {
            val glideReq = Glide.with(context)
                .load(url)
                .transition(withCrossFade())

            if (isCircle) glideReq.apply(RequestOptions.circleCropTransform())
            placeholder?.let { glideReq.apply(RequestOptions.placeholderOf(it)) }

            glideReq.into(imageView)
        }
    }

}