package com.example.motivation

import com.example.motivation.helper.MotivationConstants
import com.example.motivation.repository.Phrase
import com.example.motivation.repository.PhraseRepository
import org.junit.Assert
import org.junit.Test

class PhraseRepositoryTest {

    private val happy = MotivationConstants.PHRASEFILTER.HAPPY
    private val sunny = MotivationConstants.PHRASEFILTER.SUNNY

    private val langPt = MotivationConstants.LANGUAGE.PORTUGUESE

    // Lista de frases replicada para verificação nos testes
    private val testPhrases: List<Phrase> = listOf(
        Phrase("Não sabendo que era impossível, foi lá e fez.", happy, langPt),
        Phrase("Você não é derrotado quando perde, você é derrotado quando desiste!", happy, langPt),
        Phrase("Quando está mais escuro, vemos mais estrelas!", happy, langPt),
        Phrase("Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente.", happy, langPt),
        Phrase("Não pare quando estiver cansado, pare quando tiver terminado.", happy, langPt),
        Phrase("O que você pode fazer agora que tem o maior impacto sobre o seu sucesso?", happy, langPt),
        Phrase("A melhor maneira de prever o futuro é inventá-lo.", sunny, langPt),
        Phrase("Você perde todas as chances que você não aproveita.", sunny, langPt),
        Phrase("Fracasso é o condimento que dá sabor ao sucesso.", sunny, langPt),
        Phrase(" Enquanto não estivermos comprometidos, haverá hesitação!", sunny, langPt),
        Phrase("Se você não sabe onde quer ir, qualquer caminho serve.", sunny, langPt),
        Phrase("Se você acredita, faz toda a diferença.", sunny, langPt),
        Phrase("Riscos devem ser corridos, porque o maior perigo é não arriscar nada!", sunny, langPt)
    )

    @Test
    fun `getPhrase should return a happy phrase when happy filter is selected`() {
        val repository = PhraseRepository()

        // Chama o método a ser testado
        val phraseDescription = repository.getPhrase(happy, langPt)

        // Pega a lista de descrições de frases que esperamos para a categoria "happy"
        val expectedHappyPhrases = testPhrases.filter { it.category == happy }.map { it.description }

        // Verifica se a frase retornada está contida na lista de frases esperadas
        Assert.assertTrue(expectedHappyPhrases.contains(phraseDescription))
    }
}
