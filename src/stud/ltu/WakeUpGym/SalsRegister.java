package stud.ltu.WakeUpGym;

import java.util.ArrayList;

/**
 * Testar Singleton-pattern
 */
public final class SalsRegister {
    private static SalsRegister singleRegister = new SalsRegister();

    ArrayList<Sal> register = new ArrayList<>();

    public static SalsRegister getInstance() {
        return singleRegister;
    }

    /**
     * Skapar i nuläget några salar statiskt som en placeholder för test.
     */
    private SalsRegister() {
        register.add(new Sal(Aktivitet.YOGA ,"Lotusblomman",3,3));      //ph, move to method
        register.add(new Sal(Aktivitet.SPINNING ,"Racecourse", 5, 5));        //ph
        register.add(new Sal(Aktivitet.AEROBICS ,"Lilla Nirvana", 4, 6));   //ph
    }

    //TODO FORTSÄTT HÄR MED: returnera alla salar som går att boka m.h.a. en print-metod för val i WakeUpFacade
}
