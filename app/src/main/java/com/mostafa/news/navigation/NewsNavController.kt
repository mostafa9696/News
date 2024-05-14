package com.mostafa.news.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mostafa.news_list.model.NewsPresentation

class NewsNavController(val navController: NavHostController)  {
    fun upPress() = navController.navigateUp()

    fun navToDetails(news: NewsPresentation) {
       // navController.currentBackStackEntry?.savedStateHandle?.set(BundleKey.NEWS_KEY, news)
        val gson: Gson = GsonBuilder().create()
        val newsJson = gson.toJson(news)
        navController.navigate("${MainDestinations.DETAILS_ROUTE}/$newsJson")
    }
}


@Composable
fun rememberAppNavController(
    navController: NavHostController = rememberNavController(),
): NewsNavController = remember(navController) {
    NewsNavController(navController)
}