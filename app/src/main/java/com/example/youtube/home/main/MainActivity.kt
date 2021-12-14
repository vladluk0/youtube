package com.example.youtube.home.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.youtube.AppNavigation
import com.example.youtube.R
import com.example.youtube.Screen
import com.example.youtube.ui.theme.YoutubeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YoutubeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Home()
                }
            }
        }
    }
}

@Composable
fun Home() {
    val navController = rememberNavController()

    val currentScreen by navController.currentScreenAsState()
    Scaffold(
        bottomBar = {
            HomeBottomNavigationBar(
                onNavigationSelected = { screen ->  
                    navController.navigate(screen.route)
                },
                selectedNavigation = currentScreen
            )
        }
    ) {
        AppNavigation(navController = navController)
    }
}

@Composable
private fun NavController.currentScreenAsState(): State<Screen> {
    val selectedItem = remember { mutableStateOf<Screen>(Screen.Main) }

    val listener = NavController.OnDestinationChangedListener {_, destination, _ ->
        when {
            destination.hierarchy.any { it.route == Screen.Main.route } -> {
                selectedItem.value = Screen.Main
            }
            destination.hierarchy.any { it.route == Screen.Shorts.route } -> {
                selectedItem.value = Screen.Shorts
            }
            destination.hierarchy.any { it.route == Screen.Add.route } -> {
                selectedItem.value = Screen.Add
            }
            destination.hierarchy.any { it.route == Screen.Subscriptions.route } -> {
                selectedItem.value = Screen.Subscriptions
            }
            destination.hierarchy.any { it.route == Screen.Library.route } -> {
                selectedItem.value = Screen.Library
            }
        }
    }

    addOnDestinationChangedListener(listener)

    return selectedItem
}
@Composable
fun HomeBottomNavigationBar(
    onNavigationSelected: (Screen) -> Unit,
    selectedNavigation: Screen
) {
    val items = listOf(
        NavigationItem.MainItem,
        NavigationItem.ShortsItem,
        NavigationItem.AddItem,
        NavigationItem.SubscriptionItem,
        NavigationItem.LibraryItem,
    )
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.black),
        contentColor = Color.White
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.contentDescription
                    )
                },
                selected = selectedNavigation == item.screen,
                onClick = { onNavigationSelected(item.screen) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    YoutubeTheme {
        Home()
    }
}

sealed class NavigationItem(
    val screen: Screen,
    @DrawableRes val icon: Int,
    val contentDescription: String,
    val text: String
) {
    object MainItem : NavigationItem(Screen.Main, R.drawable.ic_main, "", "Головна")
    object ShortsItem : NavigationItem(Screen.Shorts, R.drawable.ic_shorts, "", "Youtube shorts")
    object AddItem : NavigationItem(Screen.Add, R.drawable.ic_add, "", "")
    object SubscriptionItem : NavigationItem(Screen.Subscriptions, R.drawable.ic_subscriptions, "", "Підписки")
    object LibraryItem : NavigationItem(Screen.Library, R.drawable.ic_library, "", "Бібліотека")
}
