package com.example.mescompetences.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.mescompetences.MainActivity
import com.example.mescompetences.R
import com.example.mescompetences.models.CompetenceModel
import com.example.mescompetences.models.TagModel
import com.example.mescompetences.repositories.CompetenceRepository

class NewCompetenceFragment(
    private val mainActivity: MainActivity
) : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_competence, container, false)

        val nameView = view.findViewById<EditText>(R.id.name_input)
        val tagView = view.findViewById<EditText>(R.id.tags_input)
        val descriptionView = view.findViewById<EditText>(R.id.description_input)
        val buttonView = view.findViewById<Button>(R.id.button_submit)

        buttonView.setOnClickListener {
            val name = nameView.text.toString()
            val tags = tagView.text.split(",").map { it.trim().lowercase() }
            val description = descriptionView.text.toString()
            val competence = CompetenceModel(name = name, description = description)
            for(tag in tags){
                val modelTag = TagModel(name = tag)
                competence.tags.add(modelTag)
            }
            CompetenceRepository.insert(competence)
            mainActivity.loadFragment(HomeFragment(mainActivity), R.string.home_page_title)
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        val player = MediaPlayer.create(mainActivity, R.raw.noice_sound)
        player.setLooping(true); //Set looping
        player.setVolume(1000.0f, 1000.0f);
        player.setLooping(false)
        player.start();
    }

}