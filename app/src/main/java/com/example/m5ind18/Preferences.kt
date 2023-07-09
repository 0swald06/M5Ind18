package com.example.m5ind18

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {

    companion object {
        private const val SHARED_PREFERENCES_NAME = "Preferences058"
        private const val  KEY_WORD_LIST= "Wordlist"

    }
    // instanciar las preferencias la clase
    private val  sharedPreferences : SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)


    fun saveWordList(wordList: WordList){
        // SE AGREGA PARA COMBINAR LAS LISTAS
        val previousWordList = getWordList().getWordPairs() // Obtener la lista anterior de palabras
        val combinedWordPairs = ArrayList(previousWordList) // Crear una nueva lista combinada

        combinedWordPairs.addAll(wordList.getWordPairs()) // Combinar con la nueva lista

        val editor = sharedPreferences.edit()
        editor.putString(KEY_WORD_LIST, WordList().apply { getWordPairs().addAll(combinedWordPairs) }.getSerializedString())
        editor.apply()

    }

    fun getWordList(): WordList{

        val serializeWordList = sharedPreferences.getString(KEY_WORD_LIST,"")
        // return  WordList().apply { deserializeFromString(serializeWordList) }
        // agrego esto para no borrar la palabra anterior
        // lamo la clase
        val wordList=WordList()
        wordList.deserializeFromString(serializeWordList)
        return wordList

    }
}