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
    private Plats[] platser; //skapa array med antal platser (null init)
    private boolean[] bokadPlats; //detta är en "ok" lösning (false init)
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
        this.platser = new Plats[rader * platserPerRad];
        this.bokadPlats = new boolean[rader * platserPerRad];
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

    public boolean isLedig(int i){
        assert i < platser.length;      //Assert legal index into array
        return !bokadPlats[i];
    }

    //boka platsen på arrayposition "i"
    public void bokaPlats(int i) {
        bokadPlats[i] = true;
    }

    //används ej atm TODO Eventuellt ta bort
    public boolean[] getPlatsStatus() {
        //returnera array med information om platsen är bokad(ej bokad plats=false, bokad plats=true)
        return bokadPlats;
    }

    public void printSal(){
        printSolidLine();
        for (int i = 0; i < rader; i++){
            printPlatsLine(i);
            printSolidLine();
        }
    }

    //skriv ut avskiljare mellan platserna vågrätt, samt övre och under kant
    private void printSolidLine(){
        for (int i = 0; i < platserPerRad; i++){
            System.out.print("----");
        }
        System.out.println();
    }

    /**
     * Skriv ut platsnummer för lediga platser, XX för upptagna
     * @param rad det radnummer som ska skrivas ut.
     */
    private void printPlatsLine(int rad){
        for (int i = rad; i < (platserPerRad * (rad + 1)); i++){
                System.out.print("|" + platsOrBokad(i) + "|");
        }
        System.out.println();
    }

    private String platsOrBokad(int i){
        if (bokadPlats[i] == false) {
            return platser[i].toString();
        } else {
            return "XX";
        }
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
