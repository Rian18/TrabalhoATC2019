/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atc;

import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cassi
 */
public class Main {

    public static void main(String[] args) {

        List<Automato> automatos = new ArrayList<>();
        Automato a1 = Automato.automatoSimples('a');
        Automato a2 = Automato.automatoSimples('b');
        Automato a3 = Automato.automatoSimples('c');

        
        Automato a4 = Automato.automatoAuxiliar();
        a4.construirAuto(a1, a2);
        
        Automato a5 = Automato.automatoAuxiliar();
        a5.concatenaAuto(a4, a3);

      

        

        reconheceLinguagem("ac", a5);
        reconheceLinguagem("ab", a5);
        reconheceLinguagem("bc", a5);
        reconheceLinguagem("aa", a5);
        
     
        
       
        /*  
         a5.reconheceLinguagem("ab");
         a5.reconheceLinguagem("baab");
         a5.reconheceLinguagem("babababa");
         */
    }

    public static void reconheceLinguagem(String chave, Automato automato) {

         Estado inicial = automato.getInicial();
         List<Transicao> possiveisTransicoes = new ArrayList<>();
         int flag = 0;
         
         
        for (int i = 0; i < chave.length(); i++) {
            flag = 0;
            char c = chave.charAt(i);

            for (int j = 0; j < automato.getTransicoes().size(); j++) {
                
                if (automato.getTransicoes().get(j).getSimbolo() == c ) {
                    possiveisTransicoes.add(automato.getTransicoes().get(j));
                }
            }
            
        for (Transicao transicao:possiveisTransicoes){
            
            if(transicao.getOrigem() == automato.getAtual()){
                
                automato.setAtual(transicao.getDestino());
                flag = 1;
            }

        }
        }
        
        if(flag==0){
            System.out.println("Não Reconhece: " + chave);
            
        }

        else{
            System.out.println("Reconhece: " + chave);
        }
        automato.setAtual(automato.getInicial());
    }
}
