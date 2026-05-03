package com.vald3nir.shoppinglist.repository.api

import com.vald3nir.shoppinglist.BuildConfig
import com.vald3nir.shoppinglist.domain.dto.DownloadProductDTO
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.toolkit.core.services.analytics.AnalyticsHelper
import com.vald3nir.toolkit.core.services.firebase.FirebaseDatabase
import kotlinx.serialization.json.Json
import javax.inject.Inject

private val jsonDecoder = Json { ignoreUnknownKeys = true }

internal class FirebaseDataSource @Inject constructor(private val analyticsHelper: AnalyticsHelper) {

    suspend fun downloadLists(owner: String): List<ShoppingListDTO> = runCatching {
        val path = getListPath()
        val dataJson = FirebaseDatabase.readList(path = "$path/$owner")
        analyticsHelper.onLog("Download Lists: $dataJson")
        dataJson.map { jsonDecoder.decodeFromString<ShoppingListDTO>(it) }
    }.getOrElse {
        emptyList()
    }

    suspend fun uploadLists(owner: String, lists: List<ShoppingListDTO>) = runCatching {
        val path = getListPath()
        FirebaseDatabase.insertOrUpdate(path = "$path/$owner", data = lists)
        analyticsHelper.onLog("Upload Lists: $lists")
    }

    private fun getListPath() = if (BuildConfig.FLAVOR == "prod") "lists_prod" else "lists_dev"

    suspend fun downloadProducts(): List<DownloadProductDTO> = runCatching {
        val dataJson = FirebaseDatabase.readList(path = "products")
        analyticsHelper.onLog("Products: $dataJson")
        dataJson.map { jsonDecoder.decodeFromString<DownloadProductDTO>(it) }
    }.getOrElse {
        emptyList()
    }
}