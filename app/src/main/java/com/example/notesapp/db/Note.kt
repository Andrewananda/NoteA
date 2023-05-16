package com.example.notesapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_table")
data class Note(
    val title: String,
    val description: String,
    @PrimaryKey(autoGenerate = false) val id: Int? = null
)
