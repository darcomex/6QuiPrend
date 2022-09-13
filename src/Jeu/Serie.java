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
	/** Liste des cartes de la s�rie */
	private ArrayList<Carte> cartes;
	/** Nombre de carte maximale dans la s�rie */
	private static final int CARTE_MAX = 5;
	/** Identifiant permettant de diff�rencier les s�ries */
	private int numeroSerie;
	/** Compteur statique pour compter les s�ries */
	private static int numeroSerieCpt = 1;
	
	/**
	 * Constructeur par d�faut de Serie, initialise la liste et
	 * incr�mente le compteur de s�ries
	 */
	public Serie() {
		this.cartes = new ArrayList<>();
		this.numeroSerie = this.numeroSerieCpt++;
	}
	
	/**
	 * Retourne la derni�re carte de la s�rie
	 * @return Carte, la derni�re carte de la s�rie
	 */
	public Carte getDerniereCarte() {
		return this.cartes.get(this.cartes.size()-1);
	}
	
	/**
	 * Ajoute une carte dans la s�rie
	 * @param c, la carte � ajouter
	 * @return int, le nombre de t�te de boeufs
	 * @return 0, si la s�rie n'a pas �t� r�initialis�e
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
	 * Retire les cartes de la s�rie
	 * @return ArrayList, la liste des cartes retir�e
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
	 * M�thode toString qui permet de retourner la chaine de caract�res de toutes
	 * les cartes de la s�rie, ainsi que le num�ro de la s�rie
	 * @return String, la cha�ne de caract�res
	 */
	public String toString() {
		StringBuilder chaine = new StringBuilder("- S�rie n� " + this.numeroSerie + " : ");
		for(int i = 0; i < this.cartes.size(); i++) {
			chaine.append(this.cartes.get(i));
			if(i != this.cartes.size()-1)
				chaine.append(", ");
		}
		return chaine.toString();
	}
}