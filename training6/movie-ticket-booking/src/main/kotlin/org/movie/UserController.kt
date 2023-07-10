package org.movie

import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class UserController {

    @Inject
    var userRepository : UserRepository? = null

    @POST
    @Path("/addUser")
    fun addUser(user: User) = userRepository?.addUser(user)
}
