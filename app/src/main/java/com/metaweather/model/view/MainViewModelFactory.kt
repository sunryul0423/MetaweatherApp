package com.metaweather.model.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.metaweather.interfaces.ApiRequest

/**
 * MainViewModel Factory 객체
 * @author SR.Park
 * @since 2019.02.26
 * @param apiRequest API 인터페이스
 */
@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val apiRequest: ApiRequest) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(apiRequest) as T
    }
}