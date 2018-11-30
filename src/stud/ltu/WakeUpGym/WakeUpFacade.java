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

    //Använd Medlem-klassen för att skapa medlem-objekt.
    private static void registreraMedlem(){
        medlReg.addMedlem(new Medlem());
    }

    /**
     * Kontroll av personnummret.
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

    private static void forlangMedlemskap(){
        if (user == null) {
            System.out.println("Du måste logga in innan du kan förlänga ditt medlemskap!");
        } else {
            user.setAbonnemang();
            System.out.println("Ett " + user.getAbonnemang().getManader() + " månaders abonnemang har registrerats på dig! Grattis!");
        }
    }

    //Visa möjliga alternativ på aktiviteter och boka plats på en aktivitet
    private static void bokaPlats(Medlem m){
        Sal s;
        int val = -1;       //sentinel value
        if (user == null){
            throw new IllegalStateException("Du behöver logga in innan du kan boka en plats!");
        } else {
            geAktivitetsAlternativ();
        }

        Scanner sc = new Scanner(System.in);
        try {
            val = Integer.parseInt(sc.nextLine()) - 1;     //Läs in menyvalet
        } catch (NumberFormatException e){e.printStackTrace();}

        if ((val >= 0) && (val <= Aktivitet.values().length)){  //kontrollera giltigt val
            Aktivitet tmp = getAktivitet(val);                  //hämta vilken aktivitet som valet är associerat med
            s = salsReg.getAktivitetsSal(tmp);                  //använd Aktivitet:en för att matcha mot Sal i registret
            System.out.println(s.getNamn() + " - "
                    + s.getAktivitet().toString());             //Skriv ut namn på salen och den aktivitet som utförs där
            s.printSal();                                       //skriv ut en representation av salens platser
        } else {
            throw new IllegalArgumentException("Aktiviteten finns inte, vänligen ange giltig aktivitet:");
        }
        if (s.redanBokat(m)){
            throw new IllegalCallerException("Du har redan bokat en plats på det här passet!");
        } else {
            s.bokaPlats(platsValInput(), m);
        }
    }

    private static char[] platsValInput(){
        char[] val = new char[2];
        Scanner sc = new Scanner(System.in);
        System.out.println("Vänligen skriv ditt platsval på formatet [Bokstav][Siffra](ex: 4c): ");
        String tmpString = sc.nextLine();  //store input String
        char[] tmpCharArray = tmpString.toCharArray(); //convert String to charArray
        //move the 2 first characters of the charArray to val char[].
        for (int i = 0; i < 2; i++) {
            val[i] = tmpCharArray[i];
        }
        return val;
    }

    private static void geAktivitetsAlternativ(){
        int n = Aktivitet.values().length;
        System.out.printf("Vilken aktivitet vill du boka? Skriv en siffra mellan 1 och %d", n);
        //Skriv ut möjliga aktiviteter ur Aktivitet-enum och numrera automatiskt.
        for (Aktivitet a : Aktivitet.values()){
            System.out.printf("%n%d.%s", a.ordinal() + 1, a);
        }
        System.out.println();
    }

    private static Aktivitet getAktivitet(int val){
        for (Aktivitet a : Aktivitet.values()){
            if(val == a.ordinal()){
                return a;
            }
        }
        return null;
    }

    //avsluta programmet
    private static void avsluta(){
        System.exit(0);
    }

    //main method
    public static void main(String args[]){
        visaMeny();
    }
}
