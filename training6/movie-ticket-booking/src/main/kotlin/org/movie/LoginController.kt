package org.movie

import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.MediaType

@Path("/login")
class LoginController {

    @Inject
    var userRepository : UserRepository? = null

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun loginCheck(login: Login): Boolean? {
        return userRepository?.checkUser(login)
    }
}