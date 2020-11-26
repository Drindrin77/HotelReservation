package conteneur.TestConteneur;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testEtat.*;

public class TestNonVide {

	private Conteneur C;
	private Object A1, A2, B1, B2;

	// Creation d'un conteneur partiellement rempli
	@BeforeEach
	public void creerConteneurNonVide() {
		try {
			C = new Conteneur(5);
		} catch (Exception e) {
			fail();
		}
		A1 = new Object();
		A2 = new Object();
		B1 = new Object();
		B2 = new Object();
		try {
			C.ajouter(A1, B1);
			C.ajouter(A2, B2);
		} catch (Exception e) {
			fail();
		}
	}
	
	//Savoir si le conteneur n'est pas vide
	@Test
	public void nonVide() {
		assertFalse(C.estVide());
	}
	
	//Savoir si le conteneur n'est pas plein
	@Test
	public void nonPlein() {
		assertNotEquals(C.taille(), C.capacite());
	}
	
	//Ajoute un nouveau couple
	@Test
	public void ajoute() {
		try {
			C.ajouter(new Object(), new Object());
		}catch(Exception e) {
			fail();
		}
	}
	
	//Ajoute un nouveau couple null
	@Test
	public void ajouteNull() {
		try {
			C.ajouter(null, null);
		}catch(Exception e) {
			fail();
		}
	}
	
	//Supprime un élément qui n'existe pas
	@Test
	public void supprimeInconnu() {
		try {
			C.retirer(new Object());
		}catch(Exception e) {
			fail();
		}
	}
	// Supprime un élément qui existe
	@Test
	public void supprime() {
		try {
			C.retirer(A1);
		}catch(Exception e) {
			fail();
		}
	}
	// Supprime un couple null
	@Test
	public void supprimeNull() {
		try {
			C.retirer(null);
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void getElement() {
		try {
			assertEquals(C.valeur(A1), B1);
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void getUnknownElement() {
		try {
			C.valeur(new Object());
		}catch(Exception e) {
			assertTrue(e instanceof ErreurConteneur);
		}
	}
}