package stud.ltu.WakeUpGym;

import java.util.Date;

public class Abonnemang {
    private Date startDatum;
    /*
    * Behövs en mer avancerad Date-tjoflöjt för att omsätta månader till prenumerationslängd
    * Eventuellt anta att en månad alltid är 30 dagar, men måste fortfarande räkna rätt på dagarna.
    */
    private Date slutDatum;
    private int kostnad;
    private boolean medlem = false; //

    //beräkna kostnaden för abonnemanget utifrån om personen redan är medlem eller ej samt antal månader.
    Abonnemang(int manader, boolean medlem) {
        this.medlem = medlem;
        this.kostnad = beraknaKostnad(manader, medlem);
    }

    /*
    * Ersätt bool param med metodanr. till metod isMedlem(); lagra isMedlem(bool) i Medlem?
    */
    public int beraknaKostnad(int manader, boolean isMedlem){
        int totalKostnad;  //placeholder
        final int basPris = 250;
        final int extra = 50;

        //Kontrollera att param månader är ett lagligt värde, glöm ej excep-handling längre "upp" i programmet.
        if (manader <= 0) {
            throw new IllegalArgumentException("Antal månader måste vara ett positivt heltal!");
        }

        //Stör mig på att intervallet 1-2 månader inte är en multiplier av 3, då hade den här koden kunnat vara vackrare
        if (manader > 12) {
            totalKostnad = manader * basPris;
        }
        else if (manader > 6) {
            totalKostnad = (manader * basPris) + (manader * extra);
        }
        else if (manader > 2) {
            totalKostnad = (manader * basPris) + (manader * extra * 2);
        }
        else {
            totalKostnad = (manader* basPris) + (manader * extra * 3);
        }
        /*Oklart hur detta ska hanteras längre upp i programmet, men denna uträkning måste ske innan medlemskapet
        registreras på personen. */
        if (!isMedlem) {
            totalKostnad += 100;
        }

        return totalKostnad;
    }
}
