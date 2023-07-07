package org.movie

import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType

@Path("/user")
class UserController {

    @Inject
    var userRepository : UserRepository? = null

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun personById(@PathParam("id") id: Int) = userRepository?.getName(id)


    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    fun persons() = userRepository?.getPersons()

    @POST
    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_JSON)
    fun addUser(user: User) = userRepository?.addUser(user)
}
