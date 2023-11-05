public class Sala {
    private int nAssentos;
    private String tipoTela;
    private String localizacao;
    public Sala(int assentos, String tipoTela, String localizacao){
        this.localizacao = localizacao;
        this.nAssentos = assentos;
        this.tipoTela = tipoTela;
    }

    @Override
    public String toString() {
        return String.format("\nSala{" +
                "\nnAssentos=" + nAssentos +
                ",\n tipoTela='" + tipoTela + '\'' +
                ",\n localizacao='" + localizacao + '\'' +
                '}');
    }
}
