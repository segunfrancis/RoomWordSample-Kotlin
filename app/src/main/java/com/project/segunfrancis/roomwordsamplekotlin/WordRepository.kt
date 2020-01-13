package com.project.segunfrancis.roomwordsamplekotlin

import androidx.lifecycle.LiveData

/**
 * Created by SegunFrancis
 */

// Pass in the DAO instead of the whole
// database, because you only need access to the DAO
class WordRepository(private val wordDao: WordDao) {

    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}