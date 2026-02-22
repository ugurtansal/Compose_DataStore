package com.ugurtansal.compose_datastore.PreferencesDataStore

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ugurtansal.compose_datastore.ui.theme.Compose_DataStoreTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.Hashtable

class MainPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_DataStoreTheme {
                MainPageStore()
            }
        }
    }
}

@Composable
fun MainPageStore() {
    val context= LocalContext.current
    val aDs= AppDatastore(context)

    LaunchedEffect(Unit) {
        val job: Job= CoroutineScope(Dispatchers.Main).launch {

            //Records
            aDs.saveName("Ahmet")
            aDs.saveAge(25)
            aDs.saveHeight(170.5)
            aDs.saveMarried(true)

            val list=HashSet<String>()
            list.add(   "Mehmet")
            list.add("Fatma")
            aDs.saveFriendList(list)

            //Read
            val name=aDs.readName()
            val age=aDs.readAge()
            val savedHeight=aDs.readHeight()
            val savedMarried=aDs.readMarried()
            val savedFriends=aDs.readFriendList()




        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Compose_DataStoreTheme {
        MainPageStore()
    }
}