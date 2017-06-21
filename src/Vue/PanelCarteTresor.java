/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Model.TypeCarteTresor;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author ferreijo
 */
public abstract class PanelCarteTresor extends JPanel{
    private TypeCarteTresor type;
    protected boolean clicked;
    
    public PanelCarteTresor(TypeCarteTresor type){
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.type = type;
    }
    
    public boolean getClicked(){
        return clicked;
    }
}
