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

    public String getTipoTela() {
        return tipoTela;
    }

    public void setTipoTela(String tipoTela) {
        this.tipoTela = tipoTela;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return String.format("\n\n\tSala{" +
                "\n\t\tNumero de Assentos = " + nAssentos +
                ",\n\t\tTipoTela = ' " + tipoTela + " ' " +
                ",\n\t\tLocalizacao = ' " + localizacao + " ' \n \t\t}\n");
    }
}
