package org.movie

import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.QueryParam

@Path("/theatre")
class TheatreController {

    @Inject
    var theatreRepository: TheatreRepository? = null

    @POST
    @Path("/addTheatre")
    fun addTheatre(theatre: Theatre): Unit? {
        return theatreRepository?.addTheatre(theatre)
    }

    @GET
    @Path("/getTheatre/{movie}")
    fun theatreByMovie(@PathParam("movie") movie: String): MutableList<Theatres>? {
        return theatreRepository?.fetchTheatreByMovie(movie)
    }

    @GET
    @Path("/fetchTheatre/{region}")
    fun fetchTheatreByRegion(@PathParam("region") region: String, @QueryParam("movie") movie: String): MutableList<Theatres>? {
        return theatreRepository?.fetchTheatreByRegion(region, movie)
    }
}