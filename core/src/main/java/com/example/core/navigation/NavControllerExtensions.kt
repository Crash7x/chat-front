package com.example.core.navigation

import androidx.navigation.NavController

fun NavController.navigate(navigationCommon: NavCommand) {
    navigate(navigationCommon.action, navigationCommon.args, navigationCommon.navOptions)
}