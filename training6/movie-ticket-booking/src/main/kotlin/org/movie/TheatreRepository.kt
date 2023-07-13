package org.movie

import io.agroal.api.AgroalDataSource
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class TheatreRepository {

    @Inject
    var dataSource: AgroalDataSource? = null

    fun addTheatre(theatre: Theatre) {
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("insert into region (`name`) values (?)").use { preparedStatement ->
                preparedStatement.setString(1, theatre.region)
                preparedStatement.executeUpdate()
            }
        }
        var regionid: Int = 0
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("select id from region where name = ?").use { preparedStatement ->
                preparedStatement.setString(1, theatre.region)
                preparedStatement.executeQuery().use { resultSet ->
                    if (resultSet.next()) {
                        regionid = resultSet.getInt("id")
                    }
                }
            }
        }
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("insert into theatre (`name`, `show`, `seatcapacity`, `rowcapacity`, `regionid`, `movieid`) values (?, ?, ?, ?, ?, ?)").use { preparedStatement ->
                preparedStatement.setString(1, theatre.name)
                preparedStatement.setInt(3, theatre.seatCapacity)
                preparedStatement.setInt(4, theatre.rowCapacity)
                preparedStatement.setInt(5, regionid)
                preparedStatement.setInt(6, 1)
                for (eachShow in theatre.show) {
                    preparedStatement.setString(2, eachShow)
                    preparedStatement.executeUpdate()
                }
            }
        }
    }

    fun fetchTheatreByMovie(name: String): MutableList<Theatres> {
        var movieId: Int = 0
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("select id from movies where name = ?").use { preparedStatement ->
                preparedStatement.setString(1, name)
                preparedStatement.executeQuery().use { resultSet ->
                    if (resultSet.next()) {
                        movieId = resultSet.getInt("id")
                    }
                }
            }
        }
        val theatreNameList: MutableSet<String> = mutableSetOf()
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("select name from theatre where movieid = ?").use { stmt ->
                stmt.setInt(1, movieId)
                stmt.executeQuery().use { resultSet ->
                    while (resultSet.next()) {
                        theatreNameList.add(resultSet.getString("name"))
                    }
                }
            }
        }
        val theatreList: MutableList<Theatres> = mutableListOf()
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("select * from theatre where name = ? and movieid = ?").use { stmt ->
                stmt.setInt(2, movieId)
                for (theatreName in theatreNameList) {
                    var currentName: String = ""
                    val showList: MutableList<String> = mutableListOf()
                    stmt.setString(1, theatreName)
                    stmt.executeQuery().use { resultSet ->
                        while (resultSet.next()) {
                            currentName = resultSet.getString("name")
                            showList.add(resultSet.getString("show"))
                        }
                    }
                    theatreList.add(Theatres(currentName, showList))
                }
            }
        }
        return theatreList
    }
    fun fetchTheatreByRegion(region: String, movie: String): MutableList<Theatres> {
        var regionId: Int = 0
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("select id from region where name = ?").use { preparedStatement ->
                preparedStatement.setString(1, region)
                preparedStatement.executeQuery().use { resultSet ->
                    if (resultSet.next()) {
                        regionId = resultSet.getInt("id")
                    }
                }
            }
        }
        var movieId: Int = 0
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("select id from movies where name = ?").use { preparedStatement ->
                preparedStatement.setString(1, movie)
                preparedStatement.executeQuery().use { resultSet ->
                    if (resultSet.next()) {
                        movieId = resultSet.getInt("id")
                    }
                }
            }
        }
        val theatreNameList: MutableSet<String> = mutableSetOf()
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("select name from theatre where regionid = ? and movieid = ?").use { stmt ->
                stmt.setInt(1, regionId)
                stmt.setInt(2, movieId)
                stmt.executeQuery().use { resultSet ->
                    while (resultSet.next()) {
                        theatreNameList.add(resultSet.getString("name"))
                    }
                }
            }
        }
        val theatreList: MutableList<Theatres> = mutableListOf()
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("select * from theatre where name = ? and regionid = ? and movieid = ?").use { stmt ->
                stmt.setInt(2, regionId)
                stmt.setInt(3, movieId)
                for (theatreName in theatreNameList) {
                    var currentName: String = ""
                    val showList: MutableList<String> = mutableListOf()
                    stmt.setString(1, theatreName)
                    stmt.executeQuery().use { resultSet ->
                        while (resultSet.next()) {
                            currentName = resultSet.getString("name")
                            showList.add(resultSet.getString("show"))
                        }
                    }
                    theatreList.add(Theatres(currentName, showList))
                }
            }
        }
        return theatreList
    }
}