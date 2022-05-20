package com.example.mescompetences.popups

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mescompetences.R
import com.example.mescompetences.adapters.TagsAdapter
import com.example.mescompetences.models.CompetenceModel
import com.example.mescompetences.repositories.CompetenceRepository
import java.util.*

class CompetenceDetailPopup(context: Context, val competence : CompetenceModel) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_competence_detail)

        val nameView = findViewById<TextView>(R.id.competence_title)
        val descriptionView = findViewById<TextView>(R.id.description_field)
        val tagsView = findViewById<RecyclerView>(R.id.tags_recyler)
        val btnLvlDown = findViewById<ImageButton>(R.id.btn_level_down)
        val btnLvlUp = findViewById<ImageButton>(R.id.btn_level_up)
        val level = findViewById<TextView>(R.id.niveau_field)
        val btnDelete = findViewById<Button>(R.id.btn_delete_comp)

        nameView.text = competence.name
        descriptionView.text = competence.description
        tagsView.adapter = TagsAdapter(competence.tags)
        level.text = competence.level.toString()

        fun changeLvl(btnDown: ImageButton, btnUp: ImageButton, up: Boolean) {
            if(up) {
                competence.level++
                CompetenceRepository.insert(competence)
                level.text = competence.level.toString()
                if (level.text == "20") btnUp.setBackgroundColor(Color.parseColor("#D3D3D3"))
                else btnDown.setBackgroundColor(Color.parseColor("#F00000"))
            }
            else {
                competence.level--
                CompetenceRepository.insert(competence)
                level.text = competence.level.toString()
                if (level.text == "0") btnDown.setBackgroundColor(Color.parseColor("#D3D3D3"))
                else btnUp.setBackgroundColor(Color.parseColor("#32FF34"))
            }
        }

        btnLvlDown.setOnClickListener {
            changeLvl(btnLvlDown, btnLvlUp, false)
        }

        btnLvlUp.setOnClickListener {
            changeLvl(btnLvlDown, btnLvlUp, true)
        }

        btnDelete.setOnClickListener() {
            CompetenceRepository.remove(competence)
            dismiss()
        }
        }
}