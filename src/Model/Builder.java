package Model;

public interface Builder {
    Builder buildFilme(String titulo, int duracao, EnumGeneroFilme genero);
    Builder buildIngresso(EnumTipoIngresso tipo, EnumCategoriaIngresso categoria);
    Builder buildSala(int assentos, String tipoTela, String localizacao);
    Sessao build();
}
