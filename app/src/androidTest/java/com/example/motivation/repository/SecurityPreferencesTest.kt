package com.example.motivation.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Test
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.motivation.helper.MotivationConstants

@RunWith(AndroidJUnit4::class)
class SecurityPreferencesTest {

    private lateinit var mSecurityPreferences: SecurityPreferences

    @Test
    fun testStoreAndGetString() {
        // Context
        val context = ApplicationProvider.getApplicationContext<Context>()
        mSecurityPreferences = SecurityPreferences(context)

        // Define key and value
        val key = MotivationConstants.KEY.PERSON_NAME
        val value = "Test User"

        // Store the value
        mSecurityPreferences.storeString(key, value)

        // Retrieve the value
        val retrievedValue = mSecurityPreferences.getString(key)

        // Assert that the retrieved value is the same as the stored value
        assertEquals(value, retrievedValue)
    }
}
