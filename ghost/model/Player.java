package ghost.model;

/**
 * Represente un joueur humain
 */
public class Player {
	
	/**
	 * Couleur du joueur
	 */
	protected String color;
	
	/**
	 * Constructeur avec la couleur
	 * @param c couleur du joueur
	 */
	public Player(String c){
		this.color = c;
	}

	/**
	 * Getter de la couleur
	 * @return color du Player
	 */
	public String getColor(){
		return color;
	}
	
	@Override
	public String toString(){
		if(color.equals("WHITE"))
			return "blanc";
		else
			return "noir";
	}

	public void jouer() {
		System.out.println("fail");

	}
	
	public Coord getChoice(){
		return new Coord(0,0);
	}

}

