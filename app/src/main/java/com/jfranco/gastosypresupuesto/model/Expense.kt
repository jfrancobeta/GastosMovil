package com.jfranco.gastosypresupuesto.model

data class Expense(
    var id: String = "",
    val category: String = "",
    val amount: Double = 0.0,
    val date: String = ""

){
    constructor() : this("", "", 0.0, "")
}