package com.mostafa.news.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mostafa.details.ui.DetailsScreen
import com.mostafa.news.navigation.BundleKey
import com.mostafa.news.navigation.MainDestinations
import com.mostafa.news.navigation.NewsNavController
import com.mostafa.news.ui.theme.MyNewsTheme
import com.mostafa.news_list.NewsScreen
import com.mostafa.news_list.model.NewsPresentation

@Composable
fun NewsApp(
    appNavController: NewsNavController,
) {
    MyNewsTheme {
        Surface {
            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = appNavController.navController,
                startDestination = MainDestinations.NEWS_ROUTE
            ) {
                newsAppNavGraph(
                    upPress = appNavController::upPress,
                    onNewsClick = appNavController::navToDetails,
                )
            }
        }
    }
}

private fun NavGraphBuilder.newsAppNavGraph(
    upPress: () -> Unit,
    onNewsClick: (news: NewsPresentation) -> Unit,
) {

    composable(route = MainDestinations.NEWS_ROUTE) {
        NewsScreen(onNewsClick = onNewsClick)
    }

    composable(route = "${MainDestinations.DETAILS_ROUTE}/{${BundleKey.NEWS_KEY}}"
        ) {
        val gson: Gson = GsonBuilder().create()
        val newsJson = it.arguments?.getString(BundleKey.NEWS_KEY)
        val news = gson.fromJson(newsJson, NewsPresentation::class.java)

        DetailsScreen(
            onPressBack = upPress,
            newsPresentation = news
        )
    }

}