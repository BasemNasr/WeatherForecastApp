package com.bn.weatherforecastapp.weather.data.repo

import android.app.Activity
import android.app.Application
import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.bn.kotlinproject.webservices.WebService
import com.bn.weatherforecastapp.database.WeatherRoomDatabase
import com.bn.weatherforecastapp.utils.App
import com.bn.weatherforecastapp.utils.Constants
import com.bn.weatherforecastapp.weather.data.background.UpdateWeatherEntityAsyncTask
import com.bn.weatherforecastapp.weather.data.dao.WeatherDao
import com.bn.weatherforecastapp.weather.data.entities.WeatherEntity
import com.bn.weatherforecastapp.weather.data.models.City
import com.bn.weatherforecastapp.weather.data.models.WeatherData
import com.bn.weatherforecastapp.weather.data.models.response.WeatherResponse
import com.bn.weatherforecastapp.weather.events.ShowDialogEvent
import com.bn.weatherforecastapp.weather.events.ShowRetryBtnEvent
import okhttp3.ResponseBody
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class WeatherRepository (private var weatherDao:WeatherDao,
    private val application: Application) {
    private var context: Context
    private var db: WeatherRoomDatabase? = null

    private val webService: WebService
    private var mWeatherEntity: LiveData<WeatherEntity>? = null
    init {
        this.context = application.applicationContext
        mWeatherEntity = weatherDao.getWeatherEntity()
        this.webService = WebService()
    }

    fun getWeatherEntityByCityName(cityName: String): LiveData<WeatherEntity> {
        loadWeathers(cityName)
        return weatherDao.getWeatherEntityByCityName(cityName)
    }



     fun updateWeatherEntity(weatherEntity: WeatherEntity){
        UpdateWeatherEntityAsyncTask(weatherDao, weatherEntity).execute()

    }

    fun loadWeathers(cityName:String) {
        EventBus.getDefault().post(ShowDialogEvent(Constants.OPEN_DIALOG_TYPE))

        Log.e("loadWeathers", "calling")
        webService.getAPI().getCurrentWeatherData(cityName, Constants.APP_ID).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.code() == 200) {

                    val weatherResponse: WeatherResponse = response.body()

                    if(weatherResponse!=null){
                        var weatherDataList:List<WeatherData> = weatherResponse.list
                        var CityInfo:City = weatherResponse.city
                        val weatherEntity =  WeatherEntity(cityName,weatherDataList,CityInfo)
                        updateWeatherEntity(weatherEntity)
                        App().saveCityName(CityInfo.name);

                    }

                }else if(response.code()==404){
                    EventBus.getDefault().post(ShowRetryBtnEvent(Constants.NOT_FOUND_CITY_TYPE));
                }
                EventBus.getDefault().post(ShowDialogEvent(Constants.DISMISS_DIALOG_TYPE))
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Toast.makeText(context,"حدث مشكلة في جلب البيانات", Toast.LENGTH_SHORT).show()
                EventBus.getDefault().post(ShowRetryBtnEvent(Constants.ERROR_INTERNET_TYPE))
                EventBus.getDefault().post(ShowDialogEvent(Constants.DISMISS_DIALOG_TYPE))

            }

        })
    }


}