package com.mohamedfahmy.taskkoinz.domain.usecase

import com.mohamedfahmy.taskkoinz.domain.repository.HomeRepository

class GetItemsUseCase(private val homeRepository: HomeRepository) {
    suspend fun execute(paramsMap: Map<String, String>) =
        homeRepository.getItems(paramsMap)

}