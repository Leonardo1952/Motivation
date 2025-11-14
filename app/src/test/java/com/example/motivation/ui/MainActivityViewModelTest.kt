package com.example.motivation.ui

import androidx.lifecycle.ViewModel
import com.example.motivation.repository.PhraseRepository
import com.example.motivation.repository.SecurityPreferences

class MainActivityViewModel(
    private val repository: PhraseRepository,
    private val securityPreferences: SecurityPreferences
) : ViewModel() {

    fun refreshPhrase() {
        // TODO: Implement phrase refresh logic
    }
}
