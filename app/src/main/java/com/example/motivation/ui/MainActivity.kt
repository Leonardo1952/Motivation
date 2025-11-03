package com.example.motivation.ui

import com.example.motivation.helper.SecurityPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivation.MotivationConstants
import com.example.motivation.R
import com.example.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener  {

    private lateinit var binding: ActivityMainBinding
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        securityPreferences = SecurityPreferences(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setListeners()
        getUserName()
    }

    override fun onClick(v: View){
        if(v.id == R.id.button_new_phrase){
            handleNewPhrase()
        }
    }

    private fun getUserName(){
      val  name =  securityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        binding.textviewName.text = name
    }

    private fun handleNewPhrase(){}


    private fun setListeners(){
        binding.buttonNewPhrase.setOnClickListener(this)
    }

}