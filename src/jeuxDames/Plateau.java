package jeuxDames;

import java.util.*;

import Algorithmes.AlphaBeta;
import Algorithmes.Element;
import Algorithmes.EtatDamier;
import Algorithmes.Type;

public class Plateau {
	protected Scanner sc = new Scanner(System.in);
	public int matricePlateau[][] = new int[10][10]; // Matrice du plateau initialisïṡẄe totalement ïṡẄ 0.
	protected int ircopie, jrcopie, dm;
	protected int bloque = 0;

	// Constructeur du damier
	public Plateau() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if ((i + j) % 2 != 0 & i < 4) // les cases qui ont des pions noir
				{
					matricePlateau[i][j] = 2;
				}
				if ((i + j) % 2 != 0 & i > 5) // les cases qui ont des pions blanc
				{
					matricePlateau[i][j] = 1;
				}
			}
		}
	}
	public Plateau(int matricePlateau[][]) {
		this.matricePlateau=matricePlateau;
	}
	// Affiche le damier
	public void affiche() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (matricePlateau[i][j] < 5) {
					System.out.print(matricePlateau[i][j] + "  ");
				} else
					System.out.print(matricePlateau[i][j] + " ");
			}
			System.out.println(" ");
			System.out.println(" ");
		}
	}

	// Test si un pion peut manger un pion adverse
	public boolean PeutManger(int i, int j, int couleurJeton) {
		int a, k, b, a2, b2;
		boolean n = true, o = true, p = true, q = true;
		a = 1;
		k = 2;
		b = 2;
		a2 = 10;
		b2 = 20;
		if (couleurJeton == 2) {
			a = 2;
			b = 1;
			a2 = 20;
			b2 = 10;
		} // Test quel joueur joue et determine si les jeton adverses sont les 1 ou les 2.
		if (matricePlateau[i][j] == a) {
			if (i >= 2 && j >= 2) {
				if ((matricePlateau[i - 1][j - 1] == b || matricePlateau[i - 1][j - 1] == b2) && (matricePlateau[i - 2][j - 2] == 0)) {
					return true;
				}
			}
			if (i >= 2 && j < 8) {
				if ((matricePlateau[i - 1][j + 1] == b || matricePlateau[i - 1][j + 1] == b2) && (matricePlateau[i - 2][j + 2] == 0)) {
					return true;
				}
			}
			if (i < 8 && j < 8) {
				if ((matricePlateau[i + 1][j + 1] == b || matricePlateau[i + 1][j + 1] == b2) && (matricePlateau[i + 2][j + 2] == 0)) {
					return true;
				}
			}
			if (i < 8 && j >= 2) {
				if ((matricePlateau[i + 1][j - 1] == b || matricePlateau[i + 1][j - 1] == b2) && (matricePlateau[i + 2][j - 2] == 0)) {
					return true;
				}
			}
		} // Test pour chaque pion si il a des pions adverses a portïṡẄe.
		else {
			if (matricePlateau[i][j] == a2) {
				while (k < 10 && (n || o || p || q)) // Si le pion est une dame on teste toutes ses diagonales en
														// entier.
				{
					if (appartientPlateau(i + k, j + k) && n) {
						if (matricePlateau[i + k - 1][j + k - 1] == couleurJeton || matricePlateau[i + k - 1][j + k - 1] == couleurJeton * 10) {
							n = false;
						} else {
							if (matricePlateau[i + k - 1][j + k - 1] == b || matricePlateau[i + k - 1][j + k - 1] == b2) {
								if (matricePlateau[i + k][j + k] == 0) {
									return true;
								} else {
									n = false;
								}
							}
						}
					}
					if (appartientPlateau(i + k, j - k) && o) {
						if (matricePlateau[i + k - 1][j - k + 1] == couleurJeton || matricePlateau[i + k - 1][j - k + 1] == couleurJeton * 10) {
							o = false;
						} else {
							if (matricePlateau[i + k - 1][j - k + 1] == b || matricePlateau[i + k - 1][j - k + 1] == b2) {
								if (matricePlateau[i + k][j - k] == 0) {
									return true;
								} else {
									o = false;
								}
							}
						}
					}
					if (appartientPlateau(i - k, j + k) && p) {
						if (matricePlateau[i - k + 1][j + k - 1] == couleurJeton || matricePlateau[i - k + 1][j + k - 1] == couleurJeton * 10) {
							p = false;
						} else {
							if (matricePlateau[i - k + 1][j + k - 1] == b || matricePlateau[i - k + 1][j + k - 1] == b2) {
								if (matricePlateau[i - k][j + k] == 0) {
									return true;
								} else {
									p = false;
								}
							}
						}
					}
					if (appartientPlateau(i - k, j - k) && q) {
						if (matricePlateau[i - k + 1][j - k + 1] == couleurJeton || matricePlateau[i - k + 1][j - k + 1] == couleurJeton * 10) {
							q = false;
						} else {
							if (matricePlateau[i - k + 1][j - k + 1] == b || matricePlateau[i - k + 1][j - k + 1] == b2) {
								if (matricePlateau[i - k][j - k] == 0) {
									return true;
								} else {
									q = false;
								}
							}
						}
					} // Une fois la dame dans le tableau des pions qui ont une proie a portïṡẄe
						// il faut arreter de chercher si elle peut manger sur ses diagonales.
					k += 1;
				}
				return false;
			}
		}
		return false;
	}

	public boolean PeutBouger(int i, int j, int couleurJeton) {
		boolean b = false;
		if (PeutManger(i, j, couleurJeton))
			return true;
		else {
			int a, a2;
			a = 1;
			a2 = 10;
			if (couleurJeton == 2) {
				a = 2;
				a2 = 20;
			} // Test quel joueur joue et determine si les jeton adverses sont les 1 ou les 2.
			if (matricePlateau[i][j] == a) {
				if (i >= 1 && j >= 1 && couleurJeton == 1) {
					if (matricePlateau[i - 1][j - 1] == 0) {
						b = true;
					}
				}
				if (i >= 1 && j < 9 && couleurJeton == 1) {
					if (matricePlateau[i - 1][j + 1] == 0) {
						b = true;
					}
				}
				if (i < 9 && j < 9 && couleurJeton == 2) {
					if (matricePlateau[i + 1][j + 1] == 0) {
						b = true;
					}
				}
				if (i < 9 && j >= 1 && couleurJeton == 2) {
					if (matricePlateau[i + 1][j - 1] == 0) {
						b = true;
					}
				}
			} // Test pour chaque pion si il a de la place
			if (matricePlateau[i][j] == a2) // pour les dames
			{
				if (i >= 1 && j >= 1) {
					if (matricePlateau[i - 1][j - 1] == 0) {
						b = true;
					}
				}
				if (i >= 1 && j < 9) {
					if (matricePlateau[i - 1][j + 1] == 0) {
						b = true;
					}
				}
				if (i < 9 && j < 9) {
					if (matricePlateau[i + 1][j + 1] == 0) {
						b = true;
					}
				}
				if (i < 9 && j >= 1) {
					if (matricePlateau[i + 1][j - 1] == 0) {
						b = true;
					}
				}
			}
		}
		return b;
	}

	// Cette fonction calcule parmis les pions pouvant manger un autre pion ceux
	// ayant le chemin potentiel avec le plus de prises et renvoit leurs
	// coordonnées dans un tableau.
	public Vector<Integer> DoitJouer(int couleurJeton) {
		Vector<Integer> ti = new Vector<Integer>(); // Ce tableau sera le nombre de prises maximum pour chaque pions
		Vector<Integer> nul = new Vector<Integer>();
		int i, j, s2 = 0, s = 0;
		for (i = 0; i < 10; i++) {
			for (j = 0; j < 10; j++) // Parcours du plateau.
			{
				if (matricePlateau[i][j] == couleurJeton || matricePlateau[i][j] == couleurJeton * 10) {
					if (PeutBouger(i, j, couleurJeton)) // Test pour chaque pion si il peut manger un adversaire.
					{
						nul = new Vector<Integer>();
						s2 = MaxPrises(i, j, nul, (matricePlateau[i][j] > 9), couleurJeton); // Test le nombre maximum que le pion va manger
						if (s2 > s) {
							ti = new Vector<Integer>();
							ti.add(i);
							ti.add(j);
							s = s2;
						} // Si ce nombre est superieur au prïṡẄcedent max, il remplace tout le
							// tableau par les coordonnïṡẄes du nouveau pion
						else if (s2 == s) {
							ti.add(i);
							ti.add(j);
						}
					}
				}
			}
		} // Sinon si il est ïṡẄgal a l'ancien maximum, on ajoute ses
			// coordonnïṡẄes au tableau.
		return ti;
	}

	// Cette fonction determine le nombre de prises maximum pour un jeton
	// donnïṡẄ.

	public int MaxPrises(int i, int j, Vector<Integer> ni, Boolean dame, int couleurJeton) {
		int k = 1, k2 = 1, ja, a, b, c, d = 0;
		a = b = c = d;
		Boolean p, q, r, s = true;
		p = q = r = s;
		Boolean amange = false;
		if (couleurJeton == 1)
			ja = 2;
		else
			ja = 1;
		while (appartientPlateau(i + k, j + k) && p) {
			if (matricePlateau[i + k][j + k] == couleurJeton || matricePlateau[i + k][j + k] == couleurJeton * 10) {
				p = false;
			}
			if (matricePlateau[i + k][j + k] == ja || matricePlateau[i + k][j + k] == ja * 10) {
				if (ni.contains(i + k) && ni.contains(j + k)) {
					p = false;
				} else {
					k2 = 1;
					if ((appartientPlateau((i + k) + k2, (j + k + k2))) && !(matricePlateau[i + k + k2][j + k + k2] == couleurJeton
							|| matricePlateau[(i + k) + k2][(j + k) + k2] == couleurJeton * 10 || matricePlateau[(i + k) + k2][(j + k) + k2] == ja
							|| matricePlateau[(i + k) + k2][(j + k) + k2] == ja * 10)) {
						amange = true;
					}
					while (appartientPlateau((i + k) + k2, (j + k) + k2) && p) {
						if (matricePlateau[(i + k) + k2][(j + k) + k2] == couleurJeton || matricePlateau[(i + k) + k2][(j + k) + k2] == couleurJeton * 10
								|| matricePlateau[(i + k) + k2][(j + k) + k2] == ja || matricePlateau[(i + k) + k2][(j + k) + k2] == ja * 10) {
							p = false;
						}
						if (matricePlateau[(i + k) + k2][(j + k) + k2] == 0) {
							ni.add(i + k);
							ni.add(j + k);
							a = max(a, MaxPrises((i + k) + k2, (j + k) + k2, ni, dame, couleurJeton));
							if (!dame)
								k2 += 11;
							else
								k2++;
							ni.remove(ni.size() - 1);
							ni.remove(ni.size() - 1);
						}
					}
					if (amange) {
						a++;
					}
				}
			}
			if (!dame)
				k = 11;
			else
				k++;
		}
		k = 1;
		amange = false;
		while (appartientPlateau(i + k, j - k) && q) {
			if (matricePlateau[i + k][j - k] == couleurJeton || matricePlateau[i + k][j - k] == couleurJeton * 10) {
				q = false;
			}
			if (matricePlateau[i + k][j - k] == ja || matricePlateau[i + k][j - k] == ja * 10) {
				if (ni.contains(i + k) && ni.contains(j - k)) {
					q = false;
				} // a modifier***********
				else {
					k2 = 1;
					if ((appartientPlateau((i + k) + k2, (j - k) - k2)) && !(matricePlateau[(i + k) + k2][(j - k) - k2] == couleurJeton
							|| matricePlateau[(i + k) + k2][(j - k) - k2] == couleurJeton * 10 || matricePlateau[(i + k) + k2][(j - k) - k2] == ja
							|| matricePlateau[(i + k) + k2][(j - k) - k2] == ja * 10)) {
						amange = true;
					}
					while (appartientPlateau((i + k) + k2, (j - k) - k2) && q) {
						if (matricePlateau[(i + k) + k2][(j - k) - k2] == couleurJeton || matricePlateau[(i + k) + k2][(j - k) - k2] == couleurJeton * 10
								|| matricePlateau[(i + k) + k2][(j - k) - k2] == ja || matricePlateau[(i + k) + k2][(j - k) - k2] == ja * 10) {
							q = false;
						}
						if (matricePlateau[(i + k) + k2][(j - k) - k2] == 0) {
							ni.add(i + k);
							ni.add(j - k);
							b = max(b, MaxPrises((i + k) + k2, (j - k) - k2, ni, dame, couleurJeton));
							if (!dame)
								k2 += 11;
							else
								k2++;
							ni.remove(ni.size() - 1);
							ni.remove(ni.size() - 1);
						}
					}
					if (amange) {
						b++;
					}
				}
			}
			if (!dame)
				k = 11;
			else
				k++;
		}
		k = 1;
		amange = false;
		while (appartientPlateau(i - k, j + k) && r) {
			if (matricePlateau[i - k][j + k] == couleurJeton || matricePlateau[i - k][j + k] == couleurJeton * 10) {
				r = false;
			}
			if (matricePlateau[i - k][j + k] == ja || matricePlateau[i - k][j + k] == ja * 10) {
				if (ni.contains(i - k) && ni.contains(j + k)) {
					r = false;
				} else {
					k2 = 1;
					if ((appartientPlateau((i - k) - k2, (j + k) + k2)) && !(matricePlateau[(i - k) - k2][(j + k) + k2] == couleurJeton
							|| matricePlateau[(i - k) - k2][(j + k) + k2] == couleurJeton * 10 || matricePlateau[(i - k) - k2][(j + k) + k2] == ja
							|| matricePlateau[(i - k) - k2][(j + k) + k2] == ja * 10)) {
						amange = true;
					}
					while (appartientPlateau((i - k) - k2, (j + k) + k2) && r) {
						if (matricePlateau[(i - k) - k2][(j + k) + k2] == couleurJeton || matricePlateau[(i - k) - k2][(j + k) + k2] == couleurJeton * 10
								|| matricePlateau[(i - k) - k2][(j + k) + k2] == ja || matricePlateau[(i - k) - k2][(j + k) + k2] == ja * 10) {
							r = false;
						}
						if (matricePlateau[(i - k) - k2][(j + k) + k2] == 0) {
							ni.add(i - k);
							ni.add(j + k);
							c = max(c, MaxPrises((i - k) - k2, (j + k) + k2, ni, dame, couleurJeton));
							if (!dame)
								k2 += 11;
							else
								k2++;
							ni.remove(ni.size() - 1);
							ni.remove(ni.size() - 1);
						}
					}
					if (amange) {
						c++;
					}
				}
			}
			if (!dame)
				k = 11;
			else
				k++;
		}
		k = 1;
		amange = false;
		while (appartientPlateau(i - k, j - k) && s) {
			if (matricePlateau[i - k][j - k] == couleurJeton || matricePlateau[i - k][j - k] == couleurJeton * 10) {
				s = false;
			}
			if (matricePlateau[i - k][j - k] == ja || matricePlateau[i - k][j - k] == ja * 10) {
				if (ni.contains(i - k) && ni.contains(j - k)) {
					s = false;
				} else {
					k2 = 1;
					if ((appartientPlateau((i - k) - k2, (j - k) - k2))
							&& !(matricePlateau[(i - k) - k2][(j - k) - k2] == couleurJeton || matricePlateau[(i - k) - k2][(j - k) - k2] == couleurJeton * 10
									|| matricePlateau[(i - k) - k2][(j - k) - k2] == ja || matricePlateau[(i - k) - k2][(j - k) - k2] == ja * 10))
						amange = true;
					while (appartientPlateau((i - k) - k2, (j - k) - k2) && s) {
						if (matricePlateau[(i - k) - k2][(j - k) - k2] == couleurJeton || matricePlateau[(i - k) - k2][(j - k) - k2] == couleurJeton * 10
								|| matricePlateau[(i - k) - k2][(j - k) - k2] == ja || matricePlateau[(i - k) - k2][(j - k) - k2] == ja * 10) {
							s = false;
						}
						if (matricePlateau[(i - k) - k2][(j - k) - k2] == 0) {
							ni.add(i - k);
							ni.add(j - k);
							d = max(d, MaxPrises((i - k) - k2, (j - k) - k2, ni, dame, couleurJeton));
							if (!dame)
								k2 += 11;
							else
								k2++;
							ni.remove(ni.size() - 1);
							ni.remove(ni.size() - 1);
						}
					}
					if (amange) {
						d++;
					}
				}
			}
			if (!dame)
				k = 11;
			else
				k++;
		}
		return max(a, max(b, max(c, d)));
	}

	public int max(int m, int n) {
		if (m >= n)
			return m;
		else
			return n;
	};

	// Determine si une case est bien definie dans le plateau
	public boolean appartientPlateau(int i, int j) {
		if (i >= 0 && i < 10 && j >= 0 && j < 10)
			return true;
		else
			return false;
	}

	// Ensuite voici l'algo qui a partir d'un pion choisit par le joueur, renvoit le
	// tableau de boolïṡẄen des quatres cases, si matricePlateau[i]=1: i est une des cases
	// ïṡẄ jouer. Si matricePlateau[i]=0 la case n'est pas accessible ou n'est pas le chemin
	// le plus long.
	// Sachant que pour moi les cases a,b,c et d par rapport a un pion 1 sont : a b
	// (Ca me parait juste plus clair si on les appelle comme ca plutot que
	// matricePlateau[i-2][j-2] etc...)
	// 2 2
	// 1
	// 2 2
	// d c
	public Vector<Integer> CasesAJouer(int i, int j, int couleurJeton) {
		int k2 = 1, k = 2, a = 0, b = 0, c = 0, d = 0, s = 0, ja;
		if (couleurJeton == 1)
			ja = 2;
		else
			ja = 1;
		boolean n = true;
		Vector<Integer> nul = new Vector<Integer>();
		Vector<Integer> max = new Vector<Integer>();

		if (PeutManger(i, j, couleurJeton)) {
			if (matricePlateau[i][j] < 10) // Au cas ou on a un pion
			{
				if ((i + k <= 9 && j - k >= 0)) {
					if ((matricePlateau[i + k - 1][j - k + 1] == ja || matricePlateau[i + k - 1][j - k + 1] == ja * 10) && matricePlateau[i + k][j - k] == 0) {
						nul.add(i + k - 1);
						nul.add(j - k + 1);
						a = 1 + MaxPrises(i + k, j - k, nul, (matricePlateau[i][j] > 9), couleurJeton);
					}
				}
				if ((i + k <= 9 && j + k <= 9)) {
					if ((matricePlateau[i + k - 1][j + k - 1] == ja || matricePlateau[i + k - 1][j + k - 1] == ja * 10) && matricePlateau[i + k][j + k] == 0) {
						nul.add(i + k - 1);
						nul.add(j + k - 1);
						b = 1 + MaxPrises(i + k, j + k, nul, (matricePlateau[i][j] > 9), couleurJeton);
					}
				}
				if ((i - k >= 0 && j + k <= 9)) {
					if ((matricePlateau[i - k + 1][j + k - 1] == ja || matricePlateau[i - k + 1][j + k - 1] == ja * 10) && matricePlateau[i - k][j + k] == 0) {
						nul.add(i - k + 1);
						nul.add(j + k - 1);
						c = 1 + MaxPrises(i - k, j + k, nul, (matricePlateau[i][j] > 9), couleurJeton);
					}
				}
				if ((i - k >= 0 && j - k >= 0)) {
					if ((matricePlateau[i - k + 1][j - k + 1] == ja || matricePlateau[i - k + 1][j - k + 1] == ja * 10) && matricePlateau[i - k][j - k] == 0) {
						nul.add(i - k + 1);
						nul.add(j - k + 1);
						d = 1 + MaxPrises(i - k, j - k, nul, (matricePlateau[i][j] > 9), couleurJeton);
					}
				}
				s = max(a, max(b, max(c, d)));
				if ((i + k <= 9 && j - k >= 0)) {
					if (a == s && (matricePlateau[i + k - 1][j - k + 1] == ja || matricePlateau[i + k - 1][j - k + 1] == ja * 10)
							&& matricePlateau[i + k][j - k] == 0) {
						max.add(i + k);
						max.add(j - k);
					}
				}
				if ((i + k <= 9 && j + k <= 9)) {
					if (b == s && (matricePlateau[i + k - 1][j + k - 1] == ja || matricePlateau[i + k - 1][j + k - 1] == ja * 10)
							&& matricePlateau[i + k][j + k] == 0) {
						max.add(i + k);
						max.add(j + k);

					}
				}
				if ((i - k >= 0 && j + k <= 9)) {
					if (c == s && (matricePlateau[i - k + 1][j + k - 1] == ja || matricePlateau[i - k + 1][j + k - 1] == ja * 10)
							&& matricePlateau[i - k][j + k] == 0) {
						max.add(i - k);
						max.add(j + k);
					}
				}
				if ((i - k >= 0 && j - k >= 0)) {
					if (d == s && (matricePlateau[i - k + 1][j - k + 1] == ja || matricePlateau[i - k + 1][j - k + 1] == ja * 10)
							&& matricePlateau[i - k][j - k] == 0) {
						max.add(i - k);
						max.add(j - k);
					}
				}
			}
			// *********pour les dames**********
			else {
				k = 1;

				s = MaxPrises(i, j, nul, true, couleurJeton);

				nul = new Vector<Integer>();
				if (s > 0) {
					while (appartientPlateau(i + k, j + k) && n) {
						k2 = 1;
						if ((matricePlateau[i + k][j + k] == ja) || (matricePlateau[i + k][j + k] == ja * 10)) {
							while (appartientPlateau(i + k + k2, j + k + k2) && matricePlateau[i + k + k2][j + k + k2] == 0) {
								nul.add(i + k);
								nul.add(j + k);
								a = max(a, MaxPrises(i + k + k2, j + k + k2, nul, true, couleurJeton));
								nul = new Vector<Integer>();
								nul.add(i + k);
								nul.add(j + k);
								n = false;
								k2++;
							}
							if (!(appartientPlateau(i + k + 1, j + k + 1) && matricePlateau[i + k + 1][j + k + 1] == 0))
								n = false;
							else
								a++;
						} else if (matricePlateau[i + k][j + k] == couleurJeton || matricePlateau[i + k][j + k] == couleurJeton * 10) {
							n = false;
						} else {
							k++;
						}
					}
					if (a == s) {
						k++;
						while (appartientPlateau(i + k, j + k) && matricePlateau[i + k][j + k] == 0) {
							if (MaxPrises(i + k, j + k, nul, true, couleurJeton) == s - 1) {
								max.add(i + k);
								max.add(j + k);
							}
							k++;
						}
					}
					n = true;
					k = 1;
					nul = new Vector<Integer>();
					while (appartientPlateau(i + k, j - k) && n) {
						k2 = 1;
						if ((matricePlateau[i + k][j - k] == ja) || (matricePlateau[i + k][j - k] == ja * 10)) {
							while (appartientPlateau(i + k + k2, j - k - k2) && matricePlateau[i + k + k2][j - k - k2] == 0) {
								nul.add(i + k);
								nul.add(j - k);
								b = max(b, MaxPrises(i + k + k2, j - k - k2, nul, true, couleurJeton));
								nul = new Vector<Integer>();
								nul.add(i + k);
								nul.add(j - k);
								n = false;
								k2++;
							}
							if (!(appartientPlateau(i + k + 1, j - k - 1) && matricePlateau[i + k + 1][j - k - 1] == 0))
								n = false;
							else
								b++;
						} else if (matricePlateau[i + k][j - k] == couleurJeton || matricePlateau[i + k][j - k] == couleurJeton * 10) {
							n = false;
						} else {
							k++;
						}
					}
					if (b == s) {
						k++;
						while (appartientPlateau(i + k, j - k) && matricePlateau[i + k][j - k] == 0) {
							if (MaxPrises(i + k, j - k, nul, true, couleurJeton) == s - 1) {
								max.add(i + k);
								max.add(j - k);
							}
							k++;
						}
					}
					n = true;
					k = 1;
					nul = new Vector<Integer>();
					while (appartientPlateau(i - k, j + k) && n) {
						k2 = 1;
						if ((matricePlateau[i - k][j + k] == ja) || (matricePlateau[i - k][j + k] == ja * 10)) {
							while (appartientPlateau((i - k) - k2, j + k + k2) && matricePlateau[(i - k) - k2][j + k + k2] == 0) {
								nul.add(i - k);
								nul.add(j + k);
								c = max(c, MaxPrises((i - k) - k2, (j + k) + k2, nul, true, couleurJeton));
								nul = new Vector<Integer>();
								nul.add(i - k);
								nul.add(j + k);
								n = false;
								k2++;
							}
							if (!(appartientPlateau((i - k) - 1, j + k + 1) && matricePlateau[(i - k) - 1][j + k + 1] == 0))
								n = false;
							else
								c++;
						} else if (matricePlateau[i - k][j + k] == couleurJeton || matricePlateau[i - k][j + k] == couleurJeton * 10) {
							n = false;
						} else {
							k++;
						}
					}
					if (c == s) {
						k++;
						while (appartientPlateau(i - k, j + k) && matricePlateau[i - k][j + k] == 0) {
							if (MaxPrises(i - k, j + k, nul, true, couleurJeton) == s - 1) {
								max.add(i - k);
								max.add(j + k);
							}
							k++;
						}
					}
					n = true;
					k = 1;
					nul = new Vector<Integer>();
					while (appartientPlateau(i - k, j - k) && n) {
						k2 = 1;
						if ((matricePlateau[i - k][j - k] == ja) || (matricePlateau[i - k][j - k] == ja * 10)) {
							while (appartientPlateau(i - k - k2, j - k - k2) && matricePlateau[i - k - k2][j - k - k2] == 0) {
								nul.add(i - k);
								nul.add(j - k);
								d = max(d, MaxPrises(i - k - k2, j - k - k2, nul, true, couleurJeton));
								nul = new Vector<Integer>();
								nul.add(i - k);
								nul.add(j - k);
								n = false;
								k2++;
							}
							if (!(appartientPlateau(i - k - 1, j - k - 1) && matricePlateau[i - k - 1][j - k - 1] == 0))
								n = false;
							else
								d++;
						} else if (matricePlateau[i - k][j - k] == couleurJeton || matricePlateau[i - k][j - k] == couleurJeton * 10) {
							n = false;
						} else {
							k++;
						}
					}
					if (d == s) {
						k++;
						while (appartientPlateau(i - k, j - k) && matricePlateau[i - k][j - k] == 0) {
							if (MaxPrises(i - k, j - k, nul, true, couleurJeton) == s - 1) {
								max.add(i - k);
								max.add(j - k);
							}
							k++;
						}
					}
				} else {
					while (appartientPlateau(i + k, j + k) && matricePlateau[i + k][j + k] == 0) {
						max.add(i + k);
						max.add(j + k);
						k++;
					}
					k = 1;
					while (appartientPlateau(i + k, j - k) && matricePlateau[i + k][j - k] == 0) {
						max.add(i + k);
						max.add(j - k);
						k++;
					}
					k = 1;
					while (appartientPlateau(i - k, j + k) && matricePlateau[i - k][j + k] == 0) {
						max.add(i - k);
						max.add(j + k);
						k++;
					}
					k = 1;
					while (appartientPlateau(i - k, j - k) && matricePlateau[i - k][j - k] == 0) {
						max.add(i - k);
						max.add(j - k);
						k++;
					}
				}
			}
			return max;
		} else {
			return CasesDispo(i, j, couleurJeton);
		}
	}

	// Retourne les cases disponible pour un pion qui ne peut pas manger
	// d'adversaire.
	public Vector<Integer> CasesDispo(int i, int j, int couleurJeton) {
		boolean n = true, o = true, p = true, q = true;
		int k;
		if (couleurJeton == 1)
			k = -1;
		else
			k = 1;
		Vector<Integer> m = new Vector<Integer>();
		if (matricePlateau[i][j] > 2) { // Pour les dames
			while (k < 10 && k > -10) {
				if (i + k < 10 && j + k < 10 && i + k >= 0 && j + k >= 0) {
					if (matricePlateau[i + k][j + k] == 0 && n) {
						m.add(i + k);
						m.add(j + k);
					} else {
						n = false;
					}
				}
				if (i + k < 10 && j - k > 0 && i + k >= 0 && j - k < 10) {
					if (matricePlateau[i + k][j - k] == 0 && o) {
						m.add(i + k);
						m.add(j - k);
					} else {
						o = false;
					}
				}
				if (i - k > 0 && j + k < 10 && i - k < 10 && j + k >= 0) {
					if (matricePlateau[i - k][j + k] == 0 && p) {
						m.add(i - k);
						m.add(j + k);
					} else {
						p = false;
					}
				}
				if (i - k > 0 && j - k > 0 && i - k < 10 && j - k < 10) {
					if (matricePlateau[i - k][j - k] == 0 && q) {
						m.add(i - k);
						m.add(j - k);
					} else {
						q = false;
					}
				}
				if (couleurJeton == 1) {
					k--;
				} else {
					k++;
				}
			}
		} else {
			if (couleurJeton == 1) {
				if (j + 1 < 10 && matricePlateau[i + k][j + 1] == 0) {
					m.add(i + k);
					m.add(j + 1);
				} // Pour les pions du joueur 1
				if (j - 1 >= 0 && matricePlateau[i + k][j - 1] == 0) {
					m.add(i + k);
					m.add(j - 1);
				}
			} else {
				if (j + 1 < 10 && matricePlateau[i + k][j + 1] == 0) {
					m.add(i + k);
					m.add(j + 1);
				} // et du joueur 2
				if (j - 1 >= 0 && matricePlateau[i + k][j - 1] == 0) {
					m.add(i + k);
					m.add(j - 1);
				}
			}
		}
		return m;
	}

	// Execute le pion mangïṡẄ en fonction de la case de depart d'un pion et de
	// sa case d'arrivïṡẄe et determine si le pion a bien mangïṡẄ
	public boolean PionMange(int i, int j, int i2, int j2, int couleurJeton, Joueur joueur1, Joueur joueur2) {
		int ir, jr;
		int k = 1, ja;
		if (couleurJeton == 1) {
			ja = 2;
		} else {
			ja = 1;
		}
		if (matricePlateau[i2][j2] == couleurJeton) { // Pour les pions
			if (i2 - 1 == i || i2 + 1 == i) {
				return false;
			} else {
				if (i2 > i)
					ir = i2 - 1;
				else
					ir = i2 + 1;
				if (j2 > j)
					jr = j2 - 1;
				else
					jr = j2 + 1;

				ircopie = ir;
				jrcopie = jr;
				if (matricePlateau[ir][jr] < 10)
					if (couleurJeton == 1)
						joueur2.nombrePion -= 1;
					else
						joueur1.nombrePion -= 1;
				else if (couleurJeton == 1)
					joueur2.nombreDame -= 1;
				else
					joueur1.nombreDame -= 1;
				matricePlateau[ir][jr] = 0;
				return true;
			}
		} else {
			k = 1; // Pour les dames
			{
				while (i2 - k > i && j2 - k > j) {
					if (matricePlateau[i2 - k][j2 - k] == ja || matricePlateau[i2 - k][j2 - k] == ja * 10) {
						if (matricePlateau[i2 - k][j2 - k] < 10) {
							if (couleurJeton == 1)
								joueur2.nombrePion -= 1;
							else
								joueur1.nombrePion -= 1;
						} else {
							if (couleurJeton == 1)
								joueur2.nombreDame -= 1;
							else
								joueur1.nombreDame -= 1;
						}
						matricePlateau[i2 - k][j2 - k] = 0;
						ircopie = i2 - k;
						jrcopie = j2 - k;


						return true;
					}
					k++;
				}
			}
			{
				while (i2 - k > i && j2 + k < j) {
					if (matricePlateau[i2 - k][j2 + k] == ja || matricePlateau[i2 - k][j2 + k] == ja * 10) {
						if (matricePlateau[i2 - k][j2 + k] < 10) {
							if (couleurJeton == 1)
								joueur2.nombrePion -= 1;
							else
								joueur1.nombrePion -= 1;
						} else {
							if (couleurJeton == 1)
								joueur2.nombreDame -= 1;
							else
								joueur1.nombreDame -= 1;
						}
						matricePlateau[i2 - k][j2 + k] = 0;
						ircopie = i2 - k;
						jrcopie = j2 + k;

						return true;
					}
					k++;
				}
			}
			{
				while (i2 + k < i && j2 - k > j) {
					if (matricePlateau[i2 + k][j2 - k] == ja || matricePlateau[i2 + k][j2 - k] == ja * 10) {
						if (matricePlateau[i2 + k][j2 - k] < 10) {
							if (couleurJeton == 1)
								joueur2.nombrePion -= 1;
							else
								joueur1.nombrePion -= 1;
						} else {
							if (couleurJeton == 1)
								joueur2.nombreDame -= 1;
							else
								joueur1.nombreDame -= 1;
						}
						matricePlateau[i2 + k][j2 - k] = 0;
						ircopie = i2 + k;
						jrcopie = j2 - k;

						return true;
					}
					k++;
				}
			}
			{
				while (i2 + k < i && j2 + k < j) {
					if (matricePlateau[i2 + k][j2 + k] == ja || matricePlateau[i2 + k][j2 + k] == ja * 10) {
						if (matricePlateau[i2 + k][j2 + k] < 10) {
							if (couleurJeton == 1)
								joueur2.nombrePion -= 1;
							else
								joueur1.nombrePion -= 1;
						} else {
							if (couleurJeton == 1)
								joueur2.nombreDame -= 1;
							else
								joueur1.nombreDame -= 1;
						}
						matricePlateau[i2 + k][j2 + k] = 0;
						ircopie = i2 + k;
						jrcopie = j2 + k;

						return true;
					}
					k++;
				}
			}
		}

		return false;
	}

	// Creer une dame
	public void DameCreee(int i, int j, int couleurJeton, Joueur joueur1, Joueur joueur2) {
		if ((i == 0 && couleurJeton == 1) && (matricePlateau[i][j] != 10)) {
			matricePlateau[i][j] = 10;
			joueur1.nombreDame += 1;
			joueur1.nombrePion -= 1;

		}
		if ((i == 9 && couleurJeton == 2) && (matricePlateau[i][j] != 20)) {
			matricePlateau[i][j] = 20;
			joueur2.nombreDame += 1;
			joueur2.nombrePion -= 1;

		}
	}

	// Affiche les choix de pions a jouer ou les cases disponibles a partir d'un
	// pion
	public void AfficheChoix(Vector<Integer> q, int couleurJeton, String pseudo1, String pseudo2) {
		int i = 0;
		if (couleurJeton == 1) {
			System.out.println(pseudo1 + ", voici les pions/cases que vous pouvez jouer:");
		} else {
			System.out.println(pseudo2 + ", voici les pions/cases que vous pouvez jouer:");
		}
		while (i < q.size()) {
			System.out.print(q.elementAt(i));
			i = i + 1;
			if (i % 2 == 0)
				System.out.print(";");
			else
				System.out.print(" ");
		}
		System.out.println("");
	}

	// Renvoit le pion/la case choisie par le joueur
	public Vector<Integer> Choix(Vector<Integer> q) {
		Vector<Integer> pm = new Vector<Integer>();
		int m = 10, n = 10;
		System.out.print("entrez votre choix de pion/case (la ligne puis la colonne):");
		while (!(q.contains(m)) || !(q.contains(n))) {
			m = sc.nextInt();
			n = sc.nextInt();
			if (!(q.contains(m)) || !(q.contains(n))) {
				System.out.println("Erreur, entrez des coordonnïṡẄes valides");
			}
		}
		pm.add(m);
		pm.add(n);
		return pm;
	}

	public boolean EnDanger(int i, int j, int couleurJeton) { // Test si le pion va ÃẂtre mangÃ©
		int k = 1, ja = 2;
		if (couleurJeton == 2)
			ja = 1;
		boolean b = false, r = true;
		if (appartientPlateau(i + 1, j + 1) && appartientPlateau(i - 1, j - 1) && matricePlateau[i + 1][j + 1] == ja
				&& matricePlateau[i - 1][j - 1] == 0) {
			b = true;
		}
		if (appartientPlateau(i - 1, j + 1) && appartientPlateau(i + 1, j - 1) && matricePlateau[i - 1][j + 1] == ja
				&& matricePlateau[i + 1][j - 1] == 0) {
			b = true;
		}
		if (appartientPlateau(i + 1, j - 1) && appartientPlateau(i - 1, j + 1) && matricePlateau[i + 1][j - 1] == ja
				&& matricePlateau[i - 1][j + 1] == 0) {
			b = true;
		}
		if (appartientPlateau(i - 1, j - 1) && appartientPlateau(i + 1, j + 1) && matricePlateau[i - 1][j - 1] == ja
				&& matricePlateau[i + 1][j + 1] == 0) {
			b = true;
		} else
			while (k < 10 && r) {
				if (appartientPlateau(i + k, j + k)) {
					if (matricePlateau[i + k][j + k] != ja * 10 || matricePlateau[i + k][j + k] != 0) {
						r = false;
					} else {
						if (matricePlateau[i + k][j + k] != ja * 10 && matricePlateau[i - 1][j - 1] == 0) {
							b = true;
						}
					}
				}
				if (appartientPlateau(i - k, j + k)) {
					if (matricePlateau[i - k][j + k] != ja * 10 || matricePlateau[i - k][j + k] != 0) {
						r = false;
					} else {
						if (matricePlateau[i - k][j + k] != ja * 10 && matricePlateau[i + 1][j - 1] == 0) {
							b = true;
						}
					}
				}
				if (appartientPlateau(i + k, j - k)) {
					if (matricePlateau[i + k][j - k] != ja * 10 || matricePlateau[i + k][j - k] != 0) {
						r = false;
					} else {
						if (matricePlateau[i + k][j - k] != ja * 10 && matricePlateau[i - 1][j + 1] == 0) {
							b = true;
						}
					}
				}
				if (appartientPlateau(i - k, j - k)) {
					if (matricePlateau[i - k][j - k] != ja * 10 || matricePlateau[i - k][j - k] != 0) {
						r = false;
					} else {
						if (matricePlateau[i - k][j - k] != ja * 10 && matricePlateau[i + 1][j + 1] == 0) {
							b = true;
						}
					}
				}
			}
		return b;
	}




	// La fonction de jeu principale, appel�e par le main de la classe Partie
	public void Jeu(int couleurJeton, Joueur joueur1, Joueur joueur2) {
		boolean amange = false; // Bool pour savoir si on fait une rafle
		Vector<Integer> p = new Vector<Integer>(); // CoordonnïṡẄes du pion choisit initialement
		Vector<Integer> p2 = new Vector<Integer>(); // CoordonnïṡẄes de la case d'arrivïṡẄe
		Vector<Integer> q = new Vector<Integer>(); // Pions eligibles
		q = DoitJouer(couleurJeton); // DoitJouer(PeuventManger()) determine les pions qui peuvent jouer
		System.out.println(joueur1.pseudo + " a: " + joueur1.nombrePion + " pions et " + joueur1.nombreDame + " dames");
		System.out.println(joueur2.pseudo + " a: " + joueur2.nombrePion + " pions et " + joueur2.nombreDame + " dames");
		System.out.println();
		// Au moins un pion peut manger un adversaire
		AfficheChoix(q, couleurJeton, joueur1.pseudo, joueur2.pseudo); // Propose au joueur les pions possibles
		if (!(q.isEmpty())) {
			p = Choix(q); // Le joueur en choisit un, la fonction renvoit le tableau de deux entiers
							// correspondant a ses coordonnïṡẄes.
			q = new Vector<Integer>();
			if (matricePlateau[p.elementAt(0)][p.elementAt(1)] >= 10) {
				dm++;
			} else {
				dm = 0;
			}
			do {
				affiche();
				q = CasesAJouer(p.elementAt(0), p.elementAt(1), couleurJeton); // CasesAJouer() determine dans quelles cases le
																		// pion peut aller
				AfficheChoix(q, couleurJeton, joueur1.pseudo, joueur2.pseudo); // Propose au joueur les cases qu'il peut jouer
				p2 = Choix(q); // Le joueur en choisit une, la fonction renvoit le tableau de ses
								// coordonïṡẄes.
				matricePlateau[p2.elementAt(0)][p2.elementAt(1)] = matricePlateau[p.elementAt(0)][p.elementAt(1)];
				matricePlateau[p.elementAt(0)][p.elementAt(1)] = 0;
				amange = PionMange(p.elementAt(0), p.elementAt(1), p2.elementAt(0), p2.elementAt(1), couleurJeton, joueur1,
						joueur2); // PionMange() effectue les eliminations des pions et determine si un pion a
									// ïṡẄtïṡẄ mangïṡẄ
				p = p2;
				p2 = new Vector<Integer>();
			} while (PeutManger(p.elementAt(0), p.elementAt(1), couleurJeton) && amange); // On refait cette boucle si le joueur a
																				// mangïṡẄ un pion et on continue
																				// tant que le pion selectionnïṡẄ
																				// peut manger un pion adverse (tant que
																				// PeutManger() retourne true)
			DameCreee(p.elementAt(0), p.elementAt(1), couleurJeton, joueur1, joueur2);
		} else
			bloque = couleurJeton;

	} // DameCreee() test a la fin de chaque tour si le pion s'est ARRETE sur une case
		// de la derniere ligne, on le transforme alors en dame

	public void reinitPlateau() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if ((i + j) % 2 != 0 & i < 4) // les cases qui ont des pions noir
				{
					matricePlateau[i][j] = 2;
				} else if ((i + j) % 2 != 0 & i > 5) // les cases qui ont des pions blanc
				{
					matricePlateau[i][j] = 1;
				} else
					matricePlateau[i][j] = 0;
			}

		}

	}

}