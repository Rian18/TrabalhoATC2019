package atc;

/**
 *
 * @author Rian Alves
 */
public class Palavra {

    private String palavra;
    private String tag;

    public Palavra() {

    }

    Palavra(String trechoPalavra, String tag) {
        this.palavra = trechoPalavra;
        this.tag = tag;

    }

 
    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

   
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
