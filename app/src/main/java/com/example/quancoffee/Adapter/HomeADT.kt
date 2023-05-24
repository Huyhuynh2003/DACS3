package com.example.quancoffee.Adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quancoffee.*
import com.example.quancoffee.Lichthidau.Lichthidau
import com.example.quancoffee.Model.Option
import com.example.quancoffee.Menu.MenuMon
import com.example.quancoffee.UI.NhanVien



class HomeADT(
    private val context: Context,
    private val dataset: List<Option>
) : RecyclerView.Adapter<HomeADT.ItemViewHolder>(){
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.image_hero)
        val textView: TextView = view.findViewById(R.id.text_name)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int): HomeADT.ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu, parent, false)
        return HomeADT.ItemViewHolder(adapterLayout)
    }
    override fun onBindViewHolder(holder: HomeADT.ItemViewHolder,
                                  position: Int) {
        val item = dataset[position]
        holder.textView.text = context.resources.getString(item.name)
        holder.imageView.setImageResource(item.image)

        holder.imageView.setOnClickListener{
            when(item.name){
                R.string.tt->{
                    val i = Intent(context, MenuMon::class.java)
                    context.startActivity(i)

                  }
                R.string.ha->{
                    val i = Intent(context, NhanVien::class.java)
                    context.startActivity(i)
                }
                R.string.li->{
                    val i = Intent(context, Lichthidau::class.java)
                    context.startActivity(i)
                }
            }
        }

    }
    override fun getItemCount() = dataset.size
}
