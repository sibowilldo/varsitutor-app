package dev.dicesystems.varsitutor.bottomnavigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.dicesystems.varsitutor.data.models.VacancyModel
import dev.dicesystems.varsitutor.screen.CreateApplicationScreen
import dev.dicesystems.varsitutor.screen.FavouriteScreen
import dev.dicesystems.varsitutor.screen.HomeScreen
import dev.dicesystems.varsitutor.screen.MoreScreen
import dev.dicesystems.varsitutor.screen.NotificationScreen
import dev.dicesystems.varsitutor.screen.ProfileScreen
import dev.dicesystems.varsitutor.screen.VacancyShowScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
        composable(route = Screens.VacancyDetails.route+"/{id}/{title}/{description}/{department}/{created_at}/{expires_at}/{location}",
        arguments = listOf(
                navArgument("id"){ this.type = NavType.IntType },
                navArgument("title"){},
                navArgument("description"){},
                navArgument("department"){},
                navArgument("created_at"){},
                navArgument("expires_at"){},
                navArgument("location"){},
        )){
            VacancyShowScreen(
                navController = navigationController,
                id = it.arguments?.getInt("id"),
                title = it.arguments?.getString("title"),
                description = it.arguments?.getString("description"),
                department = it.arguments?.getString("department"),
                created_at = it.arguments?.getString("created_at"),
                expires_at = it.arguments?.getString("expires_at"),
                location = it.arguments?.getString("location"),

            )
        }
        composable(route = Screens.CreateApplication.route+"/{title}/{vacancy_id}",
        arguments = listOf(
                navArgument("title"){},
                navArgument("vacancy_id"){this.type = NavType.IntType},
        )){
            CreateApplicationScreen(
                navController = navigationController,
                title = it.arguments?.getString("title"),
                vacancy_id = it.arguments?.getInt("vacancy_id"),
            )
        }
    }
}