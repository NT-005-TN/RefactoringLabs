package com.example.refactoringapp.navigation

import io.mockk.mockk
import io.mockk.verify
import androidx.navigation.NavController
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("AppRoute - типобезопасная навигация")
class AppRouteTest {

    @Nested
    @DisplayName("Статические маршруты")
    inner class StaticRoutes {
        @Test
        fun `Welcome route has correct path`() {
            assertEquals("welcome", AppRoute.Welcome.route)
        }

        @Test
        fun `Auth route has correct path`() {
            assertEquals("auth", AppRoute.Auth.route)
        }

        @Test
        fun `All static routes are unique`() {
            val routes = listOf(
                AppRoute.Welcome, AppRoute.Auth, AppRoute.Registration,
                AppRoute.Onboarding, AppRoute.CoffeeList, AppRoute.Promotions,
                AppRoute.Profile, AppRoute.ChooseCard
            )
            val uniqueRoutes = routes.map { it.route }.toSet()
            assertEquals(routes.size, uniqueRoutes.size, "Routes should be unique")
        }
    }

    @Nested
    @DisplayName("Динамические маршруты с параметрами")
    inner class DynamicRoutes {
        @Test
        fun `QRCode route includes cardId in path`() {
            val route = AppRoute.QRCode(42)
            assertEquals("qrCode/42", route.route)
        }

        @Test
        fun `ActionDetail route includes actionId in path`() {
            val route = AppRoute.ActionDetail(123)
            assertEquals("actionProfile/123", route.route)
        }
    }

    @Nested
    @DisplayName("Extension functions для NavController")
    inner class NavigationExtensions {
        private val navController = mockk<NavController>(relaxed = true)

        @Test
        fun `navigateToAuth calls navigate with correct route`() {
            navController.navigateToAuth()
            verify { navController.navigate(AppRoute.Auth.route) }
        }

        @Test
        fun `navigateToQRCode calls navigate with cardId`() {
            navController.navigateToQRCode(7)
            verify { navController.navigate("qrCode/7") }
        }

        @Test
        fun `navigateToActionDetail calls navigate with actionId`() {
            navController.navigateToActionDetail(99)
            verify { navController.navigate("actionProfile/99") }
        }
    }

    @Test
    fun `getDebugRoutes returns non-empty list with labels`() {
        val debugRoutes = AppRoute.getDebugRoutes()
        assert(debugRoutes.isNotEmpty())
        debugRoutes.forEach { (route, label) ->
            assert(route.route.isNotEmpty()) { "Route path should not be empty" }
            assert(label.isNotEmpty()) { "Label should not be empty" }
        }
    }
}