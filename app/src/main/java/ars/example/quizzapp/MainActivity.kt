package ars.example.quizzapp

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import ars.example.quizzapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        showDialog()
        setFeatures()

    }

    private fun setFeatures() {
           val diffHash =SelectorData?.getHashDifficultyLevels()
               val cateHash =SelectorData?.getHashCategories()
        binding?.createBtn?.setOnClickListener {
            val diff = binding?.difficultySelector?.selectedItem.toString()
            val cate = binding?.CategorySelector?.selectedItem?.toString()
            val quNo = binding?.quesNoText?.text?.toString()
            if (diff.isNullOrEmpty() || cate.isNullOrEmpty() || quNo.isNullOrEmpty()) {
                Toast.makeText(this@MainActivity, "Please fill all the fields", Toast.LENGTH_SHORT)
                    .show()
            } else {
                ApiCall(diffHash?.get(diff), cateHash?.get(cate), quNo)
            }

        }
        val difficulty = diffHash?.keys
        binding?.difficultySelector?.adapter =
            ArrayAdapter(this, R.layout.difficulty_layout, difficulty!!.toMutableList())
        val categories = cateHash?.keys
        binding?.CategorySelector?.adapter =
            ArrayAdapter(this, R.layout.difficulty_layout, categories!!.toMutableList())
        binding?.questionNoSeek?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding?.quesNoText?.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun ApiCall(difficulty: String?, Category: String?, NoOfQuestion: String?) {
        val providedService = serviceBuilder.getService(service::class.java)
        val serviceResult = providedService.get_questions(Category!!, NoOfQuestion!!, difficulty!!)
        serviceResult.enqueue(object : Callback<Questions> {
            override fun onResponse(call: Call<Questions>, response: Response<Questions>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val responseToString = Gson().toJson(responseBody)
                    val intent = Intent(this@MainActivity, QuestionActivity::class.java)
                    intent.putExtra("QUESTIONS", responseToString)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@MainActivity, "Failed to fetch", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Questions>, t: Throwable) {
                Toast.makeText(this@MainActivity, "SOMETHING WENT WRONG", Toast.LENGTH_SHORT).show()
            }

        })

    }


   private  fun showDialog() {

       val sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE)
       val dialogShown = sharedPreferences.getBoolean("dialog_shown", false)
       if (!dialogShown) {
           val alertDialog = AlertDialog.Builder(this)
               .setTitle("Welcome to our QUIZ APP")
               .setMessage("We're thrilled to have you here." +
                       " To ensure a smooth and enjoyable experience while creating your quiz, " +
                       "we kindly ask that you select no more than 30 questions. This helps us provide the best possible experience for you.")
               .setNeutralButton("Start") { dialog, _ ->
                   dialog.dismiss()
                   sharedPreferences.edit().putBoolean("dialog_shown", true).apply()
               }

               .create()
           alertDialog.show()

       }
   }
}
