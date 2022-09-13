/**
 * @file Deck.java
 * @author Tea Steven 108, Mellouk Marwan 106
 * @brief Structure de donnees Deck
 * @version 1
 * @date 12/12/2021
 */

package Jeu;
import java.util.ArrayList;
import java.util.Collections;

/** 
 * Type de donnée représentant la pioche dans laquelle
 * les joueurs récupèrent leurs cartes 
 */
public class Deck {
	/** Liste des cartes dans le deck */
	private ArrayList<Carte> listeCartes;
	/** Nombre maximal de cartes */
	private final int MAXCARTE = 104;
	
	/**
	 * Constructeur par défaut de la class Deck, 
	 * initialise la liste de cartes et créer toutes
	 * les cartes jusque 104
	 */
	public Deck() {
		this.listeCartes = new ArrayList<>();
		for(int i = 0; i < MAXCARTE; i++) {
			this.listeCartes.add(new Carte());
		}
	}
	
	/**
	 * Mélange la liste des cartes
	 */
	public void melanger() {
		Collections.shuffle(this.listeCartes);
	}
	
	/**
	 * Retire la carte à la liste à l'indice 0 
	 * @return Carte, la carte retirée
	 * @pre la liste de cartes ne doit pas être vide
	 */
	public Carte retirer() {
		assert(!this.listeCartes.isEmpty());
		Carte c = new Carte(this.listeCartes.get(0));
		this.listeCartes.remove(0);
		return new Carte(c);
	}
}