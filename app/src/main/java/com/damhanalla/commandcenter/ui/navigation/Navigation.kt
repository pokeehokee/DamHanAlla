package com.damhanalla.commandcenter.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.People
import androidx.compose.ui.graphics.vector.ImageVector

enum class ScreenDestination(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    DASHBOARD("dashboard", "Overview", Icons.Outlined.Dashboard),
    GRANTS("grants", "Grants", Icons.Outlined.MonetizationOn),
    CLIENTS("clients", "Clients", Icons.Outlined.People),
    TASKS("tasks", "Tasks", Icons.Outlined.Assignment),
    VAULT("vault", "Vault", Icons.Outlined.Description)
}
