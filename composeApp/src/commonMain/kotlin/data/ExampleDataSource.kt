package data

import data.entity.PicturesItemEntity

interface ExampleDataSource {
    suspend fun getExample(): List<PicturesItemEntity>
}