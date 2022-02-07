package com.mohamedfahmy.taskkoinz.domain.usecase

import com.mohamedfahmy.taskkoinz.data.model.Photo
import com.mohamedfahmy.taskkoinz.domain.repository.HomeRepository

class SaveItemsUseCase(private val homeRepository: HomeRepository) {

    suspend fun execute(item: List<Photo>) = homeRepository.saveItem(item)
}