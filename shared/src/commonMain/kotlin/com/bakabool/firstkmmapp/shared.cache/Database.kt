package com.bakabool.firstkmmapp.shared.cache

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = KmmDB(databaseDriverFactory.createDriver())
    private val dbQuery = database.kmmDBQueries

    internal fun getAllNotes(): List<Note> {
        return dbQuery.selectAllNotes().executeAsList()
    }

    internal fun getLastNote(): Note {
        return dbQuery.selectLastNote().executeAsOne()
    }

    internal fun insertNote(title: String, body: String?) {
        return dbQuery.insertNote(title, body)
    }

    internal fun deleteAll() {
        return dbQuery.deleteAll()
    }

//    internal fun selectNoteWithTitle(title: String): Note {
//        return dbQuery.selectNoteWithTitle(title).executeAsOne()
//    }
}