package dev.dicesystems.varsitutor.bottomnavigation

import dev.dicesystems.varsitutor.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {
    // More/Nav Drawer
    object More : BottomBarScreen(
        route = "more",
        title = "More",
        icon = R.drawable.ic_bottom_menu,
        icon_focused = R.drawable.ic_bottom_focused_menu
    )

    // Home
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.ic_bottom_home2,
        icon_focused = R.drawable.ic_bottom_focused_home
    )

    // Favorites
    object Favorites : BottomBarScreen(
        route = "favorites",
        title = "Favorites",
        icon = R.drawable.ic_bottom_favorite,
        icon_focused = R.drawable.ic_bottom_focused_favorite
    )

    //    Notification
    object Notifications : BottomBarScreen(
        route = "notifications",
        title = "Notifications",
        icon = R.drawable.ic_bottom_notifications2,
        icon_focused = R.drawable.ic_bottom_focused_notifications
    )

    // Profile
    object Profile : BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = R.drawable.ic_bottom_person,
        icon_focused = R.drawable.ic_bottom_focused_person
    )
}
