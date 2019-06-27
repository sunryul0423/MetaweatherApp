package com.metaweather.model.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.metaweather.adapter.SearchWeatherAdapter
import com.metaweather.interfaces.ApiRequest
import com.metaweather.model.data.LocationResponse
import com.metaweather.model.data.WeatherData
import com.metaweather.utils.IMG_WEATHER_API
import com.metaweather.utils.LIST_SIZE
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.math.roundToInt


/**
 * 메인화면(검색화면) viewModel
 * @author SR.Park
 * @param apiRequest API Interface
 * @property weatherDataList 날씨 정보 리스트
 * @property searchWeatherAdapter recyclerView Adapter
 */
class MainViewModel(private val apiRequest: ApiRequest) : BaseViewModel() {
    private val _weatherDataList = MutableLiveData<MutableList<WeatherData>>()
    val weatherDataList: LiveData<MutableList<WeatherData>> get() = _weatherDataList
    val searchWeatherAdapter = SearchWeatherAdapter()

    init {
        reqLocationSearch()
    }

    /**
     * 지역검색 API 호출
     */
    private fun reqLocationSearch() {
        progress.value = true
        _weatherDataList.value = mutableListOf<WeatherData>().apply {
            val s = WeatherData()
            this.add(s)
        }
        addDisposable(
            apiRequest.getLocationSearch("se")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ locationSearchResList ->
                    val titleList: MutableList<String> = mutableListOf() // 지역이름 리스트
                    val maybeList: MutableList<Maybe<LocationResponse>> = mutableListOf()
                    locationSearchResList.map {
                        maybeList.add(apiRequest.getLocation(it.woeid))
                        titleList.add(it.title)
                    }


                    val weatherDataList: MutableList<WeatherData> = mutableListOf()
                    var count = 0
                    addDisposable(
                        Maybe.concat(maybeList)
                            .filter { response ->
                                LIST_SIZE <= response.weatherList.size
                            }
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe { response ->
                                val weatherData = WeatherData()
                                weatherData.localTitle = titleList[count]
                                for (i in 0 until LIST_SIZE) {
                                    if (i == 0) {
                                        weatherData.run {
                                            weatherData.todayHumidity = "${response.weatherList[i].humidity}%"
                                            weatherData.todayTemp =
                                                "${response.weatherList[i].temp.roundToInt()}℃"
                                            weatherData.todayWaterName = response.weatherList[i].weatherName
                                            weatherData.todayImageUrl = String.format(
                                                IMG_WEATHER_API,
                                                response.weatherList[i].weatherImg
                                            )
                                        }
                                    } else {
                                        weatherData.run {
                                            weatherData.tomorrowHumidity = "${response.weatherList[i].humidity}%"
                                            weatherData.tomorrowTemp =
                                                "${response.weatherList[i].temp.roundToInt()}℃"
                                            weatherData.tomorrowWaterName = response.weatherList[i].weatherName
                                            weatherData.tomorrowImageUrl = String.format(
                                                IMG_WEATHER_API,
                                                response.weatherList[i].weatherImg
                                            )
                                        }
                                    }
                                }
                                weatherDataList.add(weatherData)
                                count++
                                if (titleList.size == count) {
                                    _weatherDataList.value = weatherDataList
                                    progress.value = false
                                }
                            }
                    )
                }, {
                    errorMsg.value = it.message
                    progress.value = false
                })
        )
    }
}