package atc;

import static atc.Reconhecedor.setReconhece;
import static atc.Reconhecedor.setTag;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author cassi
 */
public class Main {

    public static void main(String[] args) {

        String tag = "ab+c.*";
        String nomeTag1 = "var1";
        char[] tagForChar = tag.toCharArray();
        AutomatoDAO.getInstance().add(AutomatoPilha.construirAutomato(tagForChar, nomeTag1));

        String nomeTag2 = "var2";
        String tag2 = "ab+d.*";

        char[] tagForChar2 = tag2.toCharArray();
        AutomatoDAO.getInstance().add(AutomatoPilha.construirAutomato(tagForChar2, nomeTag2));

        String nomeTag3 = "var3";
        String tag3 = "ab+z.*";

        char[] tagForChar3 = tag3.toCharArray();
        AutomatoDAO.getInstance().add(AutomatoPilha.construirAutomato(tagForChar3, nomeTag3));

        String palavra = "";

        int posicao_aux = 0;
        int posicao_aux2 = 0;

        System.out.println(palavra);
        while (posicao_aux <= palavra.length() - 1) {
            posicao_aux = matchPatterns(palavra, posicao_aux);
            posicao_aux2 = posicao_aux;
        }
        /*
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
         }*/
    }

    private static int matchPatterns(String palavra, int posicao) {

        List<Automato> automatos = AutomatoDAO.getInstance();
        List<String> palavras = new ArrayList<>();
        List<String> palavrasAuxiliar = new ArrayList<>();
        //List<Stack> list_comparacao = new ArrayList<>();
        Map<Automato, Integer> mapComparacao = new HashMap<Automato, Integer>();
        Map<Automato, String> mapComparacaoString = new HashMap<Automato, String>();

        //laço pra todos os automatos.
        for (int k = 0; k < automatos.size(); k++) {

            Stack<Transicao> pilhaTransicao = new Stack<>();

            int flag = 0;

            //toda vez que chamar o automato esse trem devera ser setado
            int flag_not_in = 1;

            int flag_not_finals = 0;
            int cont = 0;
            int i = 0;
            boolean breakFor = true;
            while (breakFor) {

                flag = 0;
                char c = palavra.charAt(i + posicao);

                List<Transicao> possiveisTransicoes = new ArrayList<>();

                for (int j = 0; j < automatos.get(k).getTransicoes().size(); j++) {

                    if (automatos.get(k).getTransicoes().get(j).getSimbolo() == c || automatos.get(k).getTransicoes().get(j).getSimbolo() == '*') {
                        possiveisTransicoes.add(automatos.get(k).getTransicoes().get(j));
                    }
                }

                Estado auxiliar = new Estado();
                auxiliar = automatos.get(k).getAtual();
                for (Transicao transicao : possiveisTransicoes) {

                    if (transicao.getOrigem() == automatos.get(k).getAtual()) {

                        if (transicao.getSimbolo() == '*') {
                            automatos.get(k).setAtual(transicao.getDestino());
                            pilhaTransicao.push(transicao);
                            flag = 1;
                            i = i - 1;
                            cont++;
                            break;
                        } else {
                            automatos.get(k).setAtual(transicao.getDestino());

                            pilhaTransicao.push(transicao);
                            flag = 1;
                            cont++;

                            break;
                        }
                    }

                }

                if (auxiliar == automatos.get(k).getAtual()) {
                    flag_not_in = 0;
                    breakFor = false;
                    boolean flag_estados = true;
                    int flag_finals = 0;

                    while (flag_estados) {
                        flag_finals = 0;
                        Estado estado_aux = new Estado();
                        if (pilhaTransicao.size() <= 1) {
                            mapComparacao.put(automatos.get(k), pilhaTransicao.size());
                            //list_comparacao.add(pilhaTransicao);
                            flag_estados = false;

                        } else {
                            estado_aux = pilhaTransicao.pop().getDestino();
                            for (Estado estado : automatos.get(k).getFinais()) {
                                if (estado_aux == estado) {
                                    mapComparacao.put(automatos.get(k), pilhaTransicao.size());
                                    //list_comparacao.add(pilhaTransicao);
                                    flag_finals = 1;
                                    break;

                                }

                            }
                            if (flag_finals == 1) {
                                flag_estados = false;
                            }

                        }
                    }

                }
                i++;

                if (i + posicao > palavra.length() - 1) {
                    breakFor = false;

                    flag_not_in = 0;
                    breakFor = false;
                    boolean flag_estados = true;
                    int flag_finals = 0;

                    while (flag_estados) {
                        flag_finals = 0;
                        Transicao estado_aux = new Transicao();
                        if (pilhaTransicao.size() <= 1) {
                            mapComparacao.put(automatos.get(k), pilhaTransicao.size());
                            //list_comparacao.add(pilhaTransicao);
                            flag_estados = false;

                        } else {

                            estado_aux = pilhaTransicao.pop();
                            for (Estado estado : automatos.get(k).getFinais()) {
                                if (estado_aux.getDestino() == estado) {
                                    pilhaTransicao.push(estado_aux);
                                    mapComparacao.put(automatos.get(k), pilhaTransicao.size());
                                    //list_comparacao.add(pilhaTransicao);
                                    flag_finals = 1;
                                    break;

                                }

                            }
                            if (flag_finals == 1) {
                                flag_estados = false;
                            }

                        }
                    }

                }

            }

            for (Estado estado : automatos.get(k).getFinais()) {
                if (estado == automatos.get(k).getAtual()) {
                    flag_not_finals = 1;
                    if (cont == 0) {
                        return posicao + 1;
                    } else {
                        if (posicao + cont - 1 > palavra.length() - 1) {
                            palavrasAuxiliar.add(palavra.substring(posicao));
                            mapComparacaoString.put(automatos.get(k), palavra.substring(posicao));

                        }
                        palavrasAuxiliar.add(palavra.substring(posicao, posicao + cont - 1));
                        mapComparacaoString.put(automatos.get(k), palavra.substring(posicao, posicao + cont - 1));
                    }
                }
            }
        }

        int maior = 0;

        for (int i = 0; i < mapComparacao.size(); i++) {
            if (maior < mapComparacao.get(i)) {
                maior = mapComparacao.get(i);

            }
        }

        for (Automato automato : AutomatoDAO.getInstance()) {
            automato.setAtual(automato.getInicial());

        }

        if (maior == 0) {
            System.out.println(palavra.substring(posicao, posicao + 1));
            return posicao + 1;
        } else {
            System.out.println(palavra.substring(posicao, posicao + maior));
            return (posicao + maior);

        }
    }

}
