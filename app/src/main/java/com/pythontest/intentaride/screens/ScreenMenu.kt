package com.pythontest.intentaride.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pythontest.intentaride.R

@Composable
fun MenuBottons(navController: NavHostController) {
    var sumarTexto by remember { mutableStateOf(0) }
    var expancion by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
        Box {
            Surface() {
                IconButton(onClick = { expancion = true }) {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
                }
            }
        }

        Box(Modifier.padding(end = 45.dp)) {
            DropdownMenu(
                expanded = expancion,
                onDismissRequest = { expancion = false },
                modifier = Modifier
                    .width(130.dp)
                    .background(Color(0xFF6E7B8B))
            ) {
                MenuItem("Guardar", R.drawable.save) {
                    navController.navigate("screen2")
                }

                MenuItem("Borrar", R.drawable.delete) {
                    navController.navigate("screen3")
                }

                MenuItem("Editar", R.drawable.edit) {
                    navController.navigate("screen3")
                }

                MenuItem("Copiar", R.drawable.copy) {
                    navController.navigate("screen4")
                }

                MenuItem("Pegar", R.drawable.paste) {
                    navController.navigate("screen5")

                }
            }
        }
    }
}

@Composable
fun MenuItem(texto: String,
             imageResource: Int,
             onClickAction: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 10.dp)) {
        Icon(painter = painterResource(id = imageResource), contentDescription = null)
        DropdownMenuItem(text = {
            Text(
                text = texto,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
        }, onClick = onClickAction)
    }
}
