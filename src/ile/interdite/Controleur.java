
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
        private ArrayList<Aventurier> aventuriers;
        private VueAventurier vueAventurier;
        private VuePlateau vuePlateau;
	private Collection<CarteTrésor> defausseCarteTrésor;
	private Collection<CarteInondation> piocheCarteInondation;
	private Collection<CarteInondation> défausseCarteInondation;
	private Collection<CarteInondation> défausseCarteCoulées;

        public Controleur() {
            vueAventurier = new VueAventurier ("Manon", "Explorateur",Utils.Pion.ROUGE.getCouleur(),this);
            ArrayList<Pion> pionAAfficher =  new ArrayList<>();
            vuePlateau = new VuePlateau(pionAAfficher);
        }
        
        public void initPartie(){
            //Créer les Aventuriers
            creationAventurier(2);
            
            //Creér les vues de Aventuriers
            
            
            
            //Créer la grille et mettre à jour la vuePlateau
            grille = new Grille();
            updateVuePlateau();
        }
        
        
        
	/**
	 * 
	 * @param a
         * @param c

	 */
	public void deplacement(Aventurier a, Coordonnees c) {

            if(a.getActionsRestantes()>0&&a.deplacementPossible(a.getPosition(),c,grille)){
		a.setPosition(c);
                a.setActionsRestantes(a.getActionsRestantes()-1);
                System.out.println(a.getPosition().getX()+a.getPosition().getY());
            }
            else if(a.getActionsRestantes()<0){
                System.out.println("Plus d'actions....");
            }
            else{
                System.out.println("Déplacement impossible!");
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
            Scanner sc=new Scanner(System.in);
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
            
            if(a.getNom().equals("Navigateur")){
                System.out.println("Voulez vous assécher une deuxième case? oui/non");
                if(sc.nextLine()=="oui"){
                    System.out.println("Entrer les nouvelles coordonnées");
                    if(a.assechementPossible((a.getPosition()), c, grille)){
                        for(Map.Entry i: grille.getHSTuile().entrySet()){
                            if((Coordonnees) i.getKey()==c){
                                ((Tuile)i.getValue()).setEtat(ASSECHEE);
                            }        
                        }
                        a.setActionsRestantes(a.getActionsRestantes()-1);
                    }  
                else{
                    System.out.println("Assechement non possible ici!");
                }
                }
                
            
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
                case ALLER:
                    System.out.println("Déplacement! (" + x +","+ y +")");
                    //deplacement(joueu, c);
                    break;
                case ASSECHER:
                    System.out.println("Assècher! (" + x +","+ y +")");
                    //assecher(inge, c);
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
        aventuriers = new ArrayList<>();

        Explorateur explo = new Explorateur(grille.getCoordTuile("La Porte de Bronze"));
        Ingenieur inge = new Ingenieur(grille.getCoordTuile("La Porte de Cuivre"));
        Messager mess = new Messager(grille.getCoordTuile("La Porte d’Argent"));
        Navigateur navig = new Navigateur(grille.getCoordTuile("La Porte d'Or"));
        Pilote pilot = new Pilote(grille.getCoordTuile("Heliport"));
        Plongeur plong= new Plongeur(grille.getCoordTuile("La Porte de Fer"));
        
        aventuriers.add(explo);
        aventuriers.add(inge);
        aventuriers.add(mess);
        aventuriers.add(navig);
        aventuriers.add(pilot);
        aventuriers.add(plong);
        Collections.shuffle((List<?>) aventuriers);
        
        ArrayList<Aventurier> aventuriersTemp = new ArrayList<>();
        for (int i=0 ; i<nbjoueur; i++){
            aventuriersTemp.add(aventuriers.get(i));
            //* Ecrire pour chaque joueur son rôle en utilisant joueur i : get(i).getNom();
        }
        
        aventuriers = aventuriersTemp;
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
                    if(a.getPosition().equals(e.getKey())){
                        pionAAfficher.add(getPionAventurier(a));
                    }
                }
                
                vuePlateau.updateCase(coord, nomCase, etatTuile, tresor, pionAAfficher);
            }
        }
    }

    public Pion getPionAventurier(Aventurier a){
        switch(a.getNom()){
            case "Explorateur" : return Pion.ROUGE;
            case "Ingenieur" : return Pion.VERT;
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
