package ghost.model;

import java.util.ArrayList;


/**
 * Grille representant le plateau de jeu 
 */
public class Grid {
	
	/**	
	 *  Un tableau bidimensionnel de Cells
	 */
	protected Cell[][] Grid;
		
	/**
	 * Reference au jeu
	 */
	protected Game game;
	
	/**
	 * Constructeur
	 * @param game reference au jeu
	 */
	public Grid(Game game){
		this();
		this.game = game;
	}
	
	/**
	 * Constructeur
	 */
	public Grid(){
		//Nouveau tableau de Cells
		Grid = new Cell[6][6];
	}
	
	/**
	 * Initialise la grille
	 */
	public void initGrid(){
		
		//On initialise les Cells
		for(int i = 0; i < Grid.length; i++){
			for(int j = 0; j < Grid[i].length; j++){
				Grid[i][j] = new Cell();
			}
		}
		
		//On ajoute les fantomes
		for(char i = 1; i < 5; i++){
			setCase(i, 1, new Ghost(i, 1,"WHITE", this));
			setCase(i, 0, new Ghost(i, 0,"WHITE", this));

			setCase(i, 5, new Ghost(i, 5,"BLACK", this));
			setCase(i, 4, new Ghost(i, 4,"BLACK", this));

		}
		//On defini les mauvais fantomes
		setBads();

		//On defini les portes
		setDoors();
	}

	/**
	 * Definit les mauvais fantomes de facon abitraire
	 */
	public void setBads(){
		getCase(2, 1).setGood(false);
		getCase(4, 1).setGood(false);
		getCase(1, 0).setGood(false);
		getCase(3, 0).setGood(false);

		getCase(1, 4).setGood(false);
		getCase(4, 4).setGood(false);
		getCase(2, 5).setGood(false);
		getCase(3, 5).setGood(false);
	}

	
	/**
	 * Determine aleatoirement les mauvais fantome
	 */
	public void setBadsRandom(){				
	}
	
	/***
	 * Definit les quatres portes de sortie
	 */
	public void setDoors(){
		//Les portes du blanc
		Grid[0][0] = new Door("WHITE");
		Grid[5][0] = new Door("WHITE");
		
		//es portes du noir
		Grid[5][5] = new Door("BLACK");
		Grid[0][5] = new Door("BLACK");
	}
	
	/**
	 * Indique si une Cellule est une porte
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isDoor(int x, int y){
		if(this.Grid[x][y] instanceof Door){
			return true;
		}
		return false;
	}
	
	/**
	 * Retourne la piece d'abscisse x et d'ordonnee y.
	 * @param x represente l'abscisse.
	 * @param y represente l'ordonnee.
	 * @return soit la Piece contenu dans la case x,y; soit une erreur dut aux coordonnees.
	 */
	public Piece getCase(int x, int y){
		if (x < 0 || y > 5){
			System.out.println("Erreur dans la coordonnee sur l'axe des abscisse : ("+x+","+y+")");
			return null;
		}
		if (y>5 || y<0){
			System.out.println("Erreur dans la coordonnee sur l'axe des ordonnees : ("+x+","+y+")");
			return null;
		}
		return Grid[x][y].piece;
	}
	
	/**
	 * Insert une Piece dans la case d'abscisse x et d'ordonnee y.
	 * @param x represente l'abscisse.
	 * @param y represente l'ordonnee.
	 * @param a est la Piece a mettre dans la case d'abscisse x et d'ordonnee y.
	 */
	public void setCase(int x, int y, Piece a){
		if (x < 0 || x > 5){
			System.out.println("Erreur dans la coordonnee sur l'axe des abscisse : ("+x+","+y+")"+" : "+a.toString());
		}
		if (y>5 || y<0){
			System.out.println("Erreur dans la coordonnee sur l'axe des ordonnees : ("+x+","+y+") : "+a.toString());
		}
		this.Grid[x][y].piece =a;
		
	}
    
	/**
	 * Getter de la grille
	 * @return
	 */
	public Cell[][] getGrid() {
		return Grid;
	}

	/**
	 * Setter de la grille
	 * @param Grid
	 */
	public void setGrid(Cell[][] Grid) {
		this.Grid = Grid;
	}
	
	/**
	 * Setter du jeu
	 * @param game reference au jeu
	 */
	public void setGame(Game game) {
		this.game = game;
	}
	
    /**
     * Getter du jeu
     * @return reference au jeu
     */
    public Game getGame(){
    	return game;
    }
    
    /**
     * Renvoie les mauvaises pieces
     * @return
     */
    public ArrayList<Piece> getBadPieces(){
    	ArrayList<Piece> res = new ArrayList<Piece>();
    	for(Piece p : getPieces()){
    		if(!p.isGood()){
    			res.add(p);
    		}
    	}
    	return res;
    }
    
    /**
     * Renvoie les mauvaises pieces noires
     * @return
     */
    public ArrayList<Piece> getBadBlacksPieces(){
    	ArrayList<Piece> res = new ArrayList<Piece>();
    	for(Piece p : getBadPieces()){
    		if(p.color.equals("BLACK")){
    			res.add(p);
    		}
    	}
    	return res;
    }
    
    /**
     * Renvoie les mauvaises pieces blanches
     * @return
     */
    public ArrayList<Piece> getBadWhitesPieces(){
    	ArrayList<Piece> res = new ArrayList<Piece>();
    	for(Piece p : getBadPieces()){
    		if(p.color.equals("WHITE")){
    			res.add(p);
    		}
    	}
    	return res;
    }
    
    /**
     * Renvoie les bonnes pieces
     * @return
     */
    public ArrayList<Piece> getGoodPieces(){
    	ArrayList<Piece> res = new ArrayList<Piece>();
    	for(Piece p : getPieces()){
    		if(p.isGood()){
    			res.add(p);
    		}
    	}
    	return res;
    }
    
    /**
     * Renvoie les bonnes pieces noires
     * @return
     */
    public ArrayList<Piece> getGoodBlacksPieces(){
    	ArrayList<Piece> res = new ArrayList<Piece>();
    	for(Piece p : getGoodPieces()){
    		if(p.color.equals("BLACK")){
    			res.add(p);
    		}
    	}
    	return res;
    }
    
    /**
     * Renvoie les bonnes pieces blanches
     * @return
     */
    public ArrayList<Piece> getGoodWhitesPieces(){
    	ArrayList<Piece> res = new ArrayList<Piece>();
    	for(Piece p : getGoodPieces()){
    		if(p.color.equals("WHITE")){
    			res.add(p);
    		}
    	}
    	return res;
    }

    
    /**
     * Recupere une liste de toutes les pieces
     * @return une liste
     */
    public ArrayList<Piece> getPieces(){
    	ArrayList<Piece> p = new ArrayList<Piece>();
    	for(int i=0; i<Grid.length;i++){
    		for(int j=0; j<Grid.length;j++){
    			if(this.getCase(i, j) != null){
    				p.add(this.getCase(i, j));
    			}
    		}
    	}
    	return p;
    }
    
    /**
     * Recupere une list de l'ensemble des pieces blanches
     * @return la liste
     */
    public ArrayList<Piece> getWhitePieces(){
    	ArrayList<Piece> p = new ArrayList<Piece>();
    	for(int i=0; i<Grid.length;i++){
    		for(int j=0; j<Grid.length;j++){
    			if(this.Grid[i][j] != null && this.Grid[i][j].piece.getColor().endsWith("WHITE")){
    				p.add(this.getCase(i, j));
    			}
    		}
    	}
    	return p;
    }
    
    /**
     * Recupere une list de l'ensemble des pieces noirs
     * @return la liste
     */
    public ArrayList<Piece> getBlackPieces(){
    	ArrayList<Piece> p = new ArrayList<Piece>();
    	for(int i=0; i<Grid.length;i++){
    		for(int j=0; j<Grid.length;j++){
    			if(this.Grid[i][j] != null && this.Grid[i][j].piece.getColor().endsWith("BLACK")){
    				p.add(this.getCase(i, j));
    			}
    		}
    	}
    	return p;
    }

}	
