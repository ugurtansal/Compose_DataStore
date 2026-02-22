package com.ugurtansal.compose_datastore.PreferencesDataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class AppDatastore(var context: Context) {
    val Context.ds: DataStore<Preferences>by preferencesDataStore("info")

    companion object{
        val NAME_KEY= stringPreferencesKey("NAME")
        val AGE_KEY= intPreferencesKey("AGE")
        val HEIGHT_KEY= doublePreferencesKey("HEIGHT")
        val ISMARRIED_KEY= booleanPreferencesKey("ISMARRIED")
        val FRIEND_LIST_KEY= stringSetPreferencesKey("FRIEND_LIST")
    }

    suspend fun saveName(name: String){
        context.ds.edit {
            it[NAME_KEY]=name
        }
    }

    suspend fun readName(): String{
      val p=context.ds.data.first()
        return p[NAME_KEY] ?: "Not found"
    }

    suspend fun removeName(){
        context.ds.edit {
            it.remove(NAME_KEY)
        }
    }


    suspend fun saveAge(age: Int){
        context.ds.edit {
            it[AGE_KEY]=age
        }
    }

    suspend fun readAge(): Int{
        val p=context.ds.data.first()
        return p[AGE_KEY] ?: 0
    }


    suspend fun saveHeight(height: Double){
        context.ds.edit {
            it[HEIGHT_KEY]=height
        }
    }

    suspend fun readHeight(): Double{
        val p=context.ds.data.first()
        return p[HEIGHT_KEY] ?: 0.0
    }


    suspend fun saveMarried(isMarried: Boolean){
        context.ds.edit {
            it[ISMARRIED_KEY]=isMarried
        }
    }

    suspend fun readMarried(): Boolean{
        val p=context.ds.data.first()
        return p[ISMARRIED_KEY] ?: false
    }

    suspend fun saveFriendList(friendList: Set<String>){
        context.ds.edit {
            it[FRIEND_LIST_KEY]=friendList
        }
    }

    suspend fun readFriendList(): Set<String>{
        val p=context.ds.data.first()
        return p[FRIEND_LIST_KEY] ?: emptySet()
    }
}