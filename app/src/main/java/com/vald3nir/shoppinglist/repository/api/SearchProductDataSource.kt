package com.vald3nir.shoppinglist.repository.api

import com.vald3nir.shoppinglist.domain.dto.ProductFromBarcodeDTO
import com.vald3nir.toolkit.core.services.analytics.AnalyticsHelper
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

internal class SearchProductDataSource @Inject constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val api: SearchProductAPI
) {

    suspend fun searchByBarcode(barcode: String?): String? {
        val response = runCatching { api.getProductByBarcode(barcode) }.getOrNull()
        analyticsHelper.onLog("Search Product Code: $barcode")
        analyticsHelper.onLog("Search Product Response: $response")
        return response?.product?.productName
    }
}

internal interface SearchProductAPI {

    @GET("api/v2/product/{barcode}.json")
    suspend fun getProductByBarcode(@Path("barcode") barcode: String?): ProductFromBarcodeDTO?
}