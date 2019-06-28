package com.metaweather.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.metaweather.R
import com.metaweather.databinding.ActivityMainBinding
import com.metaweather.model.view.MainViewModel
import com.metaweather.model.view.MainViewModelFactory
import org.koin.android.ext.android.inject

/**
 * 메인화면(검색화면) View
 * @author SR.Park
 * @constructor Kakao Image Api
 * @since 2019.02.20
 * @property mainViewModelFactory viewModel Factory
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    private val mainViewModelFactory: MainViewModelFactory by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel = ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel::class.java)

        // LiveData observe
        mainViewModel.networkError.observe(this, Observer {
            networkErrorToast()
        })
        mainViewModel.showProgress.observe(this, Observer {
            if (it) {
                progressDialog.show()
            } else {
                progressDialog.cancel()
            }
        })
        viewBinding.mainViewModel = mainViewModel
        viewBinding.lifecycleOwner = this
    }
}
