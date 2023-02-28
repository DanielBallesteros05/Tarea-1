package ballesteros.daniel.geoquiz

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import ballesteros.daniel.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val quizViewModel:QuizViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }
        binding.nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }
        binding.prevButton.setOnClickListener {
            quizViewModel.moveToPrev()
            updateQuestion()
        }
        updateQuestion()
        Log.d(TAG,"Tengo un QuizViewModel: $quizViewModel")
    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()}
}