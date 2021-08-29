package com.bn.weatherforecastapp.utils

import android.app.Application
import android.content.SharedPreferences


class App : Application() {

    companion object {
        private var instance: App? = null
        private var sharedPreferences: SharedPreferences? = null
        private var editor: SharedPreferences.Editor? = null
        private var PREF_NAME: String? = null
        private const val PACK_NAME = "mahalla.bn.com.mahallastore"
        private const val KEY_USER = "USER"

        //    private static final String KEY_FINANCE_STATS = "FinanceStats";
        /////
        private const val KEY_LAST_CITY_NAME = "last_city"

    }

    override fun onCreate() {
        super.onCreate()
        instance = this;
        initializeSharedPreferencesSession()

    }

    private fun initializeSharedPreferencesSession() {
        PREF_NAME = PACK_NAME + ".WEATHER"
        sharedPreferences = getSharedPreferences(PREF_NAME, 0)
        editor = sharedPreferences!!.edit()
    }

    fun saveCityName(cityName: String?) {
        editor!!.putString(KEY_LAST_CITY_NAME, cityName).apply()
    }

    fun getLastCityName():String?{
        return sharedPreferences!!.getString(KEY_LAST_CITY_NAME, "")
    }


}
