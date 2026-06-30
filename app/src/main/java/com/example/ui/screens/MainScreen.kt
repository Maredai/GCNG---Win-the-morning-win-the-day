package com.example.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AutoGraph
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.viewmodel.AlarmViewModel

@Composable
fun MainScreen(
    viewModel: AlarmViewModel,
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
                    icon = { Icon(if (currentRoute == "home_tab") Icons.Filled.Home else Icons.Outlined.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = currentRoute == "home_tab",
                    onClick = { bottomNavController.navigate("home_tab") }
                )
                NavigationBarItem(
                    icon = { Icon(if (currentRoute == "stats_tab") Icons.Filled.AutoGraph else Icons.Outlined.AutoGraph, contentDescription = "Stats") },
                    label = { Text("Stats") },
                    selected = currentRoute == "stats_tab",
                    onClick = { bottomNavController.navigate("stats_tab") }
                )
                NavigationBarItem(
                    icon = { Icon(if (currentRoute == "settings_tab") Icons.Filled.Settings else Icons.Outlined.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") },
                    selected = currentRoute == "settings_tab",
                    onClick = { bottomNavController.navigate("settings_tab") }
                )
                NavigationBarItem(
                    icon = { Icon(if (currentRoute == "profile_tab") Icons.Filled.Person else Icons.Outlined.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = currentRoute == "profile_tab",
                    onClick = { bottomNavController.navigate("profile_tab") }
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
                HomeScreen(viewModel = viewModel, onNavigateToSetup = onNavigateToSetup, onTestAlarm = onNavigateToRing)
            }
            composable("stats_tab") {
                StatsScreen()
            }
            composable("settings_tab") {
                SettingsScreen()
            }
            composable("profile_tab") {
                ProfileScreen()
            }
        }
    }
}
