package Model;

public class Sala {
    private int nAssentos;
    private String tipoTela;
    private String localizacao;
    public Sala(int assentos, String tipoTela, String localizacao){
        this.localizacao = localizacao;
        this.nAssentos = assentos;
        this.tipoTela = tipoTela;
    }

    public int getnAssentos() {
        return nAssentos;
    }

    public void setnAssentos(int nAssentos) {
        this.nAssentos = nAssentos;
    }

    public String getLocalizacao() {
        return localizacao;
    }


    @Override
    public String toString() {
        return String.format("\n\tSala{" + "\n\t\tNumero de Assentos = " + nAssentos + ",\n\t\tTipoTela = ' " + tipoTela + " ' " +
                ",\n\t\tLocalizacao = ' " + localizacao + " ' \n \t\t}");
    }
}
