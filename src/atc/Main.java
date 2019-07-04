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
                //Inserir rotina para reconhecimento de cada parte. 
                String palavra = cmd.nextLine();
                           
                char[] palavraForChar = palavra.toCharArray();
                //Reconhecedor.reconheceLinguagem(palavra, AutomatoDAO.getInstance());
                marcadorPalavra(palavraForChar);
            } else {

                String[] tag = comando.split(": ");
                if (tag.length <= 1) {
                    System.out.println("[WARNING] Tag Inv�lida!");
                } else {
                    String nomeTag = tag[0];
                    char[] tagForChar = tag[1].toCharArray();

                    if (Validador.validaLinguagem(tagForChar) == true) {
                        AutomatoDAO.getInstance().add(AutomatoPilha.construirAutomato(tagForChar, nomeTag));
                        System.out.println("[INFO] Tag V�lida!");

                    } else {
                        System.out.println("[WARNING] Tag Inv�lida!");

                    }

                }

            }
            comando = cmd.nextLine();
        }
    }
    
    public static void marcadorPalavra(char[] palavraForChar)
    {
       
        char[] auxiliar = new char[palavraForChar.length];
        String teste = new String();
        for(int i=0; i<palavraForChar.length;i++)
        {              
            auxiliar[i]= palavraForChar[i];
            teste +=Character.toString(palavraForChar[i]);
            
            System.out.println(Character.toString(palavraForChar[i])); 
            Reconhecedor.reconheceLinguagem(teste, AutomatoDAO.getInstance());
            if(Reconhecedor.isReconhece())
            { 
                System.out.println("Reconhece at� aqui "+ auxiliar);
            
            }else
            {
                System.out.println("PAROU Aqui: " + auxiliar);
            }
            
          
        
        }
    
    }

}
