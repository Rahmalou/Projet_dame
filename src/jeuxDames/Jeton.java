package jeuxDames;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;


public class Jeton extends JButton {
	
	  private int couleur;
	  private int ligne;
	  private int colonne;
	  private File c1;
	  private File c2;
	  private File c3;
	  private File c4;
	  private File icon;
	  private int CaseSelect;
	  private boolean dame;
	  
	  public Jeton(int c)
	 {
		this.couleur=c;
	    this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
	    this.setBorderPainted(false);
	    choixPions(1);
	    CaseSelect=1;
	    dame=false;
	  }
	      
	  public boolean isDame() 
	  {
		return dame;
	  }

	  public void setDame(boolean dame) 
	{
		this.dame = dame;
		this.repaint();
	}

	  public int getLigne()
	  {
		  return ligne;
	  }
	  
	  public int getColonne()
	  {
		  return colonne;
	  }
	  
	  public void choixPions(int n)
	  {
		  switch(n)
		  {
		  case 1:
			  c1= new File("cases/pion1_4.png");
			  c2= new File("cases/pion2_4.png");
			  c3= new File("cases/dame1_4.png");
			  c4= new File("cases/dame2_4.png");
			  CaseSelect=4;
			  break;
		  case 2:
			  c1= new File("cases/pion1_3.png");
			  c2= new File("cases/pion2_3.png");
			  c3= new File("cases/dame1_3.png");
			  c4= new File("cases/dame2_3.png");
			  CaseSelect=3;
			  break;
		  case 4:
			  c1= new File("cases/pion1_1.png");
			  c2= new File("cases/pion2_1.png");
			  c3= new File("cases/dame1_1.png");
			  c4= new File("cases/dame2_1.png");
			  CaseSelect=1;
			  break;
		  case 3:
			  c1= new File("cases/pion1_2.png");
			  c2= new File("cases/pion2_2.png");
			  c3= new File("cases/dame1_2.png");
			  c4= new File("cases/dame2_2.png");
			  CaseSelect=2;
			  break;
		  }
		  this.repaint();
	  }
	  
	  public int getCP()
	  {
		  return CaseSelect;
	  }
	  
	  public void paintComponent(Graphics g)
{
	    Graphics2D g2d = (Graphics2D)g;
	    switch(couleur)
	    {
	    //case noire
	    case 0:
			try {
			   Image caseNoire = ImageIO.read(new File("cases/caseNoire.png"));
			   g2d.drawImage(caseNoire,0,0,this.getWidth(),this.getHeight(),this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//g2d.setPaint(Color.darkGray);
		    //g2d.fillRect(0, 0, 80, 80);
	    	break;
	    
	    //case blanche
	    case -2:
	    	try {
	    		URL url = getClass().getResource("/dameEv/CaseBlanche.png");
				   Image caseBlanche = ImageIO.read(new File("cases/CaseBlanche.png"));
	    		g2d.drawImage(caseBlanche,0,0,this.getWidth(),this.getHeight(),this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
	    	//g2d.setPaint(Color.white);
			//g2d.fillRect(0, 0, 80, 80);
			break;
		
		//pion blanc
	    case 1:	
	    	
	    	try {	    		
				   Image pionBlanc = ImageIO.read(c1);
				   g2d.drawImage(pionBlanc,0,0,this.getWidth(),this.getHeight(),this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	/*g2d.setPaint(Color.darkGray);
		    g2d.fillRect(0, 0, 80, 80);
		    g2d.setPaint(Color.white);
		    g2d.fillOval(this.getWidth()/4, this.getHeight()/4, this.getWidth()/2, this.getHeight()/2);
		    if(this.isDame())
		    {
		    g2d.setPaint(Color.RED);
		    g2d.drawOval(this.getWidth()/4, this.getHeight()/4, this.getWidth()/2, this.getHeight()/2);
		    }*/
	    	break;
	    	
	    //pion noir
	    case 2:
	    	try {
				   Image pionNoire = ImageIO.read(c2);
				   g2d.drawImage(pionNoire,0,0,this.getWidth(),this.getHeight(),this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
	    	/*g2d.setPaint(Color.darkGray);
		    g2d.fillRect(0, 0, 80, 80);
		    g2d.setPaint(Color.black);
		    g2d.fillOval(this.getWidth()/4, this.getHeight()/4, this.getWidth()/2, this.getHeight()/2);
		    if(this.isDame())
		    {
		    g2d.setPaint(Color.YELLOW);
		    g2d.drawOval(this.getWidth()/4, this.getHeight()/4, this.getWidth()/2, this.getHeight()/2);
		    }*/
	    	break;
	    case 10:
	    	try {
				   Image dameBlanc = ImageIO.read(c3);
				   g2d.drawImage(dameBlanc,0,0,this.getWidth(),this.getHeight(),this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	/*g2d.setPaint(Color.darkGray);
		    g2d.fillRect(0, 0, 80, 80);
		    g2d.setPaint(Color.white);
		    g2d.fillOval(this.getWidth()/4, this.getHeight()/4, this.getWidth()/2, this.getHeight()/2);
		    g2d.setPaint(Color.RED);
		    g2d.drawOval(this.getWidth()/4, this.getHeight()/4, this.getWidth()/2, this.getHeight()/2);*/
	    	break;
	    case 20:
	    	try {
				   Image dameNoire = ImageIO.read(c4);
				   g2d.drawImage(dameNoire,0,0,this.getWidth(),this.getHeight(),this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
	    	/*g2d.setPaint(Color.darkGray);
		    g2d.fillRect(0, 0, 80, 80);
		    g2d.setPaint(Color.black);
		    g2d.fillOval(this.getWidth()/4, this.getHeight()/4, this.getWidth()/2, this.getHeight()/2);
		    g2d.setPaint(Color.YELLOW);
		    g2d.drawOval(this.getWidth()/4, this.getHeight()/4, this.getWidth()/2, this.getHeight()/2);*/
		    break;
	    }
	    
	   
	    	
	    
	  }        

	  public void setC(int c)
	  {
		  couleur=c;
		  this.repaint();
	  }
	  
	  public void setBord(int b)
	  {
		  switch(b)
		  {
		  case 0:
			  this.setBorderPainted(false);
			  break;
		  case 1:
			  this.setBorderPainted(true);
			  break;
		  }
		  this.repaint();
	  }
	  
	  public int getC()
	  {
		  return couleur;
	  }
	
	  public boolean hasPionNoir()
	  {
		  return getC()==2;
	  }
	  
	  public boolean hasPionBlanc()
	  {
		  return getC()==1;
	  }
	  
	  public boolean hasPion()
	  {
		  return (getC()==1 || getC()==2);
	  }
	  
	  public boolean isNoire()
	  {
		  return (getC()==1 || getC()==2 || getC()==-1);
	  }
	  
	  public String toString()
	  {
		  return "Case (" + ligne + "," + colonne + ")";
	  }
	  
	  public void setCoor(int l,int c)
	  {
		  ligne=l;
		  colonne=c;
	  }
	  
//***************************************************************************************************
	  //M�thode appel�e lors du clic de souris
	  public void mouseClicked(MouseEvent event) { }

	  //M�thode appel�e lors du survol de la souris
	  public void mouseEntered(MouseEvent event) { }

	  //M�thode appel�e lorsque la souris sort de la zone du bouton
	  public void mouseExited(MouseEvent event) { }

	  //M�thode appel�e lorsque l'on presse le bouton gauche de la souris
	  public void mousePressed(MouseEvent event) { }

	  //M�thode appel�e lorsque l'on rel�che le clic de souris
	  public void mouseReleased(MouseEvent event) { }       
}
