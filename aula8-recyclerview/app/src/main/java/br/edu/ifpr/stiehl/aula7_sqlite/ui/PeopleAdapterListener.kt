package br.edu.ifpr.stiehl.aula7_sqlite.ui

import br.edu.ifpr.stiehl.aula7_sqlite.entities.Person

interface PeopleAdapterListener {
    fun personRemoved(person: Person)
    fun personClicked(person: Person)
}