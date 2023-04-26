package dev.dicesystems.varsitutor.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.dicesystems.varsitutor.screen.FavouriteScreen
import dev.dicesystems.varsitutor.screen.HomeScreen
import dev.dicesystems.varsitutor.screen.MoreScreen
import dev.dicesystems.varsitutor.screen.NotificationScreen
import dev.dicesystems.varsitutor.screen.ProfileScreen
import dev.dicesystems.varsitutor.screen.VacancyShowScreen
import dev.dicesystems.varsitutor.screen.VacancyApplyScreen


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
        composable(route = Screens.VacancyDetails.route+"/{title}/{description}/{department}/{category}/{type}/{user}/{location}/{expires}/{created}",
        arguments = listOf(
                navArgument("title"){},
                navArgument("description"){},
                navArgument("department"){},
                navArgument("category"){},
                navArgument("type"){},
                navArgument("user"){},
                navArgument("location"){},
                navArgument("expires"){},
                navArgument("created"){},

        )){
            VacancyShowScreen(
                navController = navigationController,
                title = it.arguments?.getString("title"),
                description = it.arguments?.getString("description"),
                department = it.arguments?.getString("department"),
                category = it.arguments?.getString("category"),
                type = it.arguments?.getString("type"),
                user = it.arguments?.getString("user"),
                location = it.arguments?.getString("location"),
                expires = it.arguments?.getString("expires"),
                created = it.arguments?.getString("created"),
                )
        }
        composable(route = Screens.VacancyApply.route+"/{title}",
            arguments = listOf(
                navArgument("title"){}

                )){
            VacancyApplyScreen(
                navController = navigationController,
                title = it.arguments?.getString("title")
            )
        }

    }
}