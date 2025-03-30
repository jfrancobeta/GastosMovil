package com.jfranco.gastosypresupuesto.view

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.jfranco.gastosypresupuesto.repository.SettingsRepository
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel(private val repository: SettingsRepository) : ViewModel(){


    val backgroundColor: StateFlow<Color> = repository.backgroundColor
    val textSize: StateFlow<Float> = repository.textSize

    fun setBackgroundColor(color: Int) {
        repository.saveBackgroundColor(color)
    }

    fun setTextSize(size: Float) {
        repository.saveTextSize(size)
    }


}