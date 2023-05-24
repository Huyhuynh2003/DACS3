package com.example.quancoffee.UI

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET

data class MatchesResponse(
    @SerializedName("matches") val matches: List<Match>
)

data class Match(
    @SerializedName("id") val id: Int,
    @SerializedName("homeTeam") val homeTeam: Team,
    @SerializedName("awayTeam") val awayTeam: Team,
    @SerializedName("utcDate") val utcDate: String, // Ngày thi đấu
    @SerializedName("utcTime") val utcTime: String // Giờ thi đấu
    // Các trường dữ liệu khác
)

data class Team(
    @SerializedName("name") val name: String,
    // Các trường dữ liệu khác
)

interface ApiService {
    @GET("matches")
    fun getMatches(): Call<MatchesResponse>
}
