package com.example.quancoffee.Data

import com.example.quancoffee.Model.Option
import com.example.quancoffee.R

class Menu {
    fun loadmenu(): List<Option>{
        return listOf<Option>(
            Option(R.string.tt, R.drawable._599133274_menu_cafe_4),
            Option(R.string.ha,R.drawable.quan_ca_phe_07_1635853362),
            Option(R.string.li, R.drawable.tintuc),
        )
    }
}
