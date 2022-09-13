/**
 * @file Joueur.java
 * @author Tea Steven 108, Mellouk Marwan 106
 * @brief Structure de donnees Joueur
 * @version 1
 * @date 11/12/2021
 */

package Jeu;

/** Type de donnée représentant un Joueur du 6 qui prend */
public class Joueur {
	/** Identifiant qui permet de différencier les joueurs */
	private int identifiant;
	/** Prenom du joueur */
	private String prenom;
	/** Paquet de cartes avec lequel le joueur joue */
	private Paquet paquetJeu;
	/** Nombre de tete de boeufs du joueur */
	private int teteDeBoeufs;
	/** Compteur du nombre d'instance de joueurs */
	private static int compteurId = 0;
	
	
	/**
	 * Constructeur par défaut de la classe Joueur, initialise
	 * le paquet de jeu et de récupération, un prénom par défaut
	 * et incrémente le compteur
	 */
	public Joueur() {
		this.identifiant = compteurId++;
		this.prenom = "toto";
		this.paquetJeu = new Paquet();
		this.teteDeBoeufs = 0;
	}
	
	/**
	 * Constructeur de la classe Joueur, initialise
	 * le paquet de jeu et de récupération, un prénom en paramètre
	 * et incrémente le compteur
	 * @param prenom, chaine de caractère représentant le prénom
	 * du joueur
	 */
	public Joueur(String prenom) {
		this();
		this.prenom = prenom;
	}
	
	/**
	 * Constructeur de la classe Joueur, à partir d'un
	 * autre joueur, copie tous ces attributs sans
	 * sa référence
	 * @param j, le joueur à recopier
	 */
	public Joueur(Joueur j) {
		this.identifiant = j.identifiant;
		this.prenom = j.prenom;
		this.paquetJeu = new Paquet();
		this.paquetJeu = j.paquetJeu;
		this.teteDeBoeufs = j.teteDeBoeufs;
	}
	
	/* ------------------ ACCESSEURS ------------------ */
	
	/**
	 * Retourne l'identifiant du joueur
	 * @return int, l'identifiant
	 */
	public int getIdentifiant() {
		return this.identifiant;
	}
	
	/**
	 * Retourne le compteur static d'un joueur
	 * @return static int, le compteur
	 */
	public static int getCompteur() {
		return compteurId;
	}
	
	/**
	 * Retourne le prenom du joueur
	 * @return String, le prenom du joueur
	 */
	public String getPrenom() {
		return this.prenom;
	}
	
	/**
	 * Retourne le paquet de jeu du joueur
	 * @return Paquet, le paquet de jeu 
	 */
	public Paquet getPaquetJeu() {
		return this.paquetJeu;
	}
	
	/**
	 * Retourne le nombre de tête de boeufs
	 * @return int, le nombre de tête de boeufs 
	 */
	public int getTeteDeBoeufs() {
		return this.teteDeBoeufs;
	}
	
	/* ------------------------------------------------ */
	
	/**
	 * Retire une carte dans le paquet de jeu à partir de sa valeur
	 * @param valeur, valeur de la carte à retirer
	 * @return Carte, la carte retirée
	 * @return null, si la carte n'est pas dans le paquet
	 * @pre la valeur doit être strictement supérieur à 0
	 */
	public Carte retirerPaquetJeu(int valeur) {
		assert(valeur > 0);
		return this.paquetJeu.retirer(valeur);
	}
	
	/**
	 * Ajoute des têtes de boeufs pour le joueur
	 * @param i, le nombre de tête de boeufs à ajouter
	 * @pre i doit être strictement supérieur à 0
	 */
	public void ajouterTeteDeBoeufs(int i) {
		assert(i > 0);
		this.teteDeBoeufs += i;
	}
	
	/**
	 * Ajoute des têtes de boeufs pour le joueur à partir d'une carte
	 * @param c, la carte à ajouter
	 */
	public void ajouterTeteDeBoeufs(Carte c) {
		this.teteDeBoeufs += c.getTeteDeBoeuf();
	}
	
	/**
	 * Ajoute une carte dans le paquet de jeu
	 * @param c, la carte à ajouter dans le paquet de jeu
	 */
	public void ajouterPaquetJeu(Carte c) {
		this.paquetJeu.ajouter(c);
	}
	
	/**
	 * Méthode clone qui permet de recopier tous les attributs
	 * d'un joueur sans recopier sa référence
	 * @return Joueur, le joueur recopié
	 */
	public Joueur clone() {
		return new Joueur(this);
	}
	
	/**
	 * Méthode toString qui permet de retourner la chaine de caractères de toutes
	 * les cartes du joueur, et le prénom du joueur
	 * @return String, la chaîne de caractères
	 */
	public String toString() {
		String s = "";
		s += this.prenom;
		s += " ";
		s += this.paquetJeu.toString();
		return s;
	}
}
