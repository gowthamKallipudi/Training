package org.movie

import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.QueryParam
import java.sql.Date

@Path("/booking")
class BookingController {

    @Inject
    var bookingRepository: BookingRepository? = null

    @POST
    @Path("/addBooking")
    fun addBooking(booking: Bookings): Unit? {
        return bookingRepository?.addBooking(booking);
    }

    @GET
    @Path("/fetchBookings/{id}")
    fun fetchBookings(@PathParam("id") id: Int): MutableList<Booking>? {
        return bookingRepository?.fetchBookings(id)
    }

    @GET
    @Path("/fetchSeats")
    fun fetchSeatsByTheatreShowDate(@QueryParam("theatre") theatre: String,
                                    @QueryParam("show") show: String,
                                    @QueryParam("date") date: Date): BookedSeats? {
        return bookingRepository?.fetchSeatsByTheatreShowDate(theatre, show, date)
    }

}