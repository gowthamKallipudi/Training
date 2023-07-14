package org.movie

data class Login(var username: String,
                 var password: String)

data class Error(var username: String = "User name Password doesn't matched !!!")