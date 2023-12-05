package Control;

import Model.*;

import java.util.*;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final String FUNCIONARIO = "1";
    private static final String CLIENTE = "2";
    private static String login = "";
    private static String senha = "";
    public static void main(String[] args) {


        Sessao.SessaoBuilder sessao = new Sessao.SessaoBuilder();
        Diretor diretor = new Diretor();
        ArrayList<Sessao> sessoes = new ArrayList<>(List.of(diretor.construirSessaoJoker(sessao), diretor.construirSessaoVingadores(sessao),
                diretor.construirSessaoInterestelar(sessao)));

        int opcao = 0;

        String opcao_Cargo = "";

        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("SISTEMA DE GERENCIAMENTO CINEMATOGRAFICO");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");

        while (opcao != 5) {
            do {

                do {
                    exibirOpcoes();
                    opcao_Cargo = input.nextLine();
                    if (opcao_Cargo.equals(FUNCIONARIO)) {
                        if (autenticarFunc())
                            System.out.println("Login valido!");
                        else
                            System.out.println("Valor invalido. Tente novamente.");

                    } else if (opcao_Cargo.equals(CLIENTE)) {
                        autenticarCliente();

                    } else {
                        System.out.println("\nValor invalido. Tente novamente.\n");
                    }
                }while(!login.equalsIgnoreCase("Kauan da Silveira") && !senha.equalsIgnoreCase("1234"));

            }while(!opcao_Cargo.equals(FUNCIONARIO) && !opcao_Cargo.equals(CLIENTE));

            mensagemBoasVindas();

            System.out.print("1 - Visualizar as sessoes do dia\n2 - Venda de Ingressos\n3 - Adicionar Sessao\n4 - Remover Sessao\n5 - Sair do programa\n\nSelecione uma opcao: ");
            opcao = input.nextInt();
            System.out.println();

            switch (opcao) {
                case 1:
                    consultarSessao(sessoes);
                    break;

                case 2:
                    try {
                        vendaIngresso(sessoes, input);
                    } catch (InputMismatchException e) {
                        System.out.println("\nERRO. Voce digitou um valor invalido!\n");

                    } catch (IllegalArgumentException er) {
                        System.out.println("Ops! Parece que deu problema na hora de vender um ingresso!");
                    }

                    break;
                case 3:
                    try {
                        adicionarSessao(sessoes, input);

                    }catch (InputMismatchException e){
                        System.out.println(e.getMessage());

                    }catch (IllegalArgumentException er) {
                        System.out.println("Ops! Parece que deu problema na hora de adicionar a sessao!");
                    }

                    break;

                case 4:
                    try {
                        removerSessao(sessoes, input);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 5:
                    System.out.println("O programa ira encerrar. Agradecemos pelo contato!");
                    break;

                default:
                    System.out.println("Ops! Parece que voce digitou uma opcao invalida. Tente novamente.\n");
                    break;
            }
            input.nextLine();
        }
    }


    // --------------------- MÉTODOS ESTÁTICOS --------------------- //

    private static void exibirOpcoes(){
        System.out.println("+-------------------+-------------------+");
        System.out.println("1 - Funcionario\n2 - Cliente");
        System.out.println("+-------------------+-------------------+");
        System.out.println("\nSelecione seu cargo: ");
    }

    private static boolean autenticarFunc(){
        System.out.println("\n================================");
        System.out.println("Digite seu usuario: ");
        login = input.nextLine();
        System.out.println("Digite sua senha: ");
        senha = input.nextLine();
        System.out.println("================================\n");
        return login.equalsIgnoreCase("Kauan da Silveira") && senha.equalsIgnoreCase("1234");
    }

    private static void autenticarCliente(){
        System.out.println("Digite seu nome: ");
        login = input.nextLine();
    }

    public static void mensagemBoasVindas(){
        System.out.println("\n+-------------------+-------------------+-------------------+");
        System.out.println("\tSeja bem vindo(a) " + login.toUpperCase() + "!");
        System.out.println("+-------------------+-------------------+-------------------+\n\n");
    }
    private static void consultarSessao(ArrayList<Sessao> sessoes) {
        for (Sessao s : sessoes) {
            if (s.getEstadoDaSessao()) {
                System.out.println("========= EM ANDAMENTO ==========");
            } else {
                System.out.println("============ EM BREVE ===========");
            }
            System.out.println("Horario: " + s.getHorario() + "\nFilme: " + s.getFilme().getTitulo() + "\nDuracao: " + s.getFilme().getDuracao() + " minutos");
            System.out.println("=================================");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
        }
    }

    private static void vendaIngresso(ArrayList<Sessao> sessoes, Scanner input) {

        System.out.print("========= VENDA DE INGRESSOS =========\nSelecione o filme: ");
        input.nextLine();     //para limpar o buffer
        String filmeDesejado = input.nextLine();
        int numIngresso;

        do {
            System.out.print("Numero de Ingressos: ");
            numIngresso = input.nextInt();
        } while (numIngresso <= 0);

        //VERiFICANDO FILME, NUMERO DE ASSENTOS + SALAS DISPONIVEIS
        boolean estaNoCatalogo = false;
        boolean temSalasDisponiveis = false;
        List<String> horariosDisponiveis = new ArrayList<>();
        for (Sessao s : sessoes) {
            if (s.getTituloFilme().equalsIgnoreCase(filmeDesejado) && !s.getEstadoDaSessao()) {
                for (Sala sala : s.getSalas()) {
                    if (sala.getnAssentos() > numIngresso) {
                        System.out.printf("Horarios: %s    ", s.getHorario());
                        System.out.printf("Sala: %s     Assentos disponiveis: %d%n", sala.getLocalizacao(), sala.getnAssentos());
                        temSalasDisponiveis = true;
                    }
                }
                horariosDisponiveis.add(s.getHorario());
                estaNoCatalogo = true;
            }
        }
        if (!estaNoCatalogo) {
            System.out.println("\nSinto Muito. Model.Filme não encontrado no catalogo.\n");
        } else if (!temSalasDisponiveis) {
            System.out.println("\nSinto Muito. Não existem salas disponiveis que atendam o volume de ingressos. ");
        } else {

            //VERIFICAÇÃO DE HORARIOS

            int index = 0;
            String horario;
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
                    System.out.println("Horario não disponivel. Selecione um horário disponivel. ");

            } while (!estaNoCatalogo);

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

            } while (confirmacao > 1 || confirmacao < 0);

            if (confirmacao == 1) {

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
                        s.ocupaAssento(s.getSalaByIndex(index), numIngresso);

                        System.out.printf("%nIngresso gerado com sucesso!%n%n------------%nTipo do Ingresso: %s%nCategoria: %s%nFilme: %s%nHorario: %s%nSala: %s%n------------%n%n", novoIngresso.getTipo(), novoIngresso.getCategoria(), s.getTituloFilme(), s.getHorario(), localSala);
                    }
                }
            } else
                System.out.println("Venda cancelada.\n");
        }
    }

    private static void adicionarSessao(ArrayList<Sessao> sessoes, Scanner input) {
        System.out.println("\n=============== ADICIONAR SESSAO ===============\n");

        // Informações sobre o filme
        System.out.print("Titulo do Filme: ");
        input.nextLine();
        String tituloFilme = input.nextLine();

        if (tituloFilme.equalsIgnoreCase("") || tituloFilme.equalsIgnoreCase(" "))
            throw new InputMismatchException("\nERRO. O titulo do filme precisa ser informado. Tente novamente.\n");

        int duracaoFilme;

        System.out.print("Duração do filme ( minutos ): ");

        if(input.hasNextInt()){
            duracaoFilme = input.nextInt();

            if (duracaoFilme < 0)
                throw new InputMismatchException("\nERRO. A duracao deve ser maior que 0. Tente novamente.\n");

        }else
            throw new InputMismatchException("\nERRO. O valor precisa ser um numero. Tente novamente\n");


        System.out.println("Genero do Filme:");
        System.out.println();
        for (EnumGeneroFilme genero : EnumGeneroFilme.values()) {
            System.out.println(genero.ordinal() + 1 + " - " + genero);
        }
        System.out.println();
        System.out.print("Opcao: ");
        int opcaogeneroFilme = input.nextInt();

        if (opcaogeneroFilme < 1 || opcaogeneroFilme > 5)
            throw new InputMismatchException("\nERRO. Voce digitou uma opcao invalida. Tente novamente.\n");


        EnumGeneroFilme generoFilme = EnumGeneroFilme.values()[opcaogeneroFilme - 1];


        // Informações sobre a sala

        System.out.print("Numero de assentos: ");
        int numeroAssentos = input.nextInt();

        if (numeroAssentos < 0)
            throw new InputMismatchException("\nERRO. O numero de assentos deve ser maior que 0.\n");

        System.out.print("Tipo da Tela ( Reta e Simples / Curvada ): ");
        input.nextLine();
        String tipoTela = input.nextLine();

        if (!tipoTela.equalsIgnoreCase("reta e simples") && !tipoTela.equalsIgnoreCase("curvada"))
            throw new InputMismatchException("\nERRO. Digite um tipo de tela valido ( Reta e Simples / Curvada ). Tente novamente.");

        System.out.print("Localizacao da Model.Sala: ");
        String localizacaoSala = input.nextLine();

        if (localizacaoSala.equalsIgnoreCase(""))
            throw new InputMismatchException("\nERRO. A localizacao da Model.Sala precisa ser informada. Tente novamente.");

        // Estado da sessão

        System.out.print("Digite o estado da sessao ( true / false ): ");
        boolean estadoSessao;
        if (input.hasNextBoolean()){
            estadoSessao = input.nextBoolean();
        }else{
            throw new InputMismatchException("\nERRO. Digite um estado de sessao valido ( true / false ). Tente novamente.\n");
        }

        // Horário da sessão
        System.out.print("Digite o horario da sessão ( HH:MM ): ");
        input.nextLine();
        String horaSessao = input.nextLine();

        if (!horaSessao.contains(":")){
            throw new InputMismatchException("\nERRO. O formato do horario da sessao deve ser HH:MM. Tente novamente.\n");
        }

        if (sessoes.add(new Sessao.SessaoBuilder().buildSala(numeroAssentos, tipoTela, localizacaoSala).buildEstadoSessao(estadoSessao).buildHorario(horaSessao)
                .buildFilme(tituloFilme, duracaoFilme, generoFilme).build())) {
            System.out.println("\nModel.Sessao adicionada com sucesso!");
        } else {
            throw new IllegalArgumentException("\nErro ao adicionar a sessão. Verifique se os dados fornecidos sao validos.\n");
        }
    }

    private static void removerSessao(ArrayList<Sessao> sessoes, Scanner input) {
        System.out.println("=============== REMOVER SESSAO ===============\n");

        System.out.println("\n-=-=-=-= LISTANDO SESSOES NÃO INICIADAS -=-=-=-=");

        for (Sessao ses : sessoes) {
            if(!ses.getEstadoDaSessao()) {
                System.out.printf("Filme: %s%nHorario: %s%n%s%n", ses.getTituloFilme(), ses.getHorario(), ses.getSalas());
                System.out.println("=================================");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        ArrayList<Sessao> sessaoRemover = new ArrayList<>();
        input.nextLine();
        boolean existeFilme = false;

        // Pedindo os dados para comparar e remover

        System.out.print("\nDigite o titulo do Filme para remover a sessao: ");
        String nome_Filme_Remover = input.nextLine();

        System.out.print("Digite o horario do filme para remover a sessao ( HH:MM ): ");
        String horario_Remover_Sessao = input.nextLine();

        System.out.print("Digite a localizacao da sala para conseguir remover a sessao: ");
        String localizacaoSala_Remover = input.nextLine();

        for (Sessao s : sessoes) {
            if (!s.getSalas().isEmpty()) {
                Sala salaRemover = s.getSalas().get(0); // pegando a primeira sala da variavel s

                if (nome_Filme_Remover.equalsIgnoreCase(s.getTituloFilme()) && horario_Remover_Sessao.equalsIgnoreCase(s.getHorario()) && localizacaoSala_Remover.equalsIgnoreCase(salaRemover.getLocalizacao())) {
                    sessaoRemover.add(s);
                    existeFilme = true;
                }
            }
        }

        if (!existeFilme)
            throw new IllegalArgumentException("\nERRO. Os dados estao incorretos. Tente novamente.\n");

        if (sessoes.removeAll(sessaoRemover))
            System.out.println("Sessao removida com sucesso!");
        else
            throw new IllegalArgumentException("\nERRO. A sessao nao foi removida com sucesso. Verifique se os dados informados estao corretos e tente novamente.\n");
    }
}
