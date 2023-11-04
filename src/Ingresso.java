public class Ingresso {
    private EnumTipoIngresso tipo;
    private EnumCategoriaIngresso categoria;
    private Sessao sessao;

    public Ingresso(EnumTipoIngresso tipo, EnumCategoriaIngresso categoria, Sessao sessao) {
        this.tipo = tipo;
        this.categoria = categoria;
        this.sessao = sessao;
    }
}
