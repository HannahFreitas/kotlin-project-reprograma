package br.com.projetokotlin.controller

import br.com.projetokotlin.model.PersonTest
import br.com.projetokotlin.model.PostTest
import br.com.projetokotlin.model.UserTest
import br.com.projetokotlin.repository.PersonRepository
import br.com.projetokotlin.repository.PostRepository
import br.com.projetokotlin.repository.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.config.BeanIds
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostControllerTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext


    private lateinit var mockMvc: MockMvc

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
        private const val RECURSO = "/post/"
    }

    @BeforeEach
    fun setup() {
        BeanIds.SPRING_SECURITY_FILTER_CHAIN
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder?>(
                SecurityMockMvcConfigurers.springSecurity()).build()
    }

    @Test
    fun `must return status 403 because the person its not authenticated`() {
        val newUser = userRepository.save(user)
        val newPerson = personRepository.save(person)
        val newPost = postRepository.save(post)

        mockMvc.post(RECURSO).andExpect { status { is4xxClientError() } }

    }
}