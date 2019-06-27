package com.metaweather.model.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.metaweather.interfaces.ApiRequest

/**
 * MainViewModel Fractory 객체
 * @author SR.Park
 * @constructor Kakao Image Api
 * @since 2019.02.20
 * @param apiRequest API 인터페이스
 */
@Suppress("UNCHECKED_CAST")
class WeatherItemViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherItemVM() as T
    }
}