package com.bakabool.firstkmmapp.shared.cache

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

/**
 * The {expect} keyword tells the compiler to look for an actual implementation of this class
 * in the platform-specific sourceset (androidMain and iosMain)
 * in the same package (commonMain/com/bakabool/firstkmmapp/shared/cache) in all sourcesets.
 */
actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(KmmDB.Schema, context, "notes.db")
    }
}