package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmSetupScreen(
    onBack: () -> Unit,
    onSave: () -> Unit
) {
    var selectedAmPm by remember { mutableStateOf("AM") }
    
    val days = listOf("M", "T", "W", "T", "F", "S", "S")
    val selectedDays = remember { mutableStateListOf<Int>(0, 1, 2, 3, 4) }
    
    val tasks = listOf("Math Solver", "Memory Match", "Verse Copy", "Smile Cam", "Head Shake", "Rapid Blink", "10 Jumps", "Scan Random Item", "Color Finder", "Audio Affirmation")
    var selectedTask by remember { mutableStateOf(tasks[0]) }
    var vibrate by remember { mutableStateOf(true) }
    var snooze by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("New Alarm", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                },
                actions = {
                    TextButton(onClick = onSave) {
                        Text("Save", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(bottom = 80.dp, top = 24.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "07 : 30",
                        style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold, fontSize = 64.sp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilterChip(
                            selected = selectedAmPm == "AM",
                            onClick = { selectedAmPm = "AM" },
                            label = { Text("AM", fontWeight = FontWeight.Bold) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
                        )
                        FilterChip(
                            selected = selectedAmPm == "PM",
                            onClick = { selectedAmPm = "PM" },
                            label = { Text("PM", fontWeight = FontWeight.Bold) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
                        )
                    }
                }
            }

            item {
                Text("Repeat", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    days.forEachIndexed { index, day ->
                        val isSelected = selectedDays.contains(index)
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant)
                                .clickable {
                                    if (isSelected) selectedDays.remove(index) else selectedDays.add(index)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = day,
                                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            item {
                Card(
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Vibrate", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface)
                            Switch(checked = vibrate, onCheckedChange = { vibrate = it }, colors = SwitchDefaults.colors(checkedTrackColor = MaterialTheme.colorScheme.primary))
                        }
                        HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f), modifier = Modifier.padding(horizontal = 16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Snooze (5 mins)", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface)
                            Switch(checked = snooze, onCheckedChange = { snooze = it }, colors = SwitchDefaults.colors(checkedTrackColor = MaterialTheme.colorScheme.primary))
                        }
                    }
                }
            }

            item {
                val soundCategories = mapOf(
                    "Nature & Calm" to listOf("Morning Rain", "Forest Awakening", "Gentle Breeze"),
                    "Standard" to listOf("Classic Bell", "Digital Beep")
                )
                
                var selectedSound by remember { mutableStateOf("Morning Rain") }
                
                soundCategories.forEach { (category, sounds) ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Column(modifier = Modifier.padding(vertical = 16.dp)) {
                            Text(category, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(horizontal = 16.dp))
                            Spacer(modifier = Modifier.height(8.dp))
                            sounds.forEach { sound ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { selectedSound = sound }
                                        .padding(vertical = 12.dp, horizontal = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.Notifications, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(20.dp))
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Text(sound, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface)
                                    }
                                    RadioButton(
                                        selected = (sound == selectedSound),
                                        onClick = { selectedSound = sound },
                                        colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary, unselectedColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                                    )
                                }
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text("Wake-Up Task", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(16.dp))
                
                val taskCategories = mapOf(
                    "Mental & Logic" to listOf("Math Solver", "Memory Match", "Verse Copy"),
                    "Physical Actions" to listOf("Smile Cam", "Head Shake", "Rapid Blink", "10 Jumps"),
                    "Real World Interaction" to listOf("Scan Random Item", "Color Finder", "Audio Affirmation")
                )
                
                var selectedTask by remember { mutableStateOf("Math Solver") }

                taskCategories.forEach { (category, categoryTasks) ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(category, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(8.dp))
                            categoryTasks.forEach { task ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                                        .clickable { selectedTask = task }
                                        .padding(vertical = 8.dp, horizontal = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = (task == selectedTask),
                                        onClick = { selectedTask = task }
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(task, style = MaterialTheme.typography.bodyLarge)
                                }
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text("Difficulty Level", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(16.dp))
                
                val levels = listOf("Easy", "Medium", "Hard", "Auto-Scaling")
                var selectedLevel by remember { mutableStateOf(levels[3]) }
                
                Card(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        levels.forEach { level ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                                    .clickable { selectedLevel = level }
                                    .padding(vertical = 8.dp, horizontal = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = (level == selectedLevel),
                                    onClick = { selectedLevel = level }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(level, style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                    }
                }
            }
        }
    }
}
