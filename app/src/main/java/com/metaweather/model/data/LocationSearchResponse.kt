package com.metaweather.model.data

import com.google.gson.annotations.SerializedName

/**
 * 지역날씨 검색 API Response
 * @param woeId Where On Earth ID
 */
data class LocationSearchResponse(
    @SerializedName("woeid")
    val woeId: Int)