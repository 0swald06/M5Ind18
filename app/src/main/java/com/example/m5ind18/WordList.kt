package com.example.m5ind18

class WordList {
    // lista de datos
    private val wordPairs: MutableList<Pair<String, String>> = mutableListOf()

    // agrego  para poder que las palabras no borren la anterior y utilzar esta variable en clase SharedPrefences
    // como la varibles esta privada hago un funcion que retorne la lista para poder utilzarla desde preferencias
    fun getWordPairs(): MutableList<Pair<String, String>> {
        return wordPairs
    }
    // guardar palabras
    fun addWord(key: String, value: String) {

        wordPairs.add(Pair(key, value))
    }

    // eliminar palabras
    fun removeWord(key:String){
        wordPairs.removeAll{it.first==key}
    }


    fun getSerializedString():String{
        // agrego una coma entre las palabras
        return wordPairs.joinToString(separator = ",") {"${it.first}:${it.second}"}

    }


    fun deserializeFromString(serialized: String?) {


        // elimina la lista, elimina todo
        wordPairs.clear()

        // esta linea verifica si la cadena. no es nula o vacia
        if (!serialized.isNullOrEmpty()) {

            //   Si la condición anterior se cumple (es decir,
            //     serializedString no es nula ni está vacía),
            //     se ejecuta el bloque de código dentro de las llaves {}.

            // vamos diviendo la cadena
            val pairs = serialized.split(",")



            pairs.forEach { pairString ->

                val keyValue = pairString.split(":")
                // si la lista tiene dos elementos
                if (keyValue.size == 2) {

                    // añade las palabras si los datos son exactamente 2
                    wordPairs.add((Pair(keyValue[0], keyValue[1])))
                }

            }

        }
    }

    // muestra las palabras
    override fun toString():String{

        return wordPairs.joinToString  ("\n" ){"${it.first}:${it.second}"}
    }

}