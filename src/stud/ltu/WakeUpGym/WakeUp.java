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
package stud.ltu.WakeUpGym;

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

    /**Autentisering
     * Förslag på förbättringar:
     * 1. Lös med klasser och objekt istället för bara via metod.
     */
    private static boolean authenticate(){
        return false;
    }

    //Kostnadsberäkning
    private static int beraknaKostnad(){
        return 0;
    }

    /**Aktivitetsbokning
     * Förslag på förbättringar:
     * 1. Bokningsbara platser måste visas innan valet, ej bokningsbara ska inte erbjudas. Det är frustrerande.
     * 2. Representera platserna med ngn typ av skärmutskrift för att visualisera salsplatserna
     * 3. Bygg grafisk lösning
     */
    private static void bokaAktivitet(){

    }

    private static void avsluta(){
        //Lägg in kod för att spara saker innan avslut???
        System.exit(0);
    }

    //Använd medlem-klassen för att skapa medlem-objekt. Kanske göra en MedlemsRegister-klass också?
    private static void registreraMedlem(){}

    //main method
    public static void main(String args[]){
        visaMeny();
    }
}
