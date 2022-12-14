/*BARISIEN / BROUART TDB 18/11/22 */
package sp4_console_barisien_brouart;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author Asus
 */

public class Partie {
    private Joueur[] listeJoueur  = new Joueur[2];
    private Joueur joueurCourant;
    private PlateauDeJeu plateau = new PlateauDeJeu();
    
    public Partie(Joueur joueur1, Joueur joueur2){
        listeJoueur[0]=joueur1;
        listeJoueur[1]=joueur2;
    }
    public void attribuerCouleurAuxJoueurs(){
        Random c = new Random();
        int var = c.nextInt(0,2);
        if (var==1){
            listeJoueur[0].affecterCouleur("jaune");
            listeJoueur[1].affecterCouleur("rouge");
        }else{
            listeJoueur[0].affecterCouleur("rouge");
            listeJoueur[1].affecterCouleur("jaune");
        }
        
    }
    public void creerEtAffecterJeton(Joueur joueur){
        Jeton[] jetonsjoueur = new Jeton[30];
        for (int i=0; i<30; i++){
            jetonsjoueur[i] = new Jeton(joueur.lireCouleur());
            joueur.ajouterJeton(jetonsjoueur[i]); 
        }
    }
    public void placerTrousNoirsEtDesintegrateurs(){
        Random l = new Random();
        Random c = new Random();
        for (int i=0; i<3; i++){
            int ligne =l.nextInt(0,6);
            int colonne =c.nextInt(0,7);
            if (plateau.presenceTrouNoir(ligne, colonne)==false && plateau.presenceDesintegrateur(ligne, colonne)==false){
                plateau.placerTrouNoir(ligne, colonne);
                plateau.placerDesintegrateur(ligne,colonne);
            }else{
                i-=1;
        }
    }
        for (int j=0; j<2; j++){
            int ligne =l.nextInt(0,6);
            int colonne =c.nextInt(0,7);
            if (plateau.presenceTrouNoir(ligne, colonne)==false && plateau.presenceDesintegrateur(ligne, colonne)==false){
            plateau.placerTrouNoir(ligne, colonne);
        }else{
                j-=1;
            }
        }
        for (int k=0; k<2; k++){
            int ligne =l.nextInt(0,6);
            int colonne =c.nextInt(0,7);
            if (plateau.presenceTrouNoir(ligne, colonne)==false && plateau.presenceDesintegrateur(ligne, colonne)==false){
                plateau.placerDesintegrateur(ligne, colonne);
        }else{
                k-=1;
    }
        }
    }
public void initialiserPartie(){
    attribuerCouleurAuxJoueurs();
    creerEtAffecterJeton(listeJoueur[0]);
    creerEtAffecterJeton(listeJoueur[1]);
    placerTrousNoirsEtDesintegrateurs();   
}
public void lancerPartie() {
        initialiserPartie();
        plateau.afficherGrilleSurConsole();
        Scanner sc = new Scanner(System.in);
        
        // on choisit un joueur pour commencer
        joueurCourant = listeJoueur[0];
        
        // tant que le joueur a pas gagne, ni l'auter joueur, et que la grille est pas remplie
        
        while (plateau.partieGagnante( listeJoueur[0].lireCouleur())== false || plateau.partieGagnante( listeJoueur[1].lireCouleur())== false || plateau.grilleRemplie() == false) {
        
        System.out.println("C'est le tour de " + joueurCourant.getNom() + " (couleur " + joueurCourant.lireCouleur()+ ")");//on indique quel joueur est le premier à jouer
        
        System.out.println("Il vous reste " + joueurCourant.nombredeJetons()+ " jetons");//on indique le nombre de jeton du joueur actuel
           
            // demander au joueur son coup à jouer
            System.out.println("Si vous voulez ajouter un jeton, tapez 1\nSi vous voulez retirer un de vos jetons, tapez 2\nSi vous avez recuperez un desintagrateur et que  voulez desintegrer un jeton du joueur adverse, tapez 3");
            int cas = sc.nextInt();
            while (cas==3&&joueurCourant.getNombreDesintegrateurs()==0){
                System.out.println("Vous n'avez pas de desintegrateur pauvre con, arrête de tricher");
                System.out.println("Si vous voulez ajouter un jeton, tapez 1\nSi vous voulez retirer un de vos jetons, tapez 2\nSi vous avez recuperez un desintagrateur et que vous voulez desintegrer un jeton du joueur adverse, tapez 3");
                cas = sc.nextInt();
            }
            // exécuter le coup
            // cas 1: ajout d'un jeton : on ajoute, on active le trou noir potentiel et je donne le desintegrateur potentiel
            if (cas==1){
                Jeton j = joueurCourant.jouerJeton();
                System.out.println("Donnez le numero de la colonne entre 1 et 7");
                int col=sc.nextInt();
                int lig = plateau.ajouterJetonDansColonne(j, col-1);
                
                if (plateau.presenceTrouNoir(lig, col-1)==true){
                    plateau.supprimerJeton(lig, col-1);
                    plateau.supprimerTrouNoir(lig, col-1);
                    
                }
                if (plateau.presenceDesintegrateur(lig, col-1)==true){
                    plateau.supprimerDesintegrateur(lig, col-1);
                    joueurCourant.obtenirDesintegrateur();
                    
                }
                plateau.afficherGrilleSurConsole();
            }
            
            // cas 2 : recupeter jeton : je recupere le jeton de la grille, le redonne au joueur, et tasse la colonne
            if (cas==2){
                System.out.println("Donnez le numero de la ligne");
                int lig=sc.nextInt();
                System.out.println("Donnez le numero de la colonne");
                int col=sc.nextInt();
                Jeton jet = plateau.recupererJeton(lig-1, col-1);
               // plateau.supprimerJeton(lig-1, col-1);
                joueurCourant.ajouterJeton(jet);
                plateau.tasserColonne(col-1);
                plateau.afficherGrilleSurConsole();
            }
            
            // cas 3 : desintegrateur : je supprime le jeton de la grille ,et je tasse la colonne
            if (cas==3){
                System.out.println("Donnez le numero de la ligne");
                int lig=sc.nextInt();
                System.out.println("Donnez le numero de la colonne"); 
                int col=sc.nextInt();
                plateau.supprimerJeton(lig-1, col-1);
                plateau.tasserColonne(col-1);      
                plateau.afficherGrilleSurConsole();
            }
        if (plateau.partieGagnante( listeJoueur[0].lireCouleur())== true){
            System.out.println("C'est gagne pour "+listeJoueur[0].getNom());
            break;
        }
        
        if (plateau.partieGagnante( listeJoueur[1].lireCouleur())== true){
            System.out.println("C'est gagne pour "+listeJoueur[1].getNom());
            break;
        }
        
        if (plateau.grilleRemplie() == true){
         System.out.println("La partie est finie");   
         break;
        }
            if (joueurCourant==listeJoueur[0]){
                joueurCourant=listeJoueur[1];
            }else{
                joueurCourant=listeJoueur[0];
            }
        }
}
}