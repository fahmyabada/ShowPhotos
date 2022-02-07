package com.mohamedfahmy.taskkoinz.domain.usecase

import com.mohamedfahmy.taskkoinz.data.model.Items
import com.mohamedfahmy.taskkoinz.domain.repository.HomeRepository
import com.tayyar.delivery.data.util.Resource

class GetItemsUseCase(private val homeRepository: HomeRepository) {
    suspend fun execute(paramsMap: Map<String, String>) =
        homeRepository.getItems(paramsMap)

}