package com.krai29.dashboardwithcrud.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.krai29.dashboardwithcrud.data.Person
import com.krai29.dashboardwithcrud.R
import kotlinx.android.synthetic.main.person_item.view.*

class PersonListAdapter(val context: Context,
var items : MutableList<Person>
): RecyclerView.Adapter<PersonListAdapter.PersonViewHolder>() {

    inner class PersonViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_item,parent,false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {

        var currentPerson = items[position]
        holder.itemView.apply {
            nameTV.text = currentPerson.name
            descriptionTV.text = currentPerson.description
            Glide.with(this).load(currentPerson.personImageUrl).into(personIV)
        }

        holder.itemView.deleteBtn.setOnClickListener {
            items.removeAt(position)
            notifyDataSetChanged()
        }

        holder.itemView.editBtn.setOnClickListener {
            val intent = Intent(context, AddPersonDialog::class.java)
            intent.putExtra("POSITION",position)
            intent.putExtra("PERSON",currentPerson)
            (context as PersonActivity).startActivityForResult(intent,2)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    public fun notifyChanges(position: Int,person: Person){
        items[position] = person
        notifyDataSetChanged()
    }







}