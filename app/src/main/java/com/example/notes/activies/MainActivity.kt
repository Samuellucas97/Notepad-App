package com.example.notes.activies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.domain.Note
import com.example.notes.R
import com.example.notes.adapter.NoteAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var notes = mutableListOf<Note>()
    private var noteAdapter = NoteAdapter(notes, this::onNoteItemClick)

    private fun onNoteItemClick(note: Note) {
        val intent = Intent(this, Main2Activity::class.java)
        intent.putExtra("description", note.description)
        startActivityForResult(intent, 1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.action_add -> {
                val intent = Intent(this, Main2Activity::class.java)
                startActivityForResult(intent, 1)
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun initRecyclerView(){
        recyclerViewNotes.adapter = noteAdapter
        val layoutManager = LinearLayoutManager(this)
//        layoutManager.spanSizeLookup = object: LinearLayoutManager.SpanSizeLookup() {
//
//        }
    }

    private fun addNote(){
        // TODO
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
