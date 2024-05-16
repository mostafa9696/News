package com.mostafa.news.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.mostafa.base.model.NewsPresentation

class NewsNavController(val navController: NavHostController)  {

    fun navToDetails(news: NewsPresentation) {
        val json = Uri.encode(Gson().toJson(news))
        navController.navigate("${MainDestinations.DETAILS_ROUTE}/$json")
    }
}


@Composable
fun rememberAppNavController(
    navController: NavHostController = rememberNavController(),
): NewsNavController = remember(navController) {
    NewsNavController(navController)
}