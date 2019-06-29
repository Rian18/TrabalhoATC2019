
package atc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Rian Alves
 */
public class Automato {

    private Estado inicial; 
    private List<Estado> finais;
    private List<Estado> estados;
    private List<Transicao> transicoes;
    private Stack<Automato> pilhaAuto ;
    private Estado atual;

 

    public Automato() {
        this.estados = new ArrayList<>();
        this.transicoes = new ArrayList<>();
        this.finais = new ArrayList<>();
        this.pilhaAuto =  new Stack();
       
    }
   
    
       
    //Operação +
    public void construirAuto(Automato a1, Automato a2 )
    {
        pilhaAuto.push(a1);
        pilhaAuto.push(a2);
      
        for(int i=0;i<a1.transicoes.size();i++)
        {
            this.transicoes.add(new Transicao(this.inicial,a1.transicoes.get(i).getDestino(),a1.getTransicoes().get(i).getSimbolo()));
            this.getEstados().add(a1.getTransicoes().get(i).getDestino());
            this.getFinais().add(a1.getTransicoes().get(i).getDestino());
        }
        
         for(int i=0;i<a2.transicoes.size();i++)
        {
            this.transicoes.add(new Transicao(this.inicial,a2.transicoes.get(i).getDestino(),a2.getTransicoes().get(i).getSimbolo()));
            this.getEstados().add(a2.getTransicoes().get(i).getDestino()); 
            this.getFinais().add(a2.getTransicoes().get(i).getDestino());
        }
                         
        System.out.println(this);      
            
    }
    
    //Função para concatenar autômatos
    public void concatenaAuto(Automato a1, Automato a2)
    {
        for(int i=0;i<a1.transicoes.size();i++)
        {
            for(int j=0;j<a2.transicoes.size();j++){
            //this.inicial = a1.transicoes.get(i).getOrigem();
            this.transicoes.add(new Transicao(this.inicial,a2.transicoes.get(j).getOrigem(),a1.getTransicoes().get(i).getSimbolo()));
            this.transicoes.add(new Transicao(a2.transicoes.get(j).getOrigem(),a2.transicoes.get(j).getDestino(),a2.getTransicoes().get(j).getSimbolo()));
            this.getEstados().add(a2.getTransicoes().get(j).getOrigem());
            this.getEstados().add(a2.getTransicoes().get(j).getDestino());
            this.getFinais().add(a2.getTransicoes().get(j).getDestino());
        }
            
                 
       } 
        System.out.println(this);
    }
    
    public void fechoKleen(Automato a3)
    {
        //Para operação +
        if(a3.getFinais().size()>1)
        {
            for(int i=0;i<a3.getFinais().size();i++)
            {
                a3.transicoes.add(new Transicao(a3.transicoes.get(i).getDestino(),a3.transicoes.get(i).getOrigem() ,a3.transicoes.get(i).getSimbolo()));
            }
            
        }else //Para operação .
        {
             for(int i=0;i<a3.getFinais().size();i++)
            {
                a3.transicoes.add(new Transicao(a3.getFinais().get(i),a3.inicial ,'n'));//Repassei n como se não consumisse nada, podemos trocar isso 
            }
        }   
        
        System.out.println(a3);
    }
    public static Automato automatoAuxiliar()
    {
        Automato automato = new Automato();
        Estado ei = new Estado();
        
        
        automato.getEstados().add(ei);
        
        
        automato.setInicial(ei);
        automato.setAtual(ei);
        
        
    
        return automato ;
    }
    
    public static Automato automatoSimples(char c)
    {
        Automato automato = new Automato();
        Estado ei = new Estado();
        Estado ef = new Estado();
        
        Transicao t = new Transicao();
        t.setOrigem(ei);
        t.setDestino(ef);
        t.setSimbolo(c);
        
        automato.getEstados().add(ei);
        automato.getEstados().add(ef);
        
        automato.getTransicoes().add(t);
        automato.setInicial(ei);
        automato.getFinais().add(ef);
        
        return automato;
    }

   
    public Estado getInicial() {
        return inicial;
    }

    public void setInicial(Estado inicial) {
        this.inicial = inicial;
    }


    public List<Estado> getFinais() {
        return finais;
    }


    public void setFinais(List<Estado> finais) {
        this.finais = finais;
    }


    public List<Estado> getEstados() {
        return estados;
    }


    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }


    public List<Transicao> getTransicoes() {
        return transicoes;
    }


    public void setTransicoes(List<Transicao> transicoes) {
        this.transicoes = transicoes;
    }

    public Estado getAtual() {
        return atual;
    }

    public void setAtual(Estado atual) {
        this.atual = atual;
    }
    
   
}
    
    
   