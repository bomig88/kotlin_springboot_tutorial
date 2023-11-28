package demo.blog

import demo.blog.entity.Article
import demo.blog.entity.User
import demo.blog.repository.ArticleRepository
import demo.blog.repository.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(userRepository: UserRepository,
                            articleRepository: ArticleRepository) = ApplicationRunner {
        val bomig88 = userRepository.save(User("bomig88", "Bomi Kwon"))
        articleRepository.save(Article(
                title="Lorem",
                headline="Lorem",
                content="dolor sit amet",
                author=bomig88
        ))
        articleRepository.save(Article(
                title = "Ipsum",
                headline = "Ipsum",
                content = "dolor sit amet",
                author = bomig88
        ))
    }
}