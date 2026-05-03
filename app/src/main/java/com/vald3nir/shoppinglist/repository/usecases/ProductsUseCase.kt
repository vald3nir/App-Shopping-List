package com.vald3nir.shoppinglist.repository.usecases

import com.vald3nir.shoppinglist.domain.dto.DownloadProductDTO
import com.vald3nir.shoppinglist.domain.dto.ProductDTO
import com.vald3nir.shoppinglist.repository.api.FirebaseDataSource
import com.vald3nir.shoppinglist.repository.api.SearchProductDataSource
import com.vald3nir.shoppinglist.repository.database.dao.ProductsDao
import com.vald3nir.shoppinglist.repository.database.entities.ProductEntity
import com.vald3nir.toolkit.core.services.analytics.AnalyticsHelper
import com.vald3nir.toolkit.core.services.rest.RestClientExecutor
import com.vald3nir.toolkit.core.utils.security.generateUUID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class ProductsUseCase @Inject constructor(
    private val productsDao: ProductsDao,
    private val searchProductDataSource: SearchProductDataSource,
    private val analyticsHelper: AnalyticsHelper,
    private val dataSource: FirebaseDataSource,
) : RestClientExecutor() {

    suspend fun downloadProducts() {
        val products = dataSource.downloadProducts()
        analyticsHelper.onLog("Downloaded ${products.size} products.")
        productsDao.clearAndInsert(products.map { it.toEntity() })
    }

    suspend fun searchProductName(barcode: String?) = searchProductDataSource.searchByBarcode(barcode)

    fun getProductNames() = productsDao.getDistinctProductNames()

    fun listAll() = productsDao.getAllProducts().toDTO()
}

private fun Flow<List<ProductEntity>>.toDTO(): Flow<List<ProductDTO>> = map { it.toDTO() }
private fun List<ProductEntity>.toDTO() = this.map { it.toDTO() }

private fun ProductEntity.toDTO() = ProductDTO(
    id = id,
    name = name,
)

private fun DownloadProductDTO.toEntity() = ProductEntity(
    id = id ?: generateUUID(),
    name = name.orEmpty(),
)