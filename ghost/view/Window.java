package ghost.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ghost.Ghost;
import ghost.model.Game;

/**
 * Represente la fenetre de jeu
 * @author Amadou
 *
 */
public class Window extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Reference au jeu
	 */
	private Game game;
	
	/**
	 * La grille
	 */
	private GameGrid grid;
	
	/**
	 * Le conteneur general
	 */
	private JPanel container;
	
	/**
	 * les pieces prises blanches
	 */
	//private PiecesPrisesGame prisesBlanches;
	private JPanel prisesBlanches;
	
	/**
	 * les pieces prises noires
	 */
	private CatchedPieces prisesNoires;
	
	/**
	 * Zone d information sur le jeu
	 */
	private JTextArea logsPartie;
	
	/**
	 * Affichage du  joueur courant
	 */
	private CurrentPlayer currentPlayer;
	
	/**
	 * Affichage des coordonnees abscisses de la grille
	 */
	private JPanel coordAbscisse;
	
	/**
	 * Affichage des coordonnees ordonnees de la grille
	 */
	private JPanel coordOrdonnee;
	
	/**
	 * Constructeur
	 * @param x position x de la Window
	 * @param y position y de la Window
	 */
	public Window(int x, int y){
		super("Game de Ghost");
		game = new Game(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = new Dimension(Case.CASE_LENGTH * 13, Case.CASE_LENGTH * 10);
		this.setSize(dim);
		this.setMinimumSize(dim);
		initFenetre();
		this.setLocation(x - this.getWidth()/2, y - this.getHeight()/2);
		this.setVisible(true);
		this.setIconImage(Ghost.ICON);
	}
	
	/**
	 * Constructeur
	 * @param x position x de la Window
	 * @param y position y de la Window
	 */
	public Window(int x, int y, int z){
		super("Game de Ghost");
		game = new Game(this, z);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = new Dimension(Case.CASE_LENGTH * 13, Case.CASE_LENGTH * 10);
		this.setSize(dim);
		this.setMinimumSize(dim);
		initFenetre();
		this.setLocation(x - this.getWidth()/2, y - this.getHeight()/2);
		this.setVisible(true);
		this.setIconImage(Ghost.ICON);
	}
	
	/**
	 * Initialise les variables et positionne les elements sur la Window
	 */
	private void initFenetre(){
		
		//Creation de tous les conteneurs
		//Conteneur general de la Window
		container = new JPanel();
		container.setPreferredSize(this.getPreferredSize());
		
		
		//Affichage du Player courant
		currentPlayer = new CurrentPlayer(this);
		currentPlayer.setPreferredSize(new Dimension(Case.CASE_LENGTH * 4, Case.CASE_LENGTH));
		
		//Grid du Grid de game
		grid = new GameGrid(this);
		grid.setPreferredSize(new Dimension(Case.CASE_LENGTH * 6, Case.CASE_LENGTH * 6));
		
		//Initialisation des conteneur de pieces prises.
		//Prises blanches
		prisesBlanches = new CatchedPieces(game, true);
		prisesBlanches.setPreferredSize(new Dimension(Case.CASE_LENGTH*2, Case.CASE_LENGTH*6));
		JPanel blancPriseConteneur = new JPanel();
		blancPriseConteneur.add(prisesBlanches);
		blancPriseConteneur.setBorder(BorderFactory.createTitledBorder("Prises blanches"));
		blancPriseConteneur.setPreferredSize(new Dimension(Case.CASE_LENGTH*2, Case.CASE_LENGTH*6));
		
		//Prises noires
		prisesNoires = new CatchedPieces(game, false);
		prisesNoires.setPreferredSize(new Dimension(Case.CASE_LENGTH*2, Case.CASE_LENGTH*6));
		
		JPanel noirPriseConteneur = new JPanel();
		noirPriseConteneur.add(prisesNoires);
		noirPriseConteneur.setBorder(BorderFactory.createTitledBorder("Prises noires"));
		noirPriseConteneur.setPreferredSize(new Dimension(Case.CASE_LENGTH*2, Case.CASE_LENGTH*6));
		
		//Logs de la partie
		logsPartie = new JTextArea();
		logsPartie.setEditable(false);
		logsPartie.setLineWrap(true);
		logsPartie.setMargin(new Insets(5,5,5,5));
		JScrollPane scrollPanel = new JScrollPane(logsPartie);
		scrollPanel.setPreferredSize(new Dimension(Case.CASE_LENGTH * 8, Case.CASE_LENGTH * 1));
		scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		
		
		//Coords
		coordAbscisse = new JPanel();
		coordAbscisse.setLayout(new GridLayout(1, 6));
		for(char i = 'A'; i <= 'F'; i++){
			JLabel c = new JLabel(i+"", JLabel.CENTER);
			c.setPreferredSize(new Dimension(Case.CASE_LENGTH, 10));
			coordAbscisse.add(c);
		}
		coordOrdonnee = new JPanel();
		coordOrdonnee.setLayout(new GridLayout(6, 1));
		for(int i = 6; i >= 1; i--){
			JLabel c = new JLabel(i+"");
			c.setPreferredSize(new Dimension(10, Case.CASE_LENGTH));
			coordOrdonnee.add(c);
		}

		
		//Menu des menus du game
		Menu b = new Menu(this);
		this.setJMenuBar(b);

		
		
		//Positionnement sur le GridBagLayout
		container.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		//Placement de affichage Player courant
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(0, 0, 0, 0);
		container.add(currentPlayer, gbc);
		
		//Placement prise des pieces blanches
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 10, 0, 0);
		container.add(blancPriseConteneur, gbc);
		
		//Placement des ordonnees
		gbc.gridx = 1;
		gbc.gridheight = 1;

		container.add(coordOrdonnee, gbc);
		
		//Placement de la grid
		gbc.gridx = 2;
		gbc.insets = new Insets(0,00,0,00);
		container.add(grid, gbc);
		
		//Placement prise des pieces noires
		gbc.gridx = 3;
		gbc.insets = new Insets(0,00,0,10);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		container.add(noirPriseConteneur, gbc);
		
		//placement des abscisses
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,0,0,10);
		container.add(coordAbscisse, gbc);
		
		//Placement des logs de la partie
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.insets = new Insets(20, 0, 0, 0);
		gbc.gridwidth = 1;
		container.add(scrollPanel, gbc);
		

		this.setContentPane(container);
	}
	
	/**
	 * Ajoute un message au logs de la partie
	 * @param s message
	 */
	public void addLogPartie(String s){
		logsPartie.append(s+"\n");
		logsPartie.setCaretPosition(logsPartie.getDocument().getLength());
	}
	
	/**
	 * Vide les logs de la partie
	 */
	public void clearLogsPartie(){
		logsPartie.setText("");
	}
		
	@Override
	public void repaint(){

		this.grid.updateGrid();
		this.currentPlayer.update();
		super.repaint();
	}
	
	/**
	 * Getter game
	 * @return game
	 */
	public Game getGame(){
		return game;
	}
	
	/**
	 * Getter grid
	 * @return grid
	 */
	public GameGrid getGrid(){
		return grid;
	}
	
	/**
	 * Affiche le nom du gagnant
	 * @param gagnant
	 */
	public void tellEM(String gagnant){
	    JOptionPane.showMessageDialog(null, 
	    		"Victoire du joeur " + gagnant + ".", 
	    		"Fin de Partie", 
	   		JOptionPane.INFORMATION_MESSAGE);
	
	}
	
	/**
	 * Affiche le nom du gagnant
	 * @param gagnant
	 */
	public static void about(){
		
		String text = "<html>" +
				"<h1>GHOST</h1>" +
				"</i>Amadou SY <br />" +
				" Wilfried NJangui <br />" +
				"<i>POO - 2015-2016</i>" +
				"</html> ";
		
	    JOptionPane.showMessageDialog(null,text, 
	   		"A Propos", JOptionPane.INFORMATION_MESSAGE);
	
	}
	
	
	
	
}
