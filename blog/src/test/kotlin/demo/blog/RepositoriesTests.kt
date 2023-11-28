package demo.blog

import demo.blog.entity.Article
import demo.blog.entity.User
import demo.blog.repository.ArticleRepository
import demo.blog.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val userRepository: UserRepository,
        val articleRepository: ArticleRepository
) {

    @Test
    fun `When findByIdOrNull then return Article`() {
        val bomig88 = User("bomig88", "Bomi Kwon")
        entityManager.persist(bomig88)
        val article = Article("Lorem", "Lorem", "dolor sit amet", bomig88)
        entityManager.persist(article)
        entityManager.flush()
        val found = articleRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)
    }

    @Test
    fun `When findByLogin then return User`() {
        val bomig88 = User("bomig88", "Bomi Kwon")
        entityManager.persist(bomig88)
        entityManager.flush()
        val user = userRepository.findByLogin(bomig88.login)
        assertThat(user).isEqualTo(bomig88)
    }
}