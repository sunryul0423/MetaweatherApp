package com.metaweather.model.data

/**
 * 지역날씨 검색 API Response
 * @param woeid Where On Earth ID
 */
data class LocationSearchResponse(val title: String, val woeid: Int) {
}