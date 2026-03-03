package com.example.refactoringapp.navigation

import androidx.navigation.NavController

/**
 * Запечатанный класс (sealed class) для типобезопасной навигации.
 * Каждый экран представлен объектом или классом с параметрами.
 */
sealed class AppRoute(val route: String) {
    // Статические экраны без параметров
    object Welcome : AppRoute("welcome")
    object Auth : AppRoute("auth")
    object Registration : AppRoute("reg")
    object Onboarding : AppRoute("meet")
    object CoffeeList : AppRoute("coffeeList")
    object Promotions : AppRoute("coffeeActions")
    object Profile : AppRoute("profile")
    object ChooseCard : AppRoute("chooseCard")

    // Динамические экраны с параметрами
    data class QRCode(val cardId: Int) : AppRoute("qrCode/$cardId")
    data class ActionDetail(val actionId: Int) : AppRoute("actionProfile/$actionId")

    companion object {
        /**
         * Возвращает список маршрутов для отладки.
         * Используется в DeveloperNavigationDialog.
         */
        fun getDebugRoutes(): List<Pair<AppRoute, String>> = listOf(
            Auth to "Авторизация",
            Registration to "Регистрация",
            Onboarding to "Onboarding",
            CoffeeList to "Список карт",
            Promotions to "Акции",
            Profile to "Профиль",
            ChooseCard to "Выбор карты",
            QRCode(1) to "QR-код (cardId=1)",
            ActionDetail(1) to "Детали акции (id=1)"
        )
    }
}

/**
 * Extension functions для типобезопасной навигации.
 * Позволяют вызывать навигацию без явного создания строк.
 */
fun NavController.navigateToQRCode(cardId: Int) {
    navigate(AppRoute.QRCode(cardId).route)
}

fun NavController.navigateToActionDetail(actionId: Int) {
    navigate(AppRoute.ActionDetail(actionId).route)
}

fun NavController.navigateToAuth() {
    navigate(AppRoute.Auth.route)
}

fun NavController.navigateToRegistration() {
    navigate(AppRoute.Registration.route)
}

fun NavController.navigateToOnboarding() {
    navigate(AppRoute.Onboarding.route)
}

fun NavController.navigateToCoffeeList() {
    navigate(AppRoute.CoffeeList.route)
}

fun NavController.navigateToPromotions() {
    navigate(AppRoute.Promotions.route)
}

fun NavController.navigateToProfile() {
    navigate(AppRoute.Profile.route)
}

fun NavController.navigateToChooseCard() {
    navigate(AppRoute.ChooseCard.route)
}

fun NavController.navigateToWelcome() {
    navigate(AppRoute.Welcome.route)
}