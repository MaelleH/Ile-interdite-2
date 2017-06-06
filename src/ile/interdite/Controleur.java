
package ile.interdite;


import Model.Coordonnees;
import Vue.VueAventurier;
import Model.CarteInondation;
import Model.Grille;
import Model.Aventuriers.Aventurier;
import Model.Aventuriers.Explorateur;
import Model.Aventuriers.Ingenieur;
import Model.Aventuriers.Messager;
import Model.Aventuriers.Navigateur;
import Model.Aventuriers.Pilote;
import Model.Aventuriers.Plongeur;
import Model.Tuile;
import Model.CarteTrésor;
import Util.Utils;
import Util.Utils.EtatTuile;
import static Util.Utils.EtatTuile.ASSECHEE;
import Util.Utils.Pion;
import Vue.VuePlateau;
import java.util.*;

public class Controleur implements Observateur {

	Collection<CarteTrésor> piocheCarteTrésor;
	private Grille grille;
	private Collection<Aventurier> joueurs;
        private ArrayList<Aventurier> aventuriers;
        private VueAventurier vueAventurier;
        private VuePlateau vuePlateau;
	private Collection<CarteTrésor> defausseCarteTrésor;
	private Collection<CarteInondation> piocheCarteInondation;
	private Collection<CarteInondation> défausseCarteInondation;
	private Collection<CarteInondation> défausseCarteCoulées;
        private final Explorateur explo;
        private final Ingenieur inge = new Ingenieur();
        private final Messager mess = new Messager();
        private final Navigateur navig = new Navigateur();
        private final Pilote pilot = new Pilote();
        private final Plongeur plong = new Plongeur();

        public Controleur() {
            grille = new Grille();
            
            aventuriers = new ArrayList<>();
            explo = new Explorateur(new Coordonnees("3", "3"));
            aventuriers.add(explo);
            
            vueAventurier = new VueAventurier ("Manon", "Explorateur",Utils.Pion.ROUGE.getCouleur(),this);
            ArrayList<Utils.Pion> pionAAfficher =  new ArrayList<>();
            vuePlateau = new VuePlateau(pionAAfficher);
        }
        
        


	

	/**
	 * 
	 * @param aven1
	 * @param aven2
	 */
	public void donnerCarte(Aventurier aven1, Aventurier aven2) {
		// TODO - implement Controleur.donnerCarte
		
	}

	/**
	 * 
	 * @param aven1
	 * @param aven2
	 */
	public boolean donnationPossible(Aventurier aven1, Aventurier aven2) {
		// TODO - implement Controleur.donnationPossible
		return aven1.getPosition() == aven2.getPosition();
                
	}

	/**
	 * 
	 * @param a
	 */
	public void prendreTresor(Aventurier a) {
		// TODO - implement Controleur.prendreTresor
                
		if(priseTresorPossible(a)){
                    String tresor =(grille.getHSTuile().get(a.getPosition()).getTresor());
                    a.getMainCarteTrésor().remove(tresor);
//PENSER A METTRE UN ATTRIBUT TRESOR QUELQUE PART
 
                }
	}

	/**
	 * 
	 * @param a
     * @return 
	 */
	public boolean priseTresorPossible(Aventurier a) {
		// TODO - implement Controleur.priseTresorPossible
                String tresor =(grille.getHSTuile().get(a.getPosition()).getTresor());
                int stop=0;
                
                if ((a.getMainCarteTrésor().size()>4)&&(tresor!=null)){
                    
                    for(CarteTrésor i :a.getMainCarteTrésor()){
                        if(!(i.getNomCT()).equals(tresor)){
                          stop = stop +1;  
                        }
                    }
                    if(stop<1){
                        return true;
                    }
                }
                return false;                
	}
	public boolean doitDefausser(Aventurier a) {
		// TODO - implement Controleur.priseTresorPossible
                return (a.getMainCarteTrésor().size()>5);
        }
        
        @Override
        public void traiterMessage(Message m) {
            //A Faire 
            String x,y;
            x=m.getChampSaisieTxt().charAt(0)+"";
            y=m.getChampSaisieTxt().charAt(1)+"";
            
            Coordonnees c = new Coordonnees(x,y);
            
            
            
            
            switch (m.getBtnCliquéTxt()) {
                case ALLER:
                    System.out.println("Déplacement! (" + x +","+ y +")");
                    inge.deplacement(c, grille);
                    break;
                case ASSECHER:
                    System.out.println("Assècher! (" + x +","+ y +")");
                    inge.assecher(c, grille);
                    break;
                case AUTREACTION:
                    System.out.println("Autre Action! (" + x +","+ y +")");
                    break;
                case TERMINERTOUR:
                    System.out.println("Fin du Tour! (" + x +","+ y +")");
                    break;
                default:
                    break;
            }
            
        }
        public void creationAventurier(int nbjoueur){
            
            aventuriers.add(explo);
            aventuriers.add(inge);
            aventuriers.add(mess);
            aventuriers.add(navig);
            aventuriers.add(pilot);
            aventuriers.add(plong);
            Collections.shuffle((List<?>) aventuriers);
            for (int i=0 ; i<nbjoueur; i++){
                joueurs.add(aventuriers.get(i));
                //* Ecrire pour chaque joueur son rôle en utilisant joueur i : get(i).getNom();
            }
        }
              
    public void updateVuePlateau(){
        for(Map.Entry<Coordonnees,Tuile> e : grille.getHSTuile().entrySet()){
            if(e.getValue() != null){
                String coord = e.getKey().getX() + e.getKey().getY();
                String nomCase = e.getValue().getNomT().toString();
                EtatTuile etatTuile = e.getValue().getEtat();
                String tresor = e.getValue().getTresor();
                
                ArrayList<Pion> pionAAfficher = new ArrayList<Pion>();
                for(Aventurier a : aventuriers){
                    System.out.println(a.getPosition().equals(e.getKey()));
                    if(a.getPosition().equals(e.getKey())){
                        pionAAfficher.add(getPionAventurier(a));
                        System.out.println("oui"+getPionAventurier(a).toString());
                    }
                }
                
                vuePlateau.updateCase(coord, nomCase, etatTuile, tresor, pionAAfficher);
            }
        }
    }

    public Pion getPionAventurier(Aventurier a){
        switch(a.getNom()){
            case "Explorateur" : return Pion.VERT;
            case "Ingenieur" : return Pion.ROUGE;
            case "Messager" : return Pion.ORANGE;
            case "Navigateur" : return Pion.JAUNE;
            case "Pilote" : return Pion.BLEU;
            case "Plongeur" : return Pion.VIOLET;
            default: return null;
        }
    }
        
    public static void main(String [] args) {
        // Instanciation de la fenêtre 
        Controleur controleur = new Controleur();
        
       controleur.updateVuePlateau();
        
        
        
    }
}
