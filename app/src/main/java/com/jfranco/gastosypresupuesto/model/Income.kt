package com.jfranco.gastosypresupuesto.model

data class Income(
    var id: String = "",
    val source: String = "",
    val amount: Double = 0.0,
    val date: String = ""
){
    constructor() : this("", "", 0.0, "")
}