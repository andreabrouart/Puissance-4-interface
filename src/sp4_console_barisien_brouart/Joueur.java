/*BARISIEN / BROUART TDB 18/11/22 */
package sp4_console_barisien_brouart;

import java.util.ArrayList;


/**
 *
 * @author Asus
 */
public class Joueur {
    private String Nom;
    private String Couleur;
    private ArrayList<Jeton> reserveJetons =new ArrayList();
    private int nombreDesintegrateurs;
    
    public Joueur(String nom){
        Nom = nom;
        Couleur = null;
        nombreDesintegrateurs =0;
       
        
    }

    public String getCouleur() {
        return Couleur;
    }

    public String getNom() {
        return Nom;
    }

    public int getNombreDesintegrateurs() {
        return nombreDesintegrateurs;
    }
    public void affecterCouleur(String couleur){
    Couleur = couleur;    
    
    
}

    public String lireCouleur() {
        return Couleur;
    }
    
    public int nombredeJetons(){
      int nbrjetons = reserveJetons.size();
      return nbrjetons;
    }
    public void ajouterJeton(Jeton jeton1){
        reserveJetons.add(jeton1);
     }
    
    public Jeton jouerJeton(){
        Jeton jet = reserveJetons.remove(0);
        return jet;
    }
    public void obtenirDesintegrateur(){
        nombreDesintegrateurs +=1;
    }
    public void utiliserDesintegrateur(){
        nombreDesintegrateurs-=1;
    }
    
}
