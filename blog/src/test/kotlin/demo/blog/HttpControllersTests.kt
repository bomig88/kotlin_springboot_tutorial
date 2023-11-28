package demo.blog

import com.ninjasquad.springmockk.MockkBean
import demo.blog.entity.Article
import demo.blog.entity.User
import demo.blog.repository.ArticleRepository
import demo.blog.repository.UserRepository
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class HttpControllersTests(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var userRepository: UserRepository

    @MockkBean
    lateinit var articleRepository: ArticleRepository

    @Test
    fun `List articles`() {
        val bomig88 = User("bomig88", "Bomi Kwon")
        val lorem5Article = Article("Lorem", "Lorem", "dolor sit amet", bomig88)
        val ipsumArticle = Article("Ipsum", "Ipsum", "dolor sit amet", bomig88)
        every { articleRepository.findAllByOrderByAddedAtDesc() } returns listOf(lorem5Article, ipsumArticle)
        mockMvc.perform(get("/api/article/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].author.login").value(bomig88.login))
                .andExpect(jsonPath("\$.[0].slug").value(lorem5Article.slug))
                .andExpect(jsonPath("\$.[1].author.login").value(bomig88.login))
                .andExpect(jsonPath("\$.[1].slug").value(ipsumArticle.slug))
    }

    @Test
    fun `List users`() {
        val bomig88 = User("bomig88", "Bomi Kwon")
        val tester9 = User("tester9", "Tester")
        every { userRepository.findAll() } returns listOf(bomig88, tester9)
        mockMvc.perform(get("/api/user/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].login").value(bomig88.login))
                .andExpect(jsonPath("\$.[1].login").value(tester9.login))
    }
}