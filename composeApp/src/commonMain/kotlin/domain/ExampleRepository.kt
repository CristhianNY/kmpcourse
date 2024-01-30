package domain

import domain.model.PicturesItemModel

interface ExampleRepository {
    suspend fun getExample(): List<PicturesItemModel>
}