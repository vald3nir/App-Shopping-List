package com.vald3nir.shoppinglist.core.repository

import com.vald3nir.shoppinglist.core.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.core.repository.database.dao.CategoryDao
import com.vald3nir.shoppinglist.core.repository.database.dao.ItemShoppingListDao
import com.vald3nir.shoppinglist.core.repository.database.entities.ItemShoppingListEntity
import com.vald3nir.toolkit.core.baseclasses.ParameterInvalidException
import com.vald3nir.toolkit.core.services.analytics.AnalyticsHelper
import com.vald3nir.toolkit.core.utils.extensions.orFalse
import com.vald3nir.toolkit.core.utils.extensions.orZero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface ItemShoppingListRepository {
    suspend fun createNewShoppingListItem(item: ItemShoppingListDTO)
    fun getItemFlow(itemId: Long): Flow<ItemShoppingListDTO>
    fun getItemsByListFlow(listId: Long): Flow<List<ItemShoppingListDTO>>
    suspend fun toggleIsAdd(itemId: Long?)
    suspend fun updateItem(item: ItemShoppingListDTO)
    suspend fun removeItem(itemId: Long?)
}

internal class ItemShoppingListRepositoryImpl @Inject constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val itemShoppingListDao: ItemShoppingListDao,
    private val categoryDao: CategoryDao,
) : ItemShoppingListRepository {

    override suspend fun createNewShoppingListItem(item: ItemShoppingListDTO) {
        analyticsHelper.onLog("createNewShoppingListItem called with item: $item")
        val categoryInfo = item.product?.let { categoryDao.getProductWithCategoryByName(it) }
        itemShoppingListDao.insert(
            entity = ItemShoppingListEntity(
                shoppingListId = item.shoppingListId,
                quantity = item.quantity,
                unitPrice = item.unitPrice,
                category = categoryInfo?.categoryName,
                iconURL = categoryInfo?.iconURL,
                product = categoryInfo?.productName ?: item.product,
            )
        )
    }

    override fun getItemFlow(itemId: Long) = itemShoppingListDao.getItemFlow(itemId).map { it.toDTO() }

    override fun getItemsByListFlow(listId: Long) = itemShoppingListDao.getItemsByListFlow(listId).map { it.toDTO() }

    override suspend fun toggleIsAdd(itemId: Long?) {
        analyticsHelper.onLog("toggleIsAdd called with itemId: $itemId")
        if (itemId == null) throw ParameterInvalidException()
        itemShoppingListDao.toggleIsAdd(itemId)
    }

    override suspend fun updateItem(item: ItemShoppingListDTO) {
        analyticsHelper.onLog("updateItem called with item: $item")
        itemShoppingListDao.insert(item.toEntity())
    }

    override suspend fun removeItem(itemId: Long?) {
        analyticsHelper.onLog("removeItem called with itemId: $itemId")
        if (itemId == null) throw ParameterInvalidException()
        itemShoppingListDao.deleteItem(itemId)
    }

    private fun List<ItemShoppingListEntity>.toDTO() = this.map { it.toDTO() }
    private fun ItemShoppingListEntity?.toDTO() = ItemShoppingListDTO(
        id = this?.id,
        shoppingListId = this?.shoppingListId,
        category = this?.category,
        quantity = this?.quantity.orZero(),
        product = this?.product,
        iconURL = this?.iconURL,
        unitPrice = this?.unitPrice.orZero(),
        isAdd = this?.isAdd.orFalse()
    )

    private fun ItemShoppingListDTO.toEntity() = ItemShoppingListEntity(
        id = this.id,
        shoppingListId = this.shoppingListId,
        category = this.category,
        quantity = this.quantity,
        product = this.product,
        iconURL = this.iconURL,
        unitPrice = this.unitPrice,
        isAdd = this.isAdd
    )
}