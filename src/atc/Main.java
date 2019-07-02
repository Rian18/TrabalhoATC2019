package atc;

import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author cassi
 */
public class Main {

    public static void main(String[] args) {

        Scanner cmd = new Scanner(System.in);
        String comando;
        comando = cmd.nextLine();
        while (!comando.contentEquals(":q")) {
            if (comando.contentEquals(":l")) {
                System.out.println("[INFO] Digite nome do arquivo: ");
            } else if (comando.contentEquals(":s")) {

            } else if (comando.contentEquals(":i")) {

            } else if (comando.contentEquals(":q")) {
                // System.exit(0);
            } else if (comando.contentEquals(":p")) {
                String palavra = cmd.nextLine();
                Reconhecedor.reconheceLinguagem(palavra, AutomatoDAO.getInstance());
            } else {

                String[] tag = comando.split(": ");
                if (tag.length <= 1) {
                    System.out.println("[WARNING] Tag Inválida!");
                } else {
                    String nomeTag = tag[0];
                    char[] tagForChar = tag[1].toCharArray();

                    if (Validador.validaLinguagem(tagForChar) == true) {
                        AutomatoDAO.getInstance().add(AutomatoPilha.construirAutomato(tagForChar, nomeTag));
                        System.out.println("[INFO] Tag Válida!");

                    } else {
                        System.out.println("[WARNING] Tag Inválida!");

                    }

                }

            }
            comando = cmd.nextLine();
        }
    }

}
