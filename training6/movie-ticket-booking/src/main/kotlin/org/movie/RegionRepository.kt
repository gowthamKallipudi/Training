package org.movie

import io.agroal.api.AgroalDataSource
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class RegionRepository {

    @Inject
    var dataSource : AgroalDataSource? = null

    fun fetchLocations(): MutableList<String> {
        val regionList: MutableList<String> = mutableListOf()
        dataSource?.connection?.use { conn ->
            conn.prepareStatement("select name from region").use { preparedStatement ->
                preparedStatement.executeQuery().use { resultSet ->
                    while (resultSet.next()) {
                        regionList.add(resultSet.getString("name"))
                    }
                }
            }
        }
        return regionList
    }
}