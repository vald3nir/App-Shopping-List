package com.vald3nir.shoppinglist.repository.usecases

import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.domain.mapper.toDTO
import com.vald3nir.shoppinglist.repository.database.dao.ItemShoppingListDao
import com.vald3nir.shoppinglist.repository.database.dao.ShoppingListDao
import com.vald3nir.toolkit.core.baseclasses.ParameterInvalidException
import com.vald3nir.toolkit.core.services.analytics.AnalyticsHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class CloneListUseCase @Inject constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val shoppingListDao: ShoppingListDao,
) {
    suspend fun execute(listId: String?) {
        analyticsHelper.onLog("Clone List called with listId: $listId")
        if (listId == null) throw IllegalArgumentException("List ID cannot be null")
        shoppingListDao.cloneShoppingList(listId)
    }
}

internal class CloseEditListUseCase @Inject constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val validateTitleListUseCase: ValidateTitleListUseCase,
    private val shoppingListDao: ShoppingListDao,
) {
    suspend fun execute(listId: String?, title: String?) {
        analyticsHelper.onLog("close List Editing called with listId: $listId, title: $title")
        if (listId == null || title == null) throw IllegalArgumentException("List ID and Title cannot be null")
        shoppingListDao.closeEditionShoppingList(listId = listId, title = validateTitleListUseCase.execute(title))
    }
}

internal class DeleteListUseCase @Inject constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val shoppingListDao: ShoppingListDao,
    private val itemShoppingListDao: ItemShoppingListDao,
) {
    suspend fun execute(listId: String?) {
        analyticsHelper.onLog("delete list with listId: $listId")
        if (listId == null) throw ParameterInvalidException()
        shoppingListDao.fakeDeleteList(listId)
        itemShoppingListDao.deleteItemsByList(listId)
    }

    suspend fun execute() {
        analyticsHelper.onLog("delete all lists")
        shoppingListDao.deleteAll()
    }
}

internal class LoadListUseCase @Inject constructor(private val shoppingListDao: ShoppingListDao) {

    fun execute(listId: String): Flow<ShoppingListDTO> = shoppingListDao.getShoppingListFlow(listId).map { it?.toDTO() ?: ShoppingListDTO() }

    fun execute(): Flow<List<ShoppingListDTO>> = shoppingListDao.selectShoppingListsFlow().map { entities -> entities.map { it.toDTO() } }
}

internal class OpenEditListUseCase @Inject constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val shoppingListDao: ShoppingListDao
) {
    fun execute(): Flow<ShoppingListDTO> = flow {
        analyticsHelper.onLog("openListEditing called")
        val id = shoppingListDao.openListInEditMode()
        emit(id)
    }.flatMapLatest { listId ->
        shoppingListDao.selectShoppingListWithItemsFlow(listId).map { list ->
            list.toDTO()
        }
    }
}

internal class ValidateTitleListUseCase @Inject constructor(private val dao: ShoppingListDao) {

    suspend fun execute(title: String): String {
        // 1. Busca todos os títulos que começam com o nome desejado
        val existingTitles = dao.getSimilarTitles(title)

        // Se não existir nenhum, pode usar o nome base direto
        if (existingTitles.isEmpty() || !existingTitles.contains(title)) {
            return title
        }

        // 2. Encontra o próximo número disponível
        var counter = 2
        var newTitle = "$title $counter"
        // Garante que o novo título gerado também não existe (ex: "Lista 2")
        while (existingTitles.contains(newTitle)) {
            counter++
            newTitle = "$title $counter"
        }

        return newTitle
    }
}