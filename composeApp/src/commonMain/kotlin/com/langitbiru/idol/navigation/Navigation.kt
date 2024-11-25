// MainNavHost.kt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.*
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.touchlab.kermit.Logger
import com.langitbiru.idol.navigation.Screen
import com.langitbiru.idol.screen.Transition.TransitionScreen
import com.langitbiru.idol.screen.home.HomeScreen
import com.langitbiru.idol.screen.kabesha.KabeshaScreen

@Composable
fun MainNavHost(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val onBack: () -> Unit = {navController.popBackStack()}
    val keyboardController = LocalSoftwareKeyboardController.current
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier.onKeyEvent { keyEvent ->
            if (keyEvent.type == KeyEventType.KeyUp && keyEvent.key == Key.Escape) {
                if (navController.previousBackStackEntry != null) {
                    Logger.d("escape pressed")
                    navController.popBackStack()
                    true
                } else { false }
            } else { false }
        }
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateClick = {navController.navigate(it)}
            )
        }
        composable(Screen.Kabesha.route) {
            KabeshaScreen(
                onNavigateClick = {
                    navController.navigate(Screen.Transition.createRoute(
                        memberId = it
                    ))
                }
            )
        }
        composable(
            route = Screen.Transition.route,
            arguments = listOf(
                navArgument("memberId") {
                    type = NavType.IntType

                }
            )
        ) { backStackEntry ->
            val memberId: Int = backStackEntry.arguments?.getInt("memberId")!!
            TransitionScreen(
                memberId = memberId,
                onBackClick = onBack
            )
        }
    }
}
