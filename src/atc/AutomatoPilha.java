package atc;

import java.util.Stack;

/**
 *
 * @author Rian Alves
 */
public class AutomatoPilha {

    private static Stack<Automato> pilhaAuto = new Stack<Automato>();

    public static Automato construirAutomato(char[] tag, String tagNome) {
        pilhaAuto.clear();
        for (int i = 0; i < tag.length; i++) {
            if (tag[i] != '+' && tag[i] != '.' && tag[i] != '*') {
                Automato a = Automato.automatoSimples(tag[i]);
                pilhaAuto.push(a);
            } else if (tag[i] == '+') {
                Automato a = pilhaAuto.pop();
                Automato b = pilhaAuto.pop();
                Automato c = Automato.automatoAuxiliar();
                c.construirAuto(b, a);
                pilhaAuto.push(c);
            } else if (tag[i] == '.') {
                Automato a = pilhaAuto.pop();
                Automato b = pilhaAuto.pop();
                Automato c = Automato.automatoAuxiliar();
                c.concatenaAuto(b, a);
                pilhaAuto.push(c);
            } else if (tag[i] == '*') {
                Automato a = pilhaAuto.pop();
                //Automato c = pilhaAuto.pop();
                Automato b = new Automato();
                b = a;
                b.fechoKleen(a);
                pilhaAuto.push(b);
            }
        }
        Automato a = pilhaAuto.pop();
        a.setNome(tagNome);
        return a;
    }
}
