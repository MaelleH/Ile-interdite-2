package Model;

public enum NomTuile {
	LePontdesAbimes("Le Pont des Abimes"),
        LaPortedeBronze ("La Porte de Bronze"), 
        LaCavernedesOmbres("La Caverne des Ombres"),
        LaPortedeFer("La Porte de Fer"), 
        ("La Porte d’Or"), 
        ("Les Falaises de l’Oubli"),
        ("Le Palais de Corail"),
        ("La Porte d’Argent"), 
        ("Les Dunes de l’Illusion"),
        ("Heliport"), 
        ("La Porte de Cuivre"), 
        ("Le Jardin des Hurlements"),
        ("La Foret Pourpre"),
        ("Le Lagon Perdu"),
        ("Le Marais Brumeux"),
        ("Observatoire"),
        ("Le Rocher Fantome"),
        ("La Caverne du Brasier"),
        ("Le Temple du Soleil"),
        ("Le Temple de La Lune"),
        ("Le Palais des Marees"),
        ("Le Val du Crepuscule"),
        ("La Tour du Guet"),
        ("Le Jardin des Murmures");

        private String nomTuile = "";
        NomTuile(String nomTuile){
               this.nomTuile=nomTuile;
        }

        @Override
        public String toString(){
           return nomTuile;
        }
}
