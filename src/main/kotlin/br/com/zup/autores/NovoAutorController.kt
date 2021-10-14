package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.validator.Validator
import jakarta.inject.Inject
import javax.validation.ConstraintViolationException

@Controller("/autores")
class NovoAutorController {

    @Inject
    lateinit var validator: Validator

    @Inject
    lateinit var autorRepository: AutorRepository

    @Post
    fun cadastraAutor(@Body request: NovoAutorRequest): HttpResponse<Any> {
        val violations = validator.validate(request)
        if(violations.isNotEmpty())
            throw ConstraintViolationException(violations)

        var autor = request.toModel()
        autor = autorRepository.save(autor)
        println(request)

        val uri = UriBuilder.of("/autores/{id}")
            .expand(mutableMapOf(Pair("id", autor.id)))

        return HttpResponse.created(uri)
    }

}