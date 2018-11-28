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

    /**
     * precondition medlemmens int-array personNummer är 10 lång
     * TODO kolla att algon funkar som den ska, verkar eventuellt returnera true jämt
     */
    public static boolean pnrIsLegal(Medlem medlem) {
        int[] pnr = medlem.getPersonNummer();
        int sum = 0;
        /*
        for(int i : pnr){
            System.out.println(i);
        }
        System.out.println(pnr.length);
        */
        assert (pnr.length == 10);    //assert precondition is true

        //multiplicera med 2 och summera array, IntStream-implementation, Deitel & Deitel (s. 762)
            /*
            commented out bc not suitable to problem
            int sum = IntStream.of(pnr).map(x -> x * 2).sum();
            */

        //multiplicera varannan med 2 och summera, foor-loop implementation
        for (int i = 0; i < 10; i++){
            if (i % 2 == 0){
                int tmp = pnr[i] * 2;
                if (tmp < 10) {
                    sum += pnr[i] * 2;
                } else {             //hantera 10-18 så att tiotals-ettan endast räknas som 1 i summeringen
                    sum += 1;
                    sum += tmp % 10;
                }
            } else {
                sum += pnr[i];
            }
        }
        return (sum % 10 == 0); // return true if the sum is evenly divisible by 10
    }
}
