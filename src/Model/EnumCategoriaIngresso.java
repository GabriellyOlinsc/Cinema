package Model;

public enum EnumCategoriaIngresso {
    INGRESSO_FISICO(28),
    INGRESSO_ONLINE(20);

    private final int valor;

    EnumCategoriaIngresso(int valor){
        this.valor = valor;
    }

    public int getValor(){
        return this.valor;
    }
}
