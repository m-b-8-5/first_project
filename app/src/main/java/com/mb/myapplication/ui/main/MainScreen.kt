package com.mb.myapplication.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mb.myapplication.app.BgInputFieldColor
import com.mb.myapplication.ui.inputList.InputListScreen
import com.mb.myapplication.ui.top.TopScreen
import com.mb.myapplication.utils.Screen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val screens = listOf(
        Screen.Top,
        Screen.InputList
    )
    // transition from the top screen to animation screen
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = BgInputFieldColor,
                elevation = 0.dp
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                screens.forEach { screen ->
                    BottomNavigationItem(
                        selected = currentDestination?.hierarchy?.any { select -> select.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Image(
                                painter = painterResource(id = screen.iconId),
                                null,
                                modifier = Modifier.padding(4.dp)
                            )
                        },
                        label = { Text(text = stringResource(id = screen.resourceId)) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Top.route,
            Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.Top.route) { TopScreen() }
            composable(route = Screen.InputList.route) {
                InputListScreen()
            }
        }
    }
}