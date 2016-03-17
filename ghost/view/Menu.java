package ghost.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * Barre de menu
 */
public class Menu extends JMenuBar implements ActionListener, MouseListener, ItemListener, ChangeListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Reference a la fenetre
	 */
	private Window Window;
	
	/**
	 * Menu fichier
	 */
	private JMenu Fichier;
	
	/**
	 * Menu option
	 */
	private JMenu Option;
	
	/**
	 * Menu faq
	 */
	private JMenu Faq;
	
	/**
	 * Active/desactive l'aide au deplacement
	 */
	private JCheckBoxMenuItem aideCouleur;
	
	/**
	 * Active/desactive l'aide au deplacement
	 */
	private JCheckBoxMenuItem modeTriche;
	
	
	/**
	 * Action nouvelle partie
	 */
	private JMenuItem nouvellePartie;
	
	/**
	 * Action quitter
	 */
	private JMenuItem fermer;
	
	/**
	 * Action sauvegarder
	 */
	private JMenuItem sauvegarder;
	
	/**
	 * Action charger une partie
	 */
	private JMenuItem charger;
	
	/**
	 * Action retour menu principal
	 */
	private JMenuItem menuPrincipal;
	
	/**
	 * Action lance un menu a propos
	 */
	private JMenuItem about;
	
	/**
	 * Action lance un menu d aide
	 */
	private JMenuItem help;

	/**
	 * Constructeur
	 * @param Window reference de la Window
	 */
	public Menu(Window Window){
		this.Window = Window;
		Fichier = new JMenu("Fichier");
		Option = new JMenu("Option");
		Faq = new JMenu("Faq");
		nouvellePartie = new JMenuItem("Nouvelle Partie");
		fermer = new JMenuItem("Fermer");
		sauvegarder = new JMenuItem("Sauvegarder");
		charger = new JMenuItem("Charger");
		menuPrincipal = new JMenuItem("Menu Principal");
		about = new JMenuItem("A Propos");
		help = new JMenuItem("Help");

		aideCouleur = new JCheckBoxMenuItem("Aide Couleur", true);
		modeTriche = new JCheckBoxMenuItem("Mode triche", false);

		
		nouvellePartie.addActionListener(this);
		fermer.addActionListener(this);
		sauvegarder.addActionListener(this);
		charger.addActionListener(this);
		menuPrincipal.addActionListener(this);
		aideCouleur.addItemListener(this);
		modeTriche.addItemListener(this);
		about.addActionListener(this);
		help.addActionListener(this);

		
		this.Fichier.add(nouvellePartie);
		this.Fichier.addSeparator();
		this.Fichier.add(sauvegarder);
		this.Fichier.add(charger);	
		this.Fichier.addSeparator();
		this.Fichier.add(menuPrincipal);
		this.Fichier.add(fermer);
		
		this.Option.add(aideCouleur);
		this.Option.add(modeTriche);

		this.Faq.add(about);
		this.Faq.add(help);

		this.add(Fichier);
		this.add(Option);
		this.add(Faq);

		
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		
		if(source == nouvellePartie){
			int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment commencer une nouvelle partie ?" , "Confirmation",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(choix != JOptionPane.NO_OPTION && choix != JOptionPane.CLOSED_OPTION)
			Window.getGame().reset();
		}
		
		if(source == fermer){
			System.exit(0);
		}
		
		if(source == menuPrincipal){
			int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment retourner au menu principal ?" , "Confirmation",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(choix != JOptionPane.NO_OPTION && choix != JOptionPane.CLOSED_OPTION ){
				Window.getGame().getWindow().setVisible(false);
				Window.getGame().getWindow().dispose();
				new Home();
			}
		}
		
		if(source == about){
			ghost.view.Window.about();
		}
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getSource();
		if(source == aideCouleur){
			int etat = e.getStateChange();
			System.out.println(etat);
			if( etat == ItemEvent.SELECTED ){
				Window.getGrid().setShowReachableCases(true);	
			}
			else{ 
				Window.getGrid().setShowReachableCases(false);
			}
		}
		
		if(source == modeTriche){
			int etat = e.getStateChange();
			System.out.println(etat);
			if( etat == ItemEvent.SELECTED ){
				Window.getGrid().setShowType(true);	
			}
			else{ 
				Window.getGrid().setShowType(false);
			}
		}
		
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
	}
}
		
	