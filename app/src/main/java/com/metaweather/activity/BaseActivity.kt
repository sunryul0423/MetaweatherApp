package com.metaweather.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.metaweather.R

/**
 * AppCompatActivity를 Base로 공통적으로 필요한 변수,함수 정의
 * @author SR.Park
 * @constructor Kakao Image Api
 * @since 2019.02.20
 * @property viewBinding Binding ContentView
 * @property layoutResourceId ContentView ID
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var viewBinding: T
    protected abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, layoutResourceId)
    }

    /**
     * 네트워크 에러발생 Toast 메세지
     */
    protected fun networkErrorToast() {
        Toast.makeText(this, getString(R.string.network_error_msg), Toast.LENGTH_LONG).show()
    }
}