package Algorithmes;

// element de file
public class SSSelement implements Comparable<SSSelement>{
	
	
	public Element noeud; /// noeud
	public boolean vivant; /// Vivant  si = true sinon résolu
	public Integer valeur; /// valeur
	
	public SSSelement(Element noeud, boolean vivant, int valeur) {
		super();
		this.noeud = noeud;
		this.vivant = vivant;
		this.valeur = valeur;
	}

	@Override
	public int compareTo(SSSelement o) {
		// TODO Auto-generated method stub
		return valeur.compareTo(o.valeur);
	}

}
