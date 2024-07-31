package com.example.recyclerview

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    val titles = arrayOf("Baños, Ecuador", "Puyo, Ecuador", "Chillos, Ecuador",  "Quito, Ecuador",  "Otavalo, Ecuador", "Quito, Ecuador")
    val details = arrayOf("A 40KM de distancia\n$36 noche", "A 60KM de distancia\n" +
            "\$27 noche", "A 7KM de distancia\n" +
            "\$21 noche", "A 3KM de distancia\n" +
            "\$36 noche"+ "A 31KM de distancia\n" +
            "\$33 noche" + "A 2KM de distancia\n" +
            "\$22 noche")
    val images = intArrayOf(R.drawable.house1, R.drawable.house2, R.drawable.house3, R.drawable.house4, R.drawable.house5, R.drawable.house6)

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView
        init{
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle= itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)
        }
    }

    //Se ejecuta al entrar por primera vez al recicler view
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }
    //Se ejecuta para poblar cada elemento del recicler view
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemDetail.text = details[i]
        viewHolder.itemImage.setImageResource(images[i])
    }

    //Tamaño del areglo de items
    override fun getItemCount(): Int {
        return titles.size
    }


}