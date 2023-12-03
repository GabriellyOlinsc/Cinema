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

    public EnumGeneroFilme getGenero() {
        return this.genero;
    }
    public void setGenero(EnumGeneroFilme genero) {
        this.genero = genero;
    }


    public ArrayList<Sessao> getSessoes() {
        return sessoes;
    }

    public void setSessoes(ArrayList<Sessao> sessoes) {
        this.sessoes = sessoes;
    }

    public void addSessoes(Sessao novaSessao) {
        sessoes.add(novaSessao);
    }

    public void delSessoes(Sessao sessao){
        for(Sessao s : sessoes){
            if (s.equals(sessao)){
                this.sessoes.remove(s);
                s.getFilme().delSessoes(sessao);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("\n\tFilme {" +
                "\n\t\tTitulo = " + this.titulo +
                ",\n\t\tDuracao = " + this.duracao +
                ",\n\t\tGenero = " + this.genero +
                " }");
    }
}
