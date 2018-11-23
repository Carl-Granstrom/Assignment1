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
    }

    protected Status getStatus(){
        return this.medlStatus;
    }

    protected void setStatus(Status medlStatus){
        this.medlStatus = medlStatus;
    }

    public int[] getPersonNummer() {
        return this.personNummer;
    }
}
