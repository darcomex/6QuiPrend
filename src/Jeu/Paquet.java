/**
 * @file Paquet.java
 * @author Tea Steven 108, Mellouk Marwan 106
 * @brief Structure de donnees Paquet
 * @version 1
 * @date 13/12/2021
 */

package Jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Type de donnée représentant les paquets de cartes du joueur */
public class Paquet {
	/** Liste des cartes du paquet */
	private ArrayList<Carte> cartes;
	
	/**
	 * Constructeur par défaut de Paquet, initialise la liste
	 */
	public Paquet() {
		this.cartes = new ArrayList<>();
	}
	
	/**
	 * Constructeur de Paquet, initialise la liste et mets la carte dedans
	 * @param c, la carte à ajouter dans la liste
	 */
	public Paquet(Carte c) {
		this.cartes = new ArrayList<>();
		this.cartes.add(c);
	}
	
	/**
	 * Getter permettant de récupérer la liste de cartes
	 * @return ArrayList<Carte>, la liste de cartes du paquet
	 */
	public ArrayList<Carte> getCartes() {
		return this.cartes;
	}
	
	/**
	 * Ajoute une carte dans le paquet
	 * @param Carte, la carte à ajouter
	 */
	public void ajouter(Carte c) {
		this.cartes.add(c);
	}
	
	/**
	 * Tri le paquet de carte dans l'ordre croissant
	 * des valeurs des cartes
	 */
	public void trier() {
		for(int i = 0; i < this.cartes.size()-1; i++) {
			if(this.cartes.get(i).getValeur() > this.cartes.get(i+1).getValeur()) {
				Carte temp = new Carte(this.cartes.get(i));
				this.cartes.remove(i);
				this.cartes.add(i+1, temp);
				i = -1;
			}
		}
	}
	
	/**
	 * Recherche une carte avec sa valeur
	 * @param valeur, la valeur en entier de la carte
	 * @return int, la position de la carte dans la liste
	 * @return -1, si la carte n'est pas dans la liste
	 * @pre valeur doit être strictement supérieure à 0
	 */
	public int rechercheCarte(int valeur) {
		assert(valeur > 0 || valeur == -1);
		int trouvee = -1;
		for(int i = 0; i < this.cartes.size(); i++) {
			if(this.cartes.get(i).getValeur() == valeur)
				trouvee = i;
		}
		return trouvee;
	}
	
	/**
	 * Retire une carte dans le paquet depuis la valeur de la carte
	 * @param valeur, la valeur de la carte à retirer
	 * @pre valeur doit être strictement supérieure à 0
	 */
	public Carte retirer(int valeur) {
		assert(valeur > 0);
		int existe = this.rechercheCarte(valeur);
		if(existe != -1) {
			Carte c = new Carte(valeur);
			this.cartes.remove(existe);
			return c;
		}
		return null;
	}
	
	/**
	 * Méthode toString qui permet de retourner la chaine de caractères de toutes
	 * les cartes du paquet
	 * @return String, la chaîne de caractères
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < this.cartes.size(); i++) {
			s.append(this.cartes.get(i));
			if(i != this.cartes.size() - 1)
				s.append(", ");
		}
		return s.toString();
	}
}