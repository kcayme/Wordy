package com.example.wordy

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.wordy.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

/*
*Create a wordy app that includes the following:
    One Edit Text in which the user can enter a word
    One Text View (multiline) to the display the current word list in selected order
    Radio Button Group for the list display order selection - ascending or descending
    Buttons:
    Add – add word to the current list and display sorted list
    Clear – destroy / clear current list
* */
class MainActivity : AppCompatActivity() {
    private var wordList = ArrayList<String>()
    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clearBtn.setOnClickListener {
            wordList.clear()
            binding.wordList.text = ""
            Toast.makeText(this,wordList.toString(),Toast.LENGTH_SHORT).show()
        }

        binding.addBtn.setOnClickListener {
            if(binding.radioGrp.checkedRadioButtonId == -1) Toast.makeText(this,"No order of sorting selected!", Toast.LENGTH_SHORT).show()
            else if(binding.wordInput.text.toString().isBlank()) Toast.makeText(this, "Input must not be blank!", Toast.LENGTH_SHORT).show()
            else{
                val newWord = binding.wordInput.text.toString().trim()
                wordList.add(newWord)
                if (binding.radioButtonAsc.isChecked){
                    Collections.sort(wordList,String.CASE_INSENSITIVE_ORDER)
                    Toast.makeText(this,"Ascending",Toast.LENGTH_SHORT).show()
                }
                else if (binding.radioButtonDesc.isChecked) {
                    Collections.sort(wordList,String.CASE_INSENSITIVE_ORDER.reversed())
                    Toast.makeText(this,"Descending Selected",Toast.LENGTH_SHORT).show()
                }
                val textview = binding.wordList
                textview.text = ""
                for(word in wordList){
                    val currentText = textview.text.toString()
                    val appendStr = "$currentText\n$word"
                    textview.text = appendStr.trim()
                }
                binding.wordInput.setText("")
            }
        }
    }
}