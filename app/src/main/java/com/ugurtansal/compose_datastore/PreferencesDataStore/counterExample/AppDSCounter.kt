package com.ugurtansal.compose_datastore.PreferencesDataStore.counterExample

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class AppDSCounter (var context: Context){

    val Context.ds: DataStore<Preferences>by preferencesDataStore("info")

    companion object{
        val COUNTER_KEY= intPreferencesKey("COUNTERKEY")
    }

    suspend fun getCounter():Int{
        val p=context.ds.data.first()
        return p[COUNTER_KEY] ?: 0
    }

    suspend fun increaseCounter(){
        context.ds.edit {
            it[COUNTER_KEY]=this.getCounter()+1
        }
    }
}