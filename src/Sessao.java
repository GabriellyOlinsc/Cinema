import java.util.ArrayList;

public class Sessao {
    private boolean estadoDaSessao;
    private int horario;
    private ArrayList<Sala> salas;
    public Sessao(Sala sala, boolean estadoDaSessao, int horario){
        this.estadoDaSessao = estadoDaSessao;
        this.horario = horario;
        this.salas.add(sala);
    }

}
