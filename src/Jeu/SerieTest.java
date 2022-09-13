package Jeu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SerieTest {
	void test() {
		Joueur j = new Joueur("toto");
		Carte c = new Carte();
		Serie s = new Serie();
		
		s.ajouter(c, j);
		System.out.println(s);
	}

}
