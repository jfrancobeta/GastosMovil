package com.jfranco.gastosypresupuesto.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jfranco.gastosypresupuesto.repository.SettingsRepository
import com.jfranco.gastosypresupuesto.ui.AddTransactionScreen
import com.jfranco.gastosypresupuesto.ui.DetailsScreen
import com.jfranco.gastosypresupuesto.ui.HomeScreen
import com.jfranco.gastosypresupuesto.ui.RegActivity
import com.jfranco.gastosypresupuesto.ui.SettingsScreen
import com.jfranco.gastosypresupuesto.ui.StatsScreen
import com.jfranco.gastosypresupuesto.view.ExpenseViewModel
import com.jfranco.gastosypresupuesto.view.SettingsViewModel

@Composable
fun NavigationComponent(){
    val context = LocalContext.current
    val navController = rememberNavController()

    val viewModel = remember { ExpenseViewModel() }

    val settingModel = remember { SettingsViewModel(SettingsRepository(context)) }

    NavHost(
        navController = navController,
        startDestination = "mainScreen"
    ){

        composable("mainScreen"){
            HomeScreen(navController,viewModel,settingModel)
        }
        composable("addTransaction"){
            AddTransactionScreen(navController,viewModel,settingModel)
        }
        composable("stats"){
            StatsScreen(navController,viewModel,settingModel)
        }
        composable("settings"){
            SettingsScreen(navController,settingModel)
        }
        composable("details/{id}/{type}"){
            params ->
            val transactionId = params.arguments?.getString("id") ?: ""
            val type = params.arguments?.getString("type") ?: ""
            DetailsScreen(navController,viewModel,settingModel,idTransaction = transactionId,type)
        }





    }
}