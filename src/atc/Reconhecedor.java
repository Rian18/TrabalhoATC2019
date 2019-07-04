package atc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rian Alves
 */
public class Reconhecedor {

    private static boolean reconhece;
    private static String tag;
    public static void reconheceLinguagem(String chave, List<Automato> automatos) {

        for (int k = 0; k < automatos.size(); k++) {
            int flag = 0;

            //toda vez que chamar o automato esse trem devera ser setado
            int flag_not_in = 1;
            int flag_not_finals = 0;

            for (int i = 0; i < chave.length(); i++) {
                flag = 0;
                char c = chave.charAt(i);

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
                            flag = 1;
                            i = i - 1;
                            break;
                        } else {
                            automatos.get(k).setAtual(transicao.getDestino());
                            flag = 1;
                            break;
                        }
                    }

                }

                if (auxiliar == automatos.get(k).getAtual()) {
                    flag_not_in = 0;
                }

            }

            for (Estado estado : automatos.get(k).getFinais()) {
                if (estado == automatos.get(k).getAtual()) {
                    flag_not_finals = 1;
                }
            }

            if (flag == 0 || flag_not_in == 0 || flag_not_finals == 0) {
                System.out.println(automatos.get(k).getNome() + " não Reconhece: " + chave);
                automatos.get(k).setAtual(automatos.get(k).getInicial());
                setReconhece(false);
            } else {
                System.out.println(automatos.get(k).getNome() + " reconhece: " + chave);
                automatos.get(k).setAtual(automatos.get(k).getInicial());
                setTag(automatos.get(k).getNome());
                setReconhece(true);
             
            }
            setReconhece(false);
        }
       
    }

  
    public static boolean isReconhece() {
        return reconhece;
    }

    
    public static void setReconhece(boolean aReconhece) {
        reconhece = aReconhece;
    }

    
    public static String getTag() {
        return tag;
    }

  
    public static void setTag(String aTag) {
        tag = aTag;
    }
}
