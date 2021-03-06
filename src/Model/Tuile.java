package Model;

import Util.NomTrésor;
import Util.NomTuile;
import static Util.NomTrésor.Vide;
import Util.Utils.EtatTuile;
import java.util.Objects;

public class Tuile {
    private EtatTuile etat;
    private NomTrésor tresor;
    private NomTuile nomT;

    public Tuile (NomTuile nom,NomTrésor tresor){
        this.nomT=nom;
        this.tresor=tresor;
        this.etat=EtatTuile.ASSECHEE;
    }
    
    public Tuile (NomTuile nom){
        this.nomT=nom;
        this.tresor=Vide;
        this.etat=EtatTuile.ASSECHEE;
    }

    @Override
    public int hashCode() {
        return etat.hashCode()+tresor.hashCode()+nomT.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tuile other = (Tuile) obj;
        if (!Objects.equals(this.tresor, other.tresor)) {
            return false;
        }
        if (this.etat != other.etat) {
            return false;
        }
        if (this.nomT != other.nomT) {
            return false;
        }
        return true;
    }
        
    public EtatTuile getEtat() {
            return this.etat;
    }

    public void assechement(){
        setEtat(EtatTuile.ASSECHEE);
    }

    public void setEtat(EtatTuile etat) {
            this.etat = etat;
    }

    public NomTuile getNomT() {
            return this.nomT;
    }

    public NomTrésor getTresor() {
        return tresor;
    }

    public void setTresor(NomTrésor tresor) {
        this.tresor = tresor;
    }

    public void setNomT(NomTuile nomT) {
        this.nomT = nomT;
    }

}
