package br.edu.ifpr.stiehl.aula7_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.room.Room
import br.edu.ifpr.stiehl.aula7_sqlite.db.AppDatabase
import br.edu.ifpr.stiehl.aula7_sqlite.db.dao.PersonDao
import br.edu.ifpr.stiehl.aula7_sqlite.entities.Person
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var personDao: PersonDao
    lateinit var adapter: ArrayAdapter<Person>
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
        listPeople.setOnItemClickListener { _, _, position, _ ->
            editPerson(getPersonFromList(position))
        }
        listPeople.setOnItemLongClickListener { _, _, position, _ ->
            removePerson(getPersonFromList(position))
            true
        }

        loadData()
    }

    private fun removePerson(person: Person) {
        personDao.remove(person)
        loadData()
    }

    private fun getPersonFromList(position: Int) =
        adapter.getItem(position) as Person

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
//            personEditing?.firstName = firstName
//            personEditing?.lastName = lastName
//            personEditing?.title = title
//            personDao.update(personEditing!!)

            personEditing?.let { person ->
                person.firstName = firstName
                person.lastName = lastName
                person.title = title
                personDao.update(person)
            }
        } else {
            val person = Person(firstName, lastName, title)
            personDao.insert(person)
        }

        loadData()
    }

    private fun loadData() {
        val people = personDao.getAll()
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            people
        )
        listPeople.adapter = adapter

        clear()
    }

    private fun clear() {
        txtFirstName.setText("")
        txtLastName.setText("")
        txtTitle.setText("")

        txtFirstName.requestFocus()

        personEditing = null
    }
}
