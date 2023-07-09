package com.example.m5ind18

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.m5ind18.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

private var _binding: FragmentFirstBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      _binding = FragmentFirstBinding.inflate(inflater, container, false)
      return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.btnGuardar.setOnClickListener {

            guardaDatos(preferences)
            mostrarDatos(preferences)
        }
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun guardaDatos(preferences: Preferences){

        val wordList= WordList()
        val entero = _binding?.numeroEntero?.text.toString()
        val texto = _binding?.texto?.text.toString()
        val decimal = _binding?.numeroDecimal?.text.toString()
        wordList.addWord("entero",entero)
        wordList.addWord("texto",texto)
        wordList.addWord("decimal",decimal)
        preferences.saveWordList(wordList)

    }



    fun mostrarDatos(preferences: Preferences) {


        val wordList = preferences.getWordList()


        // Iterar sobre la lista de pares de palabras y mostrar los datos
        for (pair in wordList.getWordPairs()) {
            val key = pair.first
            val value = pair.second
            println("Clave: $key - Valor: $value")
            if (key=="entero"){
                _binding?.mostrarEntero?.text=value
            }else if (key=="texto"){
                _binding?.mostrarTexto?.text=value
            }else if (key=="decimal"){
                _binding?.mostrarDecimal?.text=value
            }
        }

        }




}