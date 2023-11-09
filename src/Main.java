import java.sql.SQLOutput;
import java.util.*;

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

        //  Sala sala1 = new Sala(49, "Reta e Simples", "Sala A");
       // Sala sala2 = new Sala(56, "Curvada", "Sala B");
       // Sala sala3 = new Sala(76, "Reta e Simples", "Sala C");/
        // Sala sala4 = new Sala(65, "Curvada", "Sala D");

        sessoes.add(new Sessao(new Sala(49, "Reta e Simples", "Sala A"), false, "19:00", filme1, ingresso1));
        sessoes.add(new Sessao(new Sala(56, "Curvada", "Sala B"), true, "16:00", filme2, ingresso2));
        sessoes.add(new Sessao(new Sala(76, "Reta e Simples", "Sala C"), true, "15:30", filme3, ingresso3));
        sessoes.add(new Sessao(new Sala(65, "Curvada", "Sala D"), false, "20:00", filme4, ingresso4));
        sessoes.add(new Sessao(new Sala(49, "Reta e Simples", "Sala A"), false, "22:00", filme4));

        int opcao = 0;

        System.out.println();
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("SISTEMA DE GERENCIAMENTO CINEMATOGRAFICO");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println();

        while (opcao != 5){
            System.out.print("1 - Visualizar as sessoes do dia\n2 - Venda de Ingressos\n3 - Adicionar Sessao\n4 - Remover Sessao\n5 - Sair do programa\n\nSelecione uma opcao: ");
            opcao = input.nextInt();
            System.out.println();

            switch(opcao) {
                case 1:
                    consultarSessao(sessoes);
                    break;
                case 2:
                    try {
                        vendaIngresso(sessoes, input);
                    }catch(InputMismatchException e){
                        System.out.println("\nERRO. Voce digitou um valor invalido!\n");

                    }catch(IllegalArgumentException er){
                        System.out.println("Ops! Parece que deu problema na hora de vender um ingresso!");
                    }
                    break;
                case 3:
                    try {
                        adicionarSessao(sessoes, input);
                    }catch(InputMismatchException  e){
                        System.out.println("\nERRO. Voce digitou um valor invalido!\n");

                    }catch(IllegalArgumentException er){
                        System.out.println("Ops! Parece que deu problema na hora de adicionar a sessao!");
                    }
                    break;
                case 4:
                    removerSessao(sessoes, input);
                    break;
                case 5:
                    System.out.println("O programa ira encerrar. Agradecemos pelo contato!");
                    break;
            }
            input.nextLine();
        }
    }

    public static void consultarSessao(ArrayList<Sessao> sessoes) {
        for (Sessao s : sessoes) {
            if (s.getEstadoDaSessao()) {
                System.out.println("========= EM ANDAMENTO =========");
            } else {
                System.out.println("============ EM BREVE ===========");
            }
            System.out.println("Horario: " + s.getHorario() + " horas" + "\nFilme: " + s.getFilme().getTitulo() + "\nDuracao: " + s.getFilme().getDuracao() + " minutos");
            System.out.println("================================");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
        }
    }
    public static void vendaIngresso(ArrayList<Sessao> sessoes, Scanner input){

        System.out.print("========= VENDA DE INGRESSOS =========\nSelecione o filme: ");
        input.nextLine();     //para limpar o buffer
        String filmeDesejado = input.nextLine();
        int numIngresso;

        do {
            System.out.print("Numero de Ingressos: ");
            numIngresso = input.nextInt();
        }while(numIngresso<=0);

        //VERiFICANDO FILME, NUMERO DE ASSENTOS + SALAS DISPONIVEIS
        boolean estaNoCatalogo = false;
        boolean temSalasDisponiveis =false;
        List<String> horariosDisponiveis = new ArrayList<String>();
        for (Sessao s : sessoes) {
            if (s.getTituloFilme().equalsIgnoreCase(filmeDesejado) && !s.getEstadoDaSessao()) {
                for (Sala sala : s.getSalas()) {
                    if (sala.getnAssentos() > numIngresso) {
                        System.out.printf("Horarios: %s    ", s.getHorario());
                        System.out.printf(" Sala: %s     Assentos disponiveis: %d%n", sala.getLocalizacao(), sala.getnAssentos());
                        temSalasDisponiveis = true;
                    }
                }
                horariosDisponiveis.add(s.getHorario());
                estaNoCatalogo = true;
            }
        }
        if (!estaNoCatalogo) {
            System.out.println("\nSinto Muito. Filme não encontrado no catálogo.\n");
        } else if (!temSalasDisponiveis) {
            System.out.println("\nSinto Muito. Não existem salas disponíveis que atendam o volume de ingressos. ");
        }else {
            //VERIFICAÇÃO DE HORARIOS
            int index = 0;
            String horario ;
            input.nextLine();
            do {
                estaNoCatalogo = false;
                System.out.print("\nSelecione um horario: ");
                horario = input.nextLine();
                for (String h : horariosDisponiveis)
                    if (Objects.equals(horario, h)) {
                        estaNoCatalogo = true;
                        break;
                    }
                if (!estaNoCatalogo)
                    System.out.println("Horario não disponível.Selecione um horário disponível ");
            }while(!estaNoCatalogo);


            //VERIFICACAO TIPO E CATEGORIA DO INGRESSO
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
            int confirmacao;
            do {
                System.out.print("Confimar venda de ingresso (digite 1 para confirmar e 0 para cancelar): ");
                confirmacao = input.nextInt();
            }while(confirmacao>1 || confirmacao<0);

            if(confirmacao == 1) {
                //CRIAÇÃO DO INGRESSO E ASSOCIAÇÃO COM SUA SESSAO
                Ingresso novoIngresso = new Ingresso(tipoIngressoSelecionado, categoriaIngressoSelecionada);
                for (Sessao s : sessoes) {
                    if (s.getTituloFilme().equalsIgnoreCase(filmeDesejado) && !s.getEstadoDaSessao() && Objects.equals(horario, s.getHorario())) {
                        String localSala = "";
                        for (Sala ambiente : s.getSalas()) {
                            if (ambiente.getnAssentos() > numIngresso) {
                                index = s.getSalas().indexOf(ambiente);
                                localSala = ambiente.getLocalizacao();
                                break;
                            }
                        }
                        novoIngresso.addSessao(s);
                        s.setIngresso(novoIngresso);
                        s.ocupaAssento(s.getSalaByIndex(index), numIngresso); //VER COMO PEGAR A SALA ESPECIFICA
                        System.out.printf("%nIngresso gerado com sucesso!%n%n------------%nTipo do Ingresso: %s%nCategoria: %s%nFilme: %s%nHorario: %s%nSala: %s%n------------%n%n", novoIngresso.getTipo(), novoIngresso.getCategoria(), s.getTituloFilme(), s.getHorario(), localSala);
                    }
                }
            }else
                System.out.println("Venda cancelada.\n");
        }
    }
    public static void adicionarSessao(ArrayList<Sessao> sessoes, Scanner input){
        System.out.println("\n=============== ADICIONAR SESSAO ===============\n");

        // Informações sobre o filme
        System.out.print("Titulo do Filme: ");
        input.nextLine();
        String tituloFilme = input.nextLine();

        int duracaoFilme;
        do {
            System.out.print("Duração do filme ( minutos ): ");
            duracaoFilme = input.nextInt();
        }while (duracaoFilme<=0);

        System.out.print("Genero do Filme:");

        for (EnumGeneroFilme genero : EnumGeneroFilme.values()) {
            System.out.println(genero.ordinal() + 1 + " - " + genero);
        }

        System.out.print("Opcao: ");
        int opcaogeneroFilme = input.nextInt();

        EnumGeneroFilme generoFilme = EnumGeneroFilme.values()[opcaogeneroFilme - 1];

        Filme novoFilme = new Filme(tituloFilme, duracaoFilme, generoFilme);

        // Informações sobre a sala

        System.out.print("Numero de assentos: ");
        int numeroAssentos = input.nextInt();

        System.out.print("Tipo da Tela ( Reta e Simples / Curvada ): ");
        input.nextLine();
        String tipoTela = input.nextLine();

        System.out.print("Localizacao da Sala: ");
        String localizacaoSala = input.nextLine();

        // Estado da sessão

        System.out.print("Digite o estado da sessao ( true / false ): ");
        boolean estadoSessao = input.nextBoolean();

        // Horário da sessão
        System.out.print("Digite o horario da sessão ( HH:MM ): ");
        input.nextLine();
        String horaSessao = input.nextLine();

        if (sessoes.add(new Sessao(new Sala(numeroAssentos, tipoTela, localizacaoSala), estadoSessao, horaSessao,novoFilme))) {
            System.out.println("\nSessao adicionada com sucesso!\n");
        } else {
            throw new IllegalArgumentException("\nErro ao adicionar a sessão. Verifique se os dados fornecidos sao validos.\n");
        }
    }
    public static void removerSessao(ArrayList<Sessao> sessoes, Scanner input){
        System.out.println("\n=============== REMOVER SESSAO ===============\n");

        System.out.println("\n-=-=-=-= LISTANDO SESSOES -=-=-=-=");

        for (Sessao ses : sessoes){
            System.out.println(ses.toString2());
            System.out.println("=================================");
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
        ArrayList<Sessao> sessaoRemover = new ArrayList<>();

        System.out.println("\nDigite o titulo do Filme para remover a sessao: ");
        input.nextLine();
        String nome_Filme_Remover = input.nextLine();
        input.nextLine();

        System.out.println("Digite o horario do filme para remover a sessao ( HH:MM ): ");
        String horario_Remover_Sessao = input.nextLine();

        for (Sessao s : sessoes){
            if (nome_Filme_Remover.equalsIgnoreCase(s.getTituloFilme()) && horario_Remover_Sessao.equalsIgnoreCase(s.getHorario())){
                sessaoRemover.add(s);
            }
        }

        sessoes.removeAll(sessaoRemover);
        System.out.println("Sessao removida com sucesso!");
    }
}
