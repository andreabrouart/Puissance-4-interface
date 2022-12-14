/*BARISIEN / BROUART TDB 18/11/22 */
package sp4_console_barisien_brouart;

/**
 *
 * @author Asus
 */
public class PlateauDeJeu {
    CelluleDeGrille[][] grille = new CelluleDeGrille[6][7];

public PlateauDeJeu(){
for (int i =0; i<6; i++){
    for (int j =0; j<7; j++){
        grille[i][j] = new CelluleDeGrille();
    }
}
}
public int ajouterJetonDansColonne(Jeton j, int col){
    for (int k=0; k<6; k++){
        if (grille[k][col].lireCouleurDuJeton() == "vide"){
            grille[k][col].affecterJeton(j);
            return k;
        }
}
    return 0;
}
public boolean grilleRemplie(){
   for (int j=0; j<7; j++){
       if (grille[5][j].presenceJeton()==false){
           return false;
       }
   } 
   return true;
}
public void afficherGrilleSurConsole(){
    for (int i=5; i>=0; i--){
        for (int j=0; j<7; j++){
            if (j==6){
                System.out.print(grille[i][j]+"\n");
            }else{
                System.out.print(grille[i][j] + " ");
            }
        }
    }
}
public boolean presenceJeton(int x, int y){
    if (grille[x][y].presenceJeton()==true){
        return true;
    }else{
    return false;
}
}
public String lireCouleurDuJeton(int x, int y){
    return grille[x][y].lireCouleurDuJeton();
}
public boolean ligneGagnantePourCouleur(String couleur){
    int cjg =0; //compte jeton gagnant
    for (int i=0; i<6; i++){
        for (int j=0; j<7; j++){
            if (grille[i][j].lireCouleurDuJeton()==couleur){
                cjg+=1;
                if (cjg==4){
                    return true;
                }
            }else{
                cjg=0;
            }
        }
        cjg=0;
    }
    return false;
}

public boolean colonneGagnantePourCouleur(String couleur){
    int cjg =0;
    for (int j=0; j<7; j++){
        for (int i=0; i<6; i++){
            if (grille[i][j].lireCouleurDuJeton()==couleur){
                cjg+=1;
                if (cjg==4){
                    return true;
            }
        }else{
                cjg=0;
            }
        }
        cjg=0;
        
}
    return false;
}
public boolean diagonaleMontanteGagnantePourCouleur(String couleur){
    int cjg=0;
    for (int i=0; i<3; i++){
        for (int j=0; j<4; j++){
            for (int k=0; k<4; k++){
                if (grille[i+k][j+k].lireCouleurDuJeton()== couleur){
                    cjg +=1;
                }else{
                    cjg = 0;
                }
                if (cjg ==4){
                    return true;
                }
                if (i==5 && j==6){
                    break;
                }
            }
            cjg =0;
    }
}
    return false;
}
public boolean diagonaleDescendanteGagnantePourCouleur(String couleur){
    int cjg=0;
    for (int i=3; i<6; i++){
        for (int j=0; j<4; j++){
            for (int k=0; k<4; k++){
               if (grille[i-k][j+k].lireCouleurDuJeton()== couleur){
                   cjg +=1;
               }else{
                   cjg=0;
               }
               if (cjg ==4){
                    return true;
            }
               if (i==0 && j==6){
                    break;
                }
        }
            cjg=0;
    }
}
    return false;
}
public boolean partieGagnante(String couleur){
    if (ligneGagnantePourCouleur(couleur)==true){
        return true;
      }
    if (colonneGagnantePourCouleur(couleur)==true){
        return true;
    }
    if (diagonaleMontanteGagnantePourCouleur(couleur)==true){
        return true;
    }
    if (diagonaleDescendanteGagnantePourCouleur(couleur)==true){
        return true;
    }
    return false;
}
public void tasserColonne(int col){
 for (int i=0; i<5; i++){
     if (grille[i][col].presenceJeton()==false){
             Jeton  j = grille[i+1][col].recupererJeton();
         
             grille[i][col].affecterJeton(j);
         }
     }
 } 
public void tasserGrille(){
    for (int i=0; i<7; i++){
        tasserColonne(i);
    }
}
public boolean colonneRemplie(int col){
    if (presenceJeton(5,col)==true){
        return true;
    }else{
    return false;
    }
}
public boolean presenceTrouNoir(int ligne, int colonne){
    if (grille[ligne][colonne].presenceTrouNoir()==true){
        return true;
    }else{
        return false;
    }
}
public void placerTrouNoir(int lig, int col){
    if (grille[lig][col].presenceTrouNoir()==false){
    grille[lig][col].placerTrouNoir();
}
}
public void supprimerTrouNoir(int lig, int col){
    if (grille[lig][col].presenceTrouNoir()==true){
    grille[lig][col].supprimerTrouNoir();
}
}
public boolean presenceDesintegrateur(int ligne, int colonne){
    if (grille[ligne][colonne].presenceDesintegrateur()==true){
        return true;
    }else{
        return false;
    }
}
public void supprimerDesintegrateur(int ligne, int colonne){
    grille[ligne][colonne].supprimerDesintegrateur();
}
public void placerDesintegrateur(int lig, int col){
    if (grille[lig][col].presenceDesintegrateur()==false){
    grille[lig][col].placerDesintegrateur();
}
}
public void supprimerJeton(int lig, int col){
    if (grille[lig][col].presenceJeton()==true){
    grille[lig][col].supprimerJeton();
}
}
public Jeton recupererJeton(int lig, int col){
    Jeton j=null;
    if (grille[lig][col].presenceJeton()==true){
    j =grille[lig][col].recupererJeton();
}
    return j ;
}

}



