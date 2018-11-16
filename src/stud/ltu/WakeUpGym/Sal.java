package stud.ltu.WakeUpGym;
/**
 * Förutsätter jämnt antal platser per rad, är en acceptabel lösning i nuläget.
 */

import java.util.ArrayList;

public final class Sal {
    private String namn;
    private int rader;
    private int platserPerRad;
    //Använder inte ArrayList då jag inte vill att platserna ska flyttas runt i arrayen med några list-metoder.
    private final Plats[] platser = new Plats[rader * platserPerRad + 1]; //skippar 0-index

    //Konstruktor
    Sal(String namn, int rader, int platserPerRad) {
        this.namn = namn;
        this.rader = rader;
        this.platserPerRad = platserPerRad;
    }

    @Override
    public String toString() {
        return namn;
    }

    /**
     * Inre klass, pga composition-struktur.
     */
    private final class Plats {
        private int rad;
        private char plats;

        //Konstruktor
        //TODO Kanske kan gå att generera rad och plats automatiskt utifrån indexeringen av objekten i salarna
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
