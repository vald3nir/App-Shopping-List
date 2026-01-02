package com.vald3nir.shoppinglist.core.repository.network.api

import com.vald3nir.shoppinglist.core.repository.network.model.ProductFromBarcodeDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface PublicServicesAPI {

    @GET("api/v0/product/{barcode}.json")
    suspend fun getProductByBarcode(@Path("barcode") barcode: String): ProductFromBarcodeDTO?
}