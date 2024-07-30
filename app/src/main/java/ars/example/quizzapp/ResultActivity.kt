package ars.example.quizzapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import ars.example.quizzapp.databinding.ActivityQuestionBinding
import ars.example.quizzapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private var  binding : ActivityResultBinding?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding?.root)

       val score = intent.getIntExtra("MARKS",0)
        val attempted = intent.getIntExtra("ATTEMPTED",0)
       val total =  intent.getIntExtra("TOTAL",0)

        if(total!=0){
          var  IntermediateResult = score.toFloat()/total.toFloat()
          val percentage = IntermediateResult*100.0
          setUpResultUi(score,attempted,percentage)
        }
    }

    private fun setUpResultUi(score:Int,attempted:Int,percentage:Double){
        if(percentage>=85.0){
            binding?.imageView2?.setImageResource(R.drawable.tropywin)
        }else{
            binding?.imageView2?.setImageResource(R.drawable.tropy)
        }
        val perCentage =  Math.round(percentage * 1000.0) / 1000.0
        binding?.Score?.text=perCentage.toString()+"% Score"
       lateinit var spanStringBuilder :SpannableStringBuilder
        if(score>1){
             spanStringBuilder=SpannableStringBuilder("You attempted " +
                     "${attempted} questions and from that ${score} answers are correct")
        }else{
            spanStringBuilder=SpannableStringBuilder("You attempted " +
                    "${attempted} questions and  from that ${score} answer is correct")
        }
        val fColor1 = ForegroundColorSpan(Color.BLUE)
        val fColor2 = ForegroundColorSpan(Color.parseColor("#33B803"))
        spanStringBuilder.setSpan(fColor1,14,26,SpannableStringBuilder.SPAN_INCLUSIVE_EXCLUSIVE)
        spanStringBuilder.setSpan(fColor2,41,50,SpannableStringBuilder.SPAN_INCLUSIVE_EXCLUSIVE)
        binding?.massage?.text =spanStringBuilder

    }
}