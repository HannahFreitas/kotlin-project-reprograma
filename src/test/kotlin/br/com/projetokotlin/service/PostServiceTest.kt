package br.com.projetokotlin.service


import br.com.projetokotlin.mapper.PostFormMapper
import br.com.projetokotlin.mapper.PostViewMapper
import br.com.projetokotlin.model.Post
import br.com.projetokotlin.model.PostTest
import br.com.projetokotlin.model.StatusPost
import br.com.projetokotlin.repository.PostRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.`should be equal to`
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable


class PostServiceTest {
    private val post = PostTest.build()
    private val pages = PageImpl(listOf(post))
    private val pageable: Pageable = mockk()

    private val postFormMapper: PostFormMapper = mockk()
    private val postViewMapper: PostViewMapper = mockk()
    private val personService: PersonService = mockk()


    private val postRepository: PostRepository = mockk {
        every { findAll(pageable) } returns pages
    }

    private val postService: PostService = mockk {
        every { findByStatus(pageable) } returns pages
    }


    @Test
    fun `could list post status`() {

        every { postRepository.findByStatus(StatusPost.ABERTO, pageable)} returns pages

        val result = postService.findByStatus(pageable)

        result `should be equal to` pages

    }


}





