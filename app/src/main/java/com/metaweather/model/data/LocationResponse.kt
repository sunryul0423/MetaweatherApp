package com.metaweather.model.data

import com.google.gson.annotations.SerializedName

/**
 * 지역날씨 검색 API Response
 * @param response 결과 리스트
 */
data class LocationResponse(
    @SerializedName("consolidated_weather")
    val weatherList: List<ConsolidatedWeather>
) {

    /**
     * @param weatherName 날씨 요약
     * @param weatherImg 아이콘 이미지 정보
     * @param temp 현재 날씨
     * @param humidity 습도
     */
    data class ConsolidatedWeather(
        @SerializedName("weather_state_name")
        val weatherName: String,
        @SerializedName("weather_state_abbr")
        val weatherImg: String,
        @SerializedName("the_temp")
        val temp: Double,
        val humidity: Int
    )
}