package br.com.zup.autores

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.http.HttpResponse

@MicronautTest
internal class NovoAutorControllerTest {

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var autor: Autor

    @BeforeEach
    internal fun setup() {
        autor = Autor("Tales Araujo", "tales.araujo@zup.com.br", "Dev na Zup")
        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown() {
        autorRepository.deleteAll()
    }

    @Test
    internal fun `deve cadastrar um novo autor`() {
        val request = HttpRequest.POST("/autores",
            NovoAutorRequest("Tales Araujo", "tales.araujo@zup.com.br", "Dev na Zup"))

        val response = client.toBlocking().exchange(request, HttpResponse::class.java)

        assertEquals(HttpStatus.CREATED, response.status)
    }

}