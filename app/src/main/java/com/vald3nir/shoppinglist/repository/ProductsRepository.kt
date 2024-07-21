package com.vald3nir.shoppinglist.repository

import com.vald3nir.shoppinglist.db.dao.ProductsDao
import com.vald3nir.shoppinglist.domain.dto.ProductDTO
import com.vald3nir.shoppinglist.domain.dto.ProductFromBarcodeDTO
import com.vald3nir.shoppinglist.domain.mapper.toDTO
import com.vald3nir.shoppinglist.domain.mapper.toModel
import com.vald3nir.shoppinglist.repository.api.PublicServicesAPI
import com.vald3nir.tolkit.networking.rest.RestClientExecutor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ProductsRepository {
    suspend fun insertProducts(products: List<ProductDTO>)
    suspend fun findProductFromBarcode(barcode: String): ProductFromBarcodeDTO?
    fun getAllProducts(): Flow<List<ProductDTO>>
}

class ProductsRepositoryImpl @Inject constructor(
    private val productsDao: ProductsDao,
    private val publicServicesAPI: PublicServicesAPI,
) : ProductsRepository, RestClientExecutor() {


    override suspend fun insertProducts(products: List<ProductDTO>) {
        productsDao.clearAndInsert(products.toModel())
    }

    override suspend fun findProductFromBarcode(barcode: String) = execute {
        publicServicesAPI.getProductByBarcode(barcode)
    }

    override fun getAllProducts() = productsDao.getAllProducts().toDTO()
}
