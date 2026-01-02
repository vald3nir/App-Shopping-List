package com.vald3nir.shoppinglist.presentation.app

import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.vald3nir.shoppinglist.core.sync.Sync
import com.vald3nir.toolkit.core.baseclasses.BaseApplication
import com.vald3nir.toolkit.core.services.threads.ProfileVerifierLogger
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : BaseApplication(), Configuration.Provider {
    @Inject
    lateinit var profileVerifierLogger: ProfileVerifierLogger

    override fun onCreate() {
        super.onCreate()
        setStrictModePolicy()
        Sync.initialize(context = this)
        profileVerifierLogger()
    }

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .build()
}