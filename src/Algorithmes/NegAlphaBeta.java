package Algorithmes;

public class NegAlphaBeta implements Algorithme {
	
	public static int negAlphaBeta(Element s, Integer alpha, Integer beta){
		
		int val=Integer.MIN_VALUE;
		// si s est une feuille alors val= h(s) si Max sinon val=-h(s)
		if (s.isFeuille()) {
			val= (s.type==Type.Max)? s.heuristique:-s.heuristique;
		}
		// sinon
		else {	
			int i=0;
			while (alpha < beta && i < s.fils.size()) {
				val = Math.max(val, -negAlphaBeta(s.fils.elementAt(i), -beta, -alpha));
				alpha=Math.max(alpha, val);
				i++;
			}

	

		}
		//retourner val
		s.heuristique=val;
		return val;
	}

}
