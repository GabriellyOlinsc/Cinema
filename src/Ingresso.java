public class Ingresso {
    private EnumTipoIngresso tipo;
    private EnumCategoriaIngresso categoria;
    private Filme filme;

    public Ingresso(EnumTipoIngresso tipo, EnumCategoriaIngresso categoria, Filme filme) {
        this.tipo = tipo;
        this.categoria = categoria;
        this.filme = filme;
    }
}
