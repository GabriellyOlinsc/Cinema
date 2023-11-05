public class Ingresso {
    private EnumTipoIngresso tipo;
    private EnumCategoriaIngresso categoria;
    private  Sessao sessao;

    public Ingresso(EnumTipoIngresso tipo, EnumCategoriaIngresso categoria) {
        this.tipo = tipo;
        this.categoria = categoria;
        this.sessao = sessao;
        sessao = null;
    }

    public void addSessao(Sessao ses){
        if(this.sessao == null){
            this.sessao = ses;
        }
    }
    @Override
    public String toString() {
        return String.format( "%nIngresso{" +
                "%ntipo=" + tipo +
                ", %ncategoria=" + categoria +
                '}');
    }
}
