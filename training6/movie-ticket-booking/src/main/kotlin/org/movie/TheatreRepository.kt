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
}