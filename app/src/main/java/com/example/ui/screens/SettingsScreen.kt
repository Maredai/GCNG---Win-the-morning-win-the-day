package com.example.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.ui.theme.LocalThemeController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    var notificationsEnabled by remember { mutableStateOf(false) }
    val themeController = LocalThemeController.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Settings", style = MaterialTheme.typography.titleLarge, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, color = MaterialTheme.colorScheme.primary) },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = MaterialTheme.colorScheme.primary)
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Outlined.AccountCircle, contentDescription = "Profile", tint = MaterialTheme.colorScheme.primary)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                SettingsSection("Preferences") {
                    SettingsItem(
                        icon = Icons.Outlined.NotificationsOff,
                        title = "Notifications",
                        subtitle = "Disabled — Open Settings",
                        showDivider = true
                    )
                    SettingsItem(
                        icon = Icons.Outlined.Language,
                        title = "Language",
                        trailingText = "us English",
                        showDivider = true
                    )
                    SettingsItem(
                        icon = Icons.Outlined.LightMode,
                        title = "Dark Mode",
                        showDivider = false,
                        trailingContent = {
                            Checkbox(
                                checked = themeController.isDarkTheme,
                                onCheckedChange = { themeController.toggleTheme() }
                            )
                        }
                    )
                }
            }
            
            item {
                SettingsSection("Support") {
                    SettingsItem(
                        icon = Icons.Outlined.Lightbulb,
                        title = "Request a Feature",
                        showDivider = true
                    )
                    SettingsItem(
                        icon = Icons.Outlined.HelpOutline,
                        title = "Contact Support",
                        showDivider = true
                    )
                    SettingsItem(
                        icon = Icons.Outlined.ChatBubbleOutline,
                        title = "Join our Discord",
                        showDivider = false
                    )
                }
            }
            
            item {
                SettingsSection("Legal") {
                    SettingsItem(
                        icon = Icons.Outlined.Lock,
                        title = "Privacy Policy",
                        showDivider = true
                    )
                    SettingsItem(
                        icon = Icons.Outlined.Description,
                        title = "Terms of Service",
                        showDivider = false
                    )
                }
            }
            
            item {
                SettingsSection("Account") {
                    SettingsItem(
                        icon = Icons.Outlined.AccountCircle,
                        title = "Account Settings",
                        showDivider = false
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsSection(title: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
        )
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                content()
            }
        }
    }
}

@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    trailingText: String? = null,
    showDivider: Boolean = true,
    onClick: () -> Unit = {},
    trailingContent: @Composable (() -> Unit)? = null
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = title, tint = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface)
                if (subtitle != null) {
                    Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            if (trailingText != null) {
                Text(trailingText, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.width(8.dp))
            }
            if (trailingContent != null) {
                trailingContent()
            } else {
                Icon(Icons.AutoMirrored.Outlined.KeyboardArrowRight, contentDescription = "More", tint = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        if (showDivider) {
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
        }
    }
}
