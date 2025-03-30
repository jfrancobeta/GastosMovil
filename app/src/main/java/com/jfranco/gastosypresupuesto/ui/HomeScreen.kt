package com.jfranco.gastosypresupuesto.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jfranco.gastosypresupuesto.model.Expense
import com.jfranco.gastosypresupuesto.view.ExpenseViewModel
import com.jfranco.gastosypresupuesto.view.SettingsViewModel

@Composable
fun HomeScreen(navController: NavController,viewModel: ExpenseViewModel,settingModel: SettingsViewModel){
    val expenses by viewModel.expenses.observeAsState(emptyList())
    val incomes by viewModel.incomes.observeAsState(emptyList())


    val backgroundColor by settingModel.backgroundColor.collectAsState()
    val textSize by settingModel.textSize.collectAsState()

    Column(modifier = Modifier.fillMaxSize().background(backgroundColor).padding(16.dp)) {
           Text(
               "Resumen de Finanzas",
               style = MaterialTheme.typography.headlineSmall,
               fontSize = textSize.sp
           )

           LazyColumn(modifier = Modifier.weight(1f)) {
               item {
                   Text("Gastos", style = MaterialTheme.typography.titleMedium, fontSize = textSize.sp)

               }
               items(expenses) { expense ->
                   Card(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(8.dp)
                           .clickable { navController.navigate("details/${expense.id}/expense") },
                       elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                   ) {
                       Row(
                           modifier = Modifier.padding(16.dp),
                           horizontalArrangement = Arrangement.SpaceBetween
                       ) {
                           Column {
                               Text(
                                   expense.category,
                                   style = MaterialTheme.typography.bodyLarge,
                                   fontSize = textSize.sp
                               )
                               Text(
                                   "💰 ${expense.amount}",
                                   style = MaterialTheme.typography.bodyMedium,
                                   fontSize = textSize.sp
                               )

                           }
                           Button(onClick = { viewModel.removeExpense(expense.id) }) {
                               Text("Eliminar", fontSize = textSize.sp)
                           }
                       }
                   }
               }
               item {
                   Text("Ingresos", style = MaterialTheme.typography.titleMedium, fontSize = textSize.sp)

               }
               items(incomes) { income ->
                   Card(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(8.dp)
                           .clickable { navController.navigate("details/${income.id}/income") },
                       elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                   ) {
                       Row(
                           modifier = Modifier.padding(16.dp),
                           horizontalArrangement = Arrangement.SpaceBetween
                       ) {
                           Column {
                               Text(income.source, style = MaterialTheme.typography.bodyLarge, fontSize = textSize.sp)
                               Text(
                                   "💰 ${income.amount}",
                                   style = MaterialTheme.typography.bodyMedium
                                   , fontSize = textSize.sp
                               )

                           }
                           Button(onClick = { viewModel.removeIncome(income.id) }) {
                               Text("Eliminar", fontSize = textSize.sp)
                           }
                       }
                   }
               }
           }

           Button(
               onClick = { navController.navigate("addTransaction") },
               modifier = Modifier.fillMaxWidth()
           ) {
               Text("Agregar Transacción",)
           }
           Spacer(modifier = Modifier.height(8.dp))
           Button(
               onClick = { navController.navigate("stats") },
               modifier = Modifier.fillMaxWidth()
           ) {
               Text("Ver Estadísticas")
           }
           Spacer(modifier = Modifier.height(8.dp))
           Button(
               onClick = { navController.navigate("settings") },
               modifier = Modifier.fillMaxWidth()
           ) {
               Text("Configuracion")
           }


       }


}