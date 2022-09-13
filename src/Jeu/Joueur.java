/**
 * @file Joueur.java
 * @author Tea Steven 108, Mellouk Marwan 106
 * @brief Structure de donnees Joueur
 * @version 1
 * @date 11/12/2021
 */

package Jeu;

/** Type de donn�e repr�sentant un Joueur du 6 qui prend */
public class Joueur {
	/** Identifiant qui permet de diff�rencier les joueurs */
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
	 * Constructeur par d�faut de la classe Joueur, initialise
	 * le paquet de jeu et de r�cup�ration, un pr�nom par d�faut
	 * et incr�mente le compteur
	 */
	public Joueur() {
		this.identifiant = compteurId++;
		this.prenom = "toto";
		this.paquetJeu = new Paquet();
		this.teteDeBoeufs = 0;
	}
	
	/**
	 * Constructeur de la classe Joueur, initialise
	 * le paquet de jeu et de r�cup�ration, un pr�nom en param�tre
	 * et incr�mente le compteur
	 * @param prenom, chaine de caract�re repr�sentant le pr�nom
	 * du joueur
	 */
	public Joueur(String prenom) {
		this();
		this.prenom = prenom;
	}
	
	/**
	 * Constructeur de la classe Joueur, � partir d'un
	 * autre joueur, copie tous ces attributs sans
	 * sa r�f�rence
	 * @param j, le joueur � recopier
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
	 * Retourne le nombre de t�te de boeufs
	 * @return int, le nombre de t�te de boeufs 
	 */
	public int getTeteDeBoeufs() {
		return this.teteDeBoeufs;
	}
	
	/* ------------------------------------------------ */
	
	/**
	 * Retire une carte dans le paquet de jeu � partir de sa valeur
	 * @param valeur, valeur de la carte � retirer
	 * @return Carte, la carte retir�e
	 * @return null, si la carte n'est pas dans le paquet
	 * @pre la valeur doit �tre strictement sup�rieur � 0
	 */
	public Carte retirerPaquetJeu(int valeur) {
		assert(valeur > 0);
		return this.paquetJeu.retirer(valeur);
	}
	
	/**
	 * Ajoute des t�tes de boeufs pour le joueur
	 * @param i, le nombre de t�te de boeufs � ajouter
	 * @pre i doit �tre strictement sup�rieur � 0
	 */
	public void ajouterTeteDeBoeufs(int i) {
		assert(i > 0);
		this.teteDeBoeufs += i;
	}
	
	/**
	 * Ajoute des t�tes de boeufs pour le joueur � partir d'une carte
	 * @param c, la carte � ajouter
	 */
	public void ajouterTeteDeBoeufs(Carte c) {
		this.teteDeBoeufs += c.getTeteDeBoeuf();
	}
	
	/**
	 * Ajoute une carte dans le paquet de jeu
	 * @param c, la carte � ajouter dans le paquet de jeu
	 */
	public void ajouterPaquetJeu(Carte c) {
		this.paquetJeu.ajouter(c);
	}
	
	/**
	 * M�thode clone qui permet de recopier tous les attributs
	 * d'un joueur sans recopier sa r�f�rence
	 * @return Joueur, le joueur recopi�
	 */
	public Joueur clone() {
		return new Joueur(this);
	}
	
	/**
	 * M�thode toString qui permet de retourner la chaine de caract�res de toutes
	 * les cartes du joueur, et le pr�nom du joueur
	 * @return String, la cha�ne de caract�res
	 */
	public String toString() {
		String s = "";
		s += this.prenom;
		s += " ";
		s += this.paquetJeu.toString();
		return s;
	}
}
