package ghost.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ghost.Ghost;


/**
 * Menu principal du jeu 
 */
public class Home extends JFrame implements ActionListener, MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Bouton un joueur
	 */
	private JButton unJoueur;
	
	/**
	 * Bouton deux joueurs
	 */
	private JButton deuxJoueurs;
	
	/**
	 * Bouton a Propos
	 */
	private JButton aPropos;
	
	/**
	 * Bouton quitter
	 */
	private JButton quitter;
	
	
	/**
	 * Bouton IA contre IA
	 */
	private JButton iavsia;
	
	/**
	 * Conteneur du Panel
	 */
	private JPanel conteneur;


	/**
	 * Constructeur
	 */
	public Home(){
		super("Jeu d'Ghost");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(Case.CASE_LENGTH * 9, Case.CASE_LENGTH * 6);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		initFenetre();
		this.setVisible(true);
		this.setIconImage(Ghost.ICON);
	}
	
	/**
	 * Initialise la fenetre
	 */
	public void initFenetre(){
		
		//Initialise les boutons
		Dimension taille = new Dimension(150, 50);
		unJoueur = new JButton("Un Player");
		unJoueur.setPreferredSize(taille);
		unJoueur.addActionListener(this);
		unJoueur.addMouseListener(this);
		deuxJoueurs = new JButton("Deux Joueurs");
		deuxJoueurs.setPreferredSize(taille);
		deuxJoueurs.addActionListener(this);
		deuxJoueurs.addMouseListener(this);
		aPropos = new JButton("A propos");
		aPropos.addActionListener(this);
		quitter = new JButton("Quitter");
		quitter.addActionListener(this);
		iavsia = new JButton("IA vs IA");
		iavsia.setPreferredSize(taille);
		iavsia.addMouseListener(this);
		iavsia.addActionListener(this);
		
		 
		//Initialise le Jpanel principal
		conteneur = new JPanel();
		conteneur.setLayout(new GridBagLayout());
		this.setContentPane(conteneur);
		
		//Label
		JLabel titre = new JLabel("GHOST");
		titre.setFont(new Font("Dialog", Font.BOLD,50));
		titre.setHorizontalAlignment(JLabel.CENTER);
		
		
		//Positionnement
		GridBagConstraints gbc = new GridBagConstraints();
		
		//positionnement titre
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		conteneur.add(titre, gbc);
		
		//positionnement un Player
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		conteneur.add(unJoueur, gbc);
		
		//postionnement deux joueurs
		gbc.gridx = 2;
		conteneur.add(deuxJoueurs, gbc);
		
		//positionnement en ligne
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		conteneur.add(iavsia, gbc);
		
		
		//positionnement a propos
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		conteneur.add(aPropos, gbc);
		
		//positionnement quitter
		gbc.gridx = 3;
		conteneur.add(quitter, gbc);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();

		if(source == unJoueur){
			this.setVisible(false);
			this.dispose();			
			new Window(this.getX()+ this.getWidth()/2, this.getY() + this.getHeight()/2, 0);

		}
		if(source == deuxJoueurs){
			this.setVisible(false);
			this.dispose();
			new Window(this.getX()+ this.getWidth()/2, this.getY() + this.getHeight()/2);
		}
		if(source == aPropos){
			//new About();
			Window.about();
		}
		if(source == quitter){
			setVisible(false);
			this.dispose();
			 System.exit(0);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void mouseEntered(MouseEvent e) {
	}
	
	public void mouseExited(MouseEvent e) {
	}
	
}

