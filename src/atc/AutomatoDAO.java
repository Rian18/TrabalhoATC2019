package atc;

import java.util.ArrayList;
import java.util.List;

public class AutomatoDAO {

    private static List<Automato> listaAutomato = new ArrayList<>();

    public static List<Automato> getInstance() {

        return listaAutomato;
    }

}
