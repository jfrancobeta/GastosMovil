package com.jfranco.gastosypresupuesto.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jfranco.gastosypresupuesto.model.Expense
import com.jfranco.gastosypresupuesto.model.Income
import com.jfranco.gastosypresupuesto.view.ExpenseViewModel
import com.jfranco.gastosypresupuesto.view.SettingsViewModel

@Composable
fun DetailsScreen(navController: NavController, viewModel: ExpenseViewModel,settingModel: SettingsViewModel, idTransaction:Int, type:String) {


    val expenses by viewModel.expenses.observeAsState(emptyList())
    val incomes by viewModel.incomes.observeAsState(emptyList())

    val backgroundColor by settingModel.backgroundColor.collectAsState()
    val textSize by settingModel.textSize.collectAsState()

    val transaction = when (type) {
        "expense" -> expenses.find { it.id == idTransaction }
        "income" -> incomes.find { it.id == idTransaction }
        else -> null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (transaction == null) {
            Text(
                text = "❌ Transacción no encontrada",
                style = MaterialTheme.typography.headlineSmall,
                fontSize = textSize.sp,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            Text(
                text = "📄 Detalles de la transacción",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = textSize.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    when (type) {
                        "expense" -> {
                            val expense = transaction as Expense
                            DetailItem("Categoría:", expense.category, textSize.sp)
                            DetailItem("Monto:", expense.amount.toString(), textSize.sp)
                            DetailItem("Fecha:", expense.date, textSize.sp)
                        }
                        "income" -> {
                            val income = transaction as Income
                            DetailItem("Fuente:", income.source, textSize.sp)
                            DetailItem("Monto:", income.amount.toString(), textSize.sp)
                            DetailItem("Fecha:", income.date, textSize.sp)
                        }
                    }
                }
            }
        }
    }




}
@Composable
fun DetailItem(label: String, value: String, textSize: TextUnit) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = label, style = MaterialTheme.typography.titleSmall, fontSize = textSize)
        Text(text = value, style = MaterialTheme.typography.bodyLarge, fontSize = textSize, color = Color.DarkGray)
    }
}