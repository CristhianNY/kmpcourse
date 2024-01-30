package domain

import data.ExampleDataSource
import data.entity.toModel
import domain.model.PicturesItemModel
import org.koin.core.annotation.Named

class ExampleRepositoryImpl(@Named("ExampleDataSource") val exampleDataSource: ExampleDataSource) :
    ExampleRepository {
    override suspend fun getExample(): List<PicturesItemModel> {
        return exampleDataSource.getExample().map {
            it.toModel()
        }
    }
}