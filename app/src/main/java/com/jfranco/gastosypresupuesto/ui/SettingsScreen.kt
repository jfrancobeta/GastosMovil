package com.jfranco.gastosypresupuesto.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jfranco.gastosypresupuesto.view.SettingsViewModel

@Composable
fun SettingsScreen(navController: NavController, settingModel: SettingsViewModel){


    val backgroundColor by settingModel.backgroundColor.collectAsState()
    val textSize by settingModel.textSize.collectAsState()

    val colorOptions = listOf(Color.White, Color.LightGray, Color.Blue, Color.Green, Color.Yellow)
    val sizeOptions = listOf(14f, 16f, 18f, 20f, 22f)

    var expandedColor by remember { mutableStateOf(false) }
    var expandedSize by remember { mutableStateOf(false) }


    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Configuracion", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))


        Box(){
            Button(onClick = {expandedColor=true}) {
                Text("Color De Fondo")
            }

            DropdownMenu(
                expanded = expandedColor,
                onDismissRequest = { expandedColor = false }

            ) {
                colorOptions.forEach { color ->
                    DropdownMenuItem(
                        text = { Box(Modifier.size(24.dp).background(color)) },
                        onClick = {
                            settingModel.setBackgroundColor(color.toArgb())
                            expandedColor = false
                        }
                    )

                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Selección de Tamaño de Texto
        Box {
            Button(onClick = { expandedSize = true }) {
                Text("Tamaño de Texto")
            }
            DropdownMenu(expanded = expandedSize, onDismissRequest = { expandedSize = false }) {
                sizeOptions.forEach { size ->
                    DropdownMenuItem(
                        text = { Text("${size.toInt()} sp", fontSize = size.sp) },
                        onClick = {
                            settingModel.setTextSize(size)
                            expandedSize = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}