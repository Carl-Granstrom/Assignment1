/*************************************************************************
 *  Syfte: Klassen är till för att hantera medlemmar i WakeUp:s system
 *
 *  Programmet skrevs som en del i Systemvetenskapsutbildningen på Luleå Tekniska Högskola(LTU)
 *  inom kursen D0019N - Programutveckling med Java. Uppgift 2.
 *
 *  Författare: Carl Granström
 *
 *  Datum: 2018-11-10
 *
 *************************************************************************/

package stud.ltu.WakeUpGym;

public class Medlem {
    private int personNummer;
    private String namn;

    private Status medlStatus;

    Medlem(){
        //initialisera medlemsstatus till inaktiv när ny medlem skapas
        Status medlStatus = Status.INACTIVE;
    }

    protected Status getStatus(){
        return this.medlStatus;
    }

    protected void setStatus(Status medlStatus){
        this.medlStatus = medlStatus;
    }
}
