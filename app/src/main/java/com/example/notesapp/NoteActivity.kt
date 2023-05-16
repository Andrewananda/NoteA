package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.notesapp.db.AppDatabase
import com.example.notesapp.db.Note
import com.example.notesapp.db.NoteDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class NoteActivity : AppCompatActivity() {

    lateinit var repository : AppDatabase
    lateinit var dao: NoteDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        repository = AppDatabase.getDatabase(applicationContext)
        dao = repository.noteDao()

        setupClickListener()
    }


    private  fun fetchNotes() {
        GlobalScope.launch(Dispatchers.IO) {
            val notes = dao.getAll()
            Log.i("INFO", notes.toString())
        }

    }


    private fun setupClickListener() {
        val button = findViewById<Button>(R.id.button)
        val txt = findViewById<EditText>(R.id.editTextTextMultiLine)

        button.setOnClickListener {
            if(txt.text.isNotEmpty()) {
                val note = Note("", txt.text.toString())

                GlobalScope.launch(Dispatchers.IO) {
                    dao.insertAll(note)
                }
                fetchNotes()
            }


        }
    }

}