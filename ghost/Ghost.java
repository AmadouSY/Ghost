package ghost;

import java.awt.Image;
import javax.imageio.ImageIO;
import ghost.view.Home;

/**
 * Classe principale qui permet de lancer le jeu
 */
public class Ghost {
	
	/**
	 * Chemin vers les images
	 */
	public static final String PATH = "/img/";
	
	/**
	 * Icone de la fenetre qui s affiche a cote du titre
	 */
	public static Image ICON = null;

	/**
	 * Main
	 * @param args
	 */
    public static void main(String[] args){
    	
    	//On charge l icone
    	try{
			ICON = ImageIO.read(Ghost.class.getResource(Ghost.PATH+"ghost.png"));
		}catch(Exception e){
			System.out.println("Impossible de charger l'image "+Ghost.class.getResource(Ghost.PATH+"ghost.png"));
		}
    	
    	//Puis on lance la fenetre
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
    		public void run(){
    			new Home(); 
    		}
    	});

    }
}
