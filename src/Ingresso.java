public class Ingresso {
    private final EnumTipoIngresso tipo;
    private final EnumCategoriaIngresso categoria;
    private Sessao sessao;

    public Ingresso(EnumTipoIngresso tipo, EnumCategoriaIngresso categoria) {
        this.tipo = tipo;
        this.categoria = categoria;
        this.sessao = null;
    }

    public void addSessao(Sessao ses){
        if(this.sessao == null)
            this.sessao = ses;
        else
            throw new IllegalArgumentException("A sessão precisa estar vazia para adicionar.");
    }

    public EnumTipoIngresso getTipo() {
        return this.tipo;
    }

    public EnumCategoriaIngresso getCategoria() {
        return this.categoria;
    }

    public Sessao getSessao() {
        return this.sessao;
    }

    public void delSessao(){
        if (sessao != null)
            this.sessao = null;
        else
            throw new IllegalArgumentException("A sessão não pode estar vazia para deletar.");
    }

    @Override
    public String toString() {
        return String.format( "%nIngresso {" +
                "%nTipo = " + this.tipo +
                ", %nCategoria = " + this.categoria +
                '}');
    }
}
