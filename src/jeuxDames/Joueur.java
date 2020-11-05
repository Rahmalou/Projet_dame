package jeuxDames;

import java.util.Scanner;

import Algorithmes.ALGO;

public class Joueur {

	protected String pseudo;
	protected int statut;
	protected int nombrePion;
	protected int nombreDame;
	protected int CouleurPions;
	protected int niveau;
	protected ALGO algorithme;



	public Joueur() {
		pseudo = " ";
		statut = 0;
		nombrePion = 20;
		nombreDame = 0;
		CouleurPions = 0;
	}

	Joueur(String p, int s, int c, int n) {
		pseudo = p;
		statut = s;
		nombrePion = 20;
		nombreDame = 0;
		CouleurPions = c;
		niveau = n;
	}

	public void saisie(int couleur) {
		Scanner sc = new Scanner(System.in);
		statut = 1;
		System.out.println("Joueur " + couleur + " ,veuillez saisir votre pseudo :");
		pseudo = sc.next();
		CouleurPions = couleur;
		nombrePion = 20;
		nombreDame = 0;
	}

	public void saisie2(int couleur) {
		statut = 2;
		Scanner sc = new Scanner(System.in);
		pseudo = "Ordinateur";
		System.out.println("Veuillez saisir le niveau du Joueur Virtuel:");
		niveau = sc.nextInt();
		CouleurPions = couleur;
		nombrePion = 20;
		nombreDame = 0;
	}

	public void affiche() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Pseudo du Joueur:" + pseudo);
		if (statut == 1)
			System.out.println("Statut du Joueur: Réel");
		else {
			System.out.println("Statut du Joueur : Virtuel");
			System.out.println("Niveau :" + niveau);
		}
		System.out.println("Nombre de pions du Joueur:" + nombrePion);
		if (CouleurPions == 1)
			System.out.println("Couleur des pions : Noir");
		else
			System.out.println("Couleur des pions : Blanc");
		sc.close();
	}

	public String getpseudo() {
		return pseudo;
	}

	public int getstatut() {
		return statut;
	}

	public int getnombrePion() {
		return nombrePion;
	}

	public int getnombreDame() {
		return nombreDame;
	}

	public void setnombreDame(int n) {
		nombreDame = n;
	}

	public void setnombrePion(int n) {
		nombrePion = n;
	}

	public int getCouleurPions() {
		return CouleurPions;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int n) {
		niveau = n;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void setStatut(int statut) {
		this.statut = statut;
	}

	public void setCouleurPions(int couleurPions) {
		CouleurPions = couleurPions;
	}
	public ALGO getAlgorithme() {
		return algorithme;
	}

	public void setAlgorithme(ALGO algorithme) {
		this.algorithme = algorithme;
	}

}
