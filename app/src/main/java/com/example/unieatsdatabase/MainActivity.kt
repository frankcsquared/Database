package com.example.unieatsdatabase

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File


class MainActivity : AppCompatActivity() {

    lateinit var nameText: EditText
    lateinit var calorieText: EditText
    lateinit var carbsText: EditText
    lateinit var fatsText: EditText
    lateinit var proteinsText: EditText
    lateinit var imageText: EditText
    lateinit var buttonSave: Button
    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ref = FirebaseDatabase.getInstance().getReference("Food")

        nameText = findViewById(R.id.nameText)
        calorieText = findViewById(R.id.calorieText)
        carbsText = findViewById(R.id.carbsText)
        fatsText = findViewById(R.id.fatsText)
        proteinsText = findViewById(R.id.proteinsText)
        buttonSave = findViewById(R.id.buttonSave)
        imageText = findViewById(R.id.imageText)

        buttonSave.setOnClickListener{
            saveFood()
        }
    }
    private fun saveFood(){
        val name = nameText.text.toString().trim()
        val calorie: Int? = calorieText.text.toString().trim().toIntOrNull()

        val carbs: Int? = carbsText.text.toString().trim().toIntOrNull()
        val fats: Int? = fatsText.text.toString().trim().toIntOrNull()
        val proteins: Int? = proteinsText.text.toString().trim().toIntOrNull()
        val image = imageText.text.toString().trim()

        var food = Food()

        if(calorie != null && carbs != null && fats != null && proteins != null) {
            val foodId = ref.push().key
            food = Food(foodId, name, calorie!!, carbs!! , fats!! , proteins!!, image!!)
        }

        if (food.name != "") {
            ref.push().setValue(food).addOnCompleteListener{
                Toast.makeText(applicationContext, "Food saved successfully", Toast.LENGTH_LONG).show()
            }
        }
        else{
            Toast.makeText(applicationContext, "Field empty, no save", Toast.LENGTH_LONG).show()
        }
    }
}
