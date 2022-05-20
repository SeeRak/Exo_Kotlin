package com.example.mescompetences.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mescompetences.R
import com.example.mescompetences.models.TagModel

class TagsAdapter(
    val liste: List<TagModel>
) : RecyclerView.Adapter<TagsAdapter.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tagNameView = view.findViewById<TextView>(R.id.tag_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tag, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tag = liste[position]
        holder.tagNameView.text = tag.name
    }

    override fun getItemCount(): Int = liste.size
}