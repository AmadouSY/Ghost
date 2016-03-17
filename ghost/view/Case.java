package ghost.view;

import ghost.Ghost;
import ghost.model.Piece;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Une case - a ne pas confondre avec une cellule
 */
public class Case extends JPanel implements MouseListener{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Taille d'une case, la taille de l'image d'une piece
	 */
	public static final int CASE_LENGTH = 60;
	
	/**
	 * Etat de la case : normal, selectionee, accessible
	 */
	public enum State{
		INITIAL, SELECTED, REACHABLE
	};
	
	/**
	 * Coordonnee en abscisse
	 */
	protected int x;
	
	/**
	 * Coordonnee en ordonnee
	 */
	protected int y;
	
	/**
	 * Couleur de la piece
	 */
	protected String color;
	
	/**
	 * Boolean vrai si la case contient une piece
	 */
	protected boolean containPiece;
	
	/**
	 * Couleur de fond
	 */
	protected Color backgroundColor;
	
	/**
	 * Etat de la piece
	 */
	protected State state;

	/**
	 * Indique si la piece est selectionne
	 */
	private boolean isSelected;
	

	
	/**
	 * Reference a la fenetre
	 */
	private Window window;
	
	/**
	 * Constructeur
	 * @param x coordonnee en abscisse
	 * @param y coordonnee en ordonnee
	 * @param backgroundColor couleur du fond
	 * @param window la fenetre
	 */
	public Case(int x, int y, Color backgroundColor, Window window){
		this.x = x;
		this.y = y;
		this.backgroundColor = backgroundColor;
		this.containPiece = false;
		this.state = State.INITIAL;
		this.setSize(CASE_LENGTH, CASE_LENGTH);
		this.window = window;
		this.addMouseListener(this);
		this.isSelected = true;
	}
	
	/**
	 * Test si l'etat de la case est SELECTED ou REACHABLE
	 * @return Vrai si selected ou reachable
	 */
	public boolean isSelected(){
		return state.equals(State.SELECTED) || state.equals(State.REACHABLE);
	}
	
	/**
	 * Setter de isSelected
	 * @param isSelected
	 */
	public void setSelected(boolean isSelected){
		this.isSelected = isSelected;
	}
	
	/**
	 * Ajout d une piece dans la case
	 * @param p Piece a placer sur la case
	 */
	public void updateCase(Piece p){
		if(p == null){
			this.color = "";
			this.containPiece = false;
		}else{
			this.color = p.getColor();
			this.containPiece = true;
		}
	}
	
	/**
	 * Getter de l abs x
	 * @return x
	 */
	public int getXTab(){
		return x;
	}
	
	/**
	 * Getter de l'ordonnee y
	 * @return y
	 */
	public int getYTab(){
		return y;
	}
	
	/**
	 * Getter de la couleur de la piece
	 * @return String
	 */
	public String getColor(){
		return color;
	}
	
	/**
	 * Getter de l etat
	 * @return state
	 */
	public State getState(){
		return state;
	}
	
	/**
	 * Setter de l etat
	 * @param rien
	 */
	public void setState(State s){
		this.state = s;
	}
	
	/**
	 * Indique si une case contient une piece
	 * @return Vrai si la case contient une piece
	 */
	public boolean containPiece(){
		return containPiece;
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		//fond
		g.setColor(backgroundColor);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//Surcouche
		if(!state.equals(State.INITIAL)){
			Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(
	            RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setComposite(AlphaComposite.getInstance(
	            AlphaComposite.SRC_OVER, 0.3f));
	        Color select = null;
	        if(state.equals(State.SELECTED)){
	        	select = new Color(0, 102, 51);
	        }else if(state.equals(State.REACHABLE)){
	        	select = new Color(0, 204, 0);
	        }
	        g2d.setColor(select);
	        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
	        g2d.setComposite(AlphaComposite.getInstance(
	            AlphaComposite.SRC_OVER, 1.0f));
		}
		
		//Contient une piece
		if(containPiece){
			if(window.getGrid().showType){
				char colorFile = (color.equals("BLACK"))? 'b': 'w';
				char typeFile = (this.window.getGame().getGrid().getCase(x,y).isGood()) ? 'g' : 'b';
				Image imgPiece = null;
				try{
					imgPiece = ImageIO.read(getClass().getResource(Ghost.PATH+"pion"+"_"+colorFile+typeFile+".png"));
					g.drawImage(imgPiece, 0, 0, this);
				}catch(IOException e){
					System.out.println("Impossible de charger l'image "+getClass().getResource(Ghost.PATH+"pion"+"_"+colorFile+".png"));
				}
			}else{
				//Dessine l'image de la piece
				char colorFile = (color.equals("BLACK"))? 'n': 'b';
				Image imgPiece = null;
				try{
					imgPiece = ImageIO.read(getClass().getResource(Ghost.PATH+"pion"+"_"+colorFile+".png"));
					g.drawImage(imgPiece, 0, 0, this);
				}catch(IOException e){
					System.out.println("Impossible de charger l'image "+getClass().getResource(Ghost.PATH+"pion"+"_"+colorFile+".png"));
				}
			}

		}
		
		//Normal
		if(!isSelected){
			Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(
	            RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setComposite(AlphaComposite.getInstance(
	            AlphaComposite.SRC_OVER, 0.5f));
	        g2d.setColor(Color.WHITE);
	        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
	        g2d.setComposite(AlphaComposite.getInstance(
		            AlphaComposite.SRC_OVER, 1.0f));
		}
		
		//Une porte
		if(this.window.getGame().getGrid().getGrid()[x][y].isDoor()){
			Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(
	            RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setComposite(AlphaComposite.getInstance(
	            AlphaComposite.SRC_OVER, 0.5f));
	        g2d.setColor(Color.RED);
	        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
	        g2d.setComposite(AlphaComposite.getInstance(
		            AlphaComposite.SRC_OVER, 1.0f));
		}

	}
	


	@Override
	public void mouseClicked(MouseEvent arg0) {
		try{
			if(isSelected){
				if(this.color.equals(window.getGame().getCurrentPlayer().getColor())){
					window.getGrid().resetSelectedCases();
					this.state = State.SELECTED;
					window.getGame().setSelectedPiece(x, y);
				}else{
					if(window.getGame().getSelectedPiece() != null){
						window.getGame().movePiece(x, y);
					}else{
						String c = (window.getGame().getCurrentPlayer().getColor().toLowerCase().equals("white")) ? "blanche" : "noire";
						window.addLogPartie("Veuillez selectionner une piece " + c);
					}
				}
			}
		}catch (NullPointerException e){
			System.out.println("La partie est terminee");
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	

	
	
}
