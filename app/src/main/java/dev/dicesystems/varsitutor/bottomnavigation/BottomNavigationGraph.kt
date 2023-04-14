package dev.dicesystems.varsitutor.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import dev.dicesystems.varsitutor.screen.FavouriteScreen
import dev.dicesystems.varsitutor.screen.MoreScreen
import dev.dicesystems.varsitutor.screen.NotificationScreen
import dev.dicesystems.varsitutor.screen.ProfileScreen
import dev.dicesystems.varsitutor.screen.StudentHomeScreen

@Composable
fun BottomNavigationGraph(
    navigationController: NavHostController
){
    NavHost(navController = navigationController,
            startDestination = BottomBarScreen.Home.route
        ){
        composable(route = BottomBarScreen.More.route){
            MoreScreen()
        }
        composable(route = BottomBarScreen.Favorites.route){
            FavouriteScreen()
        }
        composable(route = BottomBarScreen.Home.route){
            StudentHomeScreen()
        }
        composable(route = BottomBarScreen.Notifications.route){
            NotificationScreen()
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen()
        }
    }
}