/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Model.Activable;
import Model.CarteTrésor;
import Model.CarteTrésorTrophée;
import Model.Helico;
import Model.Sac;
import Model.TypeCarteActivable;
import Model.TypeCarteTresor;
import ile.interdite.Message;
import ile.interdite.Observateur;
import ile.interdite.TypeMessage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ferreijo
 */
public class VueDefausse {
    private Observateur controleur;
    
    private JFrame window;
    private JPanel mainPanel;
    
    private JPanel panelIndications;
    private JLabel labelIndications;
    
    private JPanel panelCartes;
    private ArrayList<PanelCarteTresor> listeCarteTresor;

    private JPanel panelValidation;
    private JButton boutonValidation;
    public VueDefausse(int toDump,ArrayList<CarteTrésor> cartes,Observateur obs) {
        listeCarteTresor = new ArrayList<>();
        controleur = obs;
        
        int height = 400;
        int width = 300;
        
        int posX = ((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2)-width/2;
        int posY = ((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2)-height/2;

        this.window = new JFrame();
        window.setLocation(posX, posY);
        window.setSize(width, height);
        window.setTitle("Défausse");
        
        mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        
        //Partie Nord = Instructions
        panelIndications = new JPanel();
        panelIndications.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
        mainPanel.add(panelIndications,BorderLayout.NORTH);
        
        labelIndications = new JLabel("Vous devez défausser au moins "+toDump+" cartes");
        panelIndications.add(labelIndications);
        
        //Partie Centre = Selection
        panelCartes = new JPanel(new GridLayout(3, 3));
        panelCartes.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), BorderFactory.createLineBorder(Color.gray, 2)));
        mainPanel.add(panelCartes,BorderLayout.CENTER);
        
        setListeCarteTresor(cartes);
        initPanelCarte();
        
                
        //Partie Sud = VALIDATION
        panelValidation = new JPanel();
        panelValidation.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.black));
        mainPanel.add(panelValidation,BorderLayout.SOUTH);
        
        boutonValidation = new JButton("Défausser");
        panelValidation.add(boutonValidation);
        boutonValidation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nb = 0;
                ArrayList<CarteTrésor> listeCarteTresorADefausse = new ArrayList<>();
                        
                for(PanelCarteTresor pnC : listeCarteTresor){
                    if(pnC.getClicked()){
                        nb++;
                        listeCarteTresorADefausse.add(pnC.getCarteTrésor());
                    }
                }
                if(nb>=toDump){
                    Message m = new Message();
                    m.setTypeMessage(TypeMessage.DEFAUSSER);
                    m.setListeCarteTresorADefausse(listeCarteTresorADefausse);
                    window.setVisible(false);
                    controleur.traiterMessage(m);
                    
                }
            }
        });
        
        
        window.setVisible(true);
    }
    
    public void setListeCarteTresor(ArrayList<CarteTrésor> cartes){
        listeCarteTresor.clear();
        PanelCarteActivable carteActi;
        PanelCarteTrophee carteTrophee;
        for(CarteTrésor carte : cartes){
            if(carte.getTypeCarteTresor().equals(TypeCarteTresor.Activable)){
                carteActi = new PanelCarteActivable(((Activable) carte));
                listeCarteTresor.add(carteActi);
            }else if(carte.getTypeCarteTresor().equals(TypeCarteTresor.Tresor)){
                carteTrophee = new PanelCarteTrophee(((CarteTrésorTrophée) carte));
                listeCarteTresor.add(carteTrophee);
            }
        }
    }
    public void initPanelCarte(){
        panelCartes.removeAll();
        for(PanelCarteTresor panelCartTres : listeCarteTresor){
            panelCartes.add(panelCartTres);
        }
        if(listeCarteTresor.size()<5){
            for(int i = listeCarteTresor.size();i<5;i++){
                panelCartes.add(new JPanel());
            }
        }
    }
}
