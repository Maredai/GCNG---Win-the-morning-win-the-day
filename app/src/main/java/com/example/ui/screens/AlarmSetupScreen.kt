package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmSetupScreen(
    onBack: () -> Unit,
    onSave: () -> Unit
) {
    var alarmName by remember { mutableStateOf("Morning Alarm") }
    var alarmTime by remember { mutableStateOf("07:30 AM") }
    var isScheduled by remember { mutableStateOf(true) }
    var showTimePicker by remember { mutableStateOf(false) }
    
    val timePickerState = rememberTimePickerState(
        initialHour = 7,
        initialMinute = 30,
        is24Hour = false
    )

    if (showTimePicker) {
        AlertDialog(
            onDismissRequest = { showTimePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    val amPm = if (timePickerState.hour >= 12) "PM" else "AM"
                    val hr = if (timePickerState.hour % 12 == 0) 12 else timePickerState.hour % 12
                    val min = timePickerState.minute.toString().padStart(2, '0')
                    alarmTime = String.format("%02d:%s %s", hr, min, amPm)
                    showTimePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showTimePicker = false }) {
                    Text("Cancel")
                }
            },
            text = {
                TimePicker(state = timePickerState)
            }
        )
    }
    
    val days = listOf("S", "M", "T", "W", "T", "F", "S")
    val selectedDays = remember { mutableStateListOf(1, 2, 3, 4, 5) }
    
    var bedtimeReminder by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Edit Alarm", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.padding(start = 8.dp).background(MaterialTheme.colorScheme.surface, CircleShape)) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = MaterialTheme.colorScheme.onSurface)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent)
            )
        },
        bottomBar = {
            Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Button(
                    onClick = onSave,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Save Alarm", color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp)
        ) {
            
            // Alarm Name
            item {
                AlarmCard {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = alarmName,
                            onValueChange = { alarmName = it },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            textStyle = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface),
                            singleLine = true
                        )
                    }
                }
            }
            
            // Alarm Time
            item {
                AlarmCard {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showTimePicker = true }
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Alarm Time", style = MaterialTheme.typography.titleMedium)
                        Text(alarmTime, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
            
            // Scheduled / One-time toggle
            item {
                AlarmCard {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(if (isScheduled) MaterialTheme.colorScheme.primary else Color.Transparent)
                                .clickable { isScheduled = true }
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Outlined.Repeat, contentDescription = null, tint = if (isScheduled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Scheduled", color = if (isScheduled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold)
                            }
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(if (!isScheduled) MaterialTheme.colorScheme.primary else Color.Transparent)
                                .clickable { isScheduled = false }
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Outlined.CalendarToday, contentDescription = null, tint = if (!isScheduled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Once", color = if (!isScheduled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
            
            // Repeat On
            if (isScheduled) {
                item {
                    AlarmCard {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Repeat on:", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Spacer(modifier = Modifier.height(12.dp))
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
                    }
                }
            }
            
            // Wake-Up Tasks
            item {
                AlarmCard {
                    Column {
                        TaskItem("Task 1", "Math Solver", Icons.Outlined.Calculate, showDivider = true)
                        TaskItem("Task 2", "Smile Cam", Icons.Outlined.CameraAlt, showDivider = true)
                        TaskItem("Task 3", "Audio Affirmation", Icons.Outlined.Mic, showDivider = false)
                    }
                }
            }
            
            // Sound
            item {
                AlarmCard {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.size(40.dp).background(Color(0xFFE57373), CircleShape))
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Sound", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("Morning Rain", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
                        }
                        Icon(Icons.Outlined.ChevronRight, contentDescription = "More", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
            
            // Wake-up Check
            item {
                AlarmCard {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Outlined.Refresh, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("Wake-up Check", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium)
                                Spacer(modifier = Modifier.width(8.dp))
                                Surface(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.surfaceVariant) {
                                    Text("Off", style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                                }
                            }
                            Text("A second alarm so you don't fall back asleep", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Icon(Icons.Outlined.ChevronRight, contentDescription = "More", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
            
            // Sleep Better
            item {
                AlarmCard {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Outlined.Bedtime, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(24.dp))
                            Spacer(modifier = Modifier.width(16.dp))
                            Text("Sleep Better", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
                            Icon(Icons.Outlined.ChevronRight, contentDescription = "More", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = MaterialTheme.colorScheme.surfaceVariant)
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Bedtime Reminder", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
                                Text("A gentle alarm and notification will remind you it's time for bed", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Switch(checked = bedtimeReminder, onCheckedChange = { bedtimeReminder = it })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AlarmCard(content: @Composable () -> Unit) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        content()
    }
}

@Composable
fun TaskItem(label: String, taskName: String, icon: androidx.compose.ui.graphics.vector.ImageVector, showDivider: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable {}.padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(32.dp).background(MaterialTheme.colorScheme.surfaceVariant, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(16.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(label, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(taskName, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
        }
        if (showDivider) {
            Icon(Icons.Outlined.ChevronRight, contentDescription = "More", tint = MaterialTheme.colorScheme.onSurfaceVariant)
        } else {
            Icon(Icons.Outlined.Close, contentDescription = "Remove", tint = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
    if (showDivider) {
        HorizontalDivider(modifier = Modifier.padding(start = 64.dp, end = 16.dp), color = MaterialTheme.colorScheme.surfaceVariant)
    }
}
