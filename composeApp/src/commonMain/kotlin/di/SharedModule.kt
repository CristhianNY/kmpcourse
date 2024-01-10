package di

import domain.ExampleRepository
import domain.ExampleRepositoryImpl
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.named
import org.koin.core.module.dsl.withOptions
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import screens.HomeViewModel

val sharedModule = module {

    single<ExampleRepository> {
        ExampleRepositoryImpl()
    }.withOptions {
        createdAtStart()
        named("ExampleRepository")
    }

    single {
        HomeViewModel(exampleRepository = get(qualifier = qualifier("ExampleRepository")))
    }
}