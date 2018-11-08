package stud.ltu;

import java.util.Scanner;

public class Fakultet {

    static private Scanner sc = new Scanner(System.in);
    static private int n = naturligtTal();


    private Fakultet(){}

    public static int getN() {
        return n;
    }

    public static int beraknaFakultet(int n) {

        int resultat;

        //placeholder method
        resultat = 1;

        return resultat;
    }

    //FELSÖK!
    public static int naturligtTal() {
        System.out.println("Vänligen skriv in ett naturligt tal för beräkning av fakultet: ");
        int tal = sc.nextInt();

        //kontrollera att n inte är negativt och be om ny input om det är negativt
        if (tal == 0) {
            return 0;
        } else if (tal < 0) {
            System.out.println("Fakultetsfunktionen är endast definierad för naturliga tal.");
            System.out.println();
            naturligtTal();
        }

        return tal;
    }

    public static void main(String[] args){

        //skapa fakultetobjekt, skulle kunna vara abstrakt klass iofs, kanske senare om jag orkar
        Fakultet fak = new Fakultet();
        //Be om input från användaren
        //System.out.println("Beräkna fakultet(n!): skriv in ett heltal: ");
        System.out.println("Fakulteten för " + fak.getN() + " är: " + fak.beraknaFakultet(n));
    }
}
