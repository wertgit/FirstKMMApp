package com.bakabool.firstkmmapp.shared.cache

/**
 * Next, let’s create another SDK root class.
 * This creates the Database object and exposes all the operations.
 * This will become our point of entry into the shared library for Android/iOS apps.
 * This is the class that Android and iOS apps will instantiate and perform core work.
 * This class takes in an instance of DatabseDriverFactory.
 * Thus, when iOS instantiates this class, the compiler will select the implementation from iosMain.
 */
class KmmSDK(dbDriverFactory: DatabaseDriverFactory) {
    private val database: Database = Database(dbDriverFactory)

    fun getAllNotes(): List<Note> {
        return database.getAllNotes()
    }

    fun getLastNote(): Note {
        return database.getLastNote()
    }

    fun insertNote(title: String, body: String?) {
        database.insertNote(title, body)
    }

    fun deleteAll() {
        database.deleteAll()
    }
}