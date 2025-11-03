package com.example.motivation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivation.MotivationConstants
import com.example.motivation.R
import com.example.motivation.databinding.ActivityUserBinding
import com.example.motivation.helper.SecurityPreferences
import android.widget.Toast
import android.content.Intent


class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        securityPreferences = SecurityPreferences(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setListeners()
    }

    override fun onClick(v: View){
        if(v.id == R.id.button_save){
            handleSave()
        }
    }

    private fun handleSave(){
        val name = binding.editTextId.text.toString()

        if(name.isEmpty()){
            Toast.makeText(this, "Informe seu nome", Toast.LENGTH_SHORT).show()
        } else {
            securityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME, name)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setListeners(){
        binding.buttonSave.setOnClickListener(this)
    }
}