package stud.ltu.WakeUpGym;

/**
 * Test-klass för Sal-klassen som testar utskrifsfunktionen.
 * Kan för närvarande ge ArrayIndexOutOfBoundsExceptions om rader/platyserPerRad ändras till för stora värden.
 */
public class SalTest {
    public static void main(String[] args){
        Sal s1 = new Sal(Aktivitet.AEROBICS ,"Himalaya", 4, 5);
        Sal s2 = new Sal(Aktivitet.YOGA ,"Yogi", 10, 12);

        s1.bokaPlats(3);
        s1.bokaPlats(3);
        s1.bokaPlats(8);
        s1.bokaPlats(3);
        s1.bokaPlats(11);
        s1.bokaPlats(17);

        s1.printSal();

        s2.bokaPlats(3);
        s2.bokaPlats(8);
        s2.bokaPlats(18);
        s2.bokaPlats(19);
        s2.bokaPlats(20);
        s2.bokaPlats(22);
        s2.bokaPlats(13);
        s2.bokaPlats(18);
        s2.bokaPlats(28);
        s2.bokaPlats(29);
        s2.bokaPlats(30);
        s2.bokaPlats(32);
        s2.bokaPlats(49);
        s2.bokaPlats(50);
        s2.bokaPlats(51);
        s2.bokaPlats(67);
        s2.bokaPlats(69);
        s2.bokaPlats(87);
        s2.bokaPlats(103);
        s2.bokaPlats(108);
        s2.bokaPlats(118);
        s2.bokaPlats(119);

        s2.printSal();
    }
}
