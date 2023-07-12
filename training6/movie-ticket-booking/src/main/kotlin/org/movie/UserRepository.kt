package org.movie

import io.agroal.api.AgroalDataSource
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class UserRepository {

    @Inject
    var dataSource : AgroalDataSource? = null

    fun addUser(user: User?): Boolean {
        var userState: Boolean = false
        var id: Int = 0
        dataSource?.connection?.let { conn ->
            conn.prepareStatement("insert into users (`username`, `firstname`, `lastname`, `email`, `phone`)" +
                    " VALUES (?, ?, ?, ?, ?)").use { stmt ->
                stmt.setString(1, user?.username)
                stmt.setString(2, user?.firstname)
                stmt.setString(3, user?.lastname)
                stmt.setString(4, user?.email)
                stmt.setLong(5, user?.phone!!)

                if(stmt.executeUpdate() == 1) {
                    userState = true
                }
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
        return userState
    }

    fun checkUser(login: Login?): Any? {
        val loginState: Boolean = false
        var id: Int = 0
        var user: User? = null
        dataSource?.connection?.let { conn ->
            conn.prepareStatement("select * from users where username = ?").use { stmt ->
                stmt.setString(1, login?.username)
                stmt.executeQuery().use { rs ->
                    if(rs.next()) {
                        id = rs.getInt("id")
                        user = User(id = rs.getInt("id"),
                                username = rs.getString("username"),
                                firstname = rs.getString("firstname"),
                                lastname = rs.getString("lastname"),
                                email = rs.getString("email"),
                                phone = rs.getLong("phone"))
                        dataSource?.connection?.let { conn ->
                            conn.prepareStatement("select password from password where userid = ?").use { stmt ->
                                stmt.setInt(1, id)
                                stmt.executeQuery().use { rs ->
                                    if (rs.next()){
                                        login?.password?.let { if(rs.getString("password").compareTo(it) == 0) {
//                                            loginState = true
                                            return user
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