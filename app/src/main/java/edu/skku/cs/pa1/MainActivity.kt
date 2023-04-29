package edu.skku.cs.pa1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import java.io.BufferedReader


fun compareWords(target: String, op: String): String{
    var color = ""
    for(i in 0..4 ){
        if(target[i] == op[i]){
            color += "g" // green
        }
        else if(op[i] in target){
            color += "y" // yellow
        }
        else{
            color += "b" // black
        }
    }
    return color
}

fun isInArr(word: String, letters: ArrayList<Letter>): Boolean{
    for(letter in letters){
        if(word == letter.word){
            return true
        }
    }
    return false
}
fun removeInArr(word: String, letters: ArrayList<Letter>){
    for(letter in letters){
        if(word == letter.word){
            letters.remove(letter)
            break
        }
    }

}


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myInputStream = assets.open("wordle_words.txt")
        val myOutput = myInputStream.readBytes().toString(Charsets.UTF_8).uppercase()
        val words = myOutput.split("\\s+".toRegex())
        val randomWord = words.random()
        var wordles = arrayListOf<Wordle>()
        var grayLetters = arrayListOf<Letter>()
        var yellowLetters = arrayListOf<Letter>()
        var greenLetters = arrayListOf<Letter>()

        val inputEditText = findViewById<EditText>(R.id.editTextWordle)
        var mainWordleListView = findViewById<ListView>(R.id.mainWordleListView)
        var grayWordsListView = findViewById<ListView>(R.id.grayWordsListView)
        var yellowWordsListView = findViewById<ListView>(R.id.yellowWordsListView)
        var greenWordsListView = findViewById<ListView>(R.id.greenWordsListView)


        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            var inputText = inputEditText.text.toString().uppercase()
            if(inputText in words){
                inputEditText.text.clear()
                val color = compareWords(randomWord, inputText)
                // update mainViewList
                wordles.add(Wordle(inputText, color))
                mainWordleListView.adapter = WordleViewAdapter(this, wordles)

                // update letterViewList
                for(i in 0..4){
                    val letterColor = "" + color[i]
                    val letter = "" + inputText[i]
                    when(letterColor){
                        "g"->{
                            if(!isInArr(letter, greenLetters)){
                                if(isInArr(letter, grayLetters)){
                                    removeInArr(letter, grayLetters)
                                }
                                if(isInArr(letter, yellowLetters)){
                                    removeInArr(letter, yellowLetters)
                                }
                                greenLetters.add(Letter(letter, "g"))
                            }
                        }
                        "y"->{
                            if(!isInArr(letter, greenLetters)){
                                if(!isInArr(letter, yellowLetters)) {
                                    if(isInArr(letter, grayLetters)){
                                        removeInArr(letter, grayLetters)
                                    }
                                    yellowLetters.add(Letter(letter, "y"))
                                }
                            }
                        }
                        "b"->{
                            if(!isInArr(letter, greenLetters)){
                                if(!isInArr(letter, yellowLetters)) {
                                    if(!isInArr(letter, grayLetters)){
                                        grayLetters.add(Letter(letter, "b"))
                                    }
                                }
                            }


                        }
                    }
                    grayWordsListView.adapter = LetterViewAdapter(this, grayLetters)
                    yellowWordsListView.adapter = LetterViewAdapter(this, yellowLetters)
                    greenWordsListView.adapter = LetterViewAdapter(this, greenLetters)
                }

            }
            else{
                Toast.makeText(applicationContext,
                    "Word $inputText not in the dictionary!", Toast.LENGTH_SHORT).show()
            }
        }


    }
}