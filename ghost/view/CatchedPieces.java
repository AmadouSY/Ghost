package ghost.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ghost.Ghost;
import ghost.model.Game;
import ghost.model.Piece;

/**
 * Affiche les pieces prises
 */
public class CatchedPieces extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Reference du game
	 */
	private Game game;
	protected boolean white;

	
	/**
	 * Constructeur
	 * @param game refenrence du game
	 * @param white vrai si prises whitehes
	 */
	public CatchedPieces(Game game, boolean white){
		this.white = white;
		this.game = game;
	}
	
	@Override
	public void paintComponent(Graphics g){
		ArrayList<Piece> priseTemp = game.getCatchedPieces();
		String color = (white) ? "WHITE" : "BLACK";
		
		int x=0;
		int y=0;
		for(int i=0; i<priseTemp.size(); i++){
			if(color.equals(priseTemp.get(i).getColor())){
				//Dessine l'image de la piece
				String colorFile = (color.equals("BLACK"))? "b": "w";
				String typeFile = (priseTemp.get(i).isGood()) ? "g" : "b";
				Image imgPiece = null;
				
				try{
					imgPiece = ImageIO.read(getClass().getResource(Ghost.PATH+"pion"+"_"+ colorFile + typeFile +".png"));
					g.drawImage(imgPiece, x, y, this);
					x+=Case.CASE_LENGTH;
					if(x>=Case.CASE_LENGTH * 2){
						y+=Case.CASE_LENGTH;
						x=0;
					}
					
				}catch(IOException e){
					System.out.println("Impossible de charger l'image "+getClass().getResource(Ghost.PATH+"pion"+"_"+colorFile+".png"));
				}
			}
			
		}
	}

}

