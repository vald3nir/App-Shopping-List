package com.vald3nir.shoppinglist.core.analytics

import android.util.Log
import com.vald3nir.shoppinglist.BuildConfig
import com.vald3nir.toolkit.core.services.analytics.AnalyticsEvent
import com.vald3nir.toolkit.core.services.analytics.AnalyticsHelper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AnalyticsModule {

    @Binds
    abstract fun bindsAnalyticsHelper(impl: AnalyticsHelperImpl): AnalyticsHelper
}

@Singleton
internal class AnalyticsHelperImpl @Inject constructor() : AnalyticsHelper {

    override fun logEvent(event: AnalyticsEvent) {
        Log.d("AnalyticsHelper", "Received analytics event: $event")
    }

    override fun onLog(message: String) {
        if (BuildConfig.DEBUG) {
            println("AppLog -> $message")
        }
    }
}