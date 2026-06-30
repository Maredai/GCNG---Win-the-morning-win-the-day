package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ui.screens.SplashScreen
import com.example.ui.theme.LocalThemeController
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.ThemeController
import com.example.data.AppDatabase
import com.example.data.AlarmRepository
import com.example.viewmodel.AlarmViewModel
import com.example.viewmodel.AlarmViewModelFactory

class MainActivity : ComponentActivity() {
  private val database by lazy { AppDatabase.getDatabase(this) }
  private val repository by lazy { AlarmRepository(database.alarmDao()) }
  private val viewModel: AlarmViewModel by viewModels { AlarmViewModelFactory(repository) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      var isDarkTheme by remember { mutableStateOf(false) }

      MyApplicationTheme(darkTheme = isDarkTheme) {
        val themeController = remember {
            object : ThemeController {
                override val isDarkTheme: Boolean
                    get() = isDarkTheme
                override fun toggleTheme() {
                    isDarkTheme = !isDarkTheme
                }
            }
        }

        CompositionLocalProvider(LocalThemeController provides themeController) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash") {
                composable("splash") {
                    SplashScreen(onNavigateToOnboarding = {
                        navController.navigate("auth") {
                            popUpTo("splash") { inclusive = true }
                        }
                    })
                }
                composable("auth") {
                    com.example.ui.screens.AuthScreen(onLoginSuccess = {
                        navController.navigate("assessment") {
                            popUpTo("auth") { inclusive = true }
                        }
                    })
                }
                composable("assessment") {
                    com.example.ui.screens.AssessmentScreen(onFinishAssessment = {
                        navController.navigate("onboarding") {
                            popUpTo("assessment") { inclusive = true }
                        }
                    })
                }
                composable("onboarding") {
                    com.example.ui.screens.OnboardingScreen(onFinishOnboarding = {
                        navController.navigate("home") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    })
                }
                composable("home") {
                    com.example.ui.screens.MainScreen(
                        viewModel = viewModel,
                        onNavigateToSetup = { navController.navigate("setup") },
                        onNavigateToRing = { navController.navigate("ring") }
                    )
                }
                composable("setup") {
                    com.example.ui.screens.AlarmSetupScreen(
                        viewModel = viewModel,
                        onBack = { navController.popBackStack() },
                        onSave = { navController.popBackStack() }
                    )
                }
                composable("ring") {
                    com.example.ui.screens.AlarmRingScreen(
                        onTaskSuccess = { 
                            navController.navigate("success") {
                                popUpTo("ring") { inclusive = true }
                            } 
                        }
                    )
                }
                composable("success") {
                    com.example.ui.screens.TaskSuccessScreen(
                        onFinish = { 
                            navController.navigate("home") {
                                popUpTo(0)
                            }
                        }
                    )
                }
            }
        }
        }
      }
    }
  }
}
