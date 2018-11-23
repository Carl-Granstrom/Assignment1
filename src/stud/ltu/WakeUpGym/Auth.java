package stud.ltu.WakeUpGym;

import java.util.stream.IntStream;

/**
 * TODO Några saker behöver kollas angående personnumret som slagits in, annars throw exception, och hantera detta
 * TODO längre upp i programmet: endast 0-9 i char array, char array 10 lång.
 */
public class Auth {


    Auth(Medlem medlem){

    }

    //precondition: char array c[] är 10 chars lång
    private static boolean pnrIsLegal(char[] c) {
        int[] numbers = new int[10];
        //convert char array to int array
        for (int i = 0; i < 10; i++){
            numbers[i] = Character.getNumericValue(c[i]); //return numeric val of char at index i, store in numbers[i]
        }

        //multiplicera med 2 och addera, IntStream-implementation, Deitel & Deitel (s. 762)
        int sum = IntStream.of(numbers).map(x -> x * 2).sum();

        return (sum % 10 == 0); // return true if the sum is evenly divisible by 10
    }
}
