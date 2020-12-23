package com.krai29.dashboardwithcrud.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.krai29.dashboardwithcrud.data.Person
import com.krai29.dashboardwithcrud.R
import kotlinx.android.synthetic.main.activity_person.*

class PersonActivity : AppCompatActivity() {

    private lateinit var adapter: PersonListAdapter

    private var listOfPerson = mutableListOf<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        adapter = PersonListAdapter(this,listOfPerson)
        personRecyclerView.layoutManager = LinearLayoutManager(this)
        personRecyclerView.adapter = adapter


        fabAdd.setOnClickListener {
            val intent = Intent(this, AddPersonDialog::class.java)
            startActivityForResult(intent,1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1 -> if (resultCode == 1){
                val person = data?.getSerializableExtra("PERSON") as? Person
                if (person != null) {
                    listOfPerson.add(person)
                }
            }
            2 -> if (resultCode == 2){
                val person = data?.getSerializableExtra("PERSON") as? Person
                val position = data?.getIntExtra("POSITION",-1)
                if (person != null) {
                    if (position != null) {
                        adapter.notifyChanges(position, person)
                    }
                }
            }
        }
    }
}