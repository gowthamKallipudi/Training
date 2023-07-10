package org.movie

import io.agroal.api.AgroalDataSource
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class MovieRepository {

    @Inject
    var dataSource: AgroalDataSource? = null

    fun addMovie(movie: Movie) {
        dataSource?.connection?.prepareStatement("INSERT INTO movies (`name`, `language`, `releaseDate`, `duration`," +
                " `genre`, `description`, `casting`) VALUES (?, ?, ?, ?, ?, ?, ?)").use { preparedStatement ->
                    preparedStatement?.setString(1, movie.name)
                    preparedStatement?.setString(2, movie.language)
                    preparedStatement?.setDate(3, movie.releaseDate)
                    preparedStatement?.setString(4, movie.duration)
                    preparedStatement?.setString(5, movie.genre)
                    preparedStatement?.setString(6, movie.description)
                    preparedStatement?.setString(7, movie.casting)

                    preparedStatement?.executeUpdate()
                }

    }

    fun releaseMovie(release: Release) {
        var movieId: Int = 0
        dataSource?.connection?.prepareStatement("select id from movies where name = ?").use { preparedStatement ->
            preparedStatement?.setString(1, release.movieName)

            preparedStatement?.executeQuery().use { resultSet ->
                if (resultSet?.next() == true) {
                    movieId = resultSet.getInt("id")
                }
            }
        }
        dataSource?.connection?.prepareStatement("update theatre set movieid = ? where name = ?").use { stmt ->
            stmt?.setInt(1, movieId)
            for(eachTheatre in release.theatreList) {
                stmt?.setString(2, eachTheatre)
                stmt?.executeUpdate()
            }
        }
    }

    fun fetchMovies() : MutableList<Movie> {
        val movieList: MutableList<Movie> = mutableListOf()
        dataSource?.connection?.prepareStatement("select * from movies").use { preparedStatement ->
            preparedStatement?.executeQuery().use { resultSet ->
                while (resultSet?.next() == true) {
                    if(resultSet.getString("name") != "none")
                        movieList.add(Movie(name = resultSet.getString("name"),
                                language = resultSet.getString("language"),
                                releaseDate = resultSet.getDate("releasedate"),
                                duration = resultSet.getString("duration"),
                                genre = resultSet.getString("genre"),
                                description = resultSet.getString("description"),
                                casting = resultSet.getString("casting")))
                }
            }
        }
        return movieList
    }

    fun fetchMovieByRegion(name: String): MutableList<Movie> {
        var regionId: Int = 0
        dataSource?.connection?.prepareStatement("select id from region where name = ?").use { preparedStatement ->
            preparedStatement?.setString(1, name)
            preparedStatement?.executeQuery().use { resultSet ->
                while(resultSet?.next() == true) {
                    regionId = resultSet.getInt("id")
                }
            }
        }
        val movieIdList: MutableSet<Int> = mutableSetOf()
        dataSource?.connection?.prepareStatement("select movieid from theatre where regionid = ?").use { stmt ->
            stmt?.setInt(1, regionId)
            stmt?.executeQuery().use { resultSet ->
                while(resultSet?.next() == true) {
                    movieIdList.add(resultSet.getInt("movieid"))
                }
            }
        }
        val movieList: MutableList<Movie> = mutableListOf()
        dataSource?.connection?.prepareStatement("select * from movies where id = ?").use { preparedStatement ->
            for(movieId in movieIdList) {
                preparedStatement?.setInt(1, movieId)
                preparedStatement?.executeQuery().use { resultSet ->
                    if (resultSet?.next() == true) {
                        if(resultSet.getString("name") != "none")
                            movieList.add(Movie(name = resultSet.getString("name"),
                                    language = resultSet.getString("language"),
                                    releaseDate = resultSet.getDate("releasedate"),
                                    duration = resultSet.getString("duration"),
                                    genre = resultSet.getString("genre"),
                                    description = resultSet.getString("description"),
                                    casting = resultSet.getString("casting")))
                    }
                }
            }

        }
        return movieList
    }
}