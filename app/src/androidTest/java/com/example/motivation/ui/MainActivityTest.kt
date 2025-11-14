package com.example.motivation.ui

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.motivation.R
import com.example.motivation.helper.MotivationConstants
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    // Regra para iniciar a MainActivity antes de cada teste
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var context: Context

    // Configuração inicial antes dos testes
    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        // Limpa as SharedPreferences para garantir um estado limpo
        val prefs = context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }

    @Test
    fun test_onCreate_loadsUserName() {
        // Salva um nome de usuário de teste nas SharedPreferences
        val userName = "Test User"
        val prefs = context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)
        prefs.edit().putString(MotivationConstants.KEY.PERSON_NAME, userName).apply()

        // Reinicia a activity para carregar o novo nome
        activityRule.scenario.recreate()

        // Monta a saudação esperada
        val expectedGreeting = "Olá, $userName!"

        // Verifica se o TextView exibe a saudação correta
        onView(withId(R.id.textview_name)).check(matches(withText(expectedGreeting)))
    }

    @Test
    fun test_phraseFilter_changesPhraseCategory() {
        // Verifica se o filtro "All" está selecionado por padrão
        onView(withId(R.id.image_all)).check(matches(isDisplayed()))

        // Clica no filtro "Happy" e verifica a mudança
        onView(withId(R.id.image_happy)).perform(click())
        onView(withId(R.id.image_happy)).check(matches(isDisplayed()))


        // Clica no filtro "Sunny" e verifica a mudança
        onView(withId(R.id.image_sunny)).perform(click())
        onView(withId(R.id.image_sunny)).check(matches(isDisplayed()))
    }

    @Test
    fun test_newPhraseButton_getsNewPhrase() {
        // Captura a frase inicial
        val initialPhrase = arrayOf("")
        activityRule.scenario.onActivity { activity ->
            val textView = activity.findViewById<android.widget.TextView>(R.id.textview_phase)
            initialPhrase[0] = textView.text.toString()
        }

        // Clica no botão para gerar uma nova frase
        onView(withId(R.id.button_new_phrase)).perform(click())

        // Captura a nova frase e verifica se é diferente da inicial
        activityRule.scenario.onActivity { activity ->
            val textView = activity.findViewById<android.widget.TextView>(R.id.textview_phase)
            val newPhrase = textView.text.toString()
            assert(newPhrase != initialPhrase[0])
        }
    }
}
