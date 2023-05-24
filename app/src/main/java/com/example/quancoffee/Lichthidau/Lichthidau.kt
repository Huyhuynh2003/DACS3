package com.example.quancoffee.Lichthidau

import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.quancoffee.UI.Match
import com.example.quancoffee.R
import com.example.quancoffee.UI.Home
import com.example.quancoffee.UI.MatchesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class Lichthidau : AppCompatActivity() {

    private lateinit var llMatchesContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lich_thi_dau)

        llMatchesContainer = findViewById(R.id.llMatchesContainer)

        val apiService = ApiClient.apiService
        val call = apiService.getMatches()

        call.enqueue(object : Callback<MatchesResponse> {
            override fun onResponse(
                call: Call<MatchesResponse>,
                response: Response<MatchesResponse>
            ) {
                if (response.isSuccessful) {
                    val matchesResponse = response.body()
                    val matches = matchesResponse?.matches

                    // Hiển thị dữ liệu lịch thi đấu trong giao diện
                    showMatches(matches)
                } else {
                    // Xử lý khi có lỗi trong phản hồi từ API
                    val errorBody = response.errorBody()?.string()
                    Toast.makeText(this@Lichthidau, "Error: $errorBody", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<MatchesResponse>, t: Throwable) {
                // Xử lý khi gặp lỗi trong quá trình gọi API
                Toast.makeText(this@Lichthidau, "Error: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun formatDateTime(dateTimeString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val date = inputFormat.parse(dateTimeString)
        return outputFormat.format(date)
    }

    private fun showMatches(matches: List<Match>?) {
        // Xóa tất cả các phần tử hiện tại trong LinearLayout
        llMatchesContainer.removeAllViews()

        // Tạo LinearLayout chứa các TextView
        val matchLayout = LinearLayout(this)
        matchLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        matchLayout.orientation = LinearLayout.HORIZONTAL

        // Tạo TextView cho cột "Đội nhà"
        val team1TextView = TextView(this)
        team1TextView.layoutParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1f
        )
        team1TextView.text = "Đội nhà"
        team1TextView.setTextColor(ContextCompat.getColor(this, R.color.white)) // Đặt màu văn bản
        matchLayout.addView(team1TextView)

        // Tạo TextView cho cột "Đội khách"
        val team2TextView = TextView(this)
        team2TextView.layoutParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1f
        )
        team2TextView.text = "Đội khách"
        team2TextView.setTextColor(ContextCompat.getColor(this, R.color.white)) // Đặt màu văn bản
        matchLayout.addView(team2TextView)

        // Tạo TextView cho cột "Ngày thi đấu"
        val dateTextView = TextView(this)
        dateTextView.layoutParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1f
        )
        dateTextView.text = "Ngày, giờ thi đấu"
        dateTextView.setTextColor(ContextCompat.getColor(this, R.color.white)) // Đặt màu văn bản
        matchLayout.addView(dateTextView)

        // Thêm LinearLayout chứa các TextView vào llMatchesContainer
        llMatchesContainer.addView(matchLayout)

        // Hiển thị dữ liệu lịch thi đấu trong các TextView
        if (matches != null) {
            for (match in matches) {
                val matchLayout = LinearLayout(this)
                matchLayout.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                matchLayout.orientation = LinearLayout.HORIZONTAL

                val layoutParams = LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )

                val team1TextView = TextView(this)
                team1TextView.layoutParams = layoutParams
                team1TextView.text = match.homeTeam.name
                team1TextView.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                ) // Đặt màu văn bản
                team1TextView.background = ContextCompat.getDrawable(this, R.drawable.border) // Thêm thuộc tính background
                matchLayout.addView(team1TextView)

                val team2TextView = TextView(this)
                team2TextView.layoutParams = layoutParams
                team2TextView.text = match.awayTeam.name
                team2TextView.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                ) // Đặt màu văn bản
                team2TextView.background = ContextCompat.getDrawable(this, R.drawable.border) // Thêm thuộc tính background
                matchLayout.addView(team2TextView)

                val dateTextView = TextView(this)
                dateTextView.layoutParams = layoutParams
                val formattedDateTime = formatDateTime(match.utcDate)
                dateTextView.text = formattedDateTime
                dateTextView.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                ) // Đặt màu văn bản
                dateTextView.background = ContextCompat.getDrawable(this, R.drawable.border) // Thêm thuộc tính background
                matchLayout.addView(dateTextView)

                llMatchesContainer.addView(matchLayout)
            }
        }
    }
    fun goBackToHome(view: View) {

        // Tạo một Intent để chuyển đến Home.kt
        val intent = Intent(this, Home::class.java)
        startActivity(intent)

        // Kết thúc hoạt động hiện tại để trở về Home.kt
        finish()
    }
}
