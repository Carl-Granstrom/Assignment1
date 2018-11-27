/*************************************************************************
 *  Syfte: Klassen är till för att hantera medlemmar i WakeUp:s system. Medlemmen skapas innan autentisering av
 *  personnummer sker. Autentisering måste ske innan lagring i medlemsregister.
 *
 *  Programmet skrevs som en del i Systemvetenskapsutbildningen på Luleå Tekniska Högskola(LTU)
 *  inom kursen D0019N - Programutveckling med Java. Uppgift 2.
 *
 *  @author Carl Granström
 *
 *  Datum: 2018-11-10
 *
 *************************************************************************/

package stud.ltu.WakeUpGym;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Medlem {
    private int[] personNummer = new int[10];  //storing as array also permits leading zeroes
    private String namn;
    private Status medlStatus;
    private Abonnemang abonnemang;

    //inte jättevackert att be om user input i själva konstruktorn, men det får duga TODO rensa upp i skiten
    Medlem(){
        //initialisera medlemsstatus till inaktiv när ny medlem skapas
        Status medlStatus = Status.INACTIVE;
        Scanner sc = new Scanner(System.in);
        System.out.println("Vänligen skriv ditt namn och tryck på Enter:");
        this.namn = sc.nextLine();  //TODO ordna ngn check på namnet så det blir ok. Bryt ut till statisk metod.
        //TODO bryt ut personnummerhanteringen till statisk metod
        System.out.println("Vänligen skriv in ditt personnummer som tio siffror och tryck Enter:");
        String tmpString = sc.nextLine();  //store input String
        char[] tmpCharArray = tmpString.toCharArray(); //convert String to charArray
        //move the 10 first characters of the charArray to personNummer int-array.
        for (int i = 0; i < 10; i++){
            char c = tmpCharArray[i];
            int siffra = Character.getNumericValue(c);
            if (siffra > 9 || siffra < 0) {
                throw new InputMismatchException("Input contains non-numeric characters!");
            }
            personNummer[i] = siffra;  //för in siffran i personNummer array.
        }
        System.out.println("Hur många månader vill du vara medlem?\nSkriv in en siffra: ");
        this.abonnemang = new Abonnemang(sc.nextInt(), medlStatus);
    }

    //skapa färdig testmedlem för registrering som default i MedlemsRegister
    Medlem(boolean b){

        this.namn = "Erik Ladulås";
        int[] tmp = {8, 3, 0, 9, 2, 9, 0, 3, 1, 3};
        this.personNummer = tmp;
        //initialisera medlemsstatus till inaktiv när ny medlem skapas
        this.medlStatus = Status.ACTIVE;
        this.abonnemang = new Abonnemang(16, this.medlStatus);
    }

    public Status getStatus(){
        return this.medlStatus;
    }

    public Abonnemang getAbonnemang(){
        return abonnemang;
    }

    //TODO inte färdig. Ska anropa input-logiken för antal månader och this.getStatus när detta flyttats ut från konstruktorn
    public void setAbonnemang(){
        //skapa ett nytt abonnemang på Medlem:en
        this.abonnemang = new Abonnemang(12, Status.INACTIVE);
    }

    public int[] getPersonNummer() {
        return this.personNummer;
    }

    @Override
    public String toString() {
        //build String out of int array personNummer
        StringBuilder b = new StringBuilder();
        for (int i : personNummer){
            b.append(i);
        }
        //return namn, pnr och det datum som abonnemanget tar slut
        return "Namn: " + namn + "\n Pnr: " + b.toString() + "\n" + getAbonnemang().toString() + "\n" + "Abbonemanget kostar: " + getAbonnemang().getKostnad() + ":-\n";
    }
}
