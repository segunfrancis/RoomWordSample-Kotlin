package com.project.segunfrancis.roomwordsamplekotlin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by SegunFrancis
 */

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): WordRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                )/*.addCallback(WordDatabaseCallback(scope))*/
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    // delete all content and repopulate the database whenever the app is started
    private class WordDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.wordDao())
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            // Delete all content here
            wordDao.deleteAll()

            // Add sample words
            var word = Word("Hello")
            wordDao.insert(word)
            word = Word("World!")
            wordDao.insert(word)
            word = Word("While")
            wordDao.insert(word)
            word = Word("Error")
            wordDao.insert(word)
            word = Word("Try")
            wordDao.insert(word)
            word = Word("And")
            wordDao.insert(word)
            word = Word("Catch!")
            wordDao.insert(word)
        }
    }
}