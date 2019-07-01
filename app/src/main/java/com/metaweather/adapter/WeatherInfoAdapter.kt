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
 *  날씨정보 RecyclerView Adapter Item 설정
 * @see R.layout.activity_main
 * @param view recyclerView
 * @param weatherDataList 날씨정보 리스트
 */
@BindingAdapter("weatherDataList")
fun setWeatherData(view: RecyclerView, weatherDataList: List<WeatherData>?) {
    val weatherItemVMList: MutableList<WeatherItemVM> = mutableListOf()
    weatherDataList?.map { weatherData ->
        with(WeatherItemVM()) {
            setLocalTitle(weatherData.localTitle)
            setTodayWeatherName(weatherData.todayWaterName)
            setTodayTemp(weatherData.todayTemp)
            setTodayHumidity(weatherData.todayHumidity)
            setTodayImageUrl(weatherData.todayImageUrl)
            setTomorrowWeatherName(weatherData.tomorrowWaterName)
            setTomorrowTemp(weatherData.tomorrowTemp)
            setTomorrowHumidity(weatherData.tomorrowHumidity)
            setTomorrowImageUrl(weatherData.tomorrowImageUrl)
            weatherItemVMList.add(this)
        }
    }
    with(view) {
        if (adapter == null) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = WeatherInfoAdapter().apply {
                addItem(weatherItemVMList)
            }
        } else {
            (adapter as WeatherInfoAdapter).addItem(weatherItemVMList)
        }
    }
}

/**
 * Header ViewHolder
 */
class LocationHeaderHolder(val binding: ViewHeaderWeatherItemBinding) : RecyclerView.ViewHolder(binding.root)

/**
 * 날씨정보 ViewHolder
 */
class LocationHolder(val binding: ViewWeatherItemBinding) : RecyclerView.ViewHolder(binding.root)

/**
 * 날씨정보 RecyclerView Adapter
 * @author SR.Park
 * @since 2019.02.26
 * @property weatherItemVM 아이템  RecyclerView Item 리스트
 */
class WeatherInfoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val weatherItemVM: MutableList<WeatherItemVM> = mutableListOf()

    /**
     * Item 리스트 Setting
     * @param _weatherItemVM Item 리스트
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
        return if (weatherItemVM.isEmpty()) {
            0
        } else {
            weatherItemVM.size + TYPE_HEADER_COUNT
        }
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