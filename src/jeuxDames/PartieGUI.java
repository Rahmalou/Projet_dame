package jeuxDames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Algorithmes.ALGO;
import Algorithmes.AlphaBeta;
import Algorithmes.Element;
import Algorithmes.EtatDamier;
import Algorithmes.NegAlphaBeta;
import Algorithmes.NegaMax;
import Algorithmes.SSS;
import Algorithmes.Type;

public class PartieGUI {

	private static int jetons[][] = new int[10][10];
	protected static Joueur j1;
	protected static Joueur j2;
	protected static Fenetre fenetre;
	static int n;
	static boolean newGame;
	static ZDialog zd0 = new ZDialog(null, "réglage des IA", true,0);
	static ZDialog zd1 = new ZDialog(null, "réglage des joueurs", true,1);
	static ZDialog zd2 = new ZDialog(null, "réglage des joueurs", true,2);
	

	public static void main(String[] args) throws InterruptedException, IOException {

		boolean rejoue=true;
		while(rejoue)
		{
			j1 = new Joueur();
			j2 = new Joueur();
			rejoue=false;
			fenetre = new Fenetre();
			newGame = true;
			fenetre.initMenu(j1, j2);
			//initMenu();
			n=0;
			String[] d1 = {"nouvelle partie","charger partie"};
			JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();	
		    int option = jop.showOptionDialog(null, "séléctionnez votre choix","charger partie?",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,d1,d1[1]);
		    if(option==0)
		    {
		    	newPartie();
		    }
		    else
		    {
		    	Charger();
		    	dameGui();
		    }
		    
		    fenetre.JLabelArbitre.setText("<html>Apuyez sur Espace pour changer de pions<br>Appuyez sur Entree quand vous aurez fait votre choix</html>");
	 	    while(fenetre.activeClavier){System.out.print("");}
		
	 	    Font font2 = new Font("Arial",Font.BOLD,18);
	 	    fenetre.JLabelArbitre.setFont(font2);
	 	    
		    while(n==0 && newGame)
		    {
		    	deroulementPartie();
		    }
		    
		    fenetre.JLabelJoueur1.setText("<html>"+j1.getpseudo()+"<br>"+j1.getnombrePion()+" jetons"+"<br>"+j1.getnombreDame()+" dames </html>");
	        fenetre.JLabelJoueur2.setText("<html>"+j2.getpseudo()+"<br>"+j2.getnombrePion()+" jetons"+"<br>"+j2.getnombreDame()+" dames </html>");	
	        if(fenetre.numJ==1)
	        {
	        	fenetre.JLabelArbitre.setText("<html>Au tour de "+j1.getpseudo()+"</html>");
	        }
	        else
		   	{
		   		fenetre.JLabelArbitre.setText("<html>Au tour de "+j2.getpseudo()+"</html>");
		   	}
		   
		    if(n==1)
		    {
		    	fenetre.JLabelArbitre.setForeground(new Color(186,48,48));
		    	fenetre.JLabelArbitre.setText("<html>"+j1.pseudo+" a gagnéla partie!"+"<br>Bravo!!!");
		    }
		    else if(n==2)
		    {

		    	fenetre.JLabelArbitre.setForeground(new Color(186,48,48));
		    	fenetre.JLabelArbitre.setText("<html>"+j2.pseudo+" a gagnéla partie!"+"<br>Bravo!!!");
		    }
		    else if(n==3)
		    {
		    	fenetre.JLabelArbitre.setForeground(Color.BLUE);
		    	fenetre.JLabelArbitre.setText("<html>match nul"+"<br>pfff!!!");
		    }
		    Thread.sleep(1300);
		    
		    d1[0]="oui";
		    d1[1]="non";
		    int option4 = jop.showOptionDialog(null, "Voulez-vous rejouer?", "Rejouer?", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, d1, d1[1]);
		    if(option4==0)
		    {
		    	rejoue=true;
		    	fenetre.dispose();
		    }
		    else
		    {
		    	System.exit(0);
		    }
		}
	}

	
	public static void deroulementPartie() throws InterruptedException
	{
		int c=0;
		Vector<Integer> acc=new Vector<Integer>(); 
		acc.add(0);
		acc.add(0);
		acc.add(0);
		acc.add(0);
		if(j1.nombrePion==acc.elementAt(0) && j1.nombreDame==acc.elementAt(1) && j2.nombrePion==acc.elementAt(2) && j2.nombreDame==acc.elementAt(3))
    	{
    		c+=1;
    	}
    	else
    	{
			 c=0; 
			 acc=new Vector<Integer>(); 
			 acc.add(j1.nombrePion); 
			 acc.add(j1.nombreDame); 
			 acc.add(j2.nombrePion); 
			 acc.add(j2.nombreDame);
		 }
		fenetre.JLabelJoueur1.setText("<html>"+j1.getpseudo()+"<br>"+j1.getnombrePion()+" jetons"+"<br>"+j1.getnombreDame()+" dames </html>");
        fenetre.JLabelJoueur2.setText("<html>"+j2.getpseudo()+"<br>"+j2.getnombrePion()+" jetons"+"<br>"+j2.getnombreDame()+" dames </html>");	
        if(fenetre.numJ==1)
        {
        	fenetre.JLabelArbitre.setText("<html>Au tour de "+j1.getpseudo()+"</html>");
        }
        else
	   	{
	   		fenetre.JLabelArbitre.setText("<html>Au tour de "+j2.getpseudo()+"</html>");
	   	}
        
			joue();
	
    	if(fenetre.numJ==1)
    	{
    		fenetre.numJ=2;
    	}
    	else fenetre.numJ=1;
    	n=Gagnant(c,fenetre.plateau.dm);
	}
	

	
	public static void newPartie()
	{
		fenetre.casesJouables.removeAllElements();
		//initMenu();
		fenetre.activeClavier=true;
		fenetre.activeSouris=false;
		fenetre.plateau.reinitPlateau();
		fenetre.casesJouables.removeAllElements();
		fenetre.initBord();
		String[] d1 = {"1 VS 1","1 VS IA","IA VS IA"};
		JOptionPane jop = new JOptionPane();
		jetons=fenetre.plateau.matricePlateau;
    	fenetre.changeDamier(jetons);
	    int option2 = jop.showOptionDialog(null, "mode de jeu","mode de jeu",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,d1,d1[1]);
	    if(option2==0)
        {
	    	j1.setNiveau(0);
	    	j2.setNiveau(0);
	    	j1.setStatut(1);
	    	j2.setStatut(1);
	    	j1.setCouleurPions(1);
	    	j2.setCouleurPions(2);
        	zd2.setVisible(true);
        	
            j1.setPseudo(zd2.pseudo1.getText());
            j2.setPseudo(zd2.pseudo2.getText());
            fenetre.JLabelJoueur1.setText("<html>"+j1.getpseudo()+"<br>"+j1.getnombrePion()+" jetons"+"<br>"+j1.getnombreDame()+" dames </html>");
            fenetre.JLabelJoueur2.setText("<html>"+j2.getpseudo()+"<br>"+j2.getnombrePion()+" jetons"+"<br>"+j2.getnombreDame()+" dames </html>");
        }
        else if(option2 == 1)
        {
        	j1.setStatut(1);
	    	j2.setStatut(2);
	    	j1.setCouleurPions(1);
	    	j2.setCouleurPions(2);
        	zd1.setVisible(true);
        	j1.setPseudo(zd1.pseudo1.getText());
        	j2.setPseudo("IA");
        	
        	if((String)zd1.niveau.getSelectedItem()=="faible"){j2.setNiveau(1);}
        	else if((String)zd1.niveau.getSelectedItem()=="moyen"){j2.setNiveau(2);}
        	else {j2.setNiveau(3);}
        	
        	switch (zd1.algoIA1.getSelectedIndex()) {
        	case 0: j2.setAlgorithme(ALGO.NEGAMAX);break;
        	case 1: j2.setAlgorithme(ALGO.ALPHABETA);break;
        	case 2: j2.setAlgorithme(ALGO.NEGALPHABETA);break;
        	case 3:	j2.setAlgorithme(ALGO.SSS);break;
        	}
        	
        	fenetre.JLabelJoueur1.setText("<html>"+j1.getpseudo()+"<br>"+j1.getnombrePion()+" jetons"+"<br>"+j1.getnombreDame()+" dames </html>");
            fenetre.JLabelJoueur2.setText("<html>"+j2.getpseudo()+" "+(String)zd1.niveau.getSelectedItem()+"<br>"+j2.getnombrePion()+" jetons"+"<br>"+j2.getnombreDame()+" dames </html>");
            j2.setPseudo(j2.getpseudo()+"_"+(String)zd1.niveau.getSelectedItem());
        } else if(option2 == 2) {
        	j1.setStatut(2);
	    	j2.setStatut(2);
	    	j1.setCouleurPions(1);
	    	j2.setCouleurPions(2);
	    	zd0.setVisible(true);
        	j1.setPseudo("IA 1");
        	j2.setPseudo("IA 2");
        	
          	//Niveau IA 1
        	if((String)zd0.niveau2.getSelectedItem()=="faible"){j1.setNiveau(1);}
        	else if((String)zd0.niveau2.getSelectedItem()=="moyen"){j1.setNiveau(2);}
        	else {j1.setNiveau(3);}
        	//algo IA 1
        	switch (zd0.algoIA2.getSelectedIndex()) {
        	case 0: j1.setAlgorithme(ALGO.NEGAMAX);break;
        	case 1: j1.setAlgorithme(ALGO.ALPHABETA);break;
        	case 2: j1.setAlgorithme(ALGO.NEGALPHABETA);break;
        	case 3:	j1.setAlgorithme(ALGO.SSS);break;
        	}
        	
        	//Niveau IA 2
        	if((String)zd0.niveau.getSelectedItem()=="faible"){j2.setNiveau(1);}
        	else if((String)zd0.niveau.getSelectedItem()=="moyen"){j2.setNiveau(2);}
        	else {j2.setNiveau(3);}
        	
        	//algo IA 2
        	switch (zd0.algoIA1.getSelectedIndex()) {
        	case 0: j2.setAlgorithme(ALGO.NEGAMAX);break;
        	case 1: j2.setAlgorithme(ALGO.ALPHABETA);break;
        	case 2: j2.setAlgorithme(ALGO.NEGALPHABETA);break;
        	case 3:	j2.setAlgorithme(ALGO.SSS);break;
        	}
        	
        	 fenetre.JLabelJoueur1.setText("<html>"+j1.getpseudo()+" "+(String)zd0.niveau2.getSelectedItem()+"<br>"+j1.getnombrePion()+" jetons"+"<br>"+j1.getnombreDame()+" dames </html>");
            fenetre.JLabelJoueur2.setText("<html>"+j2.getpseudo()+" "+(String)zd0.niveau.getSelectedItem()+"<br>"+j2.getnombrePion()+" jetons"+"<br>"+j2.getnombreDame()+" dames </html>");
            j1.setPseudo(j1.getpseudo()+"_"+(String)zd0.niveau2.getSelectedItem());
            j2.setPseudo(j2.getpseudo()+"_"+(String)zd0.niveau.getSelectedItem());
        }
	}
	
	public static int Gagnant(int c,int d){
		int nP1,nD1,nP2,nD2;
		nP1=j1.nombrePion;
		nD1=j1.nombreDame;
		nP2=j2.nombrePion;
		nD2=j2.nombreDame;
		if(d>=25) return 3;
		if((nP1==0 && nD1==0) || fenetre.plateau.bloque==1) return 2;
		if((nP2==0 && nD2==0) || fenetre.plateau.bloque==2) return 1;
		if((nD1==1 && nD2==2) || (nD1==2 && nD2==1) || (nD1==1 && nD2==1) && c>=5) return 3;
		if(nD1==1 && nP1==0 && c>=32)
			{if(nD2==3 || (nD2==2 && nP2==1) || (nD2==1 && nP2==2))		//SÂ’il n 'y a plus que trois dames, deux dames et un pion, ou une dame et deux pions contre une dame, la fin de partie sera considérée comme égale lorsque les deux joueurs auront encore jouéchacun 16 coups au maximum.
				return 3;}
		if(nD2==1 && nP2==0 && c>=32)
			{if(nD1==3 || (nD1==2 && nP1==1) || (nD1==1 && nP1==2))
				return 3;}
		if(c==50)
			return 3;
		return 0;
	}
	
	public static void Charger()
	{
		try
		{
			File fi = new File ("partie.txt"); // mettre le chemin absolu du fichier si il le trouve pas
			Scanner scanner = new Scanner (new BufferedReader (new FileReader (fi)));	
			while(true)
			{
				 try                        
			        {
					 	int i=0;
						int j=0;
						for(i=0;i<10;i++)
						{
							for(j=0;j<10;j++)
							{
								jetons[i][j]=scanner.nextInt();
							}
						}	
						fenetre.changeDamier(jetons);
										  
					   j1.pseudo=scanner.next();          
					   j1.statut=scanner.nextInt();  
					   j1.nombrePion=scanner.nextInt();  
					   j1.nombreDame=scanner.nextInt();  
					   j1.CouleurPions=scanner.nextInt();  
					   j1.niveau=scanner.nextInt();  
					   
					   j2.pseudo=scanner.next();          
					   j2.statut=scanner.nextInt();  
					   j2.nombrePion=scanner.nextInt();  
					   j2.nombreDame=scanner.nextInt();  
					   j2.CouleurPions=scanner.nextInt();  
					   j2.niveau=scanner.nextInt();  
					   fenetre.numJ=scanner.nextInt();
			        }
				 catch (NoSuchElementException exception)
			        {
			            break;
			        }
			}	
			scanner.close();
		}
		catch (FileNotFoundException exception)
		{
		    System.out.println ("Erreur fichier");
		}
	}
	
	public static void dameGui()
	{
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				if(fenetre.plateau.matricePlateau[i][j]==10)
				{
					fenetre.jetons[i][j].setDame(true); 
					fenetre.jetons[i][j].setC(10); 
				}
				if(fenetre.plateau.matricePlateau[i][j]==20)
				{
					fenetre.jetons[i][j].setDame(true); 
					fenetre.jetons[i][j].setC(20);
				}
			}
		}
		
	 }
	public static void joue() throws InterruptedException{
		boolean virtuel;
		int n,m = 0;
		boolean amange=false;								//Bool pour savoir si on fait une rafle
		Vector<Integer> p=new Vector<Integer>();			//Coordonn�es du pion choisit initialement
		Vector<Integer> p2=new Vector<Integer>();			//Coordonn�es de la case d'arriv�e
		Vector<Integer> q=new Vector<Integer>();			//Pions eligibles
		Element arbre = null;
		
		if (fenetre.numJ==1)
		{
			if (j1.statut==1)virtuel=false;
			else virtuel=true;
		}
		else
		{
			if(j2.statut==1)virtuel=false;
			else virtuel=true;
		}
		if (virtuel) {
			arbre= new Element(new EtatDamier(EtatDamier.copyMatrice(fenetre.plateau.matricePlateau)),(fenetre.numJ==2)? Type.Max:Type.Min);
			if(fenetre.numJ==1)
				ChoixIA(j1.niveau,j1.algorithme,arbre);
			else
				ChoixIA(j2.niveau,j2.algorithme,arbre);
			arbre.trierFils();
		}
		
		q=fenetre.plateau.DoitJouer(fenetre.numJ);
		if(!(q.isEmpty())){
		if(fenetre.CaseSelect==0)
		{
			for(int i=0;i<q.size();i+=2)
			{
				Jeton j = fenetre.jetons[q.elementAt(i)][q.elementAt(i+1)];
				fenetre.casesJouables.add(j);
			}
			
		}
		
		if (!(virtuel))
		{
			for(int i=0;i<fenetre.casesJouables.size();i++)
			{
				fenetre.casesJouables.elementAt(i).setBord(1);
			}
		while(fenetre.CaseSelect==0){System.out.print("");}
		p.add(fenetre.Ind[0]);
		p.add(fenetre.Ind[1]);			
		}
		
		else 
		{
			Thread.sleep(300);
			
			fenetre.casesJouables.removeAllElements();
	    	fenetre.initBord();
	    	fenetre.CaseSelect++;

			//si IA creer Arbre

			fenetre.Ind[0]=arbre.getFils().get(0).getEtatDamier().getPionJouer().get(0);
			fenetre.Ind[1]=arbre.getFils().get(0).getEtatDamier().getPionJouer().get(1);
			p.add(fenetre.Ind[0]);
			p.add(fenetre.Ind[1]);
		}
		//Le joueur en choisit un, la fonction renvoit le tableau de deux entiers correspondant a ses coordonn�es.
		q=new Vector<Integer>();
		
		do{
			if(amange)fenetre.CaseSelect=1;
			q=fenetre.plateau.CasesAJouer(p.elementAt(0),p.elementAt(1),fenetre.numJ);
			if(fenetre.CaseSelect==1)
			{
				for(int i=0;i<q.size();i+=2)
				{
					Jeton j = fenetre.jetons[q.elementAt(i)][q.elementAt(i+1)];
					fenetre.casesJouables.add(j);
				}					
			}
			if (!(virtuel))
			{
				for(int i=0;i<fenetre.casesJouables.size();i++)
				{
					fenetre.casesJouables.elementAt(i).setBord(1);
				}
			while(fenetre.CaseSelect==1){System.out.print("");}	
			fenetre.plateau.affiche();
			p2.addElement(fenetre.Ind[2]);
			p2.addElement(fenetre.Ind[3]);
			}
			else
			{
				Thread.sleep(300);

				
				fenetre.Ind[2]=arbre.getFils().get(0).getEtatDamier().getCoupJouer().get(0);
				fenetre.Ind[3]=arbre.getFils().get(0).getEtatDamier().getCoupJouer().get(1);
				//choix case
				p2.add(fenetre.Ind[2]);
				p2.add(fenetre.Ind[3]);
				
				p2.addElement(fenetre.Ind[2]);
				p2.addElement(fenetre.Ind[3]);
				Jeton jeton2 = new Jeton(0);
				jeton2.setCoor(fenetre.Ind[2],fenetre.Ind[3]);
				int coul = fenetre.jetons[fenetre.Ind[0]][fenetre.Ind[1]].getC();
		    	fenetre.jetons[fenetre.Ind[0]][fenetre.Ind[1]].setC(0);
		    	fenetre.plateau.matricePlateau[fenetre.Ind[0]][fenetre.Ind[1]]=0;
		    	jeton2.setC(coul);
		    	fenetre.plateau.matricePlateau[jeton2.getLigne()][jeton2.getColonne()]=coul;
		    	fenetre.jetons[fenetre.Ind[2]][fenetre.Ind[3]].setC(coul);
		        fenetre.initBord();
		        fenetre.casesJouables.removeAllElements();
		    	fenetre.CaseSelect=0;
			}
			amange=fenetre.plateau.PionMange(p.elementAt(0),p.elementAt(1),p2.elementAt(0),p2.elementAt(1),fenetre.numJ,j1,j2);		
            fenetre.mange=amange;
			if(amange){
				fenetre.jetons[fenetre.plateau.ircopie][fenetre.plateau.jrcopie].setC(0);
				fenetre.Ind[0]=fenetre.Ind[2];
				fenetre.Ind[1]=fenetre.Ind[3];
			}
			p=p2;
			p2=new Vector<Integer>();
			if (virtuel) {
				arbre= new Element(new EtatDamier(EtatDamier.copyMatrice(fenetre.plateau.matricePlateau)),(fenetre.numJ==2)? Type.Max:Type.Min);
				if(fenetre.numJ==1)
					ChoixIA(j1.niveau,j1.algorithme,arbre);
				else
					ChoixIA(j2.niveau,j2.algorithme,arbre);
				arbre.trierFils();
			
			for (int i=0; i<arbre.getFils().size();i++) {
				System.out.println("filllston " + i +" heuristique : "+ arbre.getFils().get(i).getHeuristique()  );
			}
			System.out.println("///////////////////////////////////:::");
			}
		}
		while(fenetre.plateau.PeutManger(p.elementAt(0),p.elementAt(1),fenetre.numJ) && amange);				//On refait cette boucle si le joueur a mang� un pion et on continue tant que le pion selectionn� peut manger un pion adverse (tant que PeutManger() retourne true)
		fenetre.plateau.DameCreee(p.elementAt(0),p.elementAt(1),fenetre.numJ,j1,j2);
		dameGui();
		}
		else fenetre.plateau.bloque=fenetre.numJ;
			}
	
	
	static void ChoixIA(int niveau,ALGO algorithme,Element arbre) {

		int profondeur=0;
		
		switch (niveau) {
		case 1: profondeur=1; break;
		case 2: profondeur=3; break;
		case 3:	profondeur=4; break;
		}
		arbre.construireArbre(profondeur);
		switch(algorithme) {
		case ALPHABETA:
			AlphaBeta.alphaBeta(arbre, Integer.MIN_VALUE, Integer.MAX_VALUE);
			break;
		case NEGAMAX:
			NegaMax.negaMax(arbre);
			break;
		case NEGALPHABETA:
			NegAlphaBeta.negAlphaBeta(arbre, Integer.MIN_VALUE, Integer.MAX_VALUE);
			break;
		case SSS:
			SSS.algoSSS(arbre);
			break;
		
		}

	}

}


