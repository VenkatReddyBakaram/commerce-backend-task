package example.com.service

import example.com.repository.UsersRepository
import io.ktor.server.testing.createTestEnvironment
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import kotlin.test.assertEquals

class UsersServiceTest {
    @MockK
    lateinit var usersService: UsersService
    private val repoModule = module {
        singleOf(::UsersRepository)
    }

    @Before
    fun setUp() {
        createTestEnvironment {
            stopKoin()
            startKoin {
                modules(repoModule)
            }
            MockKAnnotations.init(this)
            usersService = UsersService()
        }
    }

    @Test
    fun getUsersDataTest() {
        runBlocking {
            val inputId = "1"
            val expected = "Leanne Graham"
            val response = usersService.getUserDetails(inputId)
            assertEquals(expected, response.userResponse[0].name)
        }
    }
}
