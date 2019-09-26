package com.example.myapplicationtest.activies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplicationtest.R
import com.example.myapplicationtest.dialog.MyEditDialog
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    private var description: String? = null
    private var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        description = intent.getStringExtra("description")
        title = intent.getStringExtra("title")

        edt_textos.setText(description)

        button_cancelar.setOnClickListener{
            setResult(10)
            finish()
        }
    }
    fun openEditDialog(view: View) {
        MyEditDialog.show(
            supportFragmentManager,
            object : MyEditDialog.OnTextListener {
                override fun onSetText(text: String) {
                    Toast.makeText(this@Main2Activity, "Texto: $text", Toast.LENGTH_SHORT).show()

                    description =edt_textos.getText().toString()

                    val intent: Intent = Intent()
                    intent.putExtra("newTitle", text)
                    intent.putExtra("title", title)
                    intent.putExtra("newDescription",description)

                    setResult(RESULT_OK,intent)
                    finish()
                }
            })
    }


}
