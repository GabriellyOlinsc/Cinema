import java.util.ArrayList;

public class Sessao {
    private boolean estadoDaSessao;
    private int horario;
    private Ingresso ingresso;
    private final Filme filme;
    private ArrayList<Sala> salas = new ArrayList<Sala>(); //PODE FAZER ISSO?

    public Sessao(Sala sala, boolean estadoDaSessao, int horario, Filme filme, Ingresso ingresso){
        this.estadoDaSessao = estadoDaSessao;
        this.horario = horario;
        this.filme = filme;
        this.ingresso = ingresso;
        filme.addSessoes(this);
        ingresso.addSessao(this);
        salas.add(sala);
    }

    public void setIngresso(Ingresso ing){
        if(this.ingresso == null) {
            this.ingresso = ing;
        }
    }

    public boolean getEstadoDaSessao() {
        return estadoDaSessao;
    }

    public void setEstadoDaSessao(boolean estado) {
        this.estadoDaSessao = estado;
    }

    public ArrayList<Sala> getSalas() {
        return salas;
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
    @Override
    public String toString() {
        return String.format("\n\nSessao{" +
                "\n estadoDaSessao=" + estadoDaSessao +
                ", \n horario=" + horario + "  " +
                this.ingresso.toString()+ "  " +
                this.filme.toString() +
                ", \n salas=" + salas.toString() +
                '}');
    }
}
