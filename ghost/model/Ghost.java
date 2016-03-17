package ghost.model;

import java.util.ArrayList;

/**
 * Representation d'un Fantome
 */
public class Ghost extends Piece {
    
    /**
     * Constructeur de Ghost
     * @param x La position en abscisse
     * @param y La position en ordonnee
     * @param grid Le plateau ou se trouve le fantome
     * @param color La color de la piece
     */
    public Ghost(int x, int y, String color, Grid grid){
    	super(x, y, color, grid);
    	this.isGood = true;
    }
    
    /**
     * Verifie que le mouvement est valide pour ce fantome
     * @param x x d'arrive
     * @param y y d'arrive
     * @return Si le coup est autorisé pour cette piece
     */
    @Override
    public boolean validMove(int x, int y){
    	if(x < 0 || x > 5 || y < 0 || y > 5) 
    		return false;
    	
		if(x == this.x && y == this.y+1){
			return true;
		}
		if(x == this.x+1 && y == this.y){
			return true;
		}
		if(x == this.x && y == this.y-1){
			return true;
		}
		if(x == this.x-1 && y == this.y){
			return true;
		}

    	return false;
    }
    
    /**
     * Recupere les Coords de toutes les cases accessibles par le fantome
     * @return ArrayList<Coord> les coords des cases accessibles
     */
    @Override
    public ArrayList<Coord> reachableCells(){
    	ArrayList<Coord> coords = new ArrayList<Coord>();	
    	int xa, ya;
    	
		xa = x;
		ya = y+1;
		if(((ya) >= 0 && (ya) < 6)){
			if(this.Grid.getCase(xa, ya) != null){
				if(!this.Grid.getCase(xa,ya).color.equals(this.color))
					coords.add(new Coord(xa,ya));
			}else{
				coords.add(new Coord(xa,ya));
			}
		}
		
		xa = x+1;
		ya = y;
		if(((xa) >= 0 && (xa) < 6)){
			if(this.Grid.getCase(xa, ya) != null){
				if(!this.Grid.getCase(xa,ya).color.equals(this.color))
					coords.add(new Coord(xa,ya));
			}else{
				coords.add(new Coord(xa,ya));
			}
		}
		
		xa = x;
		ya = y-1;
		if(((ya) >= 0 && (ya) < 6)){
			if(this.Grid.getCase(xa, ya) != null){
				if(!this.Grid.getCase(xa,ya).color.equals(this.color))
					coords.add(new Coord(xa,ya));
			}else{
				coords.add(new Coord(xa,ya));
			}
		}
		
		xa = x-1;
		ya = y;
		if(((xa) >= 0 && (xa) < 6)){
			if(this.Grid.getCase(xa, ya) != null){
				if(!this.Grid.getCase(xa,ya).color.equals(this.color))
					coords.add(new Coord(xa,ya));
			}else{
				coords.add(new Coord(xa,ya));
			}
		}

    	return coords;
    }
    
}
    
