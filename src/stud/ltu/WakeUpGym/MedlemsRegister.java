package stud.ltu.WakeUpGym;

import java.util.ArrayList;

public final class MedlemsRegister {
    private static MedlemsRegister singleRegister = new MedlemsRegister();
    ArrayList<Medlem> medlemsRegister = new ArrayList<Medlem>();

    public static MedlemsRegister getInstance() {
        return singleRegister;
    }

    private MedlemsRegister(){

    }

    //behöver hantera/throw error om medlem ej har giltigt pnr
    public void addMedlem(Medlem medlem){
        if (Auth.pnrIsLegal(medlem)){
            medlemsRegister.add(medlem);
        }
        else {
            //måste fångas av ett catch-block när metoden MedlemsRegister.getInstance().addMedlem() anropas.
            throw new IllegalStateException("Medlemmen har felaktigt personnummer!");
        }
    }
}
