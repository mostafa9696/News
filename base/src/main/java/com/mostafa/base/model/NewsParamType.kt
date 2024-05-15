package com.mostafa.base.model

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson

class NewsParamType : NavType<NewsPresentation>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): NewsPresentation? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): NewsPresentation {
        return Gson().fromJson(value, NewsPresentation::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: NewsPresentation) {
        bundle.putParcelable(key, value)
    }
}