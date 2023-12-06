package Model;

import java.util.ArrayList;

public class Filme {
    private String titulo;
    private int duracao;
    private EnumGeneroFilme genero;
    private ArrayList<Sessao> sessoes;

    public Filme(String titulo, int duracao, EnumGeneroFilme genero){
        setTitulo(titulo);
        setDuracao(duracao);
        setGenero(genero);
        sessoes = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracao() {
        return this.duracao;
    }

    public void setDuracao(int duracao) {
        if(duracao > 0) {
            this.duracao = duracao;
        }else{
            throw new IllegalArgumentException("Duracao do filme invalida !");
        }
    }

    public void setGenero(EnumGeneroFilme genero) {
        this.genero = genero;
    }

    public void addSessoes(Sessao novaSessao) {
        sessoes.add(novaSessao);
    }

    @Override
    public String toString() {
        return String.format("\nFilme: " + this.titulo + ",\nDuracao:  " + this.duracao + ",\nGenero: " + this.genero );
    }
}
