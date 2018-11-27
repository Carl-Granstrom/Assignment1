package stud.ltu.WakeUpGym;
/**
 * Förutsätter jämnt antal platser per rad, är en acceptabel lösning i nuläget.
 *
 * Hade ursprungligen Sal som final pga PoLP, men det kan ju tänkas att man vill använda arv för olika typer
 * av salar om man utökar verksamheten.
 *
 * TODO Behöver(?) printmetod för att skriva ut namnet på alla Platser.
 * TODO Behöver boolean[] för att registrera vilka platser som är bokade.
 * TODO Behöver printmetod för att skriva ut namnet på alla platser som inte är bokade.
 * TODO Implementera metod för bokning. Uppdatera boolean-array.
 */

public class Sal {
    private String namn;
    private int rader;
    private int platserPerRad;
    //Använder inte ArrayList då jag inte vill att platserna ska flyttas runt i arrayen med några list-metoder.
    private final Plats[] platser = new Plats[rader * platserPerRad]; //skapa array med antal platser (null init)
    private final boolean[] bokadPlats = new boolean[platser.length]; //detta är en "ok" lösning (false init)
    char[] alfabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm'};
    /**
     * @param namn          salens namn
     * @param rader         antal rader i salen
     * @param platserPerRad antal platser per rad
     */
    Sal(String namn, int rader, int platserPerRad) {
        this.namn = namn;
        this.rader = rader;
        this.platserPerRad = platserPerRad;
        //fyll platser-array med Plats-objekt med rätt namn(1a, 1b, 1c, 2a, 2b, 2c osv...)
        for (int i = 0; i < platser.length; i++){
            int rad = (i / platserPerRad) + 1;
            int alfabetsIndex = i % platserPerRad;
            char plats = alfabet[alfabetsIndex];
            platser[i] = new Plats(rad, plats);
        }
    }

    @Override
    public String toString() {
        return namn;
    }

    public void printLedigaPlatser() {

        //för varje plats som inte är bokad, skriv ut platsen
        for (int i = 0; i < bokadPlats.length ; i++) {
            if (bokadPlats[i] == false){
                System.out.println(platser[i].toString() + " ");
            }
        }
    }

    //boka platsen på arrayposition "i"
    public void bokaPlats(int i) {
        bokadPlats[i] = true;
    }

    /**
     * Inre klass, pga composition-struktur.
     */
    private final class Plats {
        private int rad;
        private char plats;

        //Konstruktor, parametrarna kommer från Sal-konstruktorns loop
        Plats(int rad, char plats) {
            this.rad = rad;
            this.plats = plats;
        }

        @Override
        public String toString() {
            return Integer.toString(rad) + plats;
        }
    }

}
