package ghost.model;


public class IA extends Player {
	/**
	 * Aleatoire 0, --->
	 */
	int lvl;
	
	Coord choice;
	Game game;
	
	/*
	 * Constructeur
	 */
	public IA(String c) {
		super(c);
	}
	
	/**
	 * Constructeur avec niveau
	 * @param c
	 * @param lvl
	 */
	public IA(String c, Game game, int lvl) {
		this(c);
		this.lvl = lvl;
		this.game = game;
		jouer();
	}
	
	/**
	 * Fonction qui fait jouer l'ia
	 */
	@Override
	public void jouer(){
		this.choice = null;
				
		int x = -1;
		int y = -1;

		System.out.println(x);
		this.choice = new Coord(x,y);
	}
	
	/**
	 * Permet de recuperer la coordonnee a jouer
	 * @return
	 */
	public Coord getChoice(){
		System.out.println(this.choice);
		return this.choice;


	}
}
