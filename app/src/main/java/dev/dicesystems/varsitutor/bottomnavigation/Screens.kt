package dev.dicesystems.varsitutor.bottomnavigation

sealed class Screens(val route: String){
    object VacancyDetails: Screens("vacancy_details")
    object VacancyApply: Screens("vacancy_apply")



    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach {
                arg -> append("/$arg")
            }
        }
    }
}
