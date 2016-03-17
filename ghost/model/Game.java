package ghost.model;

import ghost.view.Window;

import java.util.ArrayList;

/**
 * Gere le jeu de Ghost tel que demande
 */
public class Game{
	
	
	/**
	 * Grille representant le plateau
	 */
	protected Grid Grid;
	
	/**
	 * Liste des pieces capturees
	 */
	protected ArrayList<Piece> catchedPieces;
	
	/**
	 * Nombre de pieces capturees au tour precedent 
	 */
	static int sizeCatched = -1;
	
	/**
	 * Le joueur blanc qui commence la partie
	 */
	protected Player whitePlayer;
	
	/**
	 * Le joueur noir
	 */
	protected Player blackPlayer;
	
	/**
	 * Le joueur courant - celui dont c est le tour
	 */
	protected Player currentPlayer;
	
	
	/**
	 * La piece selectionnee par le joueur courant
	 */
	protected Piece selectedPiece;
	
	
	/**
	 * La fenetre de jeu
	 */
	protected Window window;
	
	
	/**
	 * Constructeur
	 * @param window reference a la fenetre
	 */
	public Game(Window window){
		super();
		this.Grid = new Grid(this);
		this.catchedPieces = new ArrayList<Piece>();
		this.whitePlayer = new Player("WHITE");
		this.blackPlayer = new Player("BLACK");
		this.currentPlayer = whitePlayer;
		this.window = window;
		this.Grid.initGrid();
		
	}
	

	/**
	 * Constructeur
	 * @param window reference a la fenetre
	 */
	public Game(Window window, int IA){
		super();
		this.Grid = new Grid(this);
		this.catchedPieces = new ArrayList<Piece>();
		this.whitePlayer = new Player("WHITE");
		this.blackPlayer = new IA("BLACK", this, IA);
		this.currentPlayer = whitePlayer;
		this.window = window;
		this.Grid.initGrid();
		
	}
	/**
	 * Designe comme piece selectionnee
	 * @param x coordonnee en abscisse de la piece a selectionner
	 * @param y coordonnee en ordonnee de la piece a selectionner
	 */
	public void setSelectedPiece(int x, int y){
		selectedPiece = Grid.getCase(x, y);
		window.getGrid().addReachables(selectedPiece.reachableCells());
		
		if(window != null){
			window.repaint();
		}
	}
	
	/**
	 * Getter - Recupere la piece selectionne
	 * @return
	 */
	public Piece getSelectedPiece(){
		return this.selectedPiece;
	}
	
	/**
	 * Deplace la piece selectionnee sur la coordonnee (x,y)
	 * @param x la coordonnee en abscisse 
	 * @param y la coordonnee en ordonee
	 */
	public void movePiece(int x, int y){

		//Deplace la piece selectionne
		if(selectedPiece.move(x, y)){
			if(end(x,y)){
				currentPlayer = null;
			}else{
				switchPlayer();
			}
		}
		
		selectedPiece = null;
		window.getGrid().updateGrid();
		window.getGrid().resetStates();
		window.repaint();
		
		/*System.out.println("tsf");
		if(blackPlayer instanceof IA){
			if(currentPlayer == blackPlayer){
				System.out.println("tsf1.5");

				blackPlayer.jouer();
				System.out.println("tsf1.2");
				//movePiece()
				movePiece(currentPlayer.getChoice().x,currentPlayer.getChoice().y);
				System.out.println("tsf1.3");

			}
		}
		System.out.println("tsf3");*/
	}
	
	/**
	 * Test si la partie est termine
	 * @param x
	 * @param y
	 * @return Vrai si la partie est finie
	 */
	public boolean end(int x, int y){
		Player winner = winner(x,y);
		if(winner != null){
			window.addLogPartie("Vous pouvez quitter ou recommencer une partie depuis le menu Fichier");
			window.tellEM(winner.toString());
			return true;
		}
		return false;
		
	}
	
	/**
	 * Retourne le joueur qui a gagne
	 * @param x l abscisse du dernier mouvement
	 * @param y l ordonee du dernier mouvement
	 * @return le joueur gagnant
	 */
	public Player winner(int x, int y){
		
		//Si un fantome a ete capture on regarde si un joueur a gagne
		if(sizeCatched < 0 || sizeCatched!=catchedPieces.size()){
			int gw = 0, bw = 0, gb = 0, bb = 0;
			for(Piece p : this.catchedPieces){
				if(p.getColor().equals("WHITE")){
					if(p.isGood)
						gw++;
					else
						bw++;
				}else{
					if(p.isGood){
						gb++;
					}else{
						bb++;
					}
				}
			}
			
			if(gw == 4)
				return blackPlayer;
			else if(bw == 4)
				return whitePlayer;
			else if(gb == 4)
				return whitePlayer;
			else if(bb == 4)
				return blackPlayer;
		}
		
		//Sortie
		if(Grid.isDoor(x,y)){
			if(!Grid.Grid[x][y].color.equals(this.currentPlayer.color)){
				return currentPlayer;
			}
		}
		
		//Sinon pas de gagnant
		return null;

	}
	
	
	/**
	 * Recommence une partie a zero
	 */
	public void reset(){
		
		whitePlayer = new Player("WHITE");
		blackPlayer = new Player("BLACK");
		
		currentPlayer = whitePlayer;
        Grid = new Grid(this);
        catchedPieces = new ArrayList<Piece>();
		selectedPiece = null;
		
		Grid.initGrid();
		
		window.clearLogsPartie();
		window.repaint();
	}
	
	/**
	 * Change le joueur courant
	 */
	public void switchPlayer(){
		currentPlayer = (currentPlayer == whitePlayer)? blackPlayer : whitePlayer;

	}
	
	/**
	 * Getter pour le joueur courant
	 * @return la reference du currentPlayer
	 */
	public Player getCurrentPlayer(){
		return currentPlayer;
	}
	
	/**
	 * Setter pour le joueur courant
	 * @param j currentPlayer
	 */
	public void setCurrentPlayer(Player p){
		this.currentPlayer = p;
	}
	
	/**
	 * Getter pour le joueur blanc
	 * @return la reference du whitePlayer
	 */
	public Player getWhitePlayer(){
		return whitePlayer;
	}
	
	/**
	 * Getter pour le joueur noir
	 * @return la reference du blackPlayer
	 */
	public Player getBlackPlayer(){
		return blackPlayer;
	}
		
	/**
	 * Getter pour la fenetre du jeu
	 * @return window
	 */
	public Window getWindow(){
		return this.window;
	}
	
	/**
	 * Getter de la grille
	 * @return Grid
	 */
	public Grid getGrid(){
		return this.Grid;
	}
	
	/**
	 * Getter des pieces capturees
	 * @return ArrayList<Piece>
	 */
	public ArrayList<Piece> getCatchedPieces(){
		return this.catchedPieces;
	}
	
}
