package com.mostafa.news.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mostafa.base.model.NewsParamType
import com.mostafa.base.model.NewsPresentation
import com.mostafa.base.theme.MyNewsTheme
import com.mostafa.details.ui.DetailsScreen
import com.mostafa.news.navigation.BundleKey
import com.mostafa.news.navigation.MainDestinations
import com.mostafa.news.navigation.NewsNavController
import com.mostafa.news_list.ui.NewsScreen

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

    composable(route = "${MainDestinations.DETAILS_ROUTE}/{news}",
        arguments = listOf(navArgument(BundleKey.NEWS_KEY) {
            type = NewsParamType()
        })
        ) {

        val news = it.arguments?.getParcelable<NewsPresentation>(BundleKey.NEWS_KEY)

        DetailsScreen(
            newsPresentation = news!!
        )
    }

}