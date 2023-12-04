package Model;

public interface Builder {

    Builder buildEstadoSessao(boolean estadoSessao);
    Builder buildHorario(String horario);
    Builder buildFilme(String titulo, int duracao, EnumGeneroFilme genero);
    Builder buildIngresso(EnumTipoIngresso tipo, EnumCategoriaIngresso categoria);
    Builder buildSala(int assentos, String tipoTela, String localizacao);
    Sessao build();
}
