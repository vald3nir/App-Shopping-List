package com.vald3nir.shoppinglist.core.sync

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.vald3nir.shoppinglist.core.sync.workers.SyncProductsWorker
import com.vald3nir.toolkit.core.services.sync.worker.createSyncWorkRequest

internal object Sync {
    fun initialize(context: Context) {
        WorkManager.getInstance(context).apply {
            enqueueUniqueWork("SyncProducts", ExistingWorkPolicy.KEEP, createSyncWorkRequest<SyncProductsWorker>())
        }
    }
}
