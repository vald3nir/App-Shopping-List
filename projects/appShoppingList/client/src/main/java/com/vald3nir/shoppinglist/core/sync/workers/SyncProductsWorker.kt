package com.vald3nir.shoppinglist.core.sync.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.WorkerParameters
import com.vald3nir.shoppinglist.core.repository.sync.SyncProductsRepository
import com.vald3nir.toolkit.core.services.threads.Dispatcher
import com.vald3nir.toolkit.core.services.threads.JobScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

@HiltWorker
internal class SyncProductsWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: SyncProductsRepository,
    @Dispatcher(JobScope.IO) private val ioDispatcher: CoroutineDispatcher,
) : BaseSyncWorker(appContext, workerParams, ioDispatcher) {

    override suspend fun doSafeWork(): Result = withContext(ioDispatcher) {
        repository.checkDbSyncStatus()

        val syncedSuccessfully = awaitAll(
            async { repository.syncProducts() },
            async { repository.syncCategories() },
        ).all { true }

        if (syncedSuccessfully) {
            Result.success()
        } else {
            Result.retry()
        }
    }
}