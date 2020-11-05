package Algorithmes;

public class NegaMax implements Algorithme {

public static void negaMax(Element s) {
		
		if (s.isFeuille()) {
			// inverser l'heuristique si min
			if(s.type==Type.Min) s.heuristique=-s.heuristique;
			return;
		}

		int i = 0;
		while (i < s.fils.size()) {
				negaMax(s.fils.elementAt(i));		
				i++;
		}

		s.trierFils(); // rappel : cette fonction trie de façon croissante ou décroissante en fonction du type du noeud (min ou max) 
		
		//Dans les deux cas(min ou max) prendre la valeur maximum de l'inverse
		s.heuristique=(s.type!=Type.Max)? -s.fils.elementAt(0).heuristique:-s.fils.elementAt(s.fils.size()-1).heuristique;
			
	}
}
