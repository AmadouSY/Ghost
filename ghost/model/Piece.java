package ghost.model;

import java.util.ArrayList;


/**
 * Classe mere des differentes pieces
 */
public abstract class Piece {

    /**
     * Reference au Grid
     */
    protected Grid Grid;
    
    /**
     * La color de la piece
     */
    protected String color;
    
    /**
     * Boolean indique si on a un bon fantome
     */
    protected boolean isGood;
    
    /**
     * L'abscisse et l'ordonnee de la piece
     */
    protected int x,y;

    /**
     * Constructeur de Piece
     * @param x La position en abscisse
     * @param y La position en ordonnee
     * @param color La color de la piece
     */
    public Piece(int x, int y, String color, Grid Grid){
    	this.x = x;
    	this.y = y;
    	this.color = color;
    	this.Grid = Grid;
    }
    
    /**
     * Constructeur de Piece
     * @param x La position en abscisse
     * @param y La position en ordonnee
     * @param color La color de la piece
     * @param isGood La tendance de la piece
     */
    public Piece(int x, int y, String color, Grid Grid, boolean isGood){
    	this.x = x;
    	this.y = y;
    	this.color = color;
    	this.Grid = Grid;
    }

    
    /**
     * Donne la couleur de piece
     * @return La couleur de la piece
     */
    public String getColor(){
    	return color;
    }
    
    /**
     * Retourne la coordonnee X
     * @return x
     */
    public int getX(){
    	return this.x;
    }
    
    /**
     * Retourne la coordonnee Y
     * @return y
     */
    public int getY(){
    	return this.y;
    }

    /**
     * Getter de isGood
     * @return
     */
	public boolean isGood() {
		return isGood;
	}

	/**
	 * Setter de isGood
	 * @param isGood
	 */
	public void setGood(boolean isGood) {
		this.isGood = isGood;
	}
    
    /**
     * Deplace la piece sur x,y
     * @param x Le x d'arrivee
     * @param y Le y d'arrivee
     */
    public boolean move(int x,int y){
        if(validMove(x, y)){
 	
        	//Prises
            if(Grid.getCase(x, y) != null){
            	if(Grid.getCase(x, y).getColor() != this.getColor()){
            		this.Grid.getGame().getCatchedPieces().add(Grid.getCase(x, y));
            	}
            }

            //Deplacement de la piece
        	Grid.setCase(this.x, this.y, null);
            this.x = x;
            this.y = y;
            Grid.setCase(this.x, this.y, this);
            return true;

        }
        else{
            return false;
        }
        
    }
    /**
     * Recupere les Coords de toutes les cases accessibles par le fantome
     * @return ArrayList<Coord>
     */
    public ArrayList<Coord> reachableCells(){
    	return new ArrayList<Coord>();
    }
    
    /**
     * Ne fais rien, fonction surcharger par les class heritee
     * @param x
     * @param y
     * @return
     */
    public boolean validMove(int x, int y){
        return false;
    }
    
    @Override
    public String toString(){
    	return color+" ("+x+","+y+")";
    }

}
