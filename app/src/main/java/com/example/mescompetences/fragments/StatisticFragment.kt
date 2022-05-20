package com.example.mescompetences.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mescompetences.MainActivity
import com.example.mescompetences.R
import com.example.mescompetences.adapters.CompetenceAdapter
import com.example.mescompetences.repositories.CompetenceRepository

class StatisticFragment(
    private val mainActivity: MainActivity
) : Fragment() {
    private val repo = CompetenceRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_statistique, container, false)

        val recyclerTopCompetence = view.findViewById<RecyclerView>(R.id.top_competence)
        val recyclerLastCompetence = view.findViewById<RecyclerView>(R.id.last_competence_modified)
        val lvlTotal = view.findViewById<TextView>(R.id.numb_total_lvl)

        val compList = repo.competences
        val topCompetence = compList.sortedByDescending { it.level }.take(1)
        val lastCompetence = compList.sortedByDescending { it.modifiedAt.time }.take(1)

        lvlTotal.text = compList.sumOf { it.level }.toString()

        recyclerTopCompetence.adapter =
            CompetenceAdapter(topCompetence, mainActivity)
        recyclerLastCompetence.adapter =
            CompetenceAdapter(lastCompetence, mainActivity)

        return view
    }

    override fun onStart() {
        super.onStart()
        val player = MediaPlayer.create(mainActivity, R.raw.window_sound)
        player.setLooping(true); //Set looping
        player.setVolume(1000.0f, 1000.0f);
        player.setLooping(false)
        player.start();
    }
}