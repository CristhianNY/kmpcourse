package di

import data.ExampleDataSource
import data.ExampleDataSourceImpl
import domain.ExampleRepository
import domain.ExampleRepositoryImpl
import login.AuthProviderBridge
import login.SignInViewModel
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.named
import org.koin.core.module.dsl.withOptions
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import screens.HomeViewModel

val sharedModule = module {

    single { AuthProviderBridge() }
    single { SignInViewModel(get()) }

    single<ExampleDataSource> {
        ExampleDataSourceImpl()
    }.withOptions {
        createdAtStart()
        named("ExampleDataSource")
    }
    single<ExampleRepository> {
        ExampleRepositoryImpl(exampleDataSource = get(qualifier("ExampleDataSource")))
    }.withOptions {
        createdAtStart()
        named("ExampleRepository")
    }

    single {
        HomeViewModel(exampleRepository = get(qualifier = qualifier("ExampleRepository")))
    }

}