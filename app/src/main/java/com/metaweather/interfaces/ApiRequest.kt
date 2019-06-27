package com.metaweather.interfaces

import com.metaweather.model.data.LocationResponse
import com.metaweather.model.data.LocationSearchResponse
import com.metaweather.utils.LOCATION_API
import com.metaweather.utils.LOCATION_SEARCH_API
import com.metaweather.utils.QUERY
import com.metaweather.utils.WOEID
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * API 호출 Request 인터페이스
 * @author SR.Park
 * @constructor Kakao Image Api
 * @since 2019.02.20
 * @property getLocationSearch 이미지 검색 API 호출
 */
interface ApiRequest {

    /**
     * 카카오 이미지 검색 API
     */
    @GET(LOCATION_SEARCH_API)
    fun getLocationSearch(@Query(QUERY) searchText: String): Single<List<LocationSearchResponse>>

    @GET(LOCATION_API)
    fun getLocation(@Path(WOEID) woeid: Int): Maybe<LocationResponse>
}