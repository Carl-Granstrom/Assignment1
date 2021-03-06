package stud.ltu.Assignment1;
/**
 *  Syfte: Programmet hanterar beräkningar av Fakultetsfunktionen. Programmet hanterar felaktig
 *  input från användaren i form av negativa tal, fakultetsfunktionen växer så snabbt att
 *  även long drabbas av overflow-problem. i praktiken hanterar inte programmet fakultet högre än 20
 *  sedan blir resultatet för stort för long.
 *
 *  Föbättring: BigInteger skulle kunna användas för att hantera ännu större tal.
 *
 *  Programmet skrevs som en del i Systemvetenskapsutbildningen på Luleå Tekniska Högskola(LTU)
 *  inom kursen D0019N - Programutveckling med Java. Uppgift 1.2.
 *
 *  @author Carl Granström
 *
 *  Datum: 2018-11-09
 */

import java.util.Scanner;

public class Fakultet {

    //Har skippat konstruktorn då metoderna är statiska, kanske är en dålig idé...
    static private Scanner sc = new Scanner(System.in);  //skapar en Scanner för att kunna läsa input
    static private long n = inputTal();  //använder long eftersom fakultet snabbt blir väldigt stor

    public static long getN() {
        return n;
    }  //metoden behövs inte för nuvarande implementation, men är bra träning

    private static long inputTal() {
        System.out.println("Vänligen skriv in ett naturligt tal för beräkning av fakultet: ");
        n = sc.nextByte(); //läs in byte från användaren
        while (!accTal(n)) {
            System.out.println("Inte negativt, men inte heller över 20, vänligen skriv ett nytt tal");
            n = sc.nextByte();
        }
        return n;  //implicit konvertering från byte till long via promotion
    }

    private static long beraknaFakultet(long n) {
        //Fakultet 0(0!) = 1
        if (n == 0) {
            return 1;
        }
        //Beräkna fakultet rekursivt
        if (n > 1) {
            n = (n * beraknaFakultet(n - 1));
        } else {
            n *= n;
        }
        return n;
    }

    private static boolean accTal(long n) {
        return (n >= 0 && n <= 20); //kontrollera att det är input som programmet kan hantera
    }

    public static void main(String[] args){
        //Skriv ut resultat
        System.out.println("Fakulteten för " + Fakultet.getN() + " är: " + Fakultet.beraknaFakultet(n));
    }
}
