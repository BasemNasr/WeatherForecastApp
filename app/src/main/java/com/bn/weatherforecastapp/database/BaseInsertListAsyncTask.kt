package com.bn.weatherforecastapp.database

import android.os.AsyncTask


class BaseInsertListAsyncTask<T>(private val baseDao: BaseDao<T>, private val itemList: List<T>) :
    AsyncTask<Void?, Void?, Void?>() {
    override fun doInBackground(vararg params: Void?): Void? {
        baseDao.insert(itemList)
        return null
    }

}