package stud.ltu.WakeUpGym;

import java.util.stream.IntStream;

/**
 * Statisk klass för att samla autentiseringsmetoder i programmet.
 * TODO Auth-metoder för medlemmar i systemet: betalande eller ej?
 */
public class Auth {

    //går ej att instantiera
    private Auth(){
    }

    //precondition medlemmens int-array personNummer är 10 lång
    public static boolean pnrIsLegal(Medlem medlem) {
        int[] pnr = medlem.getPersonNummer();
        assert (pnr.length == 10);    //assert precondition is true

        //multiplicera med 2 och summera array, IntStream-implementation, Deitel & Deitel (s. 762)
        int sum = IntStream.of(pnr).map(x -> x * 2).sum();

        return (sum % 10 == 0); // return true if the sum is evenly divisible by 10
    }
}
