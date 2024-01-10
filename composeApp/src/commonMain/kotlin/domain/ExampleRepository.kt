package domain

interface ExampleRepository {
    suspend fun getExample(): List<String>
}