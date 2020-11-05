package Algorithmes;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class Element implements Comparable<Element> {

	Integer heuristique; // heuritique du noeud/feuille
	Vector<Element> fils = null; // liste des fils
	EtatDamier etatDamier; // etat du damier (etat noeud)
	Type type;// type du noeud (Min ou MAx)

	/// pour SSS*
	Element pere = null; // Pere du noeud
	Element frereDroite = null; // frere à droite du noeud
	////////////////////////

	// constructeur///
	public Element(EtatDamier etatD, Type type) {
		etatDamier = etatD;
		heuristique = etatD.Difference();
		this.type = type;
	}
	/////////////////

	public Vector<Element> getFils() {
		return fils;
	}

	/// Ajout filS //////////////////
	public void addFils(Element e) {

		if (fils == null) {
			fils = new Vector<Element>();
		}

		if (type == Type.Max)
			e.type = Type.Min;

		// Pour SSS
		e.pere = this;
		if (fils.size() != 0)
			fils.get(fils.size() - 1).frereDroite = e;
		///////////

		fils.add(e);
	}
	////////////////////////////////

	/// retourne vrai si c'est un feuille
	public boolean isFeuille() {
		return fils == null;
	}
	/////////////////////////////////////

	////// Teste si le noeud est max ou min
	public boolean isMax() {
		return type == Type.Max;
	}

	//////// Construit une arborescence avec une profodeur défini à partir de l'état
	//////// du noeud (etaDamier)
	public void construireArbre(int profondeur) {
		if (profondeur == 0)
			return;
		// Noir=2 ou blanc=1
		int couleurJeton = (type == Type.Max) ? 2 : 1;

		// liste des coups possibles pour une couleur a partir de l'état courant du
		// damier
		Vector<EtatDamier> v = etatDamier.coupPossible(couleurJeton);
		// pour chaque coup créer un noeud
		v.forEach(plateau -> this.addFils(new Element(plateau, (type == Type.Max) ? type.Min : type.Max)));

		// pour chaque fils créer une sous arboresence
		if (this.fils != null)
			for (Element f : this.fils)
				f.construireArbre(profondeur - 1);
	}

//// affichage console de l'abre
	public void afficherArbre(int profondeur) {
		this.etatDamier.affiche();
		if (this.fils == null)
			return;
		for (Element f : this.fils) {
			System.out.println("\n############################## " + profondeur + " " + type
					+ " ####################################\n");
			System.out.println("\n############################## heurisitique " + f.getHeuristique()
					+ " ####################################\n");
			f.afficherArbre(profondeur + 1);
		}
	}
//////////////////////////////////////

	///// compareTo pour le tri
	@Override
	public int compareTo(Element o) {
		Random random = new Random();
		/// si les heuristqiue sont égaux alors tri aléatoire
		if (this.heuristique.compareTo(o.heuristique) == 0)
			return random.nextBoolean() ? 1 : -1;
		
		///Sinon trier en fonction du type du noeud
		if (isMax()) {
			return this.heuristique.compareTo(o.heuristique);
		}
		return -this.heuristique.compareTo(o.heuristique);
	}
	/////////////////////////////////////////////////////
	
	
	/// trie les fils du noeud de facon croissante ou décroissante en fonction du type
	public void trierFils() {
		if (fils != null)
			Collections.sort(this.fils);
	}
	//////////////////////////////////
	
	
	public Integer getHeuristique() {
		return heuristique;
	}

	public void setHeuristique(Integer heuristique) {
		this.heuristique = heuristique;
	}

	public EtatDamier getEtatDamier() {
		return etatDamier;
	}

	public void setEtatDamier(EtatDamier etatDamier) {
		this.etatDamier = etatDamier;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setFils(Vector<Element> fils) {
		this.fils = fils;
	}

}
