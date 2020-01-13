package com.project.segunfrancis.roomwordsamplekotlin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by SegunFrancis
 */

@Entity(tableName = "word_table")
data class Word(@PrimaryKey @ColumnInfo(name = "word") val word: String)