package org.movie

import java.sql.Date

data class Movie(var name: String,
                 var language: String,
                 var releaseDate: Date,
                 var duration: String,
                 var genre: String,
                 var description: String,
                 var casting: String)

data class Release(var movieName: String,
                   var theatreList: MutableList<String> = mutableListOf())
