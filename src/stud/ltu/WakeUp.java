/*************************************************************************
 *  Syfte: Programmet är ett enkelt program som hanterar några funktioner på ett gym, WakeUp.
 *
 *  Programmet skrevs som en del i Systemvetenskapsutbildningen på Luleå Tekniska Högskola(LTU)
 *  inom kursen D0019N - Programutveckling med Java. Uppgift 2.
 *
 *  Författare: Carl Granström
 *
 *  Datum: 2018-11-10
 *
 *************************************************************************/
package stud.ltu;

import java.util.Scanner;

public class WakeUp {

    /**Visa meny. Bygg om lösningen helt?
     * Förslag på förbättringar:
     * 1.Använd en stack för att hantera fram/tillbaka i menyn
     * 2.Gör om till grafiskt menysnitt, med JavaFX?
     */
    private static void visaMeny(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1.Bli medlem\n2.Logga in\n3.Boka plats på aktivitet\n4.Avsluta");
            byte menyVal = sc.nextByte();
            switch (menyVal) {
                case 1:
                    registreraMedlem();
                    break;
                case 2:
                    authenticate();
                    break;
                case 3:
                    bokaAktivitet();
                    break;
                case 4:
                    avsluta();
                default:
                    System.out.println("Vänligen välj genom att skriva in rätt siffra och bekräfta genom att trycka Enter");
            }
        }
    }

    //Autentisering
    private static boolean authenticate(){
        return false;
    }

    //Kostnadsberäkning
    private static int beraknaKostnad(){
        return 0;
    }

    /**Aktivitetsbokning, bygg om till grafisk lösning?
     * Förslag på förbättringar:
     * 1. Bokningsbara platser måste visas innan valet, ej bokningsbara ska inte erbjudas. Det är frustrerande.
     */
    private static void bokaAktivitet(){

    }

    private static void avsluta(){
        //Lägg in kod för att spara saker innan avslut???
        System.exit(0);
    }

    private static void registreraMedlem(){}

    //main method
    public static void main(String args[]){
        visaMeny();
    }
}
