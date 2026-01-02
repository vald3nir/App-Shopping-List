package com.vald3nir.shoppinglist.core.repository

import com.vald3nir.shoppinglist.core.domain.dto.ProductDTO
import com.vald3nir.shoppinglist.core.repository.database.dao.ProductsDao
import com.vald3nir.shoppinglist.core.repository.database.entities.ProductEntity
import com.vald3nir.shoppinglist.core.repository.network.api.PublicServicesAPI
import com.vald3nir.shoppinglist.core.repository.network.model.ProductFromBarcodeDTO
import com.vald3nir.toolkit.core.services.rest.RestClientExecutor
import com.vald3nir.toolkit.core.utils.security.generateUUID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface ProductsRepository {
    suspend fun insertProducts(products: List<ProductDTO>)
    suspend fun findProductFromBarcode(barcode: String): ProductFromBarcodeDTO?
    fun getProductNames(): Flow<List<String>>
    fun getAllProducts(): Flow<List<ProductDTO>>
}

internal class ProductsRepositoryImpl @Inject constructor(
    private val productsDao: ProductsDao,
    private val publicServicesAPI: PublicServicesAPI,
) : ProductsRepository, RestClientExecutor() {


    override suspend fun insertProducts(products: List<ProductDTO>) {
        productsDao.clearAndInsert(products.toModel())
    }

    override suspend fun findProductFromBarcode(barcode: String) = execute {
        publicServicesAPI.getProductByBarcode(barcode)
    }

    override fun getProductNames() = productsDao.getDistinctProductNames()

    override fun getAllProducts() = productsDao.getAllProducts().toDTO()

    private fun List<ProductDTO>.toModel() = this.map { it.toModel() }

    private fun ProductDTO.toModel() = ProductEntity(
        id = id ?: generateUUID(),
        name = name,
    )

    private fun Flow<List<ProductEntity>>.toDTO(): Flow<List<ProductDTO>> = map { it.toDTO() }
    private fun List<ProductEntity>.toDTO() = this.map { it.toDTO() }

    private fun ProductEntity.toDTO() = ProductDTO(
        id = id,
        name = name,
        category = "category"
    )
}
