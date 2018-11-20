package stud.ltu.WakeUpGym;

import java.util.ArrayList;

/**
 * Testar Singleton-pattern
 */
public final class SalsRegister {
    private static SalsRegister ourInstance = new SalsRegister();

    public static SalsRegister getInstance() {
        return ourInstance;
    }

    /**
     * Skapar i nuläget några salar statiskt som en placeholder för test.
     */
    private SalsRegister() {
        ArrayList<Sal> register = new ArrayList<>();

        register.add(new Sal("Lotusblomman",3,3));      //placeholder
        register.add(new Sal("Himalaya", 5, 5));        //ph
        register.add(new Sal("Lilla Nirvana", 4, 6));   //ph
    }
}
