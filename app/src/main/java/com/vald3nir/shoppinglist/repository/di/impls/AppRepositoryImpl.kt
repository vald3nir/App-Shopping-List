package com.vald3nir.shoppinglist.repository.di.impls

import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.repository.AppRepository
import com.vald3nir.shoppinglist.repository.usecases.AddNewItemListUseCase
import com.vald3nir.shoppinglist.repository.usecases.ChangeItemStatusOnShoppingCartUseCase
import com.vald3nir.shoppinglist.repository.usecases.CloneListUseCase
import com.vald3nir.shoppinglist.repository.usecases.CloseEditListUseCase
import com.vald3nir.shoppinglist.repository.usecases.DeleteItemListUseCase
import com.vald3nir.shoppinglist.repository.usecases.DeleteListUseCase
import com.vald3nir.shoppinglist.repository.usecases.LoadItemListUseCase
import com.vald3nir.shoppinglist.repository.usecases.LoadItemsByListUseCase
import com.vald3nir.shoppinglist.repository.usecases.LoadListUseCase
import com.vald3nir.shoppinglist.repository.usecases.LoadTopFrequentsItemListsUseCase
import com.vald3nir.shoppinglist.repository.usecases.OpenEditListUseCase
import com.vald3nir.shoppinglist.repository.usecases.ProductsUseCase
import com.vald3nir.shoppinglist.repository.usecases.SyncListsUseCase
import com.vald3nir.shoppinglist.repository.usecases.UpdateItemListUseCase
import javax.inject.Inject

internal class AppRepositoryImpl @Inject constructor(
    private val productsUseCase: ProductsUseCase,
    private val closeEditListUseCase: CloseEditListUseCase,
    private val cloneListUseCase: CloneListUseCase,
    private val loadListUseCase: LoadListUseCase,
    private val openEditListUseCase: OpenEditListUseCase,
    private val deleteListUseCase: DeleteListUseCase,
    private val deleteItemListUseCase: DeleteItemListUseCase,
    private val addNewItemListUseCase: AddNewItemListUseCase,
    private val loadItemListUseCase: LoadItemListUseCase,
    private val updateItemListUseCase: UpdateItemListUseCase,
    private val loadItemsByListUseCase: LoadItemsByListUseCase,
    private val changeItemStatusOnShoppingCartUseCase: ChangeItemStatusOnShoppingCartUseCase,
    private val loadTopFrequentsItemLists: LoadTopFrequentsItemListsUseCase,
    private val syncListsUseCase: SyncListsUseCase,
) : AppRepository {

    // Sync
    override suspend fun downloadProducts() = productsUseCase.downloadProducts()
    override suspend fun syncLists() = syncListsUseCase.execute()

    // Products
    override suspend fun searchProductName(barcode: String?) = productsUseCase.searchProductName(barcode)
    override fun getProductNames() = productsUseCase.getProductNames()

    // Shopping lists
    override fun getShoppingListFlow(listId: String) = loadListUseCase.execute(listId)
    override fun getShoppingLists() = loadListUseCase.execute()
    override fun openListEditing() = openEditListUseCase.execute()
    override suspend fun closeListEditing(listId: String?, title: String?) = closeEditListUseCase.execute(listId, title)
    override suspend fun cloneShoppingList(listId: String?) = cloneListUseCase.execute(listId)
    override suspend fun deleteShoppingList(listId: String?) = deleteListUseCase.execute(listId)
    override suspend fun deleteLists() = deleteListUseCase.execute()

    // Items
    override suspend fun insertNewItemList(item: ItemShoppingListDTO) = addNewItemListUseCase.execute(item)
    override fun getItemFlow(itemId: String) = loadItemListUseCase.loadItemFlow(itemId)
    override suspend fun getItem(itemId: String?) = loadItemListUseCase.loadItem(itemId)
    override fun getItemsByListFlow(listId: String) = loadItemsByListUseCase.execute(listId)
    override fun loadTopFrequentsItemLists(listId: String?) = loadTopFrequentsItemLists.execute(listId)
    override suspend fun toggleIsAdd(itemId: String?) = changeItemStatusOnShoppingCartUseCase.execute(itemId)

    override suspend fun updateItem(item: ItemShoppingListDTO) = updateItemListUseCase.execute(item)
    override suspend fun removeItem(itemId: String?) = deleteItemListUseCase.execute(itemId)
}