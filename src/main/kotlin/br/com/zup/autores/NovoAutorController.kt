package br.com.zup.autores

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/autores")
class NovoAutorController {

    @Post
    fun cadastraAutor(@Body request: NovoAutorRequest) {
        println(request)
    }

}