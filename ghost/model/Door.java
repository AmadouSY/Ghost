package ghost.model;

/**
 * Represente une porte de sortie
 */
public class Door extends Cell{

	/**
	 * Constructeur
	 * @param c couleur du joueur a qui appartient la sortie
	 */
	public Door(String c){
		this.color = c;
	}
}
