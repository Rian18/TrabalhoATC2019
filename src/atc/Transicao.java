
package atc;

/**
 *
 * @author Rian Alves
 */
public class Transicao {
    private Estado origem;
    private Estado destino;
    private char simbolo;

    public Transicao() {
    }
    
    

    public Transicao(Estado origem, Estado destino, char simbolo) {
        this.origem = origem;
        this.destino = destino;
        this.simbolo = simbolo;
    }

    /**
     * @return the origem
     */
    public Estado getOrigem() {
        return origem;
    }

    /**
     * @param origem the origem to set
     */
    public void setOrigem(Estado origem) {
        this.origem = origem;
    }

    /**
     * @return the destino
     */
    public Estado getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(Estado destino) {
        this.destino = destino;
    }

    /**
     * @return the simbolo
     */
    public char getSimbolo() {
        return simbolo;
    }

    /**
     * @param simbolo the simbolo to set
     */
    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }
    
    
}
