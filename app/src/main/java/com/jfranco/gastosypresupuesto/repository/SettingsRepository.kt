package com.jfranco.gastosypresupuesto.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import kotlinx.coroutines.flow.MutableStateFlow

class SettingsRepository(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("settings_prefs", Context.MODE_PRIVATE)

    private val _backgroundColor = MutableStateFlow(getBackgroundColor())
    val backgroundColor = _backgroundColor

    private val _textSize = MutableStateFlow(getTextSize())
    val textSize = _textSize

    fun saveBackgroundColor(color: Int) {
        prefs.edit().putInt("background_color", color).apply()
        _backgroundColor.value = Color(color)
    }

    fun saveTextSize(size: Float) {
        prefs.edit().putFloat("text_size", size).apply()
        _textSize.value = size
    }

    private fun getBackgroundColor(): Color {
        val defaultColor = Color.White.toArgb()
        return Color(prefs.getInt("background_color", defaultColor))
    }

    private fun getTextSize(): Float {
        return prefs.getFloat("text_size", 16f)
    }
}