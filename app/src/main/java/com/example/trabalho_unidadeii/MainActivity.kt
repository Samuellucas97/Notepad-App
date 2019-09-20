package com.example.trabalho_unidadeii

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var notes = mutableListOf<Note>()
    private var notesAdapter = NoteAdapter(notes, this::onNoteItemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

    }

    private fun initRecyclerView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun onNoteItemClick( note : Note) {
        var s = "${note.title} \n ${note.description}"
        Toast.makeText( this, s , Toast.LENGTH_SHORT).show()
    }

    // :: Inflando o menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.action_add -> {
                /// -> Enviando para a outra activity
                val intent = Intent(this, Main2Activity::class.java)
                startActivityForResult(intent, 1)
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
