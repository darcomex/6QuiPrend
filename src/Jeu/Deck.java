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
 * Type de donn�e repr�sentant la pioche dans laquelle
 * les joueurs r�cup�rent leurs cartes 
 */
public class Deck {
	/** Liste des cartes dans le deck */
	private ArrayList<Carte> listeCartes;
	/** Nombre maximal de cartes */
	private final int MAXCARTE = 104;
	
	/**
	 * Constructeur par d�faut de la class Deck, 
	 * initialise la liste de cartes et cr�er toutes
	 * les cartes jusque 104
	 */
	public Deck() {
		this.listeCartes = new ArrayList<>();
		for(int i = 0; i < MAXCARTE; i++) {
			this.listeCartes.add(new Carte());
		}
	}
	
	/**
	 * M�lange la liste des cartes
	 */
	public void melanger() {
		Collections.shuffle(this.listeCartes);
	}
	
	/**
	 * Retire la carte � la liste � l'indice 0 
	 * @return Carte, la carte retir�e
	 * @pre la liste de cartes ne doit pas �tre vide
	 */
	public Carte retirer() {
		assert(!this.listeCartes.isEmpty());
		Carte c = new Carte(this.listeCartes.get(0));
		this.listeCartes.remove(0);
		return new Carte(c);
	}
}