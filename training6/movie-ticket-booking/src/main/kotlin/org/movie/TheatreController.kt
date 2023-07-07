package org.movie

import jakarta.inject.Inject
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path

@Path("/addTheatre")
class TheatreController {

    @Inject
    var theatreRepository: TheatreRepository? = null

    @POST
    fun addTheatre(theatre: Theatre): Unit? {
        return theatreRepository?.addTheatre(theatre)
    }
}