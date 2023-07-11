package org.movie

import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType

@Path("/movie")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class MovieController {

    @Inject
    var movieRepository: MovieRepository? = null

    @POST
    @Path("/addMovie")
    fun addMovie(movie: Movie) {
        movieRepository?.addMovie(movie)
    }

    @POST
    @Path("/releaseMovie")
    fun releaseMovie(release: Release) {
        movieRepository?.releaseMovie(release)
    }

    @GET
    @Path("/fetchMovies")
    fun fetchMovies(): MutableList<Movie>? {
        return movieRepository?.fetchMovies()
    }

    @GET
    @Path("/fetchMovie/{name}")
    fun fetchMovieByRegionId(@PathParam("name") name:String): MutableList<Movie>? {
        return movieRepository?.fetchMovieByRegion(name)
    }

    @GET
    @Path("/getMovie/{movieName}")
    fun fetchMovieByName(@PathParam("movieName") movieName: String): Any? {
        return movieRepository?.fetchMovieByName(movieName)
    }
}