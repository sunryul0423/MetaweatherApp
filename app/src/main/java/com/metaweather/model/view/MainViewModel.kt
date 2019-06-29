package com.metaweather.model.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.metaweather.interfaces.ApiRequest
import com.metaweather.model.data.LocationResponse
import com.metaweather.model.data.WeatherData
import com.metaweather.utils.IMG_WEATHER_API
import com.metaweather.utils.LIST_SIZE
import com.metaweather.utils.SEARCH_TEXT
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.math.roundToInt


/**
 * 메인화면 viewModel
 * @author SR.Park
 * @param apiRequest API Interface
 * @property weatherDataList 날씨 정보 리스트
 */
class MainViewModel(private val apiRequest: ApiRequest) : BaseViewModel() {
    private val _isRefresh = MutableLiveData<Boolean>()
    private val _weatherDataList = MutableLiveData<MutableList<WeatherData>>()

    val isRefresh: LiveData<Boolean> get() = _isRefresh
    val weatherDataList: LiveData<MutableList<WeatherData>> get() = _weatherDataList

    init {
        reqLocationSearch(true)
    }

    /**
     * 지역검색 API 호출
     * @param showProgress progress 사용여부 (true:Progress View, false:SwipeRefreshLayout)
     */
    fun reqLocationSearch(showProgress: Boolean) {
        if (showProgress) {
            _showProgress.value = true
        } else {
            _weatherDataList.value = null // Refresh 중 RecyclerView 초기화
        }
        addDisposable(
            apiRequest.getLocationSearch(SEARCH_TEXT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ searchResponse ->

                    val weatherDataList: MutableList<WeatherData> = mutableListOf() // 날씨정보 리스트
                    val maybeList: MutableList<Maybe<LocationResponse>> = mutableListOf() // 검색한 지역 리스트
                    searchResponse.map {
                        maybeList.add(apiRequest.getLocation(it.woeid))
                    }

                    addDisposable(
                        Maybe.concat(maybeList)
                            .filter { response ->
                                LIST_SIZE <= response.weatherList.size //API 결과가 (월,화)2개보다 크거나 같을 때
                            }
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe { response ->

                                with(WeatherData()) {
                                    this.localTitle = response.title
                                    for (i in 0 until LIST_SIZE) {
                                        if (i == 0) {
                                            this.run {
                                                this.todayHumidity = "${response.weatherList[i].humidity}%"
                                                this.todayTemp =
                                                    "${response.weatherList[i].temp.roundToInt()}℃"
                                                this.todayWaterName = response.weatherList[i].weatherName
                                                this.todayImageUrl = String.format(
                                                    IMG_WEATHER_API,
                                                    response.weatherList[i].weatherImg
                                                )
                                            }
                                        } else {
                                            this.run {
                                                this.tomorrowHumidity = "${response.weatherList[i].humidity}%"
                                                this.tomorrowTemp =
                                                    "${response.weatherList[i].temp.roundToInt()}℃"
                                                this.tomorrowWaterName = response.weatherList[i].weatherName
                                                this.tomorrowImageUrl = String.format(
                                                    IMG_WEATHER_API,
                                                    response.weatherList[i].weatherImg
                                                )
                                            }
                                        }
                                    }
                                    weatherDataList.add(this)
                                    // 검색한 지역 수와 날씨정보 결과의 수가 같으면 실행
                                    if (maybeList.size == weatherDataList.size) {
                                        _weatherDataList.value = weatherDataList
                                        _showProgress.value?.let {
                                            if (it) {
                                                _showProgress.value = false
                                            } else {
                                                _isRefresh.value = false
                                            }
                                        }
                                    }

                                }
                            }
                    )
                }, {
                    errorMsg.value = it.message
                    _showProgress.value = false
                })
        )
    }
}