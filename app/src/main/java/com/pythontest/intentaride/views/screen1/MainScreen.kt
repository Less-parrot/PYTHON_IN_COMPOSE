package com.pythontest.intentaride.views.screen1

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.chaquo.python.PyException
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.pythontest.intentaride.R

@Composable
fun MainScreen(context: Context, navController: NavHostController): String {
    var userCode by remember { mutableStateOf("") }//codigo de usario
    var result by remember { mutableStateOf("") }//output codigo
    //hacemos que el resultado que de el campo de texto, sea el código a mirar
    userCode = CampoTexto()
    //Si no se a iniciado python, lo iniciamos
    if (!Python.isStarted()) {
        Python.start(AndroidPlatform(context))
    }
    //--------Variables de Python-----------
    val py = Python.getInstance()
    val module = py.getModule("plot")
    //--------------------------------------
    Box(Modifier.fillMaxSize()) {//cuadro padre(almacena todo)
        //En este cuadro incluimos el boton de play para ejecutar el código
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd//montamos arriba a la derecha
        ) {
            IconButton(onClick = {
                try {

                    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    val currentFocus = (context as? Activity)?.currentFocus
                    currentFocus?.let {
                        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
                    }
                    val bytes = module.callAttr("plot", userCode)
                    //actualizamos la variable result, con lo que sea de resultado nuevo
                    result = bytes.toString()

                    //Nos dirigimos a la pantalla 2 y mostramos el Código
                    navController.navigate("screen2")
                }
                //mostramos el error en un mensaje emerjente
                catch (e: PyException) {
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }
            })
            {
                IconGo()
            }
        }
    }
    return result
}

@Composable
fun IconGo() {
    Icon(
        //pintamos el icono para ejecutar el código python
        painter = painterResource(id = R.drawable.play),
        contentDescription = "Icono Código Python"
    )
}