package org.movie

data class Theatre(var name: String,
                   var show: List<String> = mutableListOf(),
                   var seatCapacity: Int,
                   var rowCapacity: Int,
                   var region: String)


data class TheatreByMovie(var name: String,
                          var show: List<String> = mutableListOf())