package com.vald3nir.shoppinglist.core.sync.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.vald3nir.shoppinglist.R
import com.vald3nir.toolkit.core.services.analytics.notifyLog
import com.vald3nir.toolkit.core.services.sync.worker.defaultSyncForegroundInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal abstract class BaseSyncWorker(
    private val appContext: Context,
    workerParams: WorkerParameters,
    private val ioDispatcher: CoroutineDispatcher
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun getForegroundInfo() = appContext.defaultSyncForegroundInfo(appContext.getString(R.string.app_name))

    abstract suspend fun doSafeWork(): Result

    override suspend fun doWork(): Result = withContext(ioDispatcher) {
        try {
            doSafeWork()
        } catch (e: Exception) {
            e.notifyLog()
            Result.retry()
        }
    }
}