package com.example.mescompetences.repositories

import android.util.Log
import com.example.mescompetences.models.CompetenceModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*

object CompetenceRepository {
    val dbRef = FirebaseDatabase.getInstance("https://mes-competences-default-rtdb.europe-west1.firebasedatabase.app/").getReference("competences")

    val competences = mutableListOf<CompetenceModel>()

    fun updateAll(callback: () -> Unit) {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                competences.clear()
                snapshot.children.forEach() {
                    val competence = it.getValue(CompetenceModel::class.java)
                    competences.add(competence!!)
                }
                callback()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("Firebase", "loadPost:onCancelled", databaseError.toException())
            }
        })
    }

    fun insert(competence: CompetenceModel): Unit {
        competence.modifiedAt  = Date()
        dbRef.child(competence.id).setValue(competence)
    }

    fun remove(competence: CompetenceModel): Unit {
        dbRef.child(competence.id).removeValue()
    }
}