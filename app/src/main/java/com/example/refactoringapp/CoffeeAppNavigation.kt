import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.refactoringapp.components.BoxWithBackButton
import com.example.refactoringapp.navigation.AppRoute
import com.example.refactoringapp.theme.CoffeeDarkBrown
import com.example.refactoringapp.welcomeAfter.WelcomeScreen

/**
 * Главный навигационный граф приложения.
 * Здесь определяем все экраны и переходы между ними.
 */
@Composable
fun CoffeeAppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoute.Welcome.route
    ) {
        // 🏠 Welcome Screen
        composable(AppRoute.Welcome.route) {
            WelcomeScreen(
                onNavigateToAuth = {
                    navController.navigate(AppRoute.Auth.route)
                },
                onNavigateToRoute = { route ->
                    navController.navigate(route.route)
                }
            )
        }

        // 🔐 Placeholder screens (все экраны-заглушки добавляются через общую функцию)
        addPlaceholderScreen(AppRoute.Auth, "Авторизация", navController)
        addPlaceholderScreen(AppRoute.Registration, "Регистрация", navController)
        addPlaceholderScreen(AppRoute.Onboarding, "Onboarding", navController)
        addPlaceholderScreen(AppRoute.CoffeeList, "Список карт", navController)
        addPlaceholderScreen(AppRoute.Promotions, "Акции", navController)
        addPlaceholderScreen(AppRoute.Profile, "Профиль", navController)
        addPlaceholderScreen(AppRoute.ChooseCard, "Выбор карты", navController)

        // 📱 QR Code Screen (с аргументом cardId)
        composable(
            route = "qrCode/{cardId}",
            arguments = listOf(navArgument("cardId") { defaultValue = 0 })
        ) { backStackEntry ->
            val cardId = backStackEntry.arguments?.getInt("cardId") ?: 0
            PlaceholderScreen(
                title = "QR-код карты #$cardId",
                onBack = { navController.popBackStack() }
            )
        }

        // 🎯 Action Detail Screen (с аргументом actionId)
        composable(
            route = "actionProfile/{actionId}",
            arguments = listOf(navArgument("actionId") { defaultValue = 0 })
        ) { backStackEntry ->
            val actionId = backStackEntry.arguments?.getInt("actionId") ?: 0
            PlaceholderScreen(
                title = "Детали акции #$actionId",
                onBack = { navController.popBackStack() }
            )
        }
    }
}

/**
 * Вспомогательная функция для добавления экранов-заглушек.
 * Уменьшает дублирование кода в навигационном графе.
 */
private fun NavGraphBuilder.addPlaceholderScreen(
    route: AppRoute,
    title: String,
    navController: NavHostController
) {
    composable(route.route) {
        PlaceholderScreen(
            title = title,
            onBack = { navController.popBackStack() }
        )
    }
}

/**
 * Универсальный экран-заглушка для экранов, которые ещё не реализованы.
 * Использует компонент BoxWithBackButton для отображения.
 */
@Composable
fun PlaceholderScreen(
    title: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithBackButton(
        title = title,
        onBack = onBack
    ) {
        Text(
            text = "Экран в разработке",
            color = CoffeeDarkBrown.copy(alpha = 0.6f),
            modifier = modifier
        )
    }
}