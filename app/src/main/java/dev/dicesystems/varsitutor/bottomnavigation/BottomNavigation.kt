package dev.dicesystems.varsitutor.bottomnavigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigation() {
    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = { BottomBar(navHostController = navController) }
    ) {
        Column(modifier = Modifier.padding(it)) {
            BottomNavigationGraph(navigationController = navController)
        }
    }
}

@Composable
fun BottomBar(
    navHostController: NavHostController
) {
    val screens = listOf(
        BottomBarScreen.More,
        BottomBarScreen.Favorites,
        BottomBarScreen.Home,
        BottomBarScreen.Notifications,
        BottomBarScreen.Profile,
    )

    val navStackBackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Row(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 20.dp)
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth(),
        horizontalArrangement  = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        screens.forEach{screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navHostController)
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
    ) {
    val selected = currentDestination?.hierarchy?.any {  it.route == screen.route } == true

    val background =  if (selected) MaterialTheme.colorScheme.onPrimaryContainer else Color.Transparent
    val contentColor = if (selected) MaterialTheme.colorScheme.inverseOnSurface else MaterialTheme.colorScheme.onSurface

    Box(
        modifier = Modifier
            .height(40.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }),
    ){
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            Icon(
                painter = painterResource(id = if (selected) screen.icon_focused else screen.icon),
                contentDescription = "${screen.title} icon",
            tint = contentColor)
            AnimatedVisibility(visible = selected) {
                Text(
                    text=screen.title,
                    color = contentColor, style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}

@Preview
@Composable
fun BottomNavPreview() {
    BottomNavigation()
}