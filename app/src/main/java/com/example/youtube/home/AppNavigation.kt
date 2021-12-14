package com.example.youtube

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Shorts : Screen("shorts")
    object Add : Screen("add")
    object Subscriptions : Screen("subscriptions")
    object Library : Screen("library")
}

sealed class JustScreen(val route: String) {

    fun createRoot(root: Screen) = "${root.route} + $root"

    object Main : JustScreen("main")
    object Shorts : JustScreen("shorts")
    object Add : JustScreen("add")
    object Subscriptions : JustScreen("subscriptions")
    object Library : JustScreen("library")
}

fun NavGraphBuilder.addShortsTopLevel(
    navController: NavController
) {
    navigation(
        route = Screen.Shorts.route,
        startDestination = JustScreen.Shorts.createRoot(Screen.Shorts),
    ) {
        addShorts(navController)
    }
}

fun NavGraphBuilder.addMainTopLevel(
    navController: NavController
) {
    navigation(
        route = Screen.Main.route,
        startDestination = JustScreen.Main.createRoot(Screen.Main),
    ) {
        addMain(navController)
    }
}

fun NavGraphBuilder.addAddTopLevel(
    navController: NavController
) {
    navigation(
        route = Screen.Add.route,
        startDestination = JustScreen.Add.createRoot(Screen.Add),
    ) {
        addAdd(navController)
    }
}

fun NavGraphBuilder.addSubscriptionsTopLevel(
    navController: NavController
) {
    navigation(
        route = Screen.Subscriptions.route,
        startDestination = JustScreen.Subscriptions.createRoot(Screen.Subscriptions),
    ) {
        addSubscriptions(navController)
    }
}

fun NavGraphBuilder.addLibraryTopLevel(
    navController: NavController
) {
    navigation(
        route = Screen.Library.route,
        startDestination = JustScreen.Library.createRoot(Screen.Library),
    ) {
        addLibrary(navController)
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        addMainTopLevel(navController)
        addShortsTopLevel(navController)
        addAddTopLevel(navController)
        addSubscriptionsTopLevel(navController)
        addLibraryTopLevel(navController)
    }
}

fun NavGraphBuilder.addMain(
    navController: NavController
) {
    composable(JustScreen.Main.createRoot(Screen.Main)) {}
}

fun NavGraphBuilder.addShorts(
    navController: NavController
) {
    composable(JustScreen.Main.createRoot(Screen.Shorts)) {}
}

fun NavGraphBuilder.addAdd(
    navController: NavController
) {
    composable(JustScreen.Add.createRoot(Screen.Add)) {}
}

fun NavGraphBuilder.addSubscriptions(
    navController: NavController
) {
    composable(JustScreen.Subscriptions.createRoot(Screen.Subscriptions)) {}
}

fun NavGraphBuilder.addLibrary(
    navController: NavController
) {
    composable(JustScreen.Library.createRoot(Screen.Library)) {}
}





