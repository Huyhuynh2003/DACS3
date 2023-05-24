package com.example.quancoffee.Menu

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.quancoffee.R
import com.squareup.picasso.Picasso
import java.lang.Exception

class Thongtin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thongtin)
        val doc = intent.getStringExtra("doc")

        val textViewDoc: TextView = findViewById(R.id.textViewDoc)
        val htmlImageGetter = HtmlImageGetter(textViewDoc)
        htmlImageGetter.execute(doc)
    }

    inner class HtmlImageGetter(private val textView: TextView) : AsyncTask<String, Void, Spanned>() {
        override fun doInBackground(vararg params: String): Spanned? {
            val html = params[0]
            return Html.fromHtml(html, ImageGetter(), null)
        }

        override fun onPostExecute(result: Spanned?) {
            textView.text = result
        }
    }

    inner class ImageGetter : Html.ImageGetter {
        override fun getDrawable(source: String): Drawable {
            return try {
                val bitmap = Picasso.get().load(source).get() ?: throw Exception("Failed to load image")
                BitmapDrawable(resources, bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
                // Trả về một Drawable mặc định nếu không tải được ảnh
                resources.getDrawable(R.drawable.default_image)
            }
        }
    }
    fun goBackToTintuc(view: View) {

        // Tạo một Intent để chuyển đến Tintuc.kt
        val intent = Intent(this, MenuMon::class.java)
        startActivity(intent)

        // Kết thúc hoạt động hiện tại để trở về Tintuc.kt
        finish()
    }

}
