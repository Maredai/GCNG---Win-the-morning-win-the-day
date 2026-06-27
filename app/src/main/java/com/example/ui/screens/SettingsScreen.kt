package com.example.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.theme.LocalThemeController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    var gradualVolume by remember { mutableStateOf(true) }
    var smartSnooze by remember { mutableStateOf(false) }
    val themeController = LocalThemeController.current

    Scaffold(
        topBar = { TopAppBar(title = { Text("Settings") }) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text("Appearance", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
            }
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text("Dark Mode", style = MaterialTheme.typography.bodyLarge)
                        Text("Toggle between light and dark theme", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    Switch(checked = themeController.isDarkTheme, onCheckedChange = { themeController.toggleTheme() })
                }
            }
            item { HorizontalDivider() }
            item {
                Text("Alarm Behaviors", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
            }
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text("Gradual Volume Ramp", style = MaterialTheme.typography.bodyLarge)
                        Text("Fade in over 30 seconds", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    Switch(checked = gradualVolume, onCheckedChange = { gradualVolume = it })
                }
            }
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text("Smart Snooze Cap", style = MaterialTheme.typography.bodyLarge)
                        Text("Limit to 1 snooze, increases task difficulty", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    Switch(checked = smartSnooze, onCheckedChange = { smartSnooze = it })
                }
            }
            item { HorizontalDivider() }
            item {
                Text("App Info", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(8.dp))
                Text("GCNG v1.0", style = MaterialTheme.typography.bodyLarge)
                Text("Privacy-First Morning Alarms", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
