package Algorithmes;

public class AlphaBeta implements Algorithme {

	/// Algorithme AlphaBeta

	public static Integer alphaBeta(Element s, Integer alpha, Integer beta) {

		// Si feuille retourner heuristique
		if (s.isFeuille())
			return s.heuristique;
		// Si max
		if (s.isMax()) {
			int i = 0;
			while (alpha < beta && i < s.fils.size()) {
				// mettre a jour alpha
				alpha = Math.max(alpha, alphaBeta(s.fils.elementAt(i), alpha, beta));
				i++;
			}
			s.heuristique = alpha;
			return alpha;
		// sinon si Min
		} else {
			int i = 0;
			while (alpha < beta && i < s.fils.size()) {
				// mettre a jour beta
				beta = Math.min(beta, alphaBeta(s.fils.elementAt(i), alpha, beta));
				i++;
			}

			s.heuristique = beta;
			return beta;
		}

	}

}
