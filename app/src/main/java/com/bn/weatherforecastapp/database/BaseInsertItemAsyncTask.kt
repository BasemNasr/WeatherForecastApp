package com.bn.weatherforecastapp.database

import android.os.AsyncTask


class BaseInsertItemAsyncTask<T>(private val baseDao: BaseDao<T>, private val item: T) :
    AsyncTask<Void?, Void?, Void?>() {
    override fun doInBackground(vararg params: Void?): Void? {
        baseDao.insert(item)
        return null
    }

}