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
        
        Automato a6 = Automato.automatoAuxiliar();
        a6 = a5;
        
        a6.fechoKleen(a5);
      

        

        reconheceLinguagem("acac", a6);
        reconheceLinguagem("abac", a6);
        reconheceLinguagem("abacacc", a6);
        reconheceLinguagem("abacac", a6);
        reconheceLinguagem("abacac", a6);
        reconheceLinguagem("abacad", a6);
        reconheceLinguagem("abazcad", a6);
        reconheceLinguagem("aba", a6);
        reconheceLinguagem("abc", a6);
        reconheceLinguagem("bcac", a6);
        reconheceLinguagem("bcaa", a6);
        reconheceLinguagem("ac", a6);
        reconheceLinguagem("acacacaca", a6);
        reconheceLinguagem("acbcac", a6);
        
     
        
       
        /*  
         a5.reconheceLinguagem("ab");
         a5.reconheceLinguagem("baab");
         a5.reconheceLinguagem("babababa");
         */
    }

    public static void reconheceLinguagem(String chave, Automato automato) {

         Estado inicial = automato.getInicial();
         
         int flag = 0;
         
         //toda vez que chamar o automato esse trem devera ser setado
         int flag_not_in=1;
         int flag_not_finals=0;
         
        for (int i = 0; i < chave.length(); i++) {
            flag = 0;
            char c = chave.charAt(i);
            
            List<Transicao> possiveisTransicoes = new ArrayList<>();
            
            for (int j = 0; j < automato.getTransicoes().size(); j++) {
                
                if (automato.getTransicoes().get(j).getSimbolo() == c ||automato.getTransicoes().get(j).getSimbolo() == '*' ) {
                    possiveisTransicoes.add(automato.getTransicoes().get(j));
                }
            }
            
           Estado auxiliar = new Estado();
           auxiliar = automato.getAtual();
        for (Transicao transicao:possiveisTransicoes){
            
            if(transicao.getOrigem() == automato.getAtual()){
                
                if(transicao.getSimbolo()=='*'){
                    automato.setAtual(transicao.getDestino());
                    flag = 1;
                    i=i-1;
                    break;
                }else{
                    automato.setAtual(transicao.getDestino());
                    flag = 1;
                    break;
                    }
                }
          
            }
        
        if(auxiliar==automato.getAtual()){
           flag_not_in=0; 
        }
        
          
        }
        
        
        for(Estado estado: automato.getFinais()){
            if(estado == automato.getAtual())
                flag_not_finals=1;
        }
        
        if(flag==0 || flag_not_in==0 || flag_not_finals==0){
            System.out.println("Não Reconhece: " + chave);
            
        }

        else{
            System.out.println("Reconhece: " + chave);
        }
        automato.setAtual(automato.getInicial());
    }
}
