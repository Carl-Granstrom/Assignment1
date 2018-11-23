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
        register.add(new Sal("Lotusblomman",3,3));      //ph, move to method
        register.add(new Sal("Himalaya", 5, 5));        //ph
        register.add(new Sal("Lilla Nirvana", 4, 6));   //ph
    }
}
