package conteneur.TestConteneur;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testEtat.*;

public class TestVide {

	private Conteneur C;

	// Creation d'un conteneur vide	
	@BeforeEach
	public void creerConteneurVide() {
		try {
			C = new Conteneur(10);
		} catch (Exception e) {
			fail();
		}
	}

	// Objectif de test : remise a zero d'un conteneur vide
	// Resultat attendu : remise a zero impossible, levee de l'exception ErreurConteneur
	@Test
	public void razVide() {
		try {
			C.raz();
			fail();
			// on force le test a echouer si aucune exception n'est levee
		} catch (ErreurConteneur e) {
			// si une exception de type ErreurConteneur est levee, le test reussit
			// on verifie que le conteneur n'a pas ete modifie
			assertEquals(C.taille(), 0);
			assertEquals(C.capacite(), 10);
			assertTrue(C.estVide());
		} catch (Exception e) {
			fail();
			// si une exception d'un autre type est levee, le test echoue 
		}
	}

	@Test
	public void estVide(){
		assertTrue(C.estVide());
		assertTrue(C.taille() == 0);
	}

	@Test
	public void ajouter(){
		Object A1 = new Object();
		Object B1 = new Object();

		try{
			C.ajouter(A1,B1);
		}catch (ErreurConteneur ec){
			fail();
		}

		assertTrue(C.taille() == 1);
		assertFalse(C.estVide());
	}
}
