
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
import static Util.Utils.EtatTuile.ASSECHEE;
import java.util.*;

public class Controleur implements Observateur {

	Collection<CarteTrésor> piocheCarteTrésor;
	private Grille grille;
	private Collection<Aventurier> joueurs;
        private ArrayList<Aventurier> aventuriers;
        private VueAventurier vueAventurier;
	private Collection<CarteTrésor> defausseCarteTrésor;
	private Collection<CarteInondation> piocheCarteInondation;
	private Collection<CarteInondation> défausseCarteInondation;
	private Collection<CarteInondation> défausseCarteCoulées;
        private final Explorateur explo = new Explorateur("Explorateur");
        private final Ingenieur inge = new Ingenieur("Ingenieur");
        private final Messager mess = new Messager("Messager");
        private final Navigateur navig = new Navigateur("Navigateur");
        private final Pilote pilot = new Pilote("Pilote");
        private final Plongeur plong = new Plongeur("Plongeur");

	/**
	 * 
	 * @param a
         * @param c

	 */
	public void deplacement(Aventurier a, Coordonnees c) {
            if(a.getActionsRestantes()>0){
		a.setPosition(c);
                a.setActionsRestantes(a.getActionsRestantes()-1);
            }
            else{
                System.out.println("Plus d'actions....");
            }
	}

	/**
	 * 
	 * @param aventurier
	 * @param tuile
	 */
	public HashMap deplacementPossibleListe(Aventurier a) {
		// TODO - implement Controleur.deplacementPossible
                                
                HashMap<Coordonnees,Tuile> listeD = new HashMap<>();   
		Coordonnees p;
                p = a.getPosition();
                
                for(Map.Entry i: grille.getHSTuile().entrySet()){
                    if(a.deplacementPossible(p, (Coordonnees) i.getKey(),grille)){
                        listeD.put((Coordonnees) i.getKey(),(Tuile) i.getValue());
                    }        
                }

		return listeD;
                
	}

	public HashMap assechementPossibleListe(Aventurier a) {
		// TODO - implement Controleur.assécher
		HashMap<Coordonnees,Tuile> listeA = new HashMap<>();   
		Coordonnees p;
                p = a.getPosition();
                
                for(Map.Entry i: grille.getHSTuile().entrySet()){
                    if(a.assechementPossible(p, (Coordonnees) i.getKey(),grille)){
                        listeA.put((Coordonnees) i.getKey(),(Tuile) i.getValue());
                    }        
                }
                return listeA;


	}
        
        public void assecher(Aventurier a, Coordonnees c) {
		// TODO - implement Controleur.assécher  
            if(a.assechementPossible((a.getPosition()), c, grille)){
                if(a.getActionsRestantes()>0){
                    for(Map.Entry i: grille.getHSTuile().entrySet()){
                        if((Coordonnees) i.getKey()==c){
                            ((Tuile)i.getValue()).setEtat(ASSECHEE);
                        }        
                    }
                    a.setActionsRestantes(a.getActionsRestantes()-1);
                }
                else{
                    System.out.println("Plus d'actions....");
                } 
            }  
            else{
                System.out.println("Assechement non possible ici!");
            }
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
                case "Aller":
                    System.out.println("Déplacement! (" + x +","+ y +")");
                    deplacement(explo, c);
                    break;
                case "Assecher":
                    System.out.println("Assècher! (" + x +","+ y +")");
                    break;
                case "AutreAction":
                    System.out.println("Autre Action! (" + x +","+ y +")");
                    break;
                case "Terminer Tour":
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
                
}
