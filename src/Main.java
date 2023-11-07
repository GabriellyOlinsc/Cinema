import java.sql.SQLOutput;
import java.util.ArrayList;
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

        int opcao = 0;

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("SISTEMA DE GERENCIAMENTO CINEMATOGRAFICO");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println();

        while (opcao != 4){
            System.out.println("1 - Visualizar as sessoes do dia\n2 - Compra de Ingressos\n3 - Consulta de sessoes\n4 - Sair do programa");
            opcao = input.nextInt();

            switch(opcao){
                case 1:
                        for (Sessao s : sessoes){
                            if(s.getEstadoDaSessao()) {
                                System.out.println("========= EM ANDAMENTO =========");
                                System.out.println("Horario: " + s.getHorario() + " horas" + "\nFilme: " + s.getFilme().getTitulo() + "\nDuracao: " + s.getFilme().getDuracao() + " minutos");
                                System.out.println("================================");
                                System.out.println();
                            }else{
                                System.out.println("============ FECHADO ============");
                                System.out.println("Horario: " + s.getHorario() + " horas" + "\nFilme: " + s.getFilme().getTitulo() + "\nDuracao: " + s.getFilme().getDuracao() + " minutos");
                                System.out.println("=================================");
                                System.out.println();
                            }
                        }
                    break;
                case 2:

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
