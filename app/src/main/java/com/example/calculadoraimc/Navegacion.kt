package com.example.calculadoraimc

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavegacionApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {
        composable("inicio") {

            PantallaInicio(navController)
        }
        composable(
            route = "resultado/{nombre}/{imc}",

            arguments = listOf(
                navArgument("nombre") {
                    type = NavType.StringType
                },
                navArgument("imc") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val nombre =
                backStackEntry.arguments
                    ?.getString("nombre") ?: ""
            val imc =
                backStackEntry.arguments
                    ?.getString("imc")
                    ?.toFloatOrNull() ?: 0f

            PantallaResultado(
                nombre = nombre,
                imc = imc,
                navController = navController
            )
        }
    }
}