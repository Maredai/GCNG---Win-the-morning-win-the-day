package com.example.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssessmentScreen(onFinishAssessment: () -> Unit) {
    var step by remember { mutableStateOf(0) }
    
    val questions = listOf(
        "How many hours of sleep do you get on average?",
        "How hard is it for you to wake up in the morning?",
        "What is your primary productivity goal?",
        "How often do you hit the snooze button?",
        "What usually causes you to oversleep?",
        "How do you feel 15 minutes after waking up?",
        "What type of task would wake you up best?",
        "Where do you keep your phone at night?",
        "How does oversleeping affect your day?",
        "Are you ready to commit to waking up on time?"
    )
    val options = listOf(
        listOf("< 5 hours", "5-7 hours", "7-9 hours", "> 9 hours"),
        listOf("Very easy", "Sometimes hard", "Very difficult", "Impossible without 10 alarms"),
        listOf("More focused mornings", "Stop being late", "Build a habit", "Other"),
        listOf("Never", "1-2 times", "3-5 times", "I lose count"),
        listOf("Going to bed late", "Heavy sleeper", "Scrolling in bed", "Exhaustion"),
        listOf("Energized", "Groggy but okay", "Extremely tired", "Like a zombie"),
        listOf("Physical (Jumping/Shaking)", "Mental (Math/Memory)", "Visual (Scanning)", "Audio (Speaking)"),
        listOf("Right next to my pillow", "On a nightstand", "Across the room", "In another room"),
        listOf("It ruins my mood", "I feel rushed", "I miss important things", "It doesn't bother me"),
        listOf("Yes, absolutely", "I will try my best", "I'm nervous but ready", "We'll see")
    )

    var selectedOption by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = { Text("STEP ${step + 1} OF ${questions.size}", style = MaterialTheme.typography.labelMedium, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant, letterSpacing = 1.sp) },
                    navigationIcon = {
                        IconButton(onClick = { if (step > 0) step-- else onFinishAssessment() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onSurface)
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.background)
                )
                LinearProgressIndicator(
                    progress = { (step + 1) / questions.size.toFloat() },
                    modifier = Modifier.fillMaxWidth().height(4.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                questions[step], 
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "This helps us understand your baseline rest patterns.", 
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.height(48.dp))
            
            Column(modifier = Modifier.selectableGroup().fillMaxWidth()) {
                options[step].forEach { option ->
                    val isSelected = selectedOption == option
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                        onClick = { selectedOption = option }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(option, color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.bodyLarge)
                            RadioButton(
                                selected = isSelected,
                                onClick = { selectedOption = option },
                                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary, unselectedColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(48.dp))
            Button(
                onClick = {
                    if (step < questions.size - 1) {
                        step++
                        selectedOption = ""
                    } else {
                        onFinishAssessment()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = selectedOption.isNotEmpty(),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
            ) {
                Text(if (step < questions.size - 1) "NEXT" else "COMPLETE", fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, letterSpacing = 1.sp)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(
                onClick = { onFinishAssessment() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Skip", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
