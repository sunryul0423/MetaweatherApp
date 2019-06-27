package com.metaweather.model.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class WeatherItemVM : BaseViewModel() {
    private val _localTitle = MutableLiveData<String>()
    private val _todayWaterName = MutableLiveData<String>()
    private val _todayTemp = MutableLiveData<String>()
    private val _todayHumidity = MutableLiveData<String>()
    private val _todayImageUrl = MutableLiveData<String>()
    private val _tomorrowWaterName = MutableLiveData<String>()
    private val _tomorrowTemp = MutableLiveData<String>()
    private val _tomorrowHumidity = MutableLiveData<String>()
    private val _tomorrowImageUrl = MutableLiveData<String>()

    val localTitle: LiveData<String> get() = _localTitle
    val todayWaterName: LiveData<String> get() = _todayWaterName
    val todayTemp: LiveData<String> get() = _todayTemp
    val todayHumidity: LiveData<String> get() = _todayHumidity
    val todayImageUrl: LiveData<String> get() = _todayImageUrl
    val tomorrowWaterName: LiveData<String> get() = _tomorrowWaterName
    val tomorrowTemp: LiveData<String> get() = _tomorrowTemp
    val tomorrowHumidity: LiveData<String> get() = _tomorrowHumidity
    val tomorrowImageUrl: LiveData<String> get() = _tomorrowImageUrl

    fun setLocalTitle(localTitle: String) {
        _localTitle.value = localTitle
    }

    fun setTodayWatherName(todayWatherName: String) {
        _todayWaterName.value = todayWatherName
    }

    fun setTodayTemp(todayTemp: String) {
        _todayTemp.value = todayTemp
    }

    fun setTodayHumidity(todayHumidity: String) {
        _todayHumidity.value = todayHumidity
    }

    fun setTodayImageUrl(todayImageUrl: String) {
        _todayImageUrl.value = todayImageUrl
    }

    fun setTomorrowWatherName(tomorrowWatherName: String) {
        _tomorrowWaterName.value = tomorrowWatherName
    }

    fun setTomorrowTemp(tomorrowTemp: String) {
        _tomorrowTemp.value = tomorrowTemp
    }

    fun setTomorrowHumidity(tomorrowHumidity: String) {
        _tomorrowHumidity.value = tomorrowHumidity
    }

    fun setTomorrowImageUrl(tomorrowImageUrl: String) {
        _tomorrowImageUrl.value = tomorrowImageUrl
    }

}

