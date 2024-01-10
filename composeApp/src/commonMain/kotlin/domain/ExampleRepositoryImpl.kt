package domain

class ExampleRepositoryImpl : ExampleRepository {
    override suspend fun getExample(): List<String> {
        return listOf("Cristhian", "Ariel", "Bonilla", "Espinosa")
    }
}