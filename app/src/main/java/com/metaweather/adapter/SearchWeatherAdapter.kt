package com.metaweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.metaweather.R
import com.metaweather.activity.MainActivity
import com.metaweather.databinding.ViewWeatherItemBinding
import com.metaweather.model.data.WeatherData
import com.metaweather.model.view.WeatherItemVM
import com.metaweather.model.view.WeatherItemViewModelFactory

/**
 * 날씨 검색 RecyclerView Adapter 설정
 * @see R.layout.activity_main
 * @param view recyclerView
 * @param searchWeatherAdapter 날씨 검색 RecyclerView Adapter
 */
@BindingAdapter("searchWeatherAdapter")
fun setSearchWeatherAdapter(view: RecyclerView, searchWeatherAdapter: SearchWeatherAdapter?) {
    searchWeatherAdapter?.let {
        with(view) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = it
//            ContextCompat.getDrawable(context, R.drawable.recycler_view_under_background)?.let { drawable ->
//                val dividerItemDecoration =
//                    DividerItemDecoration(context, LinearLayoutManager(context).orientation).apply {
//                        setDrawable(drawable)
//                    }
//                addItemDecoration(dividerItemDecoration)
//            }
        }
    }
}

/**
 *  날씨 검색 RecyclerView Adapter Item 설정
 * @see R.layout.activity_main
 * @param view recyclerView
 * @param weatherDataList 날씨정보 리스트
 */
@BindingAdapter("weatherDataList")
fun setWeatherData(view: RecyclerView, weatherDataList: List<WeatherData>?) {
    weatherDataList?.let {
        val weatherItemVMList: MutableList<WeatherItemVM> = mutableListOf()
        weatherDataList.map { weatherData ->
            with(WeatherItemVM()) {
                setLocalTitle(weatherData.localTitle)
                setTodayWatherName(weatherData.todayWaterName)
                setTodayTemp(weatherData.todayTemp)
                setTodayHumidity(weatherData.todayHumidity)
                setTodayImageUrl(weatherData.todayImageUrl)
                setTomorrowWatherName(weatherData.tomorrowWaterName)
                setTomorrowTemp(weatherData.tomorrowTemp)
                setTomorrowHumidity(weatherData.tomorrowHumidity)
                setTomorrowImageUrl(weatherData.tomorrowImageUrl)
                weatherItemVMList.add(this)
            }
        }
        (view.adapter as SearchWeatherAdapter).addItem(weatherItemVMList)
    }
}


/**
 * 이미지 검색 결과 ViewHolder
 * lifecycle에 맞게 사용하기 위해 ViewModelProviders로 Factory 생성
 */
class LocationHolder(val binding: ViewWeatherItemBinding) : RecyclerView.ViewHolder(binding.root)

/**
 * 이미지 검색 결과 RecyclerView Adapter
 * @author SR.Park
 * @constructor Kakao Image Api
 * @since 2019.02.21
 * @property weatherItemVM 아이템  RecyclerView의 Item 리스트
 */
class SearchWeatherAdapter : RecyclerView.Adapter<LocationHolder>() {

    private var weatherItemVM: MutableList<WeatherItemVM> = mutableListOf()

    /**
     * Item 리스트 Setting
     * @param _weatherItemVM Setting할 Item 리스트
     */
    fun addItem(_weatherItemVM: List<WeatherItemVM>) {
        weatherItemVM.addAll(_weatherItemVM)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationHolder {
        val binding: ViewWeatherItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.view_weather_item, parent, false)
        return LocationHolder(binding)
    }

    override fun getItemCount(): Int {
        return weatherItemVM.size
    }

    override fun onBindViewHolder(holder: LocationHolder, position: Int) {
        holder.binding.weatherItemVM = weatherItemVM[position]
        holder.binding.lifecycleOwner = holder.binding.root.context as MainActivity
    }
}