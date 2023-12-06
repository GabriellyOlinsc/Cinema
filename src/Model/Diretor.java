package Model;

public class Diretor {
    public Sessao construirSessaoJoker(Sessao.SessaoBuilder sessao){
        return sessao.buildHorario("17:00")
                .buildFilme("Joker", 130, EnumGeneroFilme.DRAMA)
                .buildEstadoSessao(true)
                .buildSala(75, "Reta e Simples", "Sala A")
                .build();
    }

    public Sessao construirSessaoVingadores(Sessao.SessaoBuilder sessao){
        return sessao.buildHorario("19:00")
                .buildFilme("Vingadores Ultimato", 110, EnumGeneroFilme.ACAO)
                .buildEstadoSessao(false)
                .buildSala(60, "Curvada", "Sala B")
                .build();
    }

    public Sessao construirSessaoInterestelar(Sessao.SessaoBuilder sessao){
        return sessao.buildHorario("22:00")
                .buildFilme("Interestelar", 160, EnumGeneroFilme.DRAMA)
                .buildEstadoSessao(false)
                .buildSala(85, "Curvada", "Sala C")
                .build();
    }
}
