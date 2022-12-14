
package sp4_console_barisien_brouart;

/**
 *
 * @author Asus
 */
public class Jeton {
private String couleur;    

    public Jeton(String c){
    couleur = c;
    }   
    public void lireCouleur() {
System.out.println("Couleur du jeton : "+couleur);

}
@Override
public String toString(){
  if (couleur=="rouge"){
          return "R";
}else if(couleur=="jaune"){
      return "J";
  }else{
    return null;
}
}
}   

