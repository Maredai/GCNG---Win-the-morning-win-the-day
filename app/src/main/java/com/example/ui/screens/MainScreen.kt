package com.example.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
    onNavigateToSetup: () -> Unit,
    onNavigateToRing: () -> Unit
) {
    val bottomNavController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = currentRoute == "home_tab",
                    onClick = { bottomNavController.navigate("home_tab") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = "Stats") },
                    label = { Text("Stats") },
                    selected = currentRoute == "stats_tab",
                    onClick = { bottomNavController.navigate("stats_tab") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") },
                    selected = currentRoute == "settings_tab",
                    onClick = { bottomNavController.navigate("settings_tab") }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = "home_tab",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home_tab") {
                HomeScreen(onNavigateToSetup = onNavigateToSetup, onTestAlarm = onNavigateToRing)
            }
            composable("stats_tab") {
                StatsScreen()
            }
            composable("settings_tab") {
                SettingsScreen()
            }
        }
    }
}
