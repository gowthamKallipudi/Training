package org.movie

import io.agroal.api.AgroalDataSource
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class UserRepository {

    @Inject
    var dataSource : AgroalDataSource? = null

    fun addUser(user: User?) {
        var id: Int = 0
        dataSource?.connection?.let { conn ->
            conn.prepareStatement("insert into users (`username`, `firstname`, `lastname`, `email`, `phone`)" +
                    " VALUES (?, ?, ?, ?, ?)").use { stmt ->
                stmt.setString(1, user?.username)
                stmt.setString(2, user?.firstname)
                stmt.setString(3, user?.lastName)
                stmt.setString(4, user?.email)
                stmt.setInt(5, user?.phone!!)

                stmt.executeUpdate()
            }
            conn.prepareStatement("select id from users where username = ?").use { stmt ->
                stmt.setString(1, user?.username)
                stmt.executeQuery().use { rs ->
                    if(rs.next()) {
                        id = rs.getInt("id")
                    }
                }
            }
            conn.prepareStatement("insert into password (`password`, `userid`) values (?, ?)").use { stmt ->
                stmt.setString(1, user?.password)
                stmt.setInt(2, id)

                stmt.executeUpdate()
            }
        }
    }

    fun checkUser(login: Login?): Boolean {
        var loginState: Boolean = false
        var id: Int = 0
        dataSource?.connection?.let { conn ->
            conn.prepareStatement("select * from users where username = ?").use { stmt ->
                stmt.setString(1, login?.username)
                stmt.executeQuery().use { rs ->
                    if(rs.next()) {
                        id = rs.getInt("id")
                        dataSource?.connection?.let { conn ->
                            conn.prepareStatement("select password from password where id = ?").use { stmt ->
                                stmt.setInt(1, id)
                                stmt.executeQuery().use { rs ->
                                    if (rs.next()){
                                        login?.password?.let { if(rs.getString("password").compareTo(it) == 0) {
                                            loginState = true
                                        }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return loginState
    }
}