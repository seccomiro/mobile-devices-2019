package br.edu.ifpr.stiehl.aula7_sqlite.db.dao

import androidx.room.*
import br.edu.ifpr.stiehl.aula7_sqlite.entities.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM people ORDER BY first_name, last_name")
    fun getAll(): List<Person>

    @Query("SELECT * FROM people WHERE id = :id LIMIT 1")
    fun findById(id: Int): Person?

    @Query("SELECT * FROM people WHERE first_name LIKE :firstName AND last_name LIKE :lastName")
    fun findByName(firstName: String, lastName: String): List<Person>

    @Insert
    fun insert(person: Person): Long

    @Update
    fun update(person: Person)

    @Delete
    fun remove(person: Person)

//    @Delete
//    fun remove()
}