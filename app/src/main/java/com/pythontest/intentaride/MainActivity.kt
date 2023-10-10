package com.pythontest.intentaride

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chaquo.python.PyException
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.pythontest.intentaride.screens.CampoTexto
import com.pythontest.intentaride.screens.Screen2
import com.pythontest.intentaride.ui.theme.IntentarIDETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntentarIDETheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF354E72)//color de fondo
                ) {
                    var userCode by remember { mutableStateOf("") }//codigo de usario
                    var result by remember { mutableStateOf("") }//output codigo
                    //hacemos que el resultado que de el campo de texto, sea el código a mirar
                    userCode = CampoTexto()
                    //Si no se a iniciado python, lo iniciamos
                    if (!Python.isStarted()) {
                        Python.start(AndroidPlatform(this))
                    }
                    //--------Variables de Python-----------
                    val py = Python.getInstance()
                    val module = py.getModule("plot")
                    //--------------------------------------

                    //-------------------Controlador De Pantallas----------------------------
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "screen1") {
                        composable("screen1") { navController}
                        /*cuando nos dirijamos a la pantalla 2, le pasaremos el resultado del
                        Codigo para que sea mostrado al usario*/
                        composable("screen2") { Screen2(navController, result) }
                    }
                    //-----------------------------------------------------------------------

                    Box(Modifier.fillMaxSize()) {//cuadro padre(almacena todo)
                        //En este cuadro incluimos el boton de play para ejecutar el código
                        Box(
                            Modifier.fillMaxSize(),
                            contentAlignment = Alignment.TopEnd//montamos arriba a la derecha
                        ) {
                            IconButton(onClick = {
                                try {
                                    /*hacemos variable que llama a la función y al código
                                    a ser ejecutados y mostrados*/
                                    val bytes = module.callAttr("plot", userCode)
                                    //actualizamos la variable result, con lo que sea de resultado nuevo
                                    result = bytes.toString()

                                    currentFocus?.let {
                                        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                                            .hideSoftInputFromWindow(it.windowToken, 0)
                                    }
                                    //Nos dirigimos a la pantalla 2 y mostramos el Código
                                    navController.navigate("screen2")
                                }
                                //mostramos el error en un mensaje emerjente
                                catch (e: PyException) {
                                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
                                }
                            })
                            {
                                Icon(
                                    //pintamos el icono para ejecutar el código python
                                    painter = painterResource(id = R.drawable.play),
                                    contentDescription = "Icono Código Python"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
