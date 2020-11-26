package conteneur.TestConteneur;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testEtat.*;

public class TestPlein1 {

	private Conteneur C;
	private Object A1, A2, B1, B2, B3;

	// Creation d'un conteneur plein
	@BeforeEach
	public void creerConteneurPlein() {
		try  {
			C = new Conteneur(2);
		} catch (Exception e) {
			fail();
		}
		A1 = new Object();
		A2 = new Object();
		B1 = new Object();
		B2 = new Object();
		B3 = new Object();
		
		try  {
			C.ajouter(A1, B1);
			C.ajouter(A2, B2);
			C.ajouter(A2, B3);
		} catch (Exception e) {
			fail();
		}

	}

	// Objectif de test : ajout d'un element dont la cle est deja presente dans un conteneur plein
	// Resultat attendu : ajout possible et ancien couple de meme cle ecrase
	@Test
	public void ajouterPresentPlein() throws ErreurConteneur {

		assertTrue(C.valeur(A2).equals(B3));
		
	}
}
