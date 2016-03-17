package ghost.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Affiche le joueur courant
 */
public class CurrentPlayer extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Affichage du joueur blanc
	 */
	private PlayerCase whitePlayer;
	
	/**
	 * Affichage du joueur noir
	 */
	private PlayerCase blackPlayer;

	/**
	 * Reference a la fenetre
	 */
	private Window window;
	
	/**
	 * Constructeur
	 * @param window la fenetre
	 */
	public CurrentPlayer(Window window){
		super();
		this.window = window;
		initContainer();
	}
	
	/**
	 * initialise
	 */
	private void initContainer(){
		whitePlayer = new PlayerCase(true);
		whitePlayer.setPreferredSize(new Dimension(Case.CASE_LENGTH, Case.CASE_LENGTH));
		
		blackPlayer = new PlayerCase(false);
		blackPlayer.setPreferredSize(new Dimension(Case.CASE_LENGTH, Case.CASE_LENGTH));
		
		JLabel textTour = new JLabel("TOUR", JLabel.CENTER);
		textTour.setPreferredSize(new Dimension(Case.CASE_LENGTH * 2, Case.CASE_LENGTH));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//placement joueur blanc
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		this.add(whitePlayer, gbc);
		
		//placement texte Tour
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(textTour, gbc);
		
		//placement joueur noir
		gbc.gridx = 2;
		this.add(blackPlayer, gbc);
	}
	
	/**
	 * MAJ du joueur courant
	 */
	public void update(){
		if(window.getGame().getCurrentPlayer() == null)
			return;
		else if(window.getGame() == null || window.getGame().getCurrentPlayer().getColor().equals("BLANC")){
			whitePlayer.isTour = true;
			blackPlayer.isTour = false;
		}else{
			whitePlayer.isTour = false;
			blackPlayer.isTour = true;
		}
	}
}

/**
 * Permet d affichier le joueur courant
 */
class PlayerCase extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Vrai si c'est le tour de cet element
	 */
	public boolean isTour;
	
	/**
	 * Couleur de l'element
	 */
	private Color color;
	
	/**
	 * Constructeur
	 * @param isWhite
	 */
	public PlayerCase(boolean isWhite){
		super();
		this.color = (isWhite)? Color.white : Color.black;
		this.isTour = isWhite;
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(color);
		if(!isTour){
			g.fillRect(this.getWidth() * 3/8, this.getHeight() * 3/8, this.getWidth() / 4, this.getHeight() / 4);
			g.setColor(Color.GRAY);
			g.drawRect(this.getWidth() * 3/8, this.getHeight() * 3/8, this.getWidth() / 4, this.getHeight() / 4);
		}else{
			g.fillRect(this.getWidth() / 4, this.getHeight() / 4, this.getWidth() / 2, this.getHeight() / 2);
			g.setColor(Color.GRAY);
			g.drawRect(this.getWidth() / 4, this.getHeight() / 4, this.getWidth() / 2, this.getHeight() / 2);
		}
	}
}
