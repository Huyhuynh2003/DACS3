package com.example.quancoffee.Menu

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.quancoffee.R
import com.example.quancoffee.UI.Home
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MenuMon : AppCompatActivity() {

    private val urlGetData: String = "http://192.168.1.7/Do_an_cs3/getdata.php"

    private var mangTintuc: ArrayList<String> = ArrayList()
    private var mangIdNewspaper: ArrayList<String> = ArrayList()
    private var adapterTintuc: ArrayAdapter<String>? = null

    private lateinit var lvCourse: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_mon)

        lvCourse = findViewById(R.id.lvCourse)

        lvCourse.setOnItemClickListener { parent, view, position, id ->
            val selectedId = mangIdNewspaper[position] // Lấy giá trị idNewspaper tương ứng
            GetDataById().execute(selectedId)
        }

        adapterTintuc = ArrayAdapter(this, R.layout.list_item, android.R.id.text1, mangTintuc)
        lvCourse.adapter = adapterTintuc

        GetData().execute(urlGetData)
    }

    inner class GetData : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {
            return getContentURL(params[0])
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            val jsonArray: JSONArray = JSONArray(result)

            mangTintuc.clear()
            mangIdNewspaper.clear()

            for (i in 0 until jsonArray.length()) {
                val objectTieude: JSONObject = jsonArray.getJSONObject(i)
                val tenmon = objectTieude.getString("tenmon")
                val price = objectTieude.getString("price")
                val combinedString = "$tenmon - $price"
                mangTintuc.add(combinedString)
                mangIdNewspaper.add(objectTieude.getString("id"))
            }

            adapterTintuc?.notifyDataSetChanged()
        }
    }

    inner class GetDataById : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {
            val idNewspaper = params[0]
            val urlGetDataById: String =
                "http://192.168.1.7/Do_an_cs3/getdata_by_id.php?id=$idNewspaper"
            return getContentURL(urlGetDataById)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            val jsonObject: JSONObject = JSONObject(result)
            val doc = jsonObject.optString("doc") // Sử dụng optString thay vì getString để tránh lỗi nếu không có giá trị

            val intent = Intent(this@MenuMon, Thongtin::class.java)
            intent.putExtra("doc", doc)
            startActivity(intent)
        }
    }


    private fun getContentURL(url: String?): String {
        val content = StringBuilder()
        val url: URL = URL(url)
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        val inputStreamReader: InputStreamReader = InputStreamReader(urlConnection.inputStream)
        val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)

        var line: String? = bufferedReader.readLine()
        while (line != null) {
            content.append(line)
            line = bufferedReader.readLine()
        }

        bufferedReader.close()
        return content.toString()
    }
    fun goBackToHome(view: View) {

        // Tạo một Intent để chuyển đến Home.kt
        val intent = Intent(this, Home::class.java)
        startActivity(intent)

        // Kết thúc hoạt động hiện tại để trở về Home.kt
        finish()
    }

}

