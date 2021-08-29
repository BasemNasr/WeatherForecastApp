package com.bn.weatherforec

import android.os.Build.VERSION_CODES.Q
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bn.kotlinproject.WeathesAdapter
import com.bn.weatherforecastapp.database.WeatherRoomDatabase
import com.bn.weatherforecastapp.weather.data.ViewModel.WeatherViewModel
import com.bn.weatherforecastapp.weather.data.repo.WeatherRepository
import com.bn.weatherforecastapp.weather.ui.activities.MainActivity
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Q])
@LooperMode(LooperMode.Mode.PAUSED)
 class ExampleUnitTest   {



    lateinit var activity: MainActivity


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        activity = Robolectric.setupActivity(MainActivity::class.java)

    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun checkFeathcingDataWithValidCityName() {
        var weatherViewModel= ViewModelProviders.of(activity).get(WeatherViewModel::class.java)
        val correctCityName = "mansura"
        val objectUnderTest =  weatherViewModel.getWeatherEnitityByCityName(correctCityName)
        //when
        objectUnderTest.observe(activity, Observer {
            if (it!=null){
                assert(true)
            }else{
                assert(false)
            }

        })

    }

    @Test
    fun checkFeathcingData_WithInValidCityName() {
        var weatherViewModel= ViewModelProviders.of(activity).get(WeatherViewModel::class.java)
        val correctCityName = "dasdsadasxas"
        val objectUnderTest =  weatherViewModel.getWeatherEnitityByCityName(correctCityName)
        //when
        objectUnderTest.observe(activity, Observer {
            if (it!=null){
                assert(true)
            }else{
                assert(false)
            }

        })

    }

}