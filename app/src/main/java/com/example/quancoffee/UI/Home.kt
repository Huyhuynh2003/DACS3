package com.example.quancoffee.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.quancoffee.Adapter.HomeADT
import com.example.quancoffee.Data.Menu
import com.example.quancoffee.R

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val myDataset = Menu().loadmenu()
        val recyclerView = findViewById<RecyclerView>(R.id.rv_trangchu)
        recyclerView.adapter = HomeADT(this, myDataset)
        recyclerView.setHasFixedSize(true)


    }
}

