package my.android.gamingappui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import my.android.gamingappui.screens.Detail
import my.android.gamingappui.screens.Home
import my.android.gamingappui.data.gameList


@Composable
fun Navigation(navController: NavController){
    NavHost(navController = navController as NavHostController, startDestination = Routes.home) {
        composable(Routes.home){
            Home(navController = navController)
        }

        composable(Routes.detail,
            arguments = listOf(navArgument("gameId"){type = NavType.IntType})
        ){backStackEntry ->
            val gameId = backStackEntry.arguments?.getInt("gameId") ?: 0
            val game = gameList.find { it.gameId == gameId }
            if (game != null) {
                Detail(navController = navController, game = game)
            }
        }
    }
}