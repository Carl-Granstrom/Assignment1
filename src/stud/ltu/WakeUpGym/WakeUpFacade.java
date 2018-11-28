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

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class WakeUpFacade {
    private static Medlem user;
    private static MedlemsRegister medlReg = MedlemsRegister.getInstance();
    private static SalsRegister salsReg = SalsRegister.getInstance();

    /**
     * konstruktor
     */
    WakeUpFacade(){

    }


    /**
     * Visa meny
     */
    private static void visaMeny(){
        while (true) {
            Scanner sc = new Scanner(System.in);
            boolean continueLoop = true;
            System.out.println("\n1.Bli medlem\n2.Logga in\n3.Förläng medlemskap\n4.Boka plats på aktivitet\n5.Avsluta");
            //ta input till menyval TODO Error handling på inkorrekt input
            byte menyVal = sc.nextByte();
            switch (menyVal) {
                case 1:
                    do {
                        try {
                            registreraMedlem();
                            continueLoop = false;
                        } catch (InputMismatchException e) {
                            System.out.println(e);
                        } catch (IllegalStateException e) {
                            System.out.println(e);
                        } catch (Exception e) {
                            System.out.println("Unexpected error type! Please submit bug report!");
                            System.out.println(e);
                        }
                    } while (continueLoop);
                    continueLoop = true;
                    break;

                case 2:
                    do {
                        try {
                            //logga in som testmedlem "Magnus Ladulås"
                            loggaIn();
                            continueLoop = false;
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    } while (continueLoop);
                    continueLoop = true;
                    break;

                case 3:
                    do {
                        try {
                            //
                            forlangMedlemskap();
                            continueLoop = false;
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    } while (continueLoop);
                    continueLoop = true;
                    break;

                case 4:
                    do {
                        try {
                            //
                            bokaPlats();
                            continueLoop = false;
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    } while (continueLoop);
                    continueLoop = true;
                    break;

                case 5:
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
    private static void loggaIn(){
        Scanner sc = new Scanner(System.in);
        boolean isValid = false;
        int[] tmpPnr = new int[10];

        System.out.println("Vänligen skriv in ditt personnummer som tio siffror och tryck Enter för att logga in:");
        String tmpString = sc.nextLine();  //store input String
        char[] tmpCharArray = tmpString.toCharArray(); //convert String to charArray
        //move the 10 first characters of the charArray to personNummer int-array.
        for (int i = 0; i < 10; i++){
            char c = tmpCharArray[i];
            int siffra = Character.getNumericValue(c);
            if (siffra > 9 || siffra < 0) {
                throw new InputMismatchException("Input contains non-numeric characters!");
            }
            tmpPnr[i] = siffra;  //för in siffran i temporär array för att matcha mot pnr i MedlemsRegister
        }
        //iterera över alla medlemmarna och jämför deras pnr med det inslagna.
        for (Medlem medl : medlReg.medlemsRegister){
            if (Arrays.equals(medl.getPersonNummer(), tmpPnr)){
                user = medl;
                isValid = true;
                System.out.print("Logged in as: \n" + user.toString());
            }
        }
        if (!isValid){
            System.out.println("Ogiltig inloggning, försök igen");
        }
    }


    /**
     * Aktivitetsbokning
     * TODO Hela logiken i Sal-klassen
     * TODO Bokningsbara platser måste visas innan valet, ej bokningsbara ska inte erbjudas. Det är frustrerande.
     * TODO Representera platserna med ngn typ av skärmutskrift för att visualisera salsplatserna
     */
    private static void bokaAktivitet(){
        if (user == null){
            System.out.println("Du måste logga in innan du kan boka en plats");
        } else if (user.getStatus() != Status.ACTIVE){    //endast aktiva medlemmar får boka en plats

        }
    }

    private static void forlangMedlemskap(){
        if (user == null) {
            System.out.println("Du måste logga in innan du kan förlänga ditt medlemskap!");
        } else {
            user.setAbonnemang();
            System.out.println("Ett " + user.getAbonnemang().getManader() + " månaders abonnemang har registrerats på dig! Grattis!");
        }
    }

    private static void avsluta(){
        //Lägg in kod för att spara saker innan avslut???
        System.exit(0);
    }

    //Använd medlem-klassen för att skapa medlem-objekt. Kanske göra en MedlemsRegister-klass också?
    private static void registreraMedlem(){
        medlReg.addMedlem(new Medlem());
    }

    //placeholder method
    private static void bokaPlats(){

    }

    //main method
    public static void main(String args[]){
        visaMeny();
    }
}
