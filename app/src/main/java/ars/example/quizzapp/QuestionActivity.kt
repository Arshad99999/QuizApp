package ars.example.quizzapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import ars.example.quizzapp.databinding.ActivityMainBinding
import ars.example.quizzapp.databinding.ActivityQuestionBinding
import com.google.gson.Gson

class QuestionActivity : AppCompatActivity() {
    private var  binding : ActivityQuestionBinding?= null
    private var index  = 1
    private var marks  =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val providedData = intent.getStringExtra("QUESTIONS")
        val formattedData = Gson().fromJson(providedData,Questions::class.java)

        setUpUi(formattedData)
        setUpQuestion(formattedData)

        binding?.next?.setOnClickListener {
            index++
            if(index>formattedData.size){
                index--
                Utility(formattedData.size)
                finish()
            }else{
                setUpUi(formattedData)
                setUpQuestion(formattedData)
            }

        }
        binding?.option1?.setOnClickListener {
             if(binding?.option1?.text==formattedData?.get(index-1)?.correctAnswer){
                 marks++
             }
        }
        binding?.option2?.setOnClickListener {
            if(binding?.option2?.text==formattedData?.get(index-1)?.correctAnswer){
                marks++
            }
        }
        binding?.option3?.setOnClickListener {
            if(binding?.option3?.text==formattedData?.get(index-1)?.correctAnswer){
                marks++
            }
        }
        binding?.option4?.setOnClickListener {
            if(binding?.option4?.text==formattedData?.get(index-1)?.correctAnswer){
                marks++
            }
        }

    }

    private fun setUpQuestion(formattedData: Questions?) {
        binding?.ques?.text = formattedData?.get(index-1)?.question
        val options = (formattedData?.get(index-1)?.incorrectAnswers)?.toMutableList()
        options?.add(formattedData?.get(index-1)?.correctAnswer!!)
        val shuffled = options?.shuffled()
        println(shuffled)

        binding?.option1?.text = shuffled!![0]
        binding?.option2?.text = shuffled!![1]
        binding?.option3?.text = shuffled!![2]
        binding?.option4?.text = shuffled!![3]


    }

    private fun setUpUi(formattedData: Questions?) {

        if( index == formattedData?.size){
            binding?.next?.text ="Finish"
        }
       binding?.titleQuiz?.text = formattedData?.get(index-1)?.category
        val number ="QUESTION "+"${index}/${formattedData?.size}"
        binding?.quesNo?.text =number
        binding?.quit?.setOnClickListener {
           Utility(formattedData?.size)
        }
        binding?.quitText?.setOnClickListener {
            Utility(formattedData?.size)
        }
        binding?.radioGroup?.clearCheck()
        binding?.option2?.isChecked =false
        binding?.option1?.isChecked =false
        binding?.option3?.isChecked =false
        binding?.option3?.isChecked =false

    }
    private fun Utility(size:Int?){
        val intent  = Intent(this@QuestionActivity,ResultActivity::class.java)
        intent.putExtra("MARKS",marks)
        intent.putExtra("ATTEMPTED",index)
        intent.putExtra("TOTAL",size)
        startActivity(intent)
        finish()
    }

}