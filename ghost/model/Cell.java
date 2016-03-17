package ghost.model;

/**
 * Classe Cell
 * Represente une cellule de la grille
 *
 */
public class Cell {
	
	/**
	 * Piece contenue dans la cellule
	 */
	Piece piece;
	
	/**
	 * Couleur de la Cellule seulement si c est une porte
	 */
	String color;
	
	/**
	 * Constructeur
	 */
	public Cell(){
		this.piece = null;
	}
	
	/**
	 * Indique si une cellule est une porte
	 * @return
	 */
	public boolean isDoor(){
		return this instanceof Door;
	}
	
}
