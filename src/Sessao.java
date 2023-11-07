import java.util.ArrayList;

public class Sessao {
    private boolean estadoDaSessao;
    private String horario;
    private Ingresso ingresso;
    private final Filme filme;
    private final ArrayList<Sala> salas = new ArrayList<>(); //PODE FAZER ISSO? R: Pode sim
    public Sessao(Sala sala, boolean estadoDaSessao, String horario, Filme filme, Ingresso ingresso){
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

    @Override
    public String toString() {
        return String.format("\n\nSessao {" +
                "\n EstadoDaSessao = " + estadoDaSessao +
                ", \nHorario = " + horario + "  " +
                this.ingresso.toString()+ "  " +
                this.filme.toString() +
                ", \nSalas = " + salas.toString() +
                '}');
    }
}
