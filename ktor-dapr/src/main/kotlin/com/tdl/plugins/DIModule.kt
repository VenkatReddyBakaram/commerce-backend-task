package com.tdl.plugins

import com.tdl.repository.UsersRepository
import com.tdl.service.UsersService
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
