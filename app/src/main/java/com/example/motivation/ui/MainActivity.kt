package com.example.motivation.ui

import com.example.motivation.repository.SecurityPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivation.helper.MotivationConstants
import com.example.motivation.R
import com.example.motivation.databinding.ActivityMainBinding
import PhraseRepository
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var securityPreferences: SecurityPreferences
    private val phraseRepository = PhraseRepository()
    private var filter: Int = MotivationConstants.PHRASEFILTER.ALL

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
        handleFilter(R.id.image_all)
        refreshPhrase()
    }

    override fun onClick(v: View) {
        val listId = listOf(
            R.id.image_all, R.id.image_happy, R.id.image_sunny
        )

        if (v.id == R.id.button_new_phrase) {
            refreshPhrase()
        } else if (v.id in listId) {
            handleFilter(v.id)
        }
    }

    private fun handleFilter(id: Int){

        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.black))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.black))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.black))

        when (id) {
            R.id.image_all -> {
                filter = MotivationConstants.PHRASEFILTER.ALL
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
            R.id.image_happy -> {
                filter = MotivationConstants.PHRASEFILTER.HAPPY
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
            R.id.image_sunny -> {
                filter = MotivationConstants.PHRASEFILTER.SUNNY
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
        }
    }

    private fun refreshPhrase() {
        binding.textviewPhase.text = phraseRepository.getPhrase(filter)

    }

    private fun getUserName() {
        val name = securityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        binding.textviewName.text = name
    }

    private fun setListeners() {
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }

}