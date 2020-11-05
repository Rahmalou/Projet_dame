package Algorithmes;

import jeuxDames.*;

import java.util.Arrays;
import java.util.Vector;

import jeuxDames.Plateau;

public class EtatDamier extends Plateau {
	private Vector<Integer> coupJouer = null;
	private Vector<Integer> pionJouer = null;

	public EtatDamier() {
		super();
	}

	public EtatDamier(int[][] matricePlateau) {
		// TODO Auto-generated constructor stub
		super(matricePlateau);

	}

	public EtatDamier(int[][] matricePlateau, Vector<Integer> coupJouer, Vector<Integer> pionJouer) {
		// TODO Auto-generated constructor stub
		super(matricePlateau);
		this.coupJouer = coupJouer;
		this.pionJouer = pionJouer;

	}

	/// retourne tous les coups possibles pour une couleur a partir de l'état courant du damier
	
	public Vector<EtatDamier> coupPossible(int couleurJeton) {
		
		
		Vector<Integer> pionAJouer = DoitJouer(couleurJeton); // les pions déplacables ou deplacements obligatoires
		
		Vector<EtatDamier> plateau = new Vector<EtatDamier>();
		
		if (pionAJouer.size() != 0) {
			///pour chaque pion deplacables
			for (int k = 0; k < pionAJouer.size(); k += 2) {
				//les  deplacements possibles pour chaque pion
				Vector<Integer> caseAJouer = CasesAJouer(pionAJouer.get(k), pionAJouer.get(k + 1), couleurJeton);
				
				// pour chaque deplacement
				for (int l = 0; l < caseAJouer.size(); l += 2) {
					//creer un nouveau etatDamier (et sauvegarder le coup joué)
					EtatDamier d = new EtatDamier(copyMatrice(this.matricePlateau),
							new Vector<Integer>(
									Arrays.asList(new Integer[] { caseAJouer.get(l), caseAJouer.get(l + 1) })),
							new Vector<Integer>(
									Arrays.asList(new Integer[] { pionAJouer.get(k), pionAJouer.get(k + 1) })));

	
					Vector<Integer> pion = new Vector<Integer>();
					pion.add(pionAJouer.get(k));
					pion.add(pionAJouer.get(k + 1));
					Vector<Integer> ccase = new Vector<Integer>();
					ccase.add(caseAJouer.get(l));
					ccase.add(caseAJouer.get(l + 1));

					//// pour les prises multiples ////
					do {
						//si un pion a été mangé le supprime
						d.PionMange(pion.get(0), pion.get(1), ccase.get(0), ccase.get(1), couleurJeton, new Joueur(),
								new Joueur());
						// mettre a jour le damier (deplacement du pion)
						d.matricePlateau[pion.get(0)][pion.get(1)] = 0;
						d.matricePlateau[ccase.get(0)][ccase.get(1)] = couleurJeton;
						
						
						pion.removeAllElements();
						pion.add(ccase.get(0));
						pion.add(ccase.get(1));
						
						//// si le pion ne peut plus rien manger, sortir de la boucle
						if (!d.PeutManger(pion.get(0), pion.get(1), couleurJeton))
							break;
						ccase.removeAllElements();
						Vector<Integer> caseSuivante = d.CasesAJouer(pion.get(0), pion.get(1), couleurJeton);
						if (!caseSuivante.isEmpty()) {
							ccase.add(caseSuivante.get(0));
							ccase.add(caseSuivante.get(1));
						}
					} while (true);
					
					
					////transforme le pion en dame si possible
					d.DameCreee(pion.get(0), pion.get(1), couleurJeton, new Joueur(), new Joueur());
					
					plateau.add(d);

				}

			}
		}
		return plateau;
	}

	//// copie de la matrice
	public static int[][] copyMatrice(int[][] matrice) {
		int[][] matriceCopy = new int[10][10];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				matriceCopy[i][j] = matrice[i][j];
		return matriceCopy;

	}

	
	//// differences entre le nombre de pions des deux joueurs
	public int Difference() {
		// TODO Auto-generated method stub
		int nb1 = 0;
		int nb2 = 0;

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				switch (this.matricePlateau[i][j]) {
				//blanc
				case 1:
					nb1++;
					break;
				//Noir	
				case 2:
					nb2++;
					break;
				// dame blanche (vaut 3)	
				case 10:
					nb1 += 3;
					break;
				// dame noire (vaut 3)		
				case 20:
					nb2 += 3;
					break;
				}

			}
		return nb2 - nb1;

	}

	public Vector<Integer> getCoupJouer() {
		return coupJouer;
	}

	public void setCoupJouer(Vector<Integer> coupJouer) {
		this.coupJouer = coupJouer;
	}

	public Vector<Integer> getPionJouer() {
		return pionJouer;
	}

	public void setPionJouer(Vector<Integer> pionJouer) {
		this.pionJouer = pionJouer;
	}

}
