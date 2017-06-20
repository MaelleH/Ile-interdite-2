/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author heyrendm
 */
public class CarteTrésorTrophée extends CarteTrésor{
    private NomTrésor nomT;
    public CarteTrésorTrophée(NomTrésor nomT) {
        this.setNomT(nomT);
    }

    private void setNomT(NomTrésor nomT) {
       this.nomT = nomT;
    }
    
    @Override
    public TypeCarteTresor getTypeCarteTresor() {
        return TypeCarteTresor.Tresor;
    }  

    public NomTrésor getNomT() {
        return nomT;
    }
}
