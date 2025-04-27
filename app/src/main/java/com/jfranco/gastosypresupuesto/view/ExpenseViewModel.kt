package com.jfranco.gastosypresupuesto.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.jfranco.gastosypresupuesto.model.Expense
import com.jfranco.gastosypresupuesto.model.Income

class ExpenseViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance()
    private val expensesRef = database.getReference("expenses")
    private val incomesRef = database.getReference("incomes")

    private val _expenses = MutableLiveData<List<Expense>>()
    val expenses: LiveData<List<Expense>> = _expenses

    private val _incomes = MutableLiveData<List<Income>>()
    val incomes: LiveData<List<Income>> = _incomes


    init {
        fetchExpenses()
        fetchIncomes()
    }
    private fun fetchExpenses() {
        expensesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lista = mutableListOf<Expense>()
                for (dato in snapshot.children) {
                    val gasto = dato.getValue(Expense::class.java)
                    gasto?.let { lista.add(it) }
                }
                _expenses.value = lista
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("expensiveView", "Error al obtener los ingresos", error.toException())
            }
        })
    }

    private fun fetchIncomes() {
        incomesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lista = mutableListOf<Income>()
                for (dato in snapshot.children) {
                    val ingreso = dato.getValue(Income::class.java)
                    ingreso?.let { lista.add(it) }
                }
                _incomes.value = lista
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("expensiveView", "Error al obtener los ingresos", error.toException())
            }
        })
    }

    fun addExpense(expense: Expense) {
        val id = expensesRef.push().key!!
        expense.id = id
        expensesRef.child(id).setValue(expense)
    }

    fun removeExpense(expenseId: String) {
        expensesRef.child(expenseId).removeValue()
    }

    fun addIncome(income: Income) {
        val id = incomesRef.push().key!!
        income.id = id
        incomesRef.child(id).setValue(income)
    }

    fun removeIncome(incomeId: String) {
        incomesRef.child(incomeId).removeValue()
    }

}