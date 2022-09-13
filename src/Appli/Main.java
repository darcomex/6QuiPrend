/**
 * @file Main.java
 * @brief Projet 
 * @author Tea Steven 108, Mellouk Marwan 106
 * @version 1
 * @date 12/02/2022
 */

package Appli;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import util.Console.*;
import Jeu.*;

public class Main {
	public static void main(String[] args) {
		final int JOUEURS_MIN = 2;
		final int JOUEURS_MAX = 10;
		final int CARTE_MAX = 10;
		final int NB_SERIES_MAX = 4;
		int tourJoueur = 0;
		int choixJoueur = 0;
		
		Scanner scan = new Scanner(System.in);
		LinkedList<Joueur> joueurs = new LinkedList<>();
		LinkedList<Serie> series = new LinkedList<>();
		Deck deck = new Deck();
		ArrayList<Carte> cartesRetirees = new ArrayList<>();
		
		String chemin = "config.txt";
		try {
			Scanner scanFic = new Scanner(new FileInputStream(chemin));
			while(scanFic.hasNextLine()) {
				joueurs.add(new Joueur(scanFic.nextLine()));
			}
			scanFic.close();
			assert(joueurs.size() >= JOUEURS_MIN && joueurs.size() <= JOUEURS_MAX);
		}
		catch(FileNotFoundException e) {
			System.out.println("Impossible d'ouvrir le fichier config.txt");
			System.exit(0);
		}
		
		for(int i = 0; i < NB_SERIES_MAX; i++)
			series.add(new Serie());
		
		deck.melanger();
		distributionCartes(joueurs, deck, CARTE_MAX);
		distributionSerie(series, deck);
		
		messageBienvenue(joueurs);
		
		for(int i = 0; i < joueurs.size(); i++) {
			joueurs.get(i).getPaquetJeu().trier();
		}
		
		int joueurQuiJoue;
		while(!joueurs.get(0).getPaquetJeu().getCartes().isEmpty()) {
			tourJoueur = 0;
			
			while(tourJoueur != joueurs.size()) {
				for(joueurQuiJoue = 0; joueurQuiJoue < joueurs.size(); joueurQuiJoue++) {
					if(tourJoueur == joueurs.get(joueurQuiJoue).getIdentifiant()) {
						break; 
					}
				}
				
				System.out.println("A " + joueurs.get(joueurQuiJoue).getPrenom() + " de jouer.");
				util.Console.pause();
				
				afficherSeries(series);
				
				System.out.println("- Vos cartes : " + joueurs.get(joueurQuiJoue).getPaquetJeu());
				System.out.print("Saisissez votre choix : ");
				do {
					try {
						choixJoueur = Integer.parseInt(scan.nextLine());
						if(choixJoueur < 0)
							choixJoueur = -1;
					}
					catch(NumberFormatException e) {
						choixJoueur = -1;
					}

					if(joueurs.get(joueurQuiJoue).getPaquetJeu().rechercheCarte(choixJoueur) == -1)
						System.out.print("Vous n'avez pas cette carte, saisissez votre choix : ");
				} while(joueurs.get(joueurQuiJoue).getPaquetJeu().rechercheCarte(choixJoueur) == -1);
				
				cartesRetirees.add(joueurs.get(joueurQuiJoue).retirerPaquetJeu(choixJoueur));
				util.Console.clearScreen();
				
				tourJoueur++;
			}
			
			while(!cartesRetirees.isEmpty()) {
				resetPosJoueur(joueurs);
				trierCartesRetirees(cartesRetirees, joueurs);
				
				afficherCartesPosees(joueurs, cartesRetirees);
				
				boolean teteDeBoeufsRecup = false;
				for(int j = 0; j < joueurs.size(); j++) {
					int[] serieValide = {-1, Integer.MAX_VALUE};
					
					for(int i = 0; i < series.size(); i++) {
						if(Math.abs(cartesRetirees.get(j).getValeur() - series.get(i).getDerniereCarte().getValeur()) < serieValide[1] && cartesRetirees.get(j).getValeur() - series.get(i).getDerniereCarte().getValeur() > 0) {
							serieValide[0] = i;
							serieValide[1] = cartesRetirees.get(j).getValeur() - series.get(i).getDerniereCarte().getValeur();
						}
					}
					
					if(serieValide[0] == -1) {
						System.out.println("Pour poser la carte " + cartesRetirees.get(j) + ", " + 
						joueurs.get(j).getPrenom() + " doit choisir la série qu'il va ramasser.");
						afficherSeries(series);
						System.out.print("Saisissez votre choix : ");
						do {
							try {
								serieValide[0] = Integer.parseInt(scan.nextLine());
							}
							catch(NumberFormatException e) {
								serieValide[0] = -1;
							}
							
							if(serieValide[0] < 1 || serieValide[0] > 4)
								System.out.print("Ce n'est pas une série valide, saisissez votre choix : ");
						} while(serieValide[0] < 1 || serieValide[0] > 4);
						serieValide[0]--;
						
						ArrayList<Carte> c;
						c = series.get(serieValide[0]).retirer();
						int somme = 0;
						for(int i = 0; i < c.size(); i++) 
							somme += c.get(i).getTeteDeBoeuf();
						
						teteDeBoeufsRecup = true;
						joueurs.get(j).ajouterTeteDeBoeufs(somme);
						afficherRamassageTeteBoeufs(joueurs.get(j).getPrenom(), somme);
					}
					
					int retirer = series.get(serieValide[0]).ajouter(cartesRetirees.get(j));
					if(retirer != 0) {
						afficherRamassageTeteBoeufs(joueurs.get(j).getPrenom(), retirer);
						teteDeBoeufsRecup = true;
						joueurs.get(j).ajouterTeteDeBoeufs(retirer);
					}
				}
				if(!teteDeBoeufsRecup)
					System.out.println("Aucun joueur ne ramasse de tête de boeufs.");
				
				cartesRetirees.clear();
			}
		}
		triJoueur(joueurs);
		afficherScoreFinal(joueurs);
		
		scan.close();
	}
	
	/**
	 * Affiche le score final de tout les joueurs ainsi que le nombre de 
	 * têtes de boeufs des joueurs
	 * @param j, la liste chainée des joueurs
	 */
	private static void afficherScoreFinal(LinkedList<Joueur> j) {
		System.out.println("** Score final");
		for(int i = 0; i < j.size(); i++) {
			System.out.print(j.get(i).getPrenom() + " a ramassé " + 
		j.get(i).getTeteDeBoeufs() + " tête");
			if(j.get(i).getTeteDeBoeufs() > 1)
				System.out.print("s");
			System.out.println(" de boeufs");
		}
	}

	/**
	 * Affiche un message de bienvenue en début de partie avec le prénom
	 * de tous les joueurs
	 * @param j, liste chaînée des joueurs à afficher
	 */
	private static void messageBienvenue(LinkedList<Joueur> j) {
		System.out.print("Les " + j.size() + " joueurs sont ");
		for(int i = 0; i < j.size(); i++) {
			System.out.print(j.get(i).getPrenom());
			if(i < j.size() - 2)
				System.out.print(", ");
			else if(i == j.size() - 2)
				System.out.print(" et ");
		}
		System.out.println(". Merci de jouer à 6 qui prend !");
	}
	
	/**
	 * Affiche les séries dans le jeu
	 * @param s, liste chaînée des séries à afficher
	 */
	private static void afficherSeries(LinkedList<Serie> s) {
		for(int i = 0; i < s.size(); i++) {
			System.out.println(s.get(i));
		}
	}
	
	/**
	 * Affiche un message du joueur qui récupère les têtes de boeufs
	 * @param prenom, le prénom du joueur qui récupère les têtes de boeufs
	 * @param teteDeBoeufs, le nombre de tête de boeufs récupéré
	 */
	private static void afficherRamassageTeteBoeufs(String prenom, int teteDeBoeufs) {
		System.out.print(prenom + " a ramassé " + teteDeBoeufs + " tête");
		if(teteDeBoeufs != 1)
			System.out.print("s");
		System.out.println(" de boeufs.");
	}
	
	/**
	 * Affiche les cartes et le nom des joueurs qui sont posées leur carte
	 * @param j, la liste des joueurs
	 * @param c, la liste des cartes qui sont retirées*/
	private static void afficherCartesPosees(LinkedList<Joueur> j, ArrayList<Carte> c) {
		System.out.print("Les cartes ");
		for(int i = 0; i < c.size(); i++) {
			System.out.print(c.get(i) + " (" + j.get(i).getPrenom() + ")");
			if(i != c.size()-1)
				System.out.print(", ");
		}
		System.out.println(" vont être posées.");
	}
	
	/**
	 * Distribue les cartes aux joueurs en début de jeu
	 * @param joueurs, liste chaînée des joueurs à donner les cartes
	 * @param d, le deck dans lequel les cartes sont distribuées
	 * @param CARTE_MAX, le nombre de cartes que les joueurs vont recevoir
	 */
	private static void distributionCartes(LinkedList<Joueur> joueurs, Deck d, int CARTE_MAX) {
		for(int i = 0; i < joueurs.size(); i++) {
			for(int j = 0; j < CARTE_MAX; j++) {
				joueurs.get(i).ajouterPaquetJeu(d.retirer());
			}
		}
	}
	
	/**
	 * Distribue une carte dans chaque série en début de jeu
	 * @param series, la liste chaînée des séries qui reçoient les cartes
	 * @param d, le deck dans lequel les cartes sont piochées
	 */
	private static void distributionSerie(LinkedList<Serie> series, Deck d) {
		for(int i = 0; i < series.size(); i++) {
			series.get(i).ajouter(d.retirer());
		}
	}
	
	/**
	 * Trie l'arraylist des cartes qui ont été retirée par les joueurs, et
	 * et fait permutter les joueurs afin que les cartes correspondent au joueur
	 * qui le pose
	 * @param c, ArrayList la liste des cartes qui ont été retirée
	 * @param j, LinkedList la liste des joueurs qui ont posé les cartes
	 */
	private static void trierCartesRetirees(ArrayList<Carte> c, LinkedList<Joueur> joueurs) {
		for(int i = 0; i < c.size() - 1; i++) {
			int indice = i;
			for(int j = i + 1; j < c.size(); j++) {
				if(c.get(j).getValeur() < c.get(indice).getValeur())
					indice = j;
			}
			
			Carte cTmp = c.get(indice).clone();
			c.remove(indice);
			c.add(indice, c.get(i).clone());
			c.remove(i);
			c.add(i, cTmp);
			
			Joueur jTmp = joueurs.get(indice).clone();
			joueurs.remove(indice);
			joueurs.add(indice, joueurs.get(i).clone());
			joueurs.remove(i);
			joueurs.add(i, jTmp);
		}
	}
	
	/**
	 * Replace les joueurs à leur position initiale dans la liste chainée
	 * @param j, la liste chainée des joueurs
	 */
	private static void resetPosJoueur(LinkedList<Joueur> j) {
		for(int i = 0; i < j.size(); i++) {
			for(int k = 0; k < j.size(); k++) {
				if(i == j.get(k).getIdentifiant()) {
					Joueur tmp = j.get(k).clone();
					j.remove(k);
					j.add(i, tmp);
				}
			}
		}
	}
	
	/**
	 * Trie les joueurs dans l'ordre croissant en fonction de leur 
	 * nombre de têtes de boeufs
	 * @param j, la liste chainée des joueurs
	 */
	private static void triJoueur(LinkedList<Joueur> joueurs) {
		for(int i = 0; i < joueurs.size() - 1; i++) {
			int indice = i;
			for(int j = i + 1; j < joueurs.size(); j++) {
				if(joueurs.get(j).getTeteDeBoeufs() < joueurs.get(indice).getTeteDeBoeufs())
					indice = j;
			}
			Joueur tmp = joueurs.get(indice).clone();
			joueurs.remove(indice);
			joueurs.add(indice, joueurs.get(i).clone());
			joueurs.remove(i);
			joueurs.add(i, tmp);
		}
	}
}