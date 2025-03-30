package com.jfranco.gastosypresupuesto.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jfranco.gastosypresupuesto.model.Expense
import com.jfranco.gastosypresupuesto.model.Income

class ExpenseViewModel : ViewModel() {
    private val _expenses = MutableLiveData<List<Expense>>(
        listOf(
            Expense(id = 1, category = "Comida", amount = 15.0, date = "2025-03-28"),
            Expense(id = 2, category = "Transporte", amount = 8.0, date = "2025-03-27"),
            Expense(id = 3, category = "Cine", amount = 20.0, date = "2025-03-26")
        )
    )
    val expenses: LiveData<List<Expense>> = _expenses

    private val _incomes = MutableLiveData<List<Income>>(
        listOf(
            Income(id = 1, source = "salario", amount = 30.0, date = "2025-03-28"),
            Income(id = 2, source = "venta", amount = 8.0, date = "2025-03-27"),
            Income(id = 3, source = "prestamo", amount = 12.0, date = "2025-03-26")
        )
    )
    val incomes: LiveData<List<Income>> = _incomes


    fun addExpense(expense: Expense) {
        val updateList = _expenses.value!! + expense
        _expenses.value = updateList
    }

    fun removeExpense(expenseId: Int) {
        val updateList = _expenses.value!!.filter { it.id != expenseId }
        _expenses.value = updateList

    }

    fun addIncome(income: Income) {
        val updateList = _incomes.value!! + income
        _incomes.value = updateList
    }

    fun removeIncome(incomeId: Int) {
        val updateList = _incomes.value!!.filter { it.id != incomeId }
        _incomes.value = updateList

    }

}