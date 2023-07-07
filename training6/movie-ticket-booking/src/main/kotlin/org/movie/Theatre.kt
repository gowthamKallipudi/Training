package org.movie

data class Theatre(var name: String = "",
                   var show: List<String> = mutableListOf(),
                   var seatCapacity: Int = 0,
                   var rowCapacity: Int = 0,
                   var region: String = "")
