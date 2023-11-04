import java.util.ArrayList;

public class Filme {
    private String titulo;
    private int duracao;
    private EnumGeneroFilme genero;
    private ArrayList<Sessao> sessoes;

    public Filme(String titulo, int duracao, EnumGeneroFilme genero){
        this.titulo = titulo;
        this.duracao = duracao;
        this.genero = genero;
        sessoes = new ArrayList<Sessao>();
    }

}
