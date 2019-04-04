package br.edu.ifpr.stiehl.aula7_sqlite.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifpr.stiehl.aula7_sqlite.R
import br.edu.ifpr.stiehl.aula7_sqlite.entities.Person
import kotlinx.android.synthetic.main.item_person.view.*

class PeopleAdapter(private var people: List<Person>) :
    RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_person, parent, false)
        )

    override fun getItemCount() = people.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = people[position]
        holder.fillUI(person)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun fillUI(person: Person) {
            itemView.txtTitle.text = person.title
            itemView.txtFirstName.text = person.firstName
            itemView.txtLastName.text = person.lastName
        }

    }
}