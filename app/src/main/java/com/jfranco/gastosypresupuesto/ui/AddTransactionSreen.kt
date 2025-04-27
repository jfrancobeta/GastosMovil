package com.jfranco.gastosypresupuesto.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jfranco.gastosypresupuesto.model.Expense
import com.jfranco.gastosypresupuesto.model.Income
import com.jfranco.gastosypresupuesto.view.ExpenseViewModel
import com.jfranco.gastosypresupuesto.view.SettingsViewModel

@Composable
fun AddTransactionScreen(navController: NavController, viewModel: ExpenseViewModel,settingModel: SettingsViewModel){
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var isExpense by remember { mutableStateOf(true) }

    val backgroundColor by settingModel.backgroundColor.collectAsState()
    val textSize by settingModel.textSize.collectAsState()

    Column(modifier = Modifier.fillMaxSize().background(backgroundColor)) {
        Text("Agregar Transacción", style = MaterialTheme.typography.headlineSmall, fontSize = textSize.sp)

        // Selector de tipo (Ingreso o Gasto)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Tipo: ", fontSize = textSize.sp)
            RadioButton(selected = isExpense, onClick = { isExpense = true })
            Text("Gasto", fontSize = textSize.sp)
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(selected = !isExpense, onClick = { isExpense = false })
            Text("Ingreso", fontSize = textSize.sp)
        }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre", fontSize = textSize.sp) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Monto", fontSize = textSize.sp) },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (name.isNotEmpty() && amount.toDoubleOrNull() != null) {
                    val date = "2025-03-28" // Puedes cambiar por una fecha dinámica
                    val amountValue = amount.toDouble()

                    if (isExpense) {
                        val expense = Expense(
                            id = "", // lo genera Firebase
                            category = name,
                            amount = amountValue,
                            date = date
                        )
                        viewModel.addExpense(expense)
                    } else {
                        val income = Income(
                            id = "", // lo genera Firebase
                            source = name,
                            amount = amountValue,
                            date = date
                        )
                        viewModel.addIncome(income)
                    }

                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }
    }
}