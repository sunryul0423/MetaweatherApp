package com.metaweather.model.data

data class WeatherData(
    var localTitle: String = "",
    var todayWaterName: String = "",
    var todayTemp: String = "",
    var todayHumidity: String = "",
    var todayImageUrl: String = "",
    var tomorrowWaterName: String = "",
    var tomorrowTemp: String = "",
    var tomorrowHumidity: String = "",
    var tomorrowImageUrl: String = ""
)