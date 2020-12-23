package com.krai29.dashboardwithcrud.ui

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.krai29.dashboardwithcrud.data.Person
import com.krai29.dashboardwithcrud.R
import kotlinx.android.synthetic.main.dialog_add_person.*

class AddPersonDialog : AppCompatActivity() {

    var position : Int = -1
    var person : Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_person)

        person = intent.getSerializableExtra("PERSON") as? Person
        position = intent.getIntExtra("POSITION",-1)


        btnAdd.setOnClickListener {
            if (position == -1 && person == null) {
                val name = nameEditText.text.toString()
                val description = bioEditText.text.toString()
                val personImageUrl = imageUrlEditText.text.toString()

                if (name.isEmpty() || description.isEmpty() || personImageUrl.isEmpty()) {
                    Toast.makeText(this, "No field should be empty", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val newPerson = Person(name, description, personImageUrl)
                val resultIntent = Intent()
                resultIntent.putExtra("PERSON",newPerson)
                setResult(1,resultIntent)
                finish()
            }
            else if(person!=null && position!=-1){
                person?.name = nameEditText.text.toString()
                person?.description = bioEditText.text.toString()
                person?.personImageUrl = imageUrlEditText.text.toString()
                val resultIntent = Intent()
                resultIntent.putExtra("PERSON",person)
                resultIntent.putExtra("POSITION",position)
                setResult(2,resultIntent)
                finish()
            }
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }
}