package Model;

import java.util.ArrayList;

public class Sessao {
    private boolean estadoDaSessao;
    private String horario;
    private Ingresso ingresso;
    private final Filme filme;
    private final ArrayList<Sala> salas = new ArrayList<>();
    private Sessao(ArrayList<Sala> salas, boolean estadoDaSessao, String horario, Filme filme, Ingresso ingresso){
        this.estadoDaSessao = estadoDaSessao;
        this.horario = horario;
        this.filme = filme;
        this.ingresso = ingresso;

        this.salas.addAll(salas);
        filme.addSessoes(this);

        if (ingresso != null)
            ingresso.addSessao(this);
    }

    private Sessao(Sala sala, boolean estadoDaSessao, String horario, Filme filme){
        this.estadoDaSessao = estadoDaSessao;
        this.horario = horario;
        this.filme = filme;
        this.ingresso = null;
        filme.addSessoes(this);

        salas.add(sala);
    }
    public static class SessaoBuilder implements Builder{
        private boolean estadoDaSessao;
        private String horario;
        private Ingresso ingresso;
        private Filme filme;
        private final ArrayList<Sala> salas = new ArrayList<>();

        @Override
        public Builder buildEstadoSessao(boolean estadoSessao) {
            this.estadoDaSessao = estadoSessao;
            return this;
        }

        @Override
        public Builder buildHorario(String horario) {
            this.horario = horario;
            return this;
        }

        @Override
        public Builder buildFilme(String titulo, int duracao, EnumGeneroFilme genero) {
            this.filme = new Filme(titulo, duracao, genero);
            return this;
        }

        @Override
        public Builder buildIngresso(EnumTipoIngresso tipo, EnumCategoriaIngresso categoria) {
            this.ingresso = new Ingresso(tipo, categoria);
            return this;
        }

        @Override
        public Builder buildSala(int assentos, String tipoTela, String localizacao) {
            this.salas.add(new Sala(assentos, tipoTela, localizacao));
            return this;
        }

        @Override
        public Sessao build() {
            return new Sessao(salas, estadoDaSessao, horario, filme, ingresso);
        }
    }
    public void setIngresso(Ingresso ing){
        if(this.ingresso == null) {
            this.ingresso = ing;
            ingresso.addSessao(this);
        }
    }

    public void cadeirasDisponiveis(){
        for (Sala s : salas){
            System.out.println("Na sala localizada " + s.getLocalizacao() + " tem " + s.getnAssentos() + " assentos disponiveis.");
        }
    }

    public String getHorario() {
        return this.horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public boolean getEstadoDaSessao() {
        return this.estadoDaSessao;
    }

    public void setEstadoDaSessao(boolean estado) {
        this.estadoDaSessao = estado;
    }

    public ArrayList<Sala> getSalas() {
        return this.salas;
    }

    public void addSala(Sala sala) {
        salas.add(sala);
    }

    public Filme getFilme() {
        return filme;
    }

    public String getTituloFilme(){
        return this.filme.getTitulo();
    }

    public void ocupaAssento (Sala s, int numAssentos){
        s.setnAssentos(s.getnAssentos()- numAssentos) ;
    }
    public Sala getSalaByIndex(int index){
        return salas.get(index);
    }
    @Override
    public String toString() {
        return String.format("\n\nSessao {" +
                "\n\nEstadoDaSessao = " + estadoDaSessao + " \n " +
                "Horario = " + horario + " \n " +
                this.ingresso.toString()+ " \n " +
                this.filme.toString() +
                ",\n\nSalas = " + salas +
                "}\n\n");
    }
}
