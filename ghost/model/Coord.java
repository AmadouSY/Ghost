package ghost.model;

/**
 * Represente des coordonnees numeriques
 */
public class Coord {

	/**
	 * abscisse de la coordonnee
	 */
	public int x;
	
	/**
	 * ordonnee de la coordonne
	 */
	public int y;
	
	/**
	 * Constructeur avec abscisse et ordonnee
	 * @param x valeur en abscisse
	 * @param y caleur en ordonnee
	 */
	public Coord(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return "["+x+", "+y+"]";
	}
}
