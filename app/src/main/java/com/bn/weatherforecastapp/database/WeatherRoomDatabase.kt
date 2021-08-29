package com.bn.weatherforecastapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bn.weatherforecastapp.utils.Constants
import com.bn.weatherforecastapp.weather.data.converters.CityConventer
import com.bn.weatherforecastapp.weather.data.converters.WeatherConventer
import com.bn.weatherforecastapp.weather.data.dao.WeatherDao
import com.bn.weatherforecastapp.weather.data.entities.WeatherEntity

@Database(entities = [WeatherEntity::class],
    version = 1, exportSchema = false)
@TypeConverters(WeatherConventer::class, CityConventer::class,)
abstract class WeatherRoomDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherRoomDatabase? = null

        fun getDatabase(context: Context): WeatherRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherRoomDatabase::class.java,
                    Constants.KEY_DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }


        }

    }


}