package org.movie

data class User(var id: Int = 0,
                var username: String = "",
                var firstname: String = "",
                var lastName: String = "",
                var email: String = "",
                var phone: Int = 0,
                var password: String = "")
