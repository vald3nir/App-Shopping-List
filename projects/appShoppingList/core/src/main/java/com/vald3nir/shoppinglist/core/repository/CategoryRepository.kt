package com.vald3nir.shoppinglist.core.repository

import com.vald3nir.shoppinglist.core.domain.dto.CategoryDTO
import com.vald3nir.shoppinglist.core.repository.database.dao.CategoryDao
import com.vald3nir.shoppinglist.core.repository.database.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface CategoryRepository {
    fun getCategoriesFlow(): Flow<List<CategoryDTO>>
}

internal class CategoryRepositoryImpl @Inject constructor(private val dao: CategoryDao) : CategoryRepository {

    override fun getCategoriesFlow() = dao.getCategoriesFlow().map { it.toDTO() }

    private fun List<CategoryEntity>.toDTO() = map { it.toDTO() }

    private fun CategoryEntity.toDTO() = CategoryDTO(
        id = id,
        name = name,
        iconURL = iconURL,
    )
}