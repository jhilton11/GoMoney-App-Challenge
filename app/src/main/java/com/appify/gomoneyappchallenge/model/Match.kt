package com.appify.gomoneyappchallenge.model

data class Match(val id: String,
                 val minute: Int?,
                 val homeTeam: String,
                 val awayTeam: String,
                 val homeScore: Int,
                 val awayScore: Int,
                 val time: String,
                 val matchNo: Int) {
}