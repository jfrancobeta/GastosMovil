package com.jfranco.gastosypresupuesto.model

data class Expense(
    val id: Int,
    val category: String,
    val amount: Double,
    val date: String

)