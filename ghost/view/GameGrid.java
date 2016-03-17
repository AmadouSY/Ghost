package ghost.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComponent;

import ghost.model.Coord;
import ghost.view.Case.State;

/**
 * Conteneur qui contient toutes les Cases du jeu d'echecs a afficher
 * @see Case
 */
public class GameGrid extends JComponent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instance de la window qui contient la grille
	 */
	private Window window;
	
	/**
	 * Afficher ou non les Cases de deplacement possibles
	 */
	private boolean showReachableCases;
	
	/**
	 * Mode triche permet de voir les types des pieces
	 */
	public boolean showType;


	/**
	 * Constructeur
	 * @param window reference de la window
	 */
	public GameGrid(Window window){
		super();
		this.window = window;
		this.showReachableCases = true;
		this.showType = false;
		initGrid();
		updateGrid();
	}
	
	/**
	 * Ajoute les 64 Cases a la grille
	 */
	private void initGrid(){
		this.setLayout(new GridLayout(6,6));
		Color backgroundColor = Color.WHITE;
		//backgroundColor = new Color(911);
		for(int i = 5; i >= 0; i--){
			for(int j = 0; j < 6; j++){
				Case c = new Case(j, i, backgroundColor, window);
				if(j != 5){
					backgroundColor = (backgroundColor.equals(Color.GRAY))? Color.WHITE : Color.GRAY;
				}
				this.add(c);
			}
		}
	}
	
	/**
	 * Met a jour toutes les Cases de la grille pour que celle-ci corresponde au plateau courant
	 */
	public void updateGrid(){

		Component[] contenu = this.getComponents();
		for(int i = 0; i < contenu.length; i++){
			//Si le composant est une Case
			if(contenu[i].getClass().equals(Case.class)){
				//Mise a jour de la Case selon sa m�me position dans le plateau
				Case c = (Case)contenu[i];
				c.updateCase(window.getGame().getGrid().getCase(c.getXTab(), c.getYTab()));
			}
		}
	}
	
	/**
	 * Remet l'etat de toutes les Cases a INITIAL
	 */
	public void resetStates(){
		Component[] contenu = this.getComponents();
		for(int i = 0; i < contenu.length; i++){
			if(contenu[i].getClass().equals(Case.class)){
				Case c = (Case)contenu[i];
				if(c.getState() != State.INITIAL){
					c.setState(Case.State.INITIAL);
				}
			}
		}
	}
	
	/**
	 * Reset l'etat des Cases en Selectione ou Deplacement_Possible a Rien
	 */
	public void resetSelectedCases(){
		Component[] contenu = this.getComponents();
		for(int i = 0; i < contenu.length; i++){
			if(contenu[i].getClass().equals(Case.class)){
				Case c = (Case)contenu[i];
				if(c.getState().equals(Case.State.REACHABLE) || c.getState().equals(Case.State.SELECTED)){
					c.setState(Case.State.INITIAL);
				}
			}
		}
	}
	
	
	
	/**
	 * Passe l'etat de toutes les Cases, correspondant aux Pieces de la liste, a REACHABLE
	 * @param arrayList une liste de toute les Pieces ou l'on peut deplacer la piece courante.
	 */
	public void addReachables(ArrayList<Coord> arrayList){
		if(this.showReachableCases){
			if(arrayList == null || !showReachableCases){
				return;
			}
			
			Component[] contenu = this.getComponents();
			for(int i = 0; i < contenu.length; i++){
				if(!arrayList.isEmpty()){
					for(int j = 0; j < arrayList.size(); j++){
						Case c = (Case)contenu[i];
						if(c.getXTab() == arrayList.get(j).x && c.getYTab() == arrayList.get(j).y){
							c.setState(Case.State.REACHABLE);
							arrayList.remove(j);
							break;
						
						}
					}
				}
			}
		}
	}
	
	/**
	 * Active ou desactive la reception d'input pour toutes les Cases
	 */
	public void setTakeInput(boolean b){
		Component[] contenu = this.getComponents();
		for(int i = 0; i < contenu.length; i++){
			if(contenu[i].getClass().equals(Case.class)){
				Case c = (Case)contenu[i];
				c.setSelected(b);
			}
		}
	}
	
	/**
	 * Setter affichage de l'aide des coups
	 * @param b
	 */
	public void setShowReachableCases(boolean b){
		this.showReachableCases = b;
	}
	
	/**
	 * Setter affichage de l'aide des coups
	 * @param b
	 */
	public void setShowType(boolean b){
		this.showType = b;
		repaint();
	}
	
}
