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
    //konstruktor
    WakeUpFacade(){}

    /**
     * Visa meny.
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
                            continueLoop = false;}
                        catch (InputMismatchException e) { System.out.println(e);}
                        catch (IllegalStateException e) { System.out.println(e);}
                        catch (Exception e) {
                            System.out.println("Unexpected error type! Please submit bug report!");
                            System.out.println(e);
                        }
                    } while (continueLoop);
                    break;

                case 2:
                    do {
                        try {
                            loggaIn();
                            continueLoop = false;
                        } catch (Exception e) { System.out.println(e);}
                    } while (continueLoop);
                    break;

                case 3:
                    do {
                        try {
                            forlangMedlemskap();
                            continueLoop = false;
                        } catch (Exception e) { System.out.println(e);}
                    } while (continueLoop);
                    break;

                case 4:
                    try { bokaPlats(user); }
                    catch (Exception e) { System.out.println(e);}
                    break;

                case 5:
                    avsluta();

                default:
                    System.out.println("Vänligen välj genom att skriva in rätt siffra och bekräfta genom att trycka Enter");
            }
        }
    }

    /**Autentisering
     * TODO Snygga till och flytta ut vissa delar till metod(er)
     */
    private static void loggaIn(){
        int[] tmpPnr = getPnrInput();   //hämta int-array med personnummer från användaren.

        //Sök i registret efter ett matchande personnummer
        Medlem tmpMedlem = sokRegister(tmpPnr);     //returnera första medlemmen med matchande personnummer från registret
        if (tmpMedlem == null){
            System.out.println("Ogiltig inloggning, försök igen");  //Skriv ut felmeddelande,
        } else{
            user = tmpMedlem;
            System.out.printf("Logged in as: %n" + user.toString());
        }
    }

    private static int[] getPnrInput(){
        Scanner sc = new Scanner(System.in);
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
        return tmpPnr;
    }

    //iterera över alla medlemmarna och jämför deras pnr med det inslagna
    private static Medlem sokRegister(int[] tmpPnr){
        Medlem tmpMedl = null;
        for (Medlem medl : medlReg.medlemsRegister) {
            if (Arrays.equals(medl.getPersonNummer(), tmpPnr)){
                tmpMedl = medl;
            }
        }
        return tmpMedl;
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
        System.exit(0);
    }

    //Använd Medlem-klassen för att skapa medlem-objekt.
    private static void registreraMedlem(){
        medlReg.addMedlem(new Medlem());
    }

    //placeholder method
    private static void bokaPlats(Medlem m){
        if (user == null){
            throw new IllegalStateException("Du behöver logga in innan du kan boka en plats!");
        } else {
            int n = Aktivitet.values().length;
            System.out.printf("Vilken aktivitet vill du boka? Skriv en siffra mellan 1 och %d", n);
            //Skriv ut möjliga aktiviteter ur Aktivitet-enum och numrera automatiskt.
            for (Aktivitet a : Aktivitet.values()){
                System.out.printf("%n%d.%s", a.ordinal() + 1, a);
            }
        }
        //TODO Do stuff!
    }

    //main method
    public static void main(String args[]){
        visaMeny();
    }
}
