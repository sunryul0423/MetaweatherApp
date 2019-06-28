package com.metaweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.metaweather.R
import com.metaweather.activity.MainActivity
import com.metaweather.databinding.ViewHeaderWeatherItemBinding
import com.metaweather.databinding.ViewWeatherItemBinding
import com.metaweather.model.data.WeatherData
import com.metaweather.model.view.WeatherItemVM
import com.metaweather.utils.TYPE_HEADER_COUNT

/**
 * 날씨 검색 RecyclerView Adapter 설정
 * @see R.layout.activity_main
 * @param view recyclerView
 * @param weatherInfoAdapter 날씨 검색 RecyclerView Adapter
 */
@BindingAdapter("weatherInfoAdapter")
fun setWeatherInfoAdapter(view: RecyclerView, weatherInfoAdapter: WeatherInfoAdapter?) {
    weatherInfoAdapter?.let {
        with(view) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = it
//            ContextCompat.getDrawable(context, R.drawable.divider_drawable)?.let { drawable ->
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
        it.map { weatherData ->
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
        (view.adapter as WeatherInfoAdapter).addItem(weatherItemVMList)
    }
}

/**
 **
 * 날씨정보 ViewHolder
 * lifecycle에 맞게 사용하기 위해 ViewModelProviders로 Factory 생성
 */
class LocationHeaderHolder(val binding: ViewHeaderWeatherItemBinding) : RecyclerView.ViewHolder(binding.root)

/**
 * 날씨정보 ViewHolder
 * lifecycle에 맞게 사용하기 위해 ViewModelProviders로 Factory 생성
 */
class LocationHolder(val binding: ViewWeatherItemBinding) : RecyclerView.ViewHolder(binding.root)

/**
 * 날씨정보 RecyclerView Adapter
 * @author SR.Park
 * @constructor Kakao Image Api
 * @since 2019.02.21
 * @property weatherItemVM 아이템  RecyclerView의 Item 리스트
 */
class WeatherInfoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val weatherItemVM: MutableList<WeatherItemVM> = mutableListOf()

    /**
     * Item 리스트 Setting
     * @param _weatherItemVM Setting할 Item 리스트
     */
    fun addItem(_weatherItemVM: List<WeatherItemVM>) {
        if (weatherItemVM.isNotEmpty()) {
            weatherItemVM.clear()
        }
        weatherItemVM.addAll(_weatherItemVM)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            R.layout.view_header_weather_item
        } else {
            R.layout.view_weather_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder: RecyclerView.ViewHolder
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        when (viewType) {
            R.layout.view_header_weather_item -> holder = LocationHeaderHolder(binding as ViewHeaderWeatherItemBinding)
            else -> holder = LocationHolder(binding as ViewWeatherItemBinding)
        }
        return holder
    }

    override fun getItemCount(): Int {
        return weatherItemVM.size + TYPE_HEADER_COUNT
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LocationHeaderHolder -> holder.binding.lifecycleOwner = holder.binding.root.context as MainActivity
            is LocationHolder -> {
                holder.binding.weatherItemVM = weatherItemVM[position - TYPE_HEADER_COUNT]
                holder.binding.lifecycleOwner = holder.binding.root.context as MainActivity
            }
        }
    }
}