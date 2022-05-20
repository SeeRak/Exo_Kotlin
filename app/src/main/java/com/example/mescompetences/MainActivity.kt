package com.example.mescompetences

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mescompetences.fragments.HomeFragment
import com.example.mescompetences.fragments.NewCompetenceFragment
import com.example.mescompetences.fragments.StatisticFragment
import com.example.mescompetences.repositories.CompetenceRepository
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navMenu = findViewById<BottomNavigationView>(R.id.nav_menu)

        loadFragment(HomeFragment(this), R.string.home_page_title)
        loadFragment(HomeFragment(this), R.string.home_page_title)

        navMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_page -> {
                    loadFragment(HomeFragment(this), R.string.home_page_title)
                    return@setOnItemSelectedListener true
                }
                R.id.new_competence -> {
                    loadFragment(NewCompetenceFragment(this), R.string.new_comp_page_title)
                    return@setOnItemSelectedListener true
                }
                R.id.statistique_page -> {
                    loadFragment(StatisticFragment(this), R.string.statistics_page_title)
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }
    }

    fun loadFragment(fragment: Fragment, titleId: Int) {
        val pageTitle = findViewById<TextView>(R.id.page_title)
        pageTitle.text = resources.getString(titleId)
        CompetenceRepository.updateAll{
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onStart() {
        super.onStart()
        val player = MediaPlayer.create(this, R.raw.music_ph)
        player.setLooping(true); //Set looping
        player.setVolume(1.0f, 1.0f);
        player.setLooping(false)
        player.start();

    }
}