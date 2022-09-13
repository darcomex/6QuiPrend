/**
 * @file Carte.java
 * @author Tea Steven 108, Mellouk Marwan 106
 * @brief Structure de donnees Carte
 * @version 1
 * @date 11/12/2021
 */

package Jeu;

/** Type de donn�e repr�sentant une carte dans le jeu 6 qui prend !*/
public class Carte {
	/** Valeur num�rique de la carte */
	private int valeur;
	/** Nombre de t�tes de boeufs marqu�es sur la carte */
	private int teteDeBoeuf;
	/** Compteur interne du nombre d'instance de carte dans la partie */
	private static int compteur = 1;
	/** Variable statique permettant de compter les valeurs des cartes */
	
	/**
	 * Constructeur par d�faut de la classe Carte, initialise les t�tes de boeufs et
	 * la valeur de la carte
	 */
	public Carte() {
		int boeuf = 0;
		this.valeur = compteur++;
		String strValeur = "0" + String.valueOf(this.valeur);
		
		if(strValeur.charAt(strValeur.length() - 1) == '0') {
			boeuf += 3;
		}
		else if(strValeur.charAt(strValeur.length() - 1) == '5') {
			boeuf += 2;
		}
		if(strValeur.charAt(strValeur.length() - 2) == strValeur.charAt(strValeur.length() - 1) && strValeur.length() != 4) {
			boeuf += 5;
		}
		if(boeuf == 0) {
			boeuf += 1;
		}
		this.teteDeBoeuf = boeuf;
	}
	
	/**
	 * Constructeur de la classe Carte, initialise � partir d'une valeur de la
	 * et d�termine le nombre de t�te de boeufs
	 * @param valeur, une valeur de carte en entier
	 */
	public Carte(int valeur) {
		assert(valeur > 0);
		this.valeur = valeur;
		
		String strValeur = "0" + String.valueOf(this.valeur);
		int boeuf = 0;
		if(strValeur.charAt(strValeur.length() - 1) == '0')
			boeuf += 3;
		else if(strValeur.charAt(strValeur.length() - 1) == '5')
			boeuf += 2;
		if(strValeur.charAt(strValeur.length() - 2) == strValeur.charAt(strValeur.length() - 1))
			boeuf += 5;
		if(boeuf == 0)
			boeuf += 1;
		
		this.teteDeBoeuf = boeuf;
	}
	
	/**
	 * Constructeur permettant de copier une instance de carte
	 * sans recopier la r�f�rence
	 * @param c, la carte � recopier
	 */
	public Carte(Carte c) {
		this.valeur = c.valeur;
		this.teteDeBoeuf = c.teteDeBoeuf;
	}
	
	/**
	 * Retourne la valeur de la carte
	 * @return int, la valeur de la carte
	 */
	public int getValeur() {
		return this.valeur;
	}
	
	/**
	 * Retourne le nombre de tete de boeuf sur la carte
	 * @return int, le nombre de tete de boeufs
	 */
	public int getTeteDeBoeuf() {
		return this.teteDeBoeuf;
	}
	
	public Carte clone() {
		return new Carte(this);
	}
	
	/**
	 * M�thode toString de classe Carte, affiche la valeur
	 * de la carte ainsi que le nombre de t�te de boeufs si celui
	 * ci en a plus d'un
	 * @return String, le texte pour la carte
	 */
	public String toString() {
		if(this.teteDeBoeuf > 1)
			return this.valeur + " (" + this.teteDeBoeuf + ")";
		return String.valueOf(this.valeur);
	}
}