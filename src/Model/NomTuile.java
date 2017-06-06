package Model;

public enum NomTuile {
	LePontdesAbimes("Le Pont des Abimes"),
        LaPortedeBronze ("La Porte de Bronze"), 
        LaCavernedesOmbres("La Caverne des Ombres"),
        LaPortedeFer("La Porte de Fer"), 
        LaPortedOr("La Porte d’Or"), 
        LesFalaisesdelOubli("Les Falaises de l’Oubli"),
        LePalaisdeCorail("Le Palais de Corail"),
        LaPortedArgent("La Porte d’Argent"), 
        LesDunesdelIllusion("Les Dunes de l’Illusion"),
        Heliport("Heliport"), 
        LaPortedeCuivre("La Porte de Cuivre"), 
        LeJardindesHurlements("Le Jardin des Hurlements"),
        LaForetPourpre("La Foret Pourpre"),
        LeLagonPerdu("Le Lagon Perdu"),
        LeMaraisBrumeux("Le Marais Brumeux"),
        Observatoire("Observatoire"),
        LeRocherFantome ("Le Rocher Fantome"),
        LaCaverneduBrasier("La Caverne du Brasier"),
        LeTempleduSoleil("Le Temple du Soleil"),
        LeTempledeLaLune("Le Temple de La Lune"),
        LePalaisdesMarees("Le Palais des Marees"),
        LeValduCrepuscule("Le Val du Crepuscule"),
        LaTourduGuet("La Tour du Guet"),
        LeJardindesMurmures("Le Jardin des Murmures");

        private String nomTuile = "";
        NomTuile(String nomTuile){
               this.nomTuile=nomTuile;
        }

        @Override
        public String toString(){
           return nomTuile;
        }
}
