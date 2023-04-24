package dev.dicesystems.varsitutor.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.dicesystems.varsitutor.data.models.VacancyModel
import dev.dicesystems.varsitutor.screen.FavouriteScreen
import dev.dicesystems.varsitutor.screen.HomeScreen
import dev.dicesystems.varsitutor.screen.MoreScreen
import dev.dicesystems.varsitutor.screen.NotificationScreen
import dev.dicesystems.varsitutor.screen.ProfileScreen
import dev.dicesystems.varsitutor.screen.VacancyShowScreen

@Composable
fun BottomNavigationGraph(
    navigationController: NavHostController
){
    NavHost(navController = navigationController,
            startDestination = BottomBarScreen.Home.route
        ){
        composable(route = BottomBarScreen.More.route){
            MoreScreen(navController = navigationController)
        }
        composable(route = BottomBarScreen.Favorites.route){
            FavouriteScreen(navController = navigationController)
        }
        composable(route = BottomBarScreen.Home.route){
            HomeScreen(navController = navigationController)
        }
        composable(route = BottomBarScreen.Notifications.route){
            NotificationScreen(navController = navigationController)
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen(navController = navigationController)
        }
        composable(route = Screens.VacancyDetails.route+"/{title}/{description}",
        arguments = listOf(
                navArgument("title"){},
                navArgument("description"){},
        )){
            VacancyShowScreen(
                navController = navigationController,
                title = it.arguments?.getString("title"),
                description = it.arguments?.getString("description"))
        }
    }
}