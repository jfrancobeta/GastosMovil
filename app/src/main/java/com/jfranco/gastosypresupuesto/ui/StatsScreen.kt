package com.jfranco.gastosypresupuesto.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jfranco.gastosypresupuesto.Components.ExpensePieChart
import com.jfranco.gastosypresupuesto.view.ExpenseViewModel
import com.jfranco.gastosypresupuesto.view.SettingsViewModel

@Composable
fun StatsScreen(navController: NavController, viewModel: ExpenseViewModel,settingModel: SettingsViewModel){


    val expenses by viewModel.expenses.observeAsState(emptyList())
    val incomes by viewModel.incomes.observeAsState(emptyList())

    val totalIncome = incomes.sumOf { it.amount }
    val totalExpense = expenses.sumOf { it.amount }
    val balance = totalIncome - totalExpense

    val backgroundColor by settingModel.backgroundColor.collectAsState()
    val textSize by settingModel.textSize.collectAsState()

    Column (
        modifier = Modifier.fillMaxSize().background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text("📊 Estadísticas", style = MaterialTheme.typography.headlineSmall, fontSize = textSize.sp)

        Spacer(modifier = Modifier.height(16.dp))

        ExpensePieChart(totalIncome.toFloat(),totalExpense.toFloat())

        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
        ) {
            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("💰 Total Ingresos", style = MaterialTheme.typography.titleMedium, fontSize = textSize.sp)
                Text("+ $totalIncome", color = Color.Green, style = MaterialTheme.typography.headlineMedium, fontSize = textSize.sp)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE))
        ) {
            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("📉 Total Gastos", style = MaterialTheme.typography.titleMedium, fontSize = textSize.sp)
                Text("- $totalExpense", color = Color.Red, style = MaterialTheme.typography.headlineMedium, fontSize = textSize.sp)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = if (balance >= 0) Color(0xFFC8E6C9) else Color(0xFFFFCDD2))
        ) {
            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("💵 Saldo Final", style = MaterialTheme.typography.titleMedium, fontSize = textSize.sp)
                Text("$balance", color = if (balance >= 0) Color.Green else Color.Red, style = MaterialTheme.typography.headlineMedium, fontSize = textSize.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver" )
        }
    }
}