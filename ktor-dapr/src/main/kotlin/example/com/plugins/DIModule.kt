package example.com.plugins

import example.com.repository.UsersRepository
import example.com.service.UsersService
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun configureDI() {
    stopKoin()

    startKoin {
        modules(serviceDIModule)
        modules(repoDIModule)
    }
}

val serviceDIModule = module {
    singleOf(::UsersService)
}

val repoDIModule = module {
    singleOf(::UsersRepository)
}