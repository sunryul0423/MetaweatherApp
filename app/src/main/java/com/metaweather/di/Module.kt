package com.metaweather.di

import com.metaweather.interfaces.ApiRequest
import com.metaweather.model.view.MainViewModelFactory
import com.metaweather.utils.BASE_URL
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 통신 모듈 생성
 */
val apiModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
            .create(ApiRequest::class.java)
    }

    single {
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .cookieJar(CookieJar.NO_COOKIES)
            .addInterceptor(get() as HttpLoggingInterceptor)
            .build()
    }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}

/**
 * MainViewModel Factory 모듈 생성
 */
val mainModule = module {
    factory {
        MainViewModelFactory(get())
    }
}

/**
 * 모듈 리스트
 */
val appModule = listOf(apiModule, mainModule)