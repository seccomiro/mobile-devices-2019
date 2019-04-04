package br.edu.ifpr.stiehl.aula7_sqlite.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.edu.ifpr.stiehl.aula7_sqlite.db.dao.PersonDao
import br.edu.ifpr.stiehl.aula7_sqlite.entities.Person

@Database(entities = arrayOf(Person::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun personDao(): PersonDao
}