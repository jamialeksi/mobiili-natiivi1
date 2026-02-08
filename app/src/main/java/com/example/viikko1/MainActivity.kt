package com.example.viikko1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.viikko1.view.HomeScreen
import com.example.viikko1.ui.theme.Viikko1Theme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.viikko1.navigation.ROUTE_CALENDAR
import com.example.viikko1.navigation.ROUTE_HOME
import com.example.viikko1.navigation.ROUTE_SETTINGS
import com.example.viikko1.view.CalendarScreen
import com.example.viikko1.view.SettingsScreen
import com.example.viikko1.viewmodel.TaskViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Viikko1Theme {
                val navController = rememberNavController()

                val vm: TaskViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = ROUTE_HOME
                ) {
                    composable(ROUTE_HOME) {
                        HomeScreen(
                            vm = vm,
                            onGoCalendar = { navController.navigate(ROUTE_CALENDAR) },
                            onGoSettings = { navController.navigate(ROUTE_SETTINGS) }
                        )
                    }
                    composable(ROUTE_CALENDAR) {
                        CalendarScreen(
                            vm = vm,
                            onGoHome = { navController.navigate(ROUTE_HOME) },
                            onGoSettings = { navController.navigate(ROUTE_SETTINGS) }
                        )
                    }
                    composable(ROUTE_SETTINGS) {
                        SettingsScreen(
                            onGoHome = { navController.navigate(ROUTE_HOME) },
                            onGoCalendar = { navController.navigate(ROUTE_CALENDAR) }
                        )
                    }
                }
            }
        }
    }
}
