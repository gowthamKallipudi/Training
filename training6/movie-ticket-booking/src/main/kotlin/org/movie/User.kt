package org.movie

data class User(var id: Int,
                var username: String,
                var firstname: String,
                var lastname: String,
                var email: String,
                var phone: Int,
                var password: String = "")
