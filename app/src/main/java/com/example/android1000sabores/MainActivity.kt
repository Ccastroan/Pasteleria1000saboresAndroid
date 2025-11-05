package com.example.android1000sabores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.android1000sabores.ui.theme.Android1000SaboresTheme

import android.os.Looper
import android.os.Handler
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import com.example.android1000sabores.ui.screens.login.LoginScreen
import com.example.android1000sabores.ui.screens.splash.SplashScreen



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAPP()
        }
    }
}

@Composable
fun MyAPP() {
    var showLogin by rememberSaveable{ mutableStateOf(false) }

    val handler = remember { Handler(Looper.getMainLooper()) }
    LaunchedEffect(Unit){
        handler.postDelayed({showLogin = true}, 2000L)
    }
    MaterialTheme{
        Surface {
            if(!showLogin){
                SplashScreen()
        } else {
                LoginScreen()}
            }
        }
    }