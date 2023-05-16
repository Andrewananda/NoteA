package com.example.notesapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDAO {

    @Query("SELECT * FROM note_table")
    fun getAll(): List<Note>

    @Insert
    fun insertAll(vararg users: Note)

}