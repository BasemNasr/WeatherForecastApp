package com.bn.weatherforecastapp.database

import android.os.AsyncTask


class BaseDeleteItemAsyncTask<T>(private val baseDao: BaseDao<T>, private val item: T) :
    AsyncTask<Void?, Void?, Void?>() {
    override fun doInBackground(vararg params: Void?): Void? {
        baseDao.delete(item)
        return null
    }

}