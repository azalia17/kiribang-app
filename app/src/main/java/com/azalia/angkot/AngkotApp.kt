package com.azalia.angkot

import android.app.Application
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.azalia.angkot.ui.navigation.NavigationItem
import com.azalia.angkot.ui.navigation.Screen
import com.azalia.angkot.ui.screen.destination_map.DestinationMap
import com.azalia.angkot.ui.screen.detail_angkot.DetailAngkotScreen
import com.azalia.angkot.ui.screen.home.HomeScreen
import com.azalia.angkot.ui.screen.list.ListScreen
import com.azalia.angkot.ui.screen.map.MapScreen
import com.azalia.angkot.ui.theme.AngkotTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AngkotApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
//            if (currentRoute  != Screen.DetailAngkot.route && currentRoute  != Screen.DestinationMap.route) {
                if (currentRoute  != Screen.DetailAngkot.route) {
                BottomBar(navController = navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    modifier = modifier,
                    navigateToDestinationMap = {navController.navigate(Screen.DestinationMap.route)}
                )
            }
            composable(Screen.Map.route) {
                MapScreen(modifier = modifier)
            }
            composable(Screen.List.route) {
                ListScreen(
                    modifier = modifier,
//                    application = application,
                    navigateToDetail = { angkotId ->
                        navController.navigate(Screen.DetailAngkot.createRoute(angkotId))
                    }
                )
            }
            composable(
                route = Screen.DetailAngkot.route,
                arguments = listOf(navArgument("id") {type = NavType.StringType}),
                ) {
                val id = it.arguments?.getString("id") ?: ""
                DetailAngkotScreen(angkotId = id, navigateBack = {navController.navigateUp()})
            }
            composable(Screen.DestinationMap.route) {
                DestinationMap(modifier = modifier, navigateBack = {navController.navigateUp()},
//                    application = application,
                    )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(id = R.string.home),
                icon = Icons.Default.Home,
                screen = Screen.DestinationMap,
                contentDescription = stringResource(id = R.string.this_is_home)
            ),
            NavigationItem(
                title = stringResource(id = R.string.chat),
                icon = Icons.Default.Send,
                screen = Screen.Home,
                contentDescription = stringResource(id = R.string.this_is_chat)
            ),
            NavigationItem(
                title = stringResource(id = R.string.maps),
                icon = Icons.Default.Place,
                screen = Screen.Map,
                contentDescription = stringResource(id = R.string.this_is_maps)
            ),
            NavigationItem(
                title = stringResource(id = R.string.list),
                icon = Icons.Default.List,
                screen = Screen.List,
                contentDescription = stringResource(id = R.string.this_is_list)
            ),
        )
        NavigationBar {
            navigationItem.map { item ->
                NavigationBarItem(
//                    label = { Text(text = item.title)},
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }},
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.contentDescription
                        )
                    })

            }
        }
    }
}

//@Preview
//@Composable
//fun AngkotAppPreview() {
//    AngkotTheme {
//        AngkotApp()
//    }
//}
