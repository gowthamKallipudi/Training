package org.movie

import io.agroal.api.AgroalDataSource
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class TheatreRepository {

    @Inject
    var dataSource: AgroalDataSource? = null

    fun addTheatre(theatre: Theatre) {
        dataSource?.connection?.prepareStatement("insert into region (`name`) values (?)").use { preparedStatement ->
            preparedStatement?.setString(1, theatre.region)
            preparedStatement?.executeUpdate()
        }
        var regionid: Int = 0
        dataSource?.connection?.prepareStatement("select id from region where name = ?").use { preparedStatement ->
            preparedStatement?.setString(1, theatre.region)
            preparedStatement?.executeQuery().use { resultSet ->
                if(resultSet?.next() == true) {
                    regionid = resultSet.getInt("id")
                }
            }
        }
        dataSource?.connection?.prepareStatement("insert into theatre (`name`, `show`, `seatcapacity`, `rowcapacity`, `regionid`, `movieid`) values (?, ?, ?, ?, ?, ?)").use { preparedStatement ->
            preparedStatement?.setString(1, theatre.name)
            preparedStatement?.setInt(3, theatre.seatCapacity)
            preparedStatement?.setInt(4, theatre.rowCapacity)
            preparedStatement?.setInt(5, regionid)
            preparedStatement?.setInt(6, 1)
            for(eachShow in theatre.show) {
                preparedStatement?.setString(2, eachShow)
                preparedStatement?.executeUpdate()
            }
        }
    }

    fun fetchTheatreByMovie(name: String): MutableList<TheatreByMovie> {
        var movieId: Int = 0
        dataSource?.connection?.prepareStatement("select id from movies where name = ?").use { preparedStatement ->
            preparedStatement?.setString(1, name)
            preparedStatement?.executeQuery().use { resultSet ->
                if(resultSet?.next() == true) {
                    movieId = resultSet.getInt("id")
                }
            }
        }
        val theatreNameList: MutableSet<String> = mutableSetOf()
        dataSource?.connection?.prepareStatement("select name from theatre where movieid = ?").use { stmt ->
            stmt?.setInt(1, movieId)
            stmt?.executeQuery().use { resultSet ->
                while(resultSet?.next() == true) {
                    theatreNameList.add(resultSet.getString("name"))
                }
            }
        }
        val theatreList: MutableList<TheatreByMovie> = mutableListOf()
        dataSource?.connection?.prepareStatement("select * from theatre where name = ? and movieid = ?").use { stmt ->
            stmt?.setInt(2, movieId)
            for(theatreName in theatreNameList){
                var currentName: String = ""
                val showList: MutableList<String> = mutableListOf()
                stmt?.setString(1, theatreName)
                stmt?.executeQuery().use { resultSet ->
                    while(resultSet?.next() == true) {
                        currentName = resultSet.getString("name")
                        showList.add(resultSet.getString("show"))
                    }
                }
                theatreList.add(TheatreByMovie(currentName, showList))
            }
        }
        return theatreList
    }
}