/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Model.CarteTrésor;
import Model.TypeCarteTresor;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author ferreijo
 */
public abstract class PanelCarteTresor extends JPanel{
    private CarteTrésor carteTrésor;
    protected boolean clicked;
    
    public PanelCarteTresor(CarteTrésor carteTrésor){
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
}
