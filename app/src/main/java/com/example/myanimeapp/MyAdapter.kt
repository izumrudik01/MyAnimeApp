package com.example.myanimeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val animeList : ArrayList<User>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_anm_admin,
            parent, false)
        return MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return animeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = animeList[position]

        holder.title.text = currentitem.title
        holder.description.text = currentitem.description

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val title : TextView = itemView.findViewById(R.id.titleTv)
        val description : TextView = itemView.findViewById(R.id.descriptionTv)


    }


}