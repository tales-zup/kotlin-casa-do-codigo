package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.validator.Validator
import jakarta.inject.Inject
import javax.validation.ConstraintViolationException

@Controller("/autores")
class NovoAutorController {

    @Inject
    lateinit var validator: Validator

    @Post
    fun cadastraAutor(@Body request: NovoAutorRequest): NovoAutorRequest {
        val violations = validator.validate(request)
        if(violations.isNotEmpty())
            throw ConstraintViolationException(violations)

        println(request)
        return request
    }

}