package com.example.mescompetences.models

import java.util.*

class CompetenceModel(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "UNKNOWN",
    private var _level: Int = 1,
    val description: String = "",
    var modifiedAt: Date = Date(),
    var tags: MutableList<TagModel> = mutableListOf<TagModel>()
){
    var level: Int
        get() = _level
        set(newLevel) {
            if(newLevel < 0) _level = 0
            else if(newLevel > 20) _level = 20
            else _level = newLevel
        }
}