package dev.dicesystems.varsitutor.bottomnavigation

sealed class Screens(val route: String){
    object VacancyDetails: Screens("vacancy_details")


    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach {
                arg -> append("/$arg")
            }
        }
    }
}
