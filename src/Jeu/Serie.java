/**
 * @file Serie.java
 * @author Tea Steven 108, Mellouk Marwan 106
 * @brief Structure de donnees Serie
 * @version 1
 * @date 13/12/2021
 */

package Jeu;

import java.util.ArrayList;

public class Serie {
	/** Liste des cartes de la série */
	private ArrayList<Carte> cartes;
	/** Nombre de carte maximale dans la série */
	private static final int CARTE_MAX = 5;
	/** Identifiant permettant de différencier les séries */
	private int numeroSerie;
	/** Compteur statique pour compter les séries */
	private static int numeroSerieCpt = 1;
	
	/**
	 * Constructeur par défaut de Serie, initialise la liste et
	 * incrémente le compteur de séries
	 */
	public Serie() {
		this.cartes = new ArrayList<>();
		this.numeroSerie = this.numeroSerieCpt++;
	}
	
	/**
	 * Retourne la dernière carte de la série
	 * @return Carte, la dernière carte de la série
	 */
	public Carte getDerniereCarte() {
		return this.cartes.get(this.cartes.size()-1);
	}
	
	/**
	 * Ajoute une carte dans la série
	 * @param c, la carte à ajouter
	 * @return int, le nombre de tête de boeufs
	 * @return 0, si la série n'a pas été réinitialisée
	 */
	public int ajouter(Carte c) {
		int modif = 0;
		if(this.cartes.size() < CARTE_MAX) {
			this.cartes.add(c);
		}
		else {
			ArrayList<Carte> liste = new ArrayList<>();
			liste = this.retirer();
			for(int i = 0; i < CARTE_MAX; i++) {
				modif += liste.get(i).getTeteDeBoeuf(); 
			}
			this.cartes.add(c);
		}
		
		return modif;
	}

	/**
	 * Retire les cartes de la série
	 * @return ArrayList, la liste des cartes retirée
	 */
	public ArrayList<Carte> retirer() {
		ArrayList<Carte> liste = new ArrayList<>();
		for(int i = 0; i < this.cartes.size(); i++) {
			liste.add(this.cartes.get(i));
		}
		this.cartes.clear();
		return liste;
	}
	
	/**
	 * Méthode toString qui permet de retourner la chaine de caractères de toutes
	 * les cartes de la série, ainsi que le numéro de la série
	 * @return String, la chaîne de caractères
	 */
	public String toString() {
		StringBuilder chaine = new StringBuilder("- Série n° " + this.numeroSerie + " : ");
		for(int i = 0; i < this.cartes.size(); i++) {
			chaine.append(this.cartes.get(i));
			if(i != this.cartes.size()-1)
				chaine.append(", ");
		}
		return chaine.toString();
	}
}