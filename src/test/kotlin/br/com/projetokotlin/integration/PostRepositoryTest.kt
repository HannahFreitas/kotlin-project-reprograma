package br.com.projetokotlin.integration


import br.com.projetokotlin.model.*
import br.com.projetokotlin.repository.PersonRepository
import br.com.projetokotlin.repository.PostRepository
import br.com.projetokotlin.repository.UserRepository
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest {

    @Autowired
    private lateinit var postRepository: PostRepository

    @Autowired
    private lateinit var personRepository: PersonRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    private val post = PostTest.build()
    private val person = PersonTest.build()
    private val user = UserTest.build()



    companion object {
        @Container
        private val postgresContainer = PostgreSQLContainer<Nothing>("postgres").apply {
            withDatabaseName("testdb")
            withUsername("hannah")
            withPassword("123456")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgresContainer::getJdbcUrl)
            registry.add("spring.datasource.password", postgresContainer::getPassword)
            registry.add("spring.datasource.username", postgresContainer::getUsername)
        }
    }

    @Test
    fun `list for status`() {
        userRepository.save(user)
        personRepository.save(person)
        val newPost = postRepository.save(post)
        val status = postRepository.findByStatus(newPost.status, PageRequest.of(0, 5))


        assertThat(status).isNotNull


    }
}