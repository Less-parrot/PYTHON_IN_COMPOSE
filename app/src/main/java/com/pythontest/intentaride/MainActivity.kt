package com.pythontest.intentaride

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pythontest.intentaride.ui.theme.IntentarIDETheme
import com.pythontest.intentaride.views.screen1.MainScreen
import com.pythontest.intentaride.views.screen2.BackMainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntentarIDETheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF354E72)//color de fondo
                ) {
                    NavHostControllerScreens(context = this)
                }
            }
        }
    }
}

@Composable
fun NavHostControllerScreens(context: Context ) {
    //-------------------Controlador De Pantallas----------------------------
    val navController = rememberNavController()
    val result = MainScreen(context = context, navController = navController)
    NavHost(navController = navController, startDestination = "screen1") {
        composable("screen1") { result }
        composable("screen2") { BackMainScreen(navController, result) }
    }
}



