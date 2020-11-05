package jeuxDames;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZDialog extends JDialog {

	private int lvl;
	private boolean sendData;
	private JLabel icon;
	protected JTextField pseudo1,pseudo2;
	protected JComboBox niveau;
	protected JComboBox niveau2;
	protected JComboBox algoIA1;
	protected JComboBox algoIA2;
	
	private int joueContre;
	
	public ZDialog(JFrame parent, String title, boolean modal,int joueContre)
	{
		super(parent,title,modal);
		this.joueContre=joueContre;
		this.setSize(400,300);
		this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    this.initComponent();
	}
	
	public ZDialog()
	{
		
	}
	
	
	
	private void initComponent()
	{
		//icone
		ImageIcon monIcone = new ImageIcon("cases/icone.png");
		icon = new JLabel();
		icon.setIcon(monIcone);
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.WHITE);
	    panIcon.setLayout(new BorderLayout());
	    panIcon.add(icon,BorderLayout.CENTER);
		
		//pseudo joueur 1
	    JPanel panP1 = new JPanel();
	    if(joueContre==1 || joueContre==2){
	
		panP1.setBackground(Color.WHITE);
		panP1.setPreferredSize(new Dimension(220,60));
		pseudo1 = new JTextField();
		pseudo1.setPreferredSize(new Dimension(100,25));
		panP1.setBorder(BorderFactory.createTitledBorder("pseudo du joueur 1"));
		panP1.add(pseudo1);
	    }
	    else {
			panP1.setBackground(Color.WHITE);
			panP1.setPreferredSize(new Dimension(220,60));
			panP1.setBorder(BorderFactory.createTitledBorder("niveau de l'IA"));
			niveau2 = new JComboBox();
			niveau2.addItem("faible");
			niveau2.addItem("moyen");
			niveau2.addItem("pro");
			algoIA2= new JComboBox();
			algoIA2.addItem("negaMax");
			algoIA2.addItem("alphaBeta");
			algoIA2.addItem("negaAlphaBeta");
			algoIA2.addItem("SSS*");
			panP1.add(niveau2);
			panP1.add(algoIA2);

	    }
		JPanel content = new JPanel();
		//pseudo joueur 2 si mode de jeu est "joueur contre joueur"
		if(joueContre==2)
		{
			JPanel panP2 = new JPanel();
			panP2.setBackground(Color.WHITE);
			panP2.setPreferredSize(new Dimension(220,60));
			pseudo2 = new JTextField();
			pseudo2.setPreferredSize(new Dimension(100,25));
			panP2.setBorder(BorderFactory.createTitledBorder("pseudo du joueur 2"));
			panP2.add(pseudo2);
			
		    content.setBackground(Color.white);
		    content.add(panP1);
		    content.add(panP2);
		}
		//niveau de l'IA sinon
		else 
		{
			JPanel panN = new JPanel();
			panN.setBackground(Color.WHITE);
			panN.setPreferredSize(new Dimension(220,60));
			panN.setBorder(BorderFactory.createTitledBorder("niveau de l'IA"));
			niveau = new JComboBox();
			niveau.addItem("faible");
			niveau.addItem("moyen");
			niveau.addItem("pro");
			algoIA1= new JComboBox();
			algoIA1.addItem("negaMax");
			algoIA1.addItem("alphaBeta");
			algoIA1.addItem("negaAlphaBeta");
			algoIA1.addItem("SSS*");
			panN.add(niveau);
			panN.add(algoIA1);
	
			content.setBackground(Color.WHITE);
			content.add(panP1);
			content.add(panN);
		}
		
	    
	    JPanel control = new JPanel();
	    JButton ok =  new JButton("OK");
	    
	    ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				 setVisible(false);
			}
	    });
	    JButton cancel = new JButton("Annuler");
	    cancel.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	        setVisible(false);
	      }      
	    });

	    control.add(ok);
	    control.add(cancel);
	    
	    JPanel jpanel = new JPanel();
	    jpanel.setLayout(new BorderLayout());
	    jpanel .add(panIcon, BorderLayout.NORTH);
	    jpanel.setBackground(Color.WHITE);
	    
	    this.getContentPane().setLayout(new BorderLayout());
	    this.getContentPane().add(jpanel, BorderLayout.WEST);
	    this.getContentPane().add(content,BorderLayout.CENTER);
	    this.getContentPane().add(control,BorderLayout.SOUTH);
	}
	
}
