package br.edu.ifpr.stiehl.aula7_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import br.edu.ifpr.stiehl.aula7_sqlite.db.AppDatabase
import br.edu.ifpr.stiehl.aula7_sqlite.db.dao.PersonDao
import br.edu.ifpr.stiehl.aula7_sqlite.entities.Person
import br.edu.ifpr.stiehl.aula7_sqlite.ui.PeopleAdapter
import br.edu.ifpr.stiehl.aula7_sqlite.ui.PeopleAdapterListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PeopleAdapterListener {
    lateinit var personDao: PersonDao
    lateinit var adapter: PeopleAdapter
    var personEditing: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db =
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "person.db"
            )
                .allowMainThreadQueries()
                .build()
        personDao = db.personDao()

        btSave.setOnClickListener { savePerson() }

        loadData()
    }

    private fun removePerson(person: Person) {
        personDao.remove(person)
        loadData()
    }

    private fun editPerson(person: Person) {
        txtFirstName.setText(person.firstName)
        txtLastName.setText(person.lastName)
        txtTitle.setText(person.title)

        txtFirstName.requestFocus()

        personEditing = person
    }

    private fun savePerson() {
        val firstName = txtFirstName.text.toString()
        val lastName = txtLastName.text.toString()
        val title = txtTitle.text.toString()

        if (personEditing != null) {
            personEditing?.let { person ->
                person.firstName = firstName
                person.lastName = lastName
                person.title = title
                personDao.update(person)

                adapter.updatePerson(person)
            }
        } else {
            var person = Person(firstName, lastName, title)
            val id = personDao.insert(person).toInt()
            person = personDao.findById(id)!!

            val position = adapter.addPerson(person)
            listPeople.scrollToPosition(position)
        }
    }

    private fun loadData() {
        val people = personDao.getAll()
        adapter = PeopleAdapter(people.toMutableList(), this)
        listPeople.adapter = adapter

        listPeople.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL, false
        )
//        listPeople.layoutManager = GridLayoutManager(
//            this, 2, RecyclerView.VERTICAL, false)

        clear()
    }

    private fun clear() {
        txtFirstName.setText("")
        txtLastName.setText("")
        txtTitle.setText("")

        txtFirstName.requestFocus()

        personEditing = null
    }

    override fun personRemoved(person: Person) {
        personDao.remove(person)
    }

    override fun personClicked(person: Person) {
        editPerson(person)
    }

}