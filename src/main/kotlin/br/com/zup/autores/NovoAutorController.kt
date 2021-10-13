package br.com.zup.autores

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class NovoAutorController {

    @Post
    fun cadastraAutor(@Body @Valid request: NovoAutorRequest) {
        println(request)
    }

}