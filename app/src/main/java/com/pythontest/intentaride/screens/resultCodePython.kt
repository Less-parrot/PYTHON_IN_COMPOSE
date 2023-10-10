package com.pythontest.intentaride.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Screen2(navController: NavHostController, resulCode: String){
    Surface (color = Color.DarkGray){
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center ) {
            Column {
                Button(onClick = {
                    navController.navigate("screen1")
                }) {
                Text("Regresar a la pantalla 1")
                    //todo
                }
                Text(text = resulCode, color = Color.White, fontSize = 30.sp)
            }
        }
    }
}
