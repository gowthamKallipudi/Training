package org.movie

import java.sql.Date

data class Booking(var userName: String,
                   var theatre: String,
                   var timing: String,
                   var region: String,
                   var movieName: String,
                   var date: Date,
                   var seatNo: String)


data class BookedSeats(var seatCapacity: Int,
                       var rowCapacity: Int,
                       var bookedSeats: MutableList<String>)

data class Bookings(var userName: String,
                   var theatre: String,
                   var timing: String,
                   var region: String,
                   var movieName: String,
                   var date: Date,
                   var seatNo: MutableList<String>)