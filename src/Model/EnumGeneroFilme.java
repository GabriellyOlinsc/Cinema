package Model;

public enum EnumGeneroFilme {
    ACAO("Ação"),
    AVENTURA("Aventura"),
    SUSPENSE("Suspense"),
    COMEDIA("Comedia"),
    DRAMA("Drama");
    private final String descricao;

    EnumGeneroFilme(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }
}
