package org.movie

import io.agroal.api.AgroalDataSource
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import java.sql.Date

@ApplicationScoped
class BookingRepository {

    @Inject
    var dataSource: AgroalDataSource? = null

    fun addBooking(booking: Bookings) {
        var userId: Int = 0
        var theatreId: Int = 0
        var regionId: Int = 0
        var movieId: Int = 0
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("select id from users where username = ?").use { preparedStatement ->
                preparedStatement.setString(1, booking.userName)

                preparedStatement.executeQuery().use { resultSet ->
                    if (resultSet.next()) {
                        userId = resultSet.getInt(1)
                    }
                }
            }
        }
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("select id from movies where name = ?").use { preparedStatement ->
                preparedStatement.setString(1, booking.movieName)

                preparedStatement.executeQuery().use { resultSet ->
                    if (resultSet.next()) {
                        movieId = resultSet.getInt(1)
                    }
                }
            }
        }
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("select id from theatre where name = ?").use { preparedStatement ->
                preparedStatement.setString(1, booking.theatre)

                preparedStatement.executeQuery().use { resultSet ->
                    if (resultSet.next()) {
                        theatreId = resultSet.getInt(1)
                    }
                }
            }
        }
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("select id from region where name = ?").use { preparedStatement ->
                preparedStatement.setString(1, booking.region)

                preparedStatement.executeQuery().use { resultSet ->
                    if (resultSet.next()) {
                        regionId = resultSet.getInt(1)
                    }
                }
            }
        }
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("INSERT INTO bookings (`userid`, `theatreid`, `timings`, `regionid`," +
                    " `movieid`, `date`, `seatno`) values (?, ?, ?, ?, ?, ?, ?)").use { preparedStatement ->
                preparedStatement.setInt(1, userId)
                preparedStatement.setInt(2, theatreId)
                preparedStatement.setString(3, booking.timing)
                preparedStatement.setInt(4, regionId)
                preparedStatement.setInt(5, movieId)
                preparedStatement.setDate(6, booking.date)
                for (seatNo in booking.seatNo) {
                    preparedStatement.setString(7, seatNo)

                    preparedStatement.executeUpdate()
                }
            }
        }
    }

    fun fetchBookings(id: Int): MutableList<Booking> {
        val bookingsList: MutableList<Booking> = mutableListOf()
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("select * from bookings where userid = ?").use { preparedStatement ->
                preparedStatement.setInt(1, id)
                preparedStatement.executeQuery().use { resultSet ->
                    while (resultSet.next()) {
                        var userName: String = ""
                        var theatre: String = ""
                        var region: String = ""
                        var movie: String = ""
                        dataSource?.connection?.use {
                            connection.prepareStatement("select username from users where id = ?").use { stmt ->
                                stmt.setInt(1, resultSet.getInt("userid"))
                                stmt.executeQuery().use { resultSet ->
                                    if (resultSet.next()) {
                                        userName = resultSet.getString("username")
                                    }
                                }
                            }
                        }
                        dataSource?.connection?.use { connection ->
                            connection.prepareStatement("select name from theatre where id = ?").use { stmt ->
                                stmt.setInt(1, resultSet.getInt("theatreid"))
                                stmt.executeQuery().use { resultSet ->
                                    if (resultSet.next()) {
                                        theatre = resultSet.getString("name")
                                    }
                                }
                            }
                        }
                        dataSource?.connection?.use { connection ->
                            connection.prepareStatement("select name from region where id = ?").use { stmt ->
                                stmt.setInt(1, resultSet.getInt("regionid"))
                                stmt.executeQuery().use { resultSet ->
                                    if (resultSet.next()) {
                                        region = resultSet.getString("name")
                                    }
                                }
                            }
                        }
                        dataSource?.connection?.use { connection ->
                            connection.prepareStatement("select name from movies where id = ?").use { stmt ->
                                stmt.setInt(1, resultSet.getInt("movieid"))
                                stmt.executeQuery().use { resultSet ->
                                    if (resultSet.next()) {
                                        movie = resultSet.getString("name")
                                    }
                                }
                            }
                        }
                        bookingsList.add(Booking(id = resultSet.getInt("id"),
                                userName = userName,
                                theatre = theatre,
                                timing = resultSet.getString("timings"),
                                region = region,
                                movieName = movie,
                                date = resultSet.getDate("date"),
                                seatNo = resultSet.getString("seatNo")))
                    }
                }
            }
        }
        return bookingsList
    }

    fun fetchSeatsByTheatreShowDate(theatre: String, show: String, date: Date): BookedSeats {
        var theatreId: Int = 0
        var seatCapacity: Int = 0
        var rowCapacity: Int = 0
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("select * from theatre where name = ?").use { stmt ->
                stmt.setString(1, theatre)
                stmt.executeQuery().use { resultSet ->
                    while (resultSet.next()) {
                        if (resultSet.getString("show").compareTo(show) == 0) {
                            theatreId = resultSet.getInt("id")
                            seatCapacity = resultSet.getInt("seatcapacity")
                            rowCapacity = resultSet.getInt("rowcapacity")
                        }
                    }
                }
            }
        }
        val seatNo: MutableList<String> = mutableListOf()
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("select seatno from bookings where theatreid = ? and timings = ?" +
                    " and date = ?").use { preparedStatement ->
                preparedStatement.setInt(1, theatreId)
                preparedStatement.setString(2, show)
                preparedStatement.setDate(3, date)
                preparedStatement.executeQuery().use { resultSet ->
                    while (resultSet.next()) {
                        seatNo.add(resultSet.getString("seatno"))
                    }
                }
            }
        }
        return BookedSeats(seatCapacity, rowCapacity, seatNo)
    }

    fun deleteBooking(id: Int) {
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("delete from bookings where id = ?").use { preparedStatement ->
                preparedStatement.setInt(1, id)

                preparedStatement.execute()
            }
        }
    }
}