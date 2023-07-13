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
        dataSource?.connection?.use { conn ->
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
        var id: Int
        var user: User
        dataSource?.connection?.use { conn ->
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
                        dataSource?.connection?.use { conn ->
                            conn.prepareStatement("select password from password where userid = ?").use { stmt ->
                                stmt.setInt(1, id)
                                stmt.executeQuery().use { rs ->
                                    if (rs.next()){
                                        login?.password?.let { if(rs.getString("password").compareTo(it) == 0) {
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

    fun editUser(user: User): String {
        var userName: Boolean = false
        var emailId: Boolean = false
        var phoneNo: Boolean = false
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("select * from users where id = ?").use { preparedStatement ->
                preparedStatement.setInt(1, user.id)
                preparedStatement.executeQuery().use { resultSet ->
                    if(resultSet.next()) {
                        if(resultSet.getString("username").compareTo(user.username) == 0){
                            userName = true
                        }
                        if(resultSet.getString("email").compareTo(user.email) == 0){
                            emailId = true
                        }
                        if(resultSet.getLong("phone").compareTo(user.phone) == 0){
                            phoneNo = true
                        }
                    }
                }
            }
        }
        if (!userName) {
            dataSource?.connection?.use { connection ->
                connection.prepareStatement("select username from users where id != ? and username = ?").use { preparedStatement ->
                    preparedStatement.setInt(1, user.id)
                    preparedStatement.setString(2, user.username)

                    preparedStatement.executeQuery().use { resultSet ->
                        if(resultSet.next()) {
                            return "user name already exist"
                        }
                    }
                }
            }
        }
        if (!emailId) {
            dataSource?.connection?.use { connection ->
                connection.prepareStatement("select email from users where id != ? and email = ?").use { preparedStatement ->
                    preparedStatement.setInt(1, user.id)
                    preparedStatement.setString(2, user.email)

                    preparedStatement.executeQuery().use { resultSet ->
                        if(resultSet.next()) {
                            return "email already exist"
                        }
                    }
                }
            }
        }
        if (!phoneNo) {
            dataSource?.connection?.use { connection ->
                connection.prepareStatement("select username from users where id != ? and phone = ?").use { preparedStatement ->
                    preparedStatement.setInt(1, user.id)
                    preparedStatement.setLong(2, user.phone)

                    preparedStatement.executeQuery().use { resultSet ->
                        if(resultSet.next()) {
                            return "phone number already exist"
                        }
                    }
                }
            }
        }
        dataSource?.connection?.use { connection ->
            connection.prepareStatement("update users set lastname = ?, firstname = ?, username = ?, email = ?, " +
                    "phone = ? where id = ?").use { preparedStatement ->
                    preparedStatement.setString(1, user.lastname)
                    preparedStatement.setString(2, user.firstname)
                    preparedStatement.setString(3, user.username)
                    preparedStatement.setString(4, user.email)
                    preparedStatement.setLong(5, user.phone)
                    preparedStatement.setInt(6, user.id)

                    preparedStatement.executeUpdate()
                }
            }
        return "user data updated successfully"
    }
}