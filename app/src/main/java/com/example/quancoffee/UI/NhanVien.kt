package com.example.quancoffee.UI

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.quancoffee.R
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NhanVien : AppCompatActivity() {

    private val urlGetData: String = "http://192.168.1.7/Do_an_cs3/getdataNhanvien.php"
    private val urlAddComment: String = "http://192.168.1.7/Do_an_cs3/addNvien.php"
    private val urlDeleteComment: String =
        "http://192.168.1.7/Do_an_cs3/deleteNvien.php"
    private val urlEditComment: String = "http://192.168.1.7/Do_an_cs3/editNvien.php"

    private var mangComment: ArrayList<String> = ArrayList()
    private var adapterComment: ArrayAdapter<String>? = null
    private var selectedComment: String? = null

    private lateinit var lvComment: ListView
    private lateinit var editTextNoidung: EditText
    private lateinit var buttonAdd: Button
    private lateinit var buttonDelete: Button
    private lateinit var buttonEdit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nhan_vien)

        lvComment = findViewById(R.id.listViewMenu)
        editTextNoidung = findViewById(R.id.editTextTableName)
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonDelete = findViewById(R.id.buttonDelete)
        buttonEdit = findViewById(R.id.buttonEdit)

        adapterComment = ArrayAdapter(this, R.layout.list_item, android.R.id.text1, mangComment)
        lvComment.adapter = adapterComment

        // Xử lý sự kiện khi người dùng chọn một mục trong ListView
        lvComment.setOnItemClickListener { parent, view, position, id ->
            selectedComment = parent.getItemAtPosition(position) as? String
            editTextNoidung.setText(selectedComment)
        }

        buttonAdd.setOnClickListener { addComment() }
        buttonDelete.setOnClickListener { deleteComment() }
        buttonEdit.setOnClickListener { editComment() }

        GetData().execute(urlGetData)
    }

    inner class GetData : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {
            return getContentURL(params[0])
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            val jsonArray: JSONArray = JSONArray(result)

            mangComment.clear()

            for (i in 0 until jsonArray.length()) {
                val objectComment = jsonArray.getJSONObject(i)
                val comment = objectComment.getString("comment")
                mangComment.add(comment)
            }

            adapterComment?.notifyDataSetChanged()
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

    private fun addComment() {
        val comment = editTextNoidung.text.toString()
        val url = "$urlAddComment?comment=$comment"

        AddCommentTask().execute(url)

        editTextNoidung.text.clear()

    }

    private fun deleteComment() {
        val comment = editTextNoidung.text.toString()
        val url = "$urlDeleteComment?comment=$comment"

        DeleteCommentTask().execute(url)

        editTextNoidung.text.clear()
    }

    private fun editComment() {
        val comment = editTextNoidung.text.toString()

        if (selectedComment != null) {
            val encodedSelectedComment = java.net.URLEncoder.encode(selectedComment, "UTF-8")
            val encodedNewComment = java.net.URLEncoder.encode(comment, "UTF-8")
            val url = "$urlEditComment?oldComment=$encodedSelectedComment&newComment=$encodedNewComment"

            EditCommentTask().execute(url)
        } else {
            Toast.makeText(
                applicationContext,
                "Vui lòng chọn một bình luận để sửa đổi",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    inner class AddCommentTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {
            return getContentURL(params[0])
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            // Xử lý kết quả trả về sau khi thêm bình luận
            GetData().execute(urlGetData)

            // Hiển thị thông báo
            Toast.makeText(applicationContext, "Thêm thành công", Toast.LENGTH_SHORT).show()

            // Xóa nội dung trong EditText
            editTextNoidung.text.clear()
        }
    }

    inner class DeleteCommentTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {
            return getContentURL(params[0])
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            // Xử lý kết quả trả về sau khi xóa bình luận
            GetData().execute(urlGetData)

            // Hiển thị thông báo
            Toast.makeText(applicationContext, "Xóa thành công", Toast.LENGTH_SHORT).show()

            // Xóa nội dung trong EditText
            editTextNoidung.text.clear()
        }
    }

    inner class EditCommentTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {
            return getContentURL(params[0])
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            // Kiểm tra kết quả trả về từ file PHP
            if (result == "Bình luận đã được chỉnh sửa thành công") {
                // Hiển thị thông báo
                Toast.makeText(applicationContext, "Sửa thành công", Toast.LENGTH_SHORT).show()
            } else {
                // Hiển thị thông báo lỗi
                Toast.makeText(applicationContext, "Lỗi: $result", Toast.LENGTH_SHORT).show()
            }
            // Xử lý kết quả trả về sau khi chỉnh sửa bình luận
            GetData().execute(urlGetData)

// Xóa nội dung trong EditText

            editTextNoidung.text.clear()
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
    }
}