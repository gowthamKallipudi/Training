package org.movie

import io.agroal.api.AgroalDataSource
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class BookingRepository {

    @Inject
    var dataSource: AgroalDataSource? = null

    fun addBooking(booking: Booking) {
        var userId: Int = 0
        var theatreId: Int = 0
        var regionId: Int = 0
        var movieId: Int = 0
        dataSource?.connection?.prepareStatement("select id from users where username = ?").use { preparedStatement ->
            preparedStatement?.setString(1, booking.userName)

            preparedStatement?.executeQuery().use { resultSet ->
                if(resultSet?.next() == true) {
                    userId= resultSet.getInt(1)
                }
            }
        }
        dataSource?.connection?.prepareStatement("select id from movies where name = ?").use { preparedStatement ->
            preparedStatement?.setString(1, booking.movieName)

            preparedStatement?.executeQuery().use { resultSet ->
                if(resultSet?.next() == true) {
                    movieId= resultSet.getInt(1)
                }
            }
        }
        dataSource?.connection?.prepareStatement("select id from theatre where name = ?").use { preparedStatement ->
            preparedStatement?.setString(1, booking.theatre)

            preparedStatement?.executeQuery().use { resultSet ->
                if(resultSet?.next() == true) {
                    theatreId= resultSet.getInt(1)
                }
            }
        }
        dataSource?.connection?.prepareStatement("select id from region where name = ?").use { preparedStatement ->
            preparedStatement?.setString(1, booking.region)

            preparedStatement?.executeQuery().use { resultSet ->
                if(resultSet?.next() == true) {
                    regionId= resultSet.getInt(1)
                }
            }
        }
        dataSource?.connection?.prepareStatement("INSERT INTO bookings (`userid`, `theatreid`, `timings`, `regionid`," +
                " `movieid`, `date`, `seatno`) values (?, ?, ?, ?, ?, ?, ?)").use { preparedStatement ->
            preparedStatement?.setInt(1, userId)
            preparedStatement?.setInt(2, theatreId)
            preparedStatement?.setString(3, booking.timing)
            preparedStatement?.setInt(4, regionId)
            preparedStatement?.setInt(5, movieId)
            preparedStatement?.setDate(6, booking.date)
            preparedStatement?.setString(7, booking.seatNo)

            preparedStatement?.executeUpdate()
        }
    }

    fun fetchBookings(id: Int): MutableList<Booking> {
        val bookingsList: MutableList<Booking> = mutableListOf()
        dataSource?.connection?.prepareStatement("select * from bookings where userid = ?").use { preparedStatement ->
            preparedStatement?.setInt(1, id)
            preparedStatement?.executeQuery().use { resultSet ->
                while(resultSet?.next() == true) {
                    var userName: String = ""
                    var theatre: String = ""
                    var region: String = ""
                    var movie: String = ""
                    dataSource?.connection?.prepareStatement("select username from users where id = ?").use { stmt ->
                        stmt?.setInt(1, resultSet.getInt("userid"))
                        stmt?.executeQuery().use { resultSet ->
                            if(resultSet?.next() == true) {
                                userName = resultSet.getString("username")
                            }
                        }
                    }
                    dataSource?.connection?.prepareStatement("select name from theatre where id = ?").use { stmt ->
                        stmt?.setInt(1, resultSet.getInt("theatreid"))
                        stmt?.executeQuery().use { resultSet ->
                            if(resultSet?.next() == true) {
                                theatre = resultSet.getString("name")
                            }
                        }
                    }
                    dataSource?.connection?.prepareStatement("select name from region where id = ?").use { stmt ->
                        stmt?.setInt(1, resultSet.getInt("regionid"))
                        stmt?.executeQuery().use { resultSet ->
                            if(resultSet?.next() == true) {
                                region = resultSet.getString("name")
                            }
                        }
                    }
                    dataSource?.connection?.prepareStatement("select name from movies where id = ?").use { stmt ->
                        stmt?.setInt(1, resultSet.getInt("movieid"))
                        stmt?.executeQuery().use { resultSet ->
                            if(resultSet?.next() == true) {
                                movie = resultSet.getString("name")
                            }
                        }
                    }
                    bookingsList.add(Booking(userName = userName,
                            theatre = theatre,
                            timing = resultSet.getString("timings"),
                            region = region,
                            movieName = movie,
                            date = resultSet.getDate("date"),
                            seatNo = resultSet.getString("seatNo")))
                }
            }
        }
        return bookingsList
    }

    fun fetchSeatsByTheatreShowDate(theatre: String, show: String, date: java.sql.Date): BookedSeats {
        var theatreId: Int = 0
        var seatCapacity: Int = 0
        var rowCapacity: Int = 0
        dataSource?.connection?.prepareStatement("select * from theatre where name = ?").use { stmt ->
            stmt?.setString(1, theatre)
            stmt?.executeQuery().use { resultSet ->
                if(resultSet?.next() == true) {
                    if(resultSet.getString("show") == show) {
                        theatreId = resultSet.getInt("id")
                        seatCapacity = resultSet.getInt("seatcapacity")
                        rowCapacity = resultSet.getInt("rowcapacity")
                    }
                }
            }
        }
        val seatNo: MutableList<String> = mutableListOf()
        dataSource?.connection?.prepareStatement("select seatno from bookings where theatreid = ? and timings = ?" +
                " and date = ?").use { preparedStatement ->
            preparedStatement?.setInt(1, theatreId)
            preparedStatement?.setString(2, show)
            preparedStatement?.setDate(3, date)
            preparedStatement?.executeQuery().use { resultSet ->
                while(resultSet?.next() == true) {
                    seatNo.add(resultSet.getString("seatno"))
                }
            }
        }
        return BookedSeats(seatCapacity, rowCapacity, seatNo)
    }
}