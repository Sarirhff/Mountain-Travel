package com.example.mountaintravel.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mountaintravel.R
import com.example.mountaintravel.main.Mountain

class MountainAdapter(val listMountain: ArrayList<Mountain>): RecyclerView.Adapter<MountainAdapter.ListViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Mountain)
    }

    class ListViewHolder(itemView: View ): RecyclerView.ViewHolder(itemView) {
        var nameMountain : TextView = itemView.findViewById(R.id.name_mountain)
        var imgMountian : ImageView = itemView.findViewById(R.id.img_cardview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MountainAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: MountainAdapter.ListViewHolder, position: Int) {
        val (name, image) = listMountain[position]
        holder.nameMountain.text = name
        holder.imgMountian.setImageResource(image)
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listMountain[holder.adapterPosition])

        }
    }

    override fun getItemCount(): Int = listMountain.size

}