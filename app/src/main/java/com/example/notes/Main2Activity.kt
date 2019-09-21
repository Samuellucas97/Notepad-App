package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        button_cancelar.setOnClickListener{
            setResult(10)
            finish()
        }

    }

    fun openEditDialog(view: View) {
        MyEditDialog.show(supportFragmentManager, object : MyEditDialog.OnTextListener {
            override fun onSetText(text: String) {
                Toast.makeText(this@Main2Activity, "Texto: $text", Toast.LENGTH_SHORT).show()
            }
        })
    }


}

