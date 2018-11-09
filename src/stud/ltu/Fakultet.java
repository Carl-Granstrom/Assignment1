package stud.ltu;

import java.util.Scanner;

public class Fakultet {

    static private Scanner sc = new Scanner(System.in);
    static private int n = inputTal();

    Fakultet(){}

    public static int getN() {
        return n;
    }

    private static int inputTal() {
        System.out.println("Vänligen skriv in ett naturligt tal för beräkning av fakultet: ");
        n = sc.nextInt();
        while (!naturligtTal(n)) {
            System.out.println("Fakultet är endast definierat för naturliga tal, vänligen försök igen");
            n = sc.nextInt();
        }
        return n;
    }

    private static int beraknaFakultet(int n) {

        int resultat;

        //placeholder method
        resultat = 1;

        return resultat;
    }

    private static boolean naturligtTal(int n) {
        return (n >= 0);
    }

    public static void main(String[] args){
        System.out.println("Fakulteten för " + Fakultet.getN() + " är: " + Fakultet.beraknaFakultet(n));
    }
}
