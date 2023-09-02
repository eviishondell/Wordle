package com.example.wordle

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

object FourLetterWordList {
    // List of most common 4 letter words from: https://7esl.com/4-letter-words/
    val fourLetterWords =
        "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care,Case,Cash,City,Club,Cost,Date,Deal,Door,Duty,East,Edge,Face,Fact,Farm,Fear,File,Film,Fire,Firm,Fish,Food,Foot,Form,Fund,Game,Girl,Goal,Gold,Hair,Half,Hall,Hand,Head,Help,Hill,Home,Hope,Hour,Idea,Jack,John,Kind,King,Lack,Lady,Land,Life,Line,List,Look,Lord,Loss,Love,Mark,Mary,Mind,Miss,Move,Name,Need,News,Note,Page,Pain,Pair,Park,Part,Past,Path,Paul,Plan,Play,Post,Race,Rain,Rate,Rest,Rise,Risk,Road,Rock,Role,Room,Rule,Sale,Seat,Shop,Show,Side,Sign,Site,Size,Skin,Sort,Star,Step,Task,Team,Term,Test,Text,Time,Tour,Town,Tree,Turn,Type,Unit,User,View,Wall,Week,West,Wife,Will,Wind,Wine,Wood,Word,Work,Year,Bear,Beat,Blow,Burn,Call,Care,Cast,Come,Cook,Cope,Cost,Dare,Deal,Deny,Draw,Drop,Earn,Face,Fail,Fall,Fear,Feel,Fill,Find,Form,Gain,Give,Grow,Hang,Hate,Have,Head,Hear,Help,Hide,Hold,Hope,Hurt,Join,Jump,Keep,Kill,Know,Land,Last,Lead,Lend,Lift,Like,Link,Live,Look,Lose,Love,Make,Mark,Meet,Mind,Miss,Move,Must,Name,Need,Note,Open,Pass,Pick,Plan,Play,Pray,Pull,Push,Read,Rely,Rest,Ride,Ring,Rise,Risk,Roll,Rule,Save,Seek,Seem,Sell,Send,Shed,Show,Shut,Sign,Sing,Slip,Sort,Stay,Step,Stop,Suit,Take,Talk,Tell,Tend,Test,Turn,Vary,View,Vote,Wait,Wake,Walk,Want,Warn,Wash,Wear,Will,Wish,Work,Able,Back,Bare,Bass,Blue,Bold,Busy,Calm,Cold,Cool,Damp,Dark,Dead,Deaf,Dear,Deep,Dual,Dull,Dumb,Easy,Evil,Fair,Fast,Fine,Firm,Flat,Fond,Foul,Free,Full,Glad,Good,Grey,Grim,Half,Hard,Head,High,Holy,Huge,Just,Keen,Kind,Last,Late,Lazy,Like,Live,Lone,Long,Loud,Main,Male,Mass,Mean,Mere,Mild,Nazi,Near,Neat,Next,Nice,Okay,Only,Open,Oral,Pale,Past,Pink,Poor,Pure,Rare,Real,Rear,Rich,Rude,Safe,Same,Sick,Slim,Slow,Soft,Sole,Sore,Sure,Tall,Then,Thin,Tidy,Tiny,Tory,Ugly,Vain,Vast,Very,Vice,Warm,Wary,Weak,Wide,Wild,Wise,Zero,Ably,Afar,Anew,Away,Back,Dead,Deep,Down,Duly,Easy,Else,Even,Ever,Fair,Fast,Flat,Full,Good,Half,Hard,Here,High,Home,Idly,Just,Late,Like,Live,Long,Loud,Much,Near,Nice,Okay,Once,Only,Over,Part,Past,Real,Slow,Solo,Soon,Sure,That,Then,This,Thus,Very,When,Wide"

    // Returns a list of four letter words as a list
    fun getAllFourLetterWords(): List<String> {
        return fourLetterWords.split(",")
    }

    // Returns a random four letter word from the list in all caps
    fun getRandomFourLetterWord(): String {
        val allWords = getAllFourLetterWords()
        val randomNumber = (0..allWords.size).shuffled().last()
        return allWords[randomNumber].uppercase()
    }
}

private fun checkGuess(guess: String, answer: String): String {
    var result = ""
    for (i in 0..3) {
        if (guess[i] == answer[i]) {
            result += "O"
        }
        else if (guess[i] in answer) {
            result += "+"
        }
        else {
            result += "X"
        }
    }

    return result
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Reference to guess "edit textbox" and submit button
        val guessButton = findViewById<Button>(R.id.submitButton)
        val inputLine = findViewById<EditText>(R.id.textInput)

        // References to output lines showing user inputs and checked answers
        val guessLine1 = findViewById<TextView>(R.id.guess1)
        val checkLine1 = findViewById<TextView>(R.id.check1)
        val guessLine2 = findViewById<TextView>(R.id.guess2)
        val checkLine2 = findViewById<TextView>(R.id.check2)
        val guessLine3 = findViewById<TextView>(R.id.guess3)
        val checkLine3 = findViewById<TextView>(R.id.check3)

        // Makes array of the textviews
        var guessCount: Int = 0
        val guessTextViews: Array<TextView> = arrayOf<TextView>(guessLine1, guessLine2, guessLine3)
        val checkTextViews: Array<TextView> = arrayOf<TextView>(checkLine1, checkLine2, checkLine3)

        var correctWord = FourLetterWordList.getRandomFourLetterWord()

        guessButton.setOnClickListener {
            if(guessCount <= 2) {
                // gets text from user input
                var guess: String = inputLine.text.toString().uppercase()
                inputLine.setText("")

                if (guess.length == 4) {
                    guessTextViews[guessCount].text = guess.uppercase()
                    var result = checkGuess(guess, correctWord.uppercase())
                    checkTextViews[guessCount].text = result

                    if (result == "OOOO") {
                        // say they won
                        guessButton.isActivated = false
                        guessButton.setBackgroundColor(Color.GRAY)
                        Toast.makeText(applicationContext, "YOU WIN!!", Toast.LENGTH_LONG).show()

                    }


                    guessCount++
                } else {
                    val toast = Toast.makeText(applicationContext, "Not a 4-letter word!", Toast.LENGTH_SHORT).show()
                }

                if (guessCount == 3) {
                    guessButton.isActivated = false
                    guessButton.setBackgroundColor(Color.GRAY)
                    Toast.makeText(applicationContext, correctWord, Toast.LENGTH_LONG).show()
                }

            }
        }


    }






}