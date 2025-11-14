package com.example.motivation.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.motivation.helper.MotivationConstants
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SecurityPreferencesTest {

    private lateinit var securityPreferences: SecurityPreferences
    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        securityPreferences = SecurityPreferences(context)
    }

    @Test
    fun testStoreAndGetString() {
        val key = MotivationConstants.KEY.USER_NAME
        val value = "Test User"

        // Testa o armazenamento do valor
        securityPreferences.storeString(key, value)

        // Testa a recuperação do valor
        val retrievedValue = securityPreferences.getString(key)
        assertEquals(value, retrievedValue)
    }

    @Test
    fun testGetString_whenValueIsEmpty() {
        val key = MotivationConstants.KEY.USER_NAME

        // Garante que o valor não existe
        val prefs = context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)
        prefs.edit().remove(key).apply()

        // Testa a recuperação de uma string vazia
        val retrievedValue = securityPreferences.getString(key)
        assertEquals("", retrievedValue)
    }
}