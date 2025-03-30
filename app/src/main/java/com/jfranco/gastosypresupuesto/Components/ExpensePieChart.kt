package com.jfranco.gastosypresupuesto.Components

import android.graphics.Color
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Typeface
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

@Composable
fun ExpensePieChart(totalIncome: Float, totalExpense: Float){
    val context = LocalContext.current

    AndroidView(
        factory = { ctx ->
            PieChart(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )

                description = Description().apply { text = "Distribución de Ingresos y Gastos" }
                isDrawHoleEnabled = true
                setEntryLabelTextSize(12f)
                setEntryLabelColor(Color.BLACK)
                setCenterText("Gastos vs Ingresos")
                setCenterTextSize(14f)
                setUsePercentValues(true)
            }

        },
        update = { chart ->
            val entries = mutableListOf<PieEntry>()
            if (totalIncome > 0) entries.add(PieEntry(totalIncome, "Ingresos"))
            if (totalExpense > 0) entries.add(PieEntry(totalExpense, "Gastos"))

            val dataSet = PieDataSet(entries, "").apply {
                colors = listOf(ColorTemplate.COLORFUL_COLORS[0], ColorTemplate.COLORFUL_COLORS[1])
                valueTextSize = 14f
                valueTextColor = Color.BLACK
            }

            val data = PieData(dataSet)
            chart.data = data
            chart.invalidate() // Refresca el gráfico
        },
        modifier = Modifier.fillMaxWidth().height(300.dp)
    )
}