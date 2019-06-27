package com.metaweather.utils

/**
 * 기본 URL
 */
const val BASE_URL: String = "https://www.metaweather.com/"
/**
 * location search api
 */
const val LOCATION_SEARCH_API: String = "api/location/search/"
const val LOCATION_API: String = "api/location/{woeid}/"
const val IMG_WEATHER_API: String = "${BASE_URL}static/img/weather/png/%s.png"

/**
 * query 상수
 */
const val QUERY: String = "query"
/**
 * woeid 상수
 */
const val WOEID: String = "woeid"
const val LIST_SIZE = 2