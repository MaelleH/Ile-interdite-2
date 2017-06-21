/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue.panels;

import Model.cartesTresor.CarteTrésor;
import Util.TypeCarteTresor;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author ferreijo
 */
public abstract class PanelCarteTresor extends JPanel{
    private int type;//0 pour interface défausse, 1 pour interface utilisateur
    private CarteTrésor carteTrésor;
    private boolean clicked;
    
    public PanelCarteTresor(int type,CarteTrésor carteTrésor){
        setType(type);
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.carteTrésor = carteTrésor;
    }
    
    public boolean getClicked(){
        return clicked;
    }
    public TypeCarteTresor getTypeCarteTresor(){
        return getCarteTrésor().getTypeCarteTresor();
    }

    public CarteTrésor getCarteTrésor() {
        return carteTrésor;
    }

    public void setCarteTrésor(CarteTrésor carteTrésor) {
        this.carteTrésor = carteTrésor;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
