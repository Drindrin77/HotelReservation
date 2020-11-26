package conteneur.TestConteneur;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import testEtat.*;

public class TestInit {

	private Conteneur C;

	// Objectif de test : creation d'un conteneur de capacite > 1
	// Resultat attendu : conteneur vide cree de la capacite passee en argument
	@Test
	public void capaciteSup1() {
		try {
			C = new Conteneur(5);
		} catch (Exception e) {
			fail();
			// on force le test a echouer si une exception est levee
		}
		assertNotNull(C);
		assertEquals(C.taille(), 0);
		assertEquals(C.capacite(), 5);
		assertTrue(C.estVide());
	}
	
	// Objectif de test : creation d'un conteneur de capacite = 1
	// Resultat attendu : exception
	@Test
	public void capaciteEgale1() {
		try {
			C = new Conteneur(1);
		} catch (Exception e) {
			fail();
			// on force le test a echouer si une exception est levee
		}
		assertNotNull(C);
		assertEquals(C.taille(), 0);
		assertEquals(C.capacite(), 1);
		assertTrue(C.estVide());
	}
	
	// Objectif de test : initialiser avec une capacite = 0
	// Resultat attendu : exception
	@Test
	public void capaciteVide() {
		try {
			C = new Conteneur(0);
		} catch (Exception e) {
			fail();
			// on force le test a echouer si une exception est levee
		}
		assertNull(C);
	}
	
	// Objectif de test : initialiser avec une capacite negative ( <0 )
	// Resultat attendu : exception
	@Test
	public void capaciteNegative() {
		try {
			C = new Conteneur(-1);
		} catch (Exception e) {
			//On est censé rentrer dans le fail
			fail();
			// on force le test a echouer si une exception est levee
		}
		assertNull(C);
	}
}
