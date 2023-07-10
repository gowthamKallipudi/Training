package org.movie

import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/fetchRegion")
class RegionController {

    @Inject
    var regionRepository: RegionRepository? = null

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun fetchRegion(): MutableList<String>? {
        return regionRepository?.fetchLocations()
    }
}