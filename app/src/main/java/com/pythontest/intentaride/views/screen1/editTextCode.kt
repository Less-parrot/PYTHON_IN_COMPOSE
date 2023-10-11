package com.pythontest.intentaride.views.screen1

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContadorLineas(jumpLined: Int) {
    var count by remember { mutableStateOf(0) }
    var textList by remember { mutableStateOf(emptyList<String>()) }
    var previousCount by remember { mutableStateOf(0) }
    var numberPrevious by remember { mutableStateOf(0) }
    Column {

        if (numberPrevious < jumpLined) {
            previousCount = count // Guardar el valor actual del contador
            count++
            numberPrevious ++
            textList = textList + "$count"//enviamos esto a la lista textList

        } else if (numberPrevious > jumpLined) {
            textList = textList.dropLast(1) // Eliminar el último elemento de la lista
            count = previousCount // Restaurar el valor del contador anterior
            previousCount--
            numberPrevious --
        }

        LazyColumn {
            items(textList) { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .padding(start = 5.dp),//dejamos 5 pixeles de densidad entre la margen izquierda y los números
                    fontSize = 15.sp, color = Color(0xFFEAE0C8)//color de contador números
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoTexto(): String {
    var inputText by remember { mutableStateOf("") }//texto escrito en el campo de texto
    var userCode by remember { mutableStateOf<String?>(null) }//Codigo del usuario?
    var lineCount by remember { mutableStateOf(1) } // Contaor de líneas
    var spaceCount by remember { mutableStateOf(0) } // Contador de espacios


    Column(
        modifier = Modifier
            .padding(start = 35.dp, end = 4.dp)//le damos un campo grande en la parte izquierda y uno pequeño a la derecha
    ) {
        OutlinedTextField(
            value = inputText,//valor del campo de texto
            label = { Text(text = "Tu código", color = Color.Red) },//texto para identificar campo de texto
            onValueChange = { newValue ->
                inputText = newValue
                lineCount = inputText.count { it == '\n' } + 1//contamos la cantidad de lineas de texto|Codigo en el campo de texto
                spaceCount = inputText.length// Contar la cantidad de caracteres que tiene el campo de texto
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Red,//color de borde de Campo de Texto cuando se selecciona
                cursorColor = Color.Red,//color del cursor
                unfocusedBorderColor = Color(0xFFEAE0C8)//color borde de Campo de texto cuando no se selecciona
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Default
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Llamar a la función de traducción aquí
                    userCode = inputText
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            // .padding(bottom = 16.dp),
            textStyle = TextStyle(fontSize = 16.sp)
        )
    }

    Box (Modifier.padding(top = 25.dp)){//Hacemos un cuadro con 25 pixeles de densidad entre la margen superior y la función ContadorLineas
        ContadorLineas(jumpLined = lineCount)
    }
    return  inputText
}
