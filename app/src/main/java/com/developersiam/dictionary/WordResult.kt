package com.developersiam.dictionary

data class WordResult(
    val word: String,
    val phonetic: String?,
    val meanings: List<Meaning>,
)

data class Meaning(
    val antonyms: List<String>,
    val definitions: List<Definition>,
    val partOfSpeech: String,
    val synonyms: List<String>
)

data class Definition(
    val definition: String
)