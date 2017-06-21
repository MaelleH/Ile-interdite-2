/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Model.cartesTresor.CarteTrésor;
import ile.interdite.Message;
import ile.interdite.Observateur;
import static ile.interdite.TypeMessage.VAL2;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Util.Couleur;

/**
 *
 * @author heyrendm
 */
public class VueLancement {
    private Observateur controleur;
    private JFrame main;
    private JPanel mainP;
    
    private JPanel welcomeP;
    private JPanel joueurP;
    private JPanel nomJoueurP;
    
    private ArrayList<String> nomJ = new ArrayList<>();;
    
    private final String[] nbj = new String[]{"2","3","4"};
    private final String[] nivDif= new String[]{"Novice","Normal","Expert","Légendaire"};
    
    private final JComboBox listeNiv = new JComboBox(nbj);
    private final JComboBox listeDif = new JComboBox(nivDif);
   

    public VueLancement(Observateur obs) {
        controleur= obs;
        main = new JFrame();
        mainP = new JPanel(new GridLayout(2, 1));
        Color c;
        welcomeP = new JPanel(new BorderLayout());
        JPanel choixP = new JPanel(new BorderLayout());
        
        
        
        main.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        main.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                int reponse = JOptionPane.showConfirmDialog(main,"Voulez vous vraiment quitter?", "Quitter?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (reponse==JOptionPane.YES_OPTION){
                    main.dispose();
                }
            }
            
        });
        
        
        
        //Fenetre principale
        main.setTitle("Préparation du jeu ...");
        main.setSize(300, 350);
        
        main.add(mainP);        
        
        
        //Panneau de depart
        c = Couleur.NOIR.getColor();
        mainP.add(welcomeP);
        welcomeP.setBackground(c);        
        welcomeP.add(choixP); 
        
        //Titre 
        JLabel bv= new JLabel("WELCOME", (int) JLabel.CENTER_ALIGNMENT);
        bv.setSize(300,50);
        Font font;
        font = new Font("Arial",Font.BOLD,25);
        bv.setFont(font);
        c = Couleur.DEEP_ROSE.getColor();     
        bv.setForeground(c);
        welcomeP.add(bv,NORTH);
    
        
        //Choix 
        JPanel choixnbj = new JPanel();
        choixP.add(choixnbj,NORTH);
        //nbj 
        choixnbj.add(new JLabel("Nombre de joueurs : "));
        choixnbj.add(listeNiv);
        
        //Difficulté
        JPanel choixDif = new JPanel(new GridLayout(2, 2));
        choixP.add(choixDif,CENTER);
        choixDif.setBorder(BorderFactory.createLineBorder(c));
        choixDif.setBorder(BorderFactory.createTitledBorder("Difficulté"));
        
        // Déclaration de la combobox
        choixDif.add(listeDif);
        //bouton dif
        
        JButton val1 = new JButton("Valider Choix");

        welcomeP.add(val1,SOUTH);


        
        //Panneau choix du nom des joueurs
        joueurP = new JPanel(new BorderLayout());
        joueurP.setVisible(false);
        mainP.add(joueurP);
        nomJoueurP = new JPanel(new GridLayout(2,2));
        joueurP.add(nomJoueurP,CENTER);
        
        
        JPanel j1= new JPanel(new GridLayout(1, 2));j1.setVisible(false);
        JPanel j2= new JPanel(new GridLayout(1, 2));j2.setVisible(false);
        JPanel j3= new JPanel(new GridLayout(1, 2));j3.setVisible(false);
        JPanel j4= new JPanel(new GridLayout(1, 2));j4.setVisible(false);
        
        nomJoueurP.add(j1);nomJoueurP.add(j2);nomJoueurP.add(j3);nomJoueurP.add(j4);
        

        
        
        j1.add(new JLabel("Joueur 1 : "));TextField nj1 = new TextField("Joaquim");j1.add(nj1);
        j2.add(new JLabel("Joueur 2 : "));TextField nj2 = new TextField("Arnaud");j2.add(nj2);
        j3.add(new JLabel("Joueur 3 : "));TextField nj3 = new TextField("Maëlle");j3.add(nj3);
        j4.add(new JLabel("Joueur 4 : "));TextField nj4 = new TextField("Killian");j4.add(nj4);
        
        JButton val2 = new JButton("Valider le nom des joueurs");
        
        //Bouton pour valider les noms des joueurs et lancer le jeu
        
        
        val2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                
                switch (nbj[listeNiv.getSelectedIndex()]) {
                    case "2":
                        nomJ.add(nj1.getText());
                        nomJ.add(nj2.getText());
                        break;
                    case "3":
                        nomJ.add(nj1.getText());
                        nomJ.add(nj2.getText());
                        nomJ.add(nj3.getText());
                        break;
                    case "4":
                        nomJ.add(nj1.getText());
                        nomJ.add(nj2.getText());
                        nomJ.add(nj3.getText());
                        nomJ.add(nj4.getText());
                        break;
                    default:
                        break;
                }
                
                m.setNivDif(nivDif[listeDif.getSelectedIndex()]);
                m.setJoueurs(nomJ);
                m.setJoueur(nbj[listeNiv.getSelectedIndex()]);
                m.setTypeMessage(VAL2);
             
                controleur.traiterMessage(m);
                main.dispose();
            }
        });
        joueurP.add(val2,SOUTH);
        welcomeP.add(val1,SOUTH);    
        
        
        //Bouton pour afficher les noms des joueurs
                
        val1.addActionListener((ActionEvent e) -> {
            joueurP.setVisible(true);
            j1.setVisible(true);j2.setVisible(true);j4.setVisible(false);j3.setVisible(false);
            if(nbj[listeNiv.getSelectedIndex()].equals("3")){
                j3.setVisible(true);j4.setVisible(false);
            }
            else if(nbj[listeNiv.getSelectedIndex()].equals("4")){
                j3.setVisible(true);j4.setVisible(true);
            }
        });
        
        
        
        
        
        
        
        main.setVisible(true);
    }

    
}
