package com.ugurtansal.compose_datastore.PreferencesDataStore.counterExample

import com.ugurtansal.compose_datastore.PreferencesDataStore.AppDatastore


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ugurtansal.compose_datastore.ui.theme.Compose_DataStoreTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.Hashtable

class MainCounter : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_DataStoreTheme {
                MainCounterPage()
            }
        }
    }
}

@Composable
fun MainCounterPage() {
    val context= LocalContext.current
    val aDC= AppDSCounter(context)
    val counter= remember{ mutableStateOf(0) }

    LaunchedEffect(Unit) {
        val job: Job= CoroutineScope(Dispatchers.Main).launch {

            aDC.increaseCounter()
            counter.value=aDC.getCounter()
        }

    }


    Column (Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, Alignment.CenterHorizontally){

        Text("Number of page openings ${counter.value}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}

