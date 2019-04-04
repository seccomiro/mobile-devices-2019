package br.edu.ifpr.stiehl.aula7_sqlite.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people")
data class Person(
    @ColumnInfo(name = "first_name")
    var firstName: String,
    @ColumnInfo(name = "last_name")
    var lastName: String,
    var title: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    val fullName get() = "${firstName} ${lastName}"
    val fullNameWithTitle get() = "${title} ${fullName}"

    override fun toString()= fullNameWithTitle
}