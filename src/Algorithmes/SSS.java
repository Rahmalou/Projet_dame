package Algorithmes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SSS implements Algorithme {
	
	///Fonction pour supprimer les successeurs de s dans G
	public static void supprimerSuccesseur(ArrayList<SSSelement> G,Element s) {
	
		for (int i=0;i<s.fils.size();i++) {
			//si le fils est dans la liste
			if( G.contains(s.fils.elementAt(i))) {
				// si c'est un noeud supprimer successeurs
				if (!s.fils.elementAt(i).isFeuille()) supprimerSuccesseur(G,s.fils.elementAt(i));
				//supprimer fils
				G.remove(s.fils.elementAt(i));
			}
		
		}
	}
		
	
	
	
	public static int algoSSS(Element s) {
		
		ArrayList<SSSelement> G= new ArrayList<SSSelement>();
		
		G.add(new SSSelement(s,true,Integer.MAX_VALUE));
		Collections.sort(G); // on trie a chaque insertion
		
		while (G.get(0).vivant==false) {
			//On prend le premier element de la file
			SSSelement element= G.get(0);
			G.remove(0);
			////////
			
			// si vivant alors
			if(element.vivant==true) {
				//si feuille
				if (element.noeud.isFeuille()) {
					// on insere l'element en résolu (vivant==false)
					G.add(new SSSelement(s,false,Math.min(element.valeur, s.heuristique)));
					Collections.sort(G); // on oublie pas de trier
				}
				// sinon
				else
				{
					///si noeud max on insere tous les fils
					if (element.noeud.type==Type.Max) {
						for(int i=0;i< element.noeud.fils.size();i++) {
							G.add(new SSSelement(element.noeud.fils.elementAt(i),true,element.valeur));
						}
					}
					///sinon (min) on insere le plus a gauche
					else
					{
						G.add(new SSSelement(element.noeud.fils.elementAt(0),true,element.valeur));
					}
					
					Collections.sort(G); 
				}
			}
			//sinon si résolu
			else {
				///si MIN
				if (element.noeud.type==Type.Min) {
					//inserer pere
					G.add(new SSSelement(element.noeud.pere,false,element.valeur));
					//supprimer succeseur
					supprimerSuccesseur(G, element.noeud.pere);
					
				}
				// sinon MAX
				else 
				{
					if (element.noeud.frereDroite!=null) {
						G.add(new SSSelement(element.noeud.frereDroite,false,element.valeur));
					}
					else {
						G.add(new SSSelement(element.noeud.pere,false,element.valeur));
					}
						
				}
				
				Collections.sort(G); 
			}	
			}
		
			return (G.get(0).valeur);
				
			
		}
}


