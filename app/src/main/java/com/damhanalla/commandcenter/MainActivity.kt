package com.damhanalla.commandcenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.damhanalla.commandcenter.ui.components.EmptyStateContainer
import com.damhanalla.commandcenter.ui.components.ErrorStateContainer
import com.damhanalla.commandcenter.ui.components.LoadingStateContainer
import com.damhanalla.commandcenter.ui.navigation.ScreenDestination
import com.damhanalla.commandcenter.ui.screens.ClientsScreen
import com.damhanalla.commandcenter.ui.screens.DashboardScreen
import com.damhanalla.commandcenter.ui.screens.GrantsScreen
import com.damhanalla.commandcenter.ui.screens.TasksScreen
import com.damhanalla.commandcenter.ui.screens.VaultScreen
import com.damhanalla.commandcenter.ui.state.CommandCenterUiState
import com.damhanalla.commandcenter.ui.theme.BorderDark
import com.damhanalla.commandcenter.ui.theme.DamHanAllaTheme
import com.damhanalla.commandcenter.ui.theme.PrimaryEmerald
import com.damhanalla.commandcenter.ui.theme.SurfaceDark
import com.damhanalla.commandcenter.ui.theme.TextPrimary
import com.damhanalla.commandcenter.ui.theme.TextSecondary
import com.damhanalla.commandcenter.ui.viewmodel.CommandCenterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CommandCenterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            DamHanAllaTheme {
                val state by viewModel.uiState.collectAsStateWithLifecycle()
                var currentScreen by remember { mutableStateOf(ScreenDestination.DASHBOARD) }

                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            containerColor = SurfaceDark,
                            contentColor = TextPrimary
                        ) {
                            ScreenDestination.entries.forEach { destination ->
                                val selected = currentScreen == destination
                                NavigationBarItem(
                                    selected = selected,
                                    onClick = { currentScreen = destination },
                                    icon = {
                                        Icon(
                                            imageVector = destination.icon,
                                            contentDescription = destination.title
                                        )
                                    },
                                    label = { Text(destination.title) },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = PrimaryEmerald,
                                        selectedTextColor = PrimaryEmerald,
                                        unselectedIconColor = TextSecondary,
                                        unselectedTextColor = TextSecondary,
                                        indicatorColor = BorderDark
                                    )
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    when (val s = state) {
                        is CommandCenterUiState.Loading -> LoadingStateContainer(
                            modifier = Modifier.padding(innerPadding)
                        )
                        is CommandCenterUiState.Empty -> EmptyStateContainer(
                            message = s.message,
                            modifier = Modifier.padding(innerPadding)
                        )
                        is CommandCenterUiState.Error -> ErrorStateContainer(
                            cause = s.cause,
                            onRetry = { },
                            modifier = Modifier.padding(innerPadding)
                        )
                        is CommandCenterUiState.Success -> {
                            val contentModifier = Modifier.padding(innerPadding)
                            when (currentScreen) {
                                ScreenDestination.DASHBOARD -> DashboardScreen(
                                    state = s,
                                    onCompleteTask = { viewModel.markTaskComplete(it) },
                                    modifier = contentModifier
                                )
                                ScreenDestination.GRANTS -> GrantsScreen(
                                    opportunities = s.catalogOpportunities,
                                    modifier = contentModifier
                                )
                                ScreenDestination.CLIENTS -> ClientsScreen(
                                    clients = s.activeClients,
                                    modifier = contentModifier
                                )
                                ScreenDestination.TASKS -> TasksScreen(
                                    tasks = s.prioritizedTasks,
                                    onCompleteTask = { viewModel.markTaskComplete(it) },
                                    modifier = contentModifier
                                )
                                ScreenDestination.VAULT -> VaultScreen(
                                    documents = s.expiringDocuments,
                                    modifier = contentModifier
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
