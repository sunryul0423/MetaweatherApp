package com.metaweather

import com.metaweather.di.appModule
import com.metaweather.model.view.MainViewModelFactory
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.get
import org.koin.test.KoinTest
import org.mockito.Mockito.mock

class KoinTest : KoinTest {


    @Before
    fun before() {
        startKoin {
            androidContext(mock(MetaWeatherApplication::class.java))
            modules(appModule)
        }
    }

    @Test
    fun koinModuleTest() {
        val mainViewModelFactory: MainViewModelFactory = get()
        assertNotNull(mainViewModelFactory)
    }

    @After
    fun after() {
        stopKoin()
    }


}