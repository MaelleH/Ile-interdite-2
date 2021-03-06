package Vue;


import Util.Couleur;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.MatteBorder;
import ile.interdite.Observateur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

 
public class VueAventurier  {
     
    private final String nomAventurier;
    private final JPanel panelBoutons ;
    private final JPanel panelCentre ;
    private final JFrame window;
    private final JPanel panelAventurier;
    private final JPanel mainPanel;
    private final JButton btnAller  ;
    private final JButton btnAssecher;
    private final JButton btnAutreAction;
    private final JButton btnTerminerTour;
    private final JTextField position;
    
    private Observateur controleur;
    
    public VueAventurier (String nomJoueur, String nomAventurier, Color couleur, Observateur obs){

        this.controleur=obs;
        this.nomAventurier = nomAventurier;
        this.window = new JFrame();
        window.setSize(350, 200);

        window.setTitle(nomJoueur);
        mainPanel = new JPanel(new BorderLayout());
        this.window.add(mainPanel);

        mainPanel.setBackground(Couleur.GRIS_CLAIR.getColor());
        mainPanel.setBorder(BorderFactory.createLineBorder(couleur, 2)) ;

        // =================================================================================
        // NORD : le titre = nom de l'aventurier + nom du joueur sur la couleurActive du pion

        this.panelAventurier = new JPanel();
        panelAventurier.setBackground(couleur);
        panelAventurier.add(new JLabel(nomAventurier,SwingConstants.CENTER ));
        mainPanel.add(panelAventurier, BorderLayout.NORTH);
   
        // =================================================================================
        // CENTRE : 1 ligne pour position courante
        this.panelCentre = new JPanel(new GridLayout(2, 1));
        this.panelCentre.setOpaque(false);
        this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, couleur));
        mainPanel.add(this.panelCentre, BorderLayout.CENTER);
        
        panelCentre.add(new JLabel ("Position", SwingConstants.CENTER));
        position = new  JTextField(30); 
        position.setHorizontalAlignment(CENTER);
        panelCentre.add(position);


        // =================================================================================
        // SUD : les boutons
        this.panelBoutons = new JPanel(new GridLayout(2,2));
        this.panelBoutons.setOpaque(false);
        mainPanel.add(this.panelBoutons, BorderLayout.SOUTH);

        this.btnAller = new JButton("Aller") ;
        this.btnAssecher = new JButton("Assecher");
        this.btnAutreAction = new JButton("AutreAction") ;
        this.btnTerminerTour = new JButton("Terminer Tour") ;
        
        this.panelBoutons.add(btnAller);
        this.panelBoutons.add(btnAssecher);
        this.panelBoutons.add(btnAutreAction);
        this.panelBoutons.add(btnTerminerTour);

        this.window.setVisible(true);
        mainPanel.repaint();
        
        btnAller.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //controleur.traiterMessage(new Message(ALLER,position.getText(),nomAventurier));
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        
        btnAssecher.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                           //controleur.traiterMessage(new Message(ASSECHER,position.getText(),nomAventurier)); 
                                        }
                                    }
        );
        
        btnAutreAction.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            //controleur.traiterMessage(new Message(AUTREACTION,position.getText(),nomAventurier));
                                        }
                                    }
        );
        
        btnTerminerTour.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            //controleur.traiterMessage(new Message(TERMINERTOUR,position.getText(),nomAventurier));
                                        }
                                    }
        );
        
    }  

    public String getNomAventurier(){
        return nomAventurier;
    }
    
     public JButton getBtnAutreAction() {
        return btnAutreAction;
    }

    public void setPosition(String pos) {
        this.position.setText(pos);
    }

    public JButton getBtnAller() {
        return btnAller;
    }
    
    public JButton getBtnAssecher() {
        return btnAssecher;
    }

    public JButton getBtnTerminerTour() {
        return btnTerminerTour;
    }
 

    public void setVisible(boolean b){
        window.setVisible(b);
    }
     
}

 


