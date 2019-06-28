package com.metaweather

import android.app.Application
import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.metaweather.di.appModule
import com.metaweather.utils.DISK_CACHE_NAME
import org.koin.android.ext.android.startKoin
import java.io.InputStream


/**
 * 기본 설정 Application
 */
class MetaweatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModule)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        GlideApp.get(this).clearMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        GlideApp.get(this).trimMemory(level)
    }
}

@GlideModule
class MyAppGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory())
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val memoryCacheSizeBytes = 1024 * 1024 * 20L // 20MB
        val diskCacheSizeBytes = 1024 * 1024 * 100L  //100MB
        builder.setMemoryCache(LruResourceCache(memoryCacheSizeBytes))
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, DISK_CACHE_NAME, diskCacheSizeBytes))
    }
}