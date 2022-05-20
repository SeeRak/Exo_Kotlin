package com.example.mescompetences.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mescompetences.MainActivity
import com.example.mescompetences.R
import com.example.mescompetences.models.CompetenceModel
import com.example.mescompetences.popups.CompetenceDetailPopup

class CompetenceAdapter(
    val liste: List<CompetenceModel>,
    val context: MainActivity
) : RecyclerView.Adapter<CompetenceAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val compNameView = view.findViewById<TextView>(R.id.comp_name)
        val compLevelView = view.findViewById<TextView>(R.id.comp_level)
        val compTags = view.findViewById<RecyclerView>(R.id.comp_tags)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_competence, parent, false)
        var holder =  ViewHolder(view)
        holder.compTags.layoutManager = GridLayoutManager(parent.context, 3)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comp = liste[position]
        holder.compNameView.text = comp.name
        val niveau = "niveau "+comp.level
        holder.compLevelView.text = niveau
        holder.compTags.adapter = TagsAdapter(comp.tags)
        holder.itemView.setOnClickListener{
            CompetenceDetailPopup(context, comp).show()
        }
    }

    override fun getItemCount(): Int = liste.size
}