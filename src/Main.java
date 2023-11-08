import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        EnumGeneroFilme generoFilme1 = EnumGeneroFilme.ACAO;
        EnumGeneroFilme generoFilme2 = EnumGeneroFilme.DRAMA;

        EnumTipoIngresso tipoIngresso = EnumTipoIngresso.MEIO_INGRESSO;
        EnumTipoIngresso tipoIngresso2 = EnumTipoIngresso.INGRESSO_SORTEIO;

        EnumCategoriaIngresso categoriaIngresso1 = EnumCategoriaIngresso.INGRESSO_FISICO;
        EnumCategoriaIngresso categoriaIngresso2 = EnumCategoriaIngresso.INGRESSO_ONLINE;

        ArrayList<Sessao> sessoes = new ArrayList<>();

        Filme filme1 = new Filme("Vingadores Guerra Infinita", 90, generoFilme1);
        Filme filme2 = new Filme("Vingadores Ultimato", 180, generoFilme2);
        Filme filme3 = new Filme("Homem-Aranha 4", 87, generoFilme1);
        Filme filme4 = new Filme("Joker", 92, generoFilme2);

        Ingresso ingresso1 = new Ingresso(tipoIngresso, categoriaIngresso1);
        Ingresso ingresso2 = new Ingresso(tipoIngresso2, categoriaIngresso2);
        Ingresso ingresso3 = new Ingresso(tipoIngresso, categoriaIngresso2);
        Ingresso ingresso4 = new Ingresso(tipoIngresso2, categoriaIngresso1);

        Sala sala1 = new Sala(49, "Reta e Simples", "Sala A");
        Sala sala2 = new Sala(56, "Curvada", "Sala B");
        Sala sala3 = new Sala(76, "Reta e Simples", "Sala C");
        Sala sala4 = new Sala(65, "Curvada", "Sala D");

        sessoes.add(new Sessao(sala1, false, "19:00", filme1, ingresso1));
        sessoes.add(new Sessao(sala2, true, "16:00", filme2, ingresso2));
        sessoes.add(new Sessao(sala3, true, "15:30", filme3, ingresso3));
        sessoes.add(new Sessao(sala4, false, "20:00", filme4, ingresso4));
        sessoes.add(new Sessao(sala1, false, "22:00", filme4));

        int opcao = 0;

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("SISTEMA DE GERENCIAMENTO CINEMATOGRAFICO");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println();

        while (opcao != 4){
            System.out.println("1 - Visualizar as sessoes do dia\n2 - Compra de Ingressos\n3 - Consulta de sessoes\n4 - Sair do programa\nSelecione uma opcao: ");
            opcao = input.nextInt();

            switch(opcao) {
                case 1:
                    for (Sessao s : sessoes) {
                        if (s.getEstadoDaSessao()) {
                            System.out.println("========= EM ANDAMENTO =========");
                            System.out.println("Horario: " + s.getHorario() + " horas" + "\nFilme: " + s.getFilme().getTitulo() + "\nDuracao: " + s.getFilme().getDuracao() + " minutos");
                            System.out.println("================================");
                            System.out.println();
                        } else {
                            System.out.println("============ EM BREVE ============");
                            System.out.println("Horario: " + s.getHorario() + " horas" + "\nFilme: " + s.getFilme().getTitulo() + "\nDuracao: " + s.getFilme().getDuracao() + " minutos");
                            System.out.println("=================================");
                            System.out.println();
                        }
                    }
                    break;
                case 2:
                    System.out.print("========= VENDA DE INGRESSOS =========\nSelecione o filme: ");
                    input.nextLine();     //para limpar o buffer
                    String filmeDesejado = input.nextLine();

                    System.out.print("Numero de Ingressos: ");
                    int numIngresso = input.nextInt();

                    //VERFICANDO FILME E NUMERO DE ASSENTOS + SALAS DISPONIVEIS
                    boolean estaNoCatalogo = false;
                    List<String> horariosDisponiveis = new ArrayList<String>();
                    for (Sessao s : sessoes) {
                        if (s.getTituloFilme().equalsIgnoreCase(filmeDesejado) && !s.getEstadoDaSessao()) {
                            System.out.printf("Horarios: %s    ", s.getHorario());
                            for (Sala sala : s.getSalas()) {
                                if (sala.getnAssentos() > numIngresso) {
                                    System.out.printf(" Sala: %s     Assentos disponiveis: %d%n", sala.getLocalizacao(), sala.getnAssentos());
                                } else
                                    System.out.printf("Assentos insuficientes. Capacidade atual: %s", sala.getnAssentos());
                            }
                            horariosDisponiveis.add(s.getHorario());
                            estaNoCatalogo = true;
                        }
                    }

                    if (!estaNoCatalogo) {
                        System.out.println("Filme não encontrado no catálogo.");
                    } else {
                        //VERIFICAÇÃO DE HORARIOS
                        int index = 0;
                        estaNoCatalogo = false;
                        System.out.print("\nSelecione um horario: ");
                        input.nextLine();
                        String horario = input.nextLine();
                        for (String h : horariosDisponiveis)
                            if (Objects.equals(horario, h)) {
                                index = horariosDisponiveis.indexOf(h);
                                estaNoCatalogo = true;
                                break;
                            }
                        if (!estaNoCatalogo) {
                            System.out.println("Horario não disponível.");
                            break;
                        }

                        //VERIFICACAO TIPO DO INGRESSO
                        int opcaoTipoIngresso;
                        do {
                            System.out.println("Escolha o tipo de ingresso: \n1 - Meio Ingresso \n2 - Ingresso Sorteio");
                            opcaoTipoIngresso = input.nextInt();
                        } while (opcaoTipoIngresso < 1 || opcaoTipoIngresso > 2);

                        EnumTipoIngresso tipoIngressoSelecionado = opcaoTipoIngresso == 1 ? EnumTipoIngresso.MEIO_INGRESSO : EnumTipoIngresso.INGRESSO_SORTEIO;

                        int opcaoCategoriaIngresso;
                        do {
                            System.out.println("Escolha a categoria de ingresso:\n1 - Ingresso Físico\n2 - Ingresso Online");
                            opcaoCategoriaIngresso = input.nextInt();
                        } while (opcaoCategoriaIngresso < 1 || opcaoCategoriaIngresso > 2);

                        EnumCategoriaIngresso categoriaIngressoSelecionada = opcaoCategoriaIngresso == 1 ? EnumCategoriaIngresso.INGRESSO_FISICO : EnumCategoriaIngresso.INGRESSO_ONLINE;

                        Ingresso novoIngresso = new Ingresso(tipoIngressoSelecionado, categoriaIngressoSelecionada);
                        for(Sessao s: sessoes){
                            if(s.getTituloFilme().equalsIgnoreCase(filmeDesejado) && !s.getEstadoDaSessao() && Objects.equals(horario, s.getHorario())){
                                novoIngresso.addSessao(s);
                                s.setIngresso(novoIngresso);

                                s.ocupaAssento(s.getSalas(),numIngresso);
                            }
                        }

                    }
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("O programa ira encerrar. Agradecemos pelo contato!");
                    break;
            }
        }
    }
}
