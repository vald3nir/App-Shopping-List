package com.vald3nir.shoppinglist.core.sync.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.WorkerParameters
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.repository.AppRepository
import com.vald3nir.toolkit.core.services.sync.worker.BaseSyncWorker
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
    private val repository: AppRepository,
    @Dispatcher(JobScope.IO) private val ioDispatcher: CoroutineDispatcher,
) : BaseSyncWorker(
    appName = appContext.getString(R.string.app_name),
    appContext = appContext,
    workerParams = workerParams,
    ioDispatcher = ioDispatcher
) {

    override suspend fun doSafeWork(): Result = withContext(ioDispatcher) {
        val syncedSuccessfully = awaitAll(
            async { repository.downloadProducts() },
        ).all { true }
        if (syncedSuccessfully) {
            Result.success()
        } else {
            Result.retry()
        }
    }
}