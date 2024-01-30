package data

import data.entity.PicturesItemEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

class ExampleDataSourceImpl : ExampleDataSource {

    private var httpClient: HttpClient? = null


    init {
        httpClient = HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    override suspend fun getExample(): List<PicturesItemEntity> {
        return httpClient?.get("https://sebi.io/demo-image-api/pictures.json")
            ?.body<List<PicturesItemEntity>>() ?: emptyList()
    }
}