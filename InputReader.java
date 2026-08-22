package com.mycompany.projeto_poo;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Grupo 3
 */
public class InputReader {

    private Scanner reader;

    // Construtor
    public InputReader() {
        reader = new Scanner(System.in);
    }

    // Reader para números inteiros
    public int getIntegerNumber(String question) {
        showFormattedQuestion(question);
        while (!reader.hasNextInt()) {
            reader.nextLine();
            showFormattedQuestion(question);

        }

        int number = reader.nextInt();
        reader.nextLine();
        return number;
    }

    // Reader para números floats
    public float getFloatNumber(String question) {
        showFormattedQuestion(question);
        while (!reader.hasNextFloat()) {
            reader.nextLine();
            showFormattedQuestion(question);

        }

        float number = reader.nextFloat();
        reader.nextLine();
        return number;
    }

    // Reader para texto
    public String getText(String question) {

        showFormattedQuestion(question);

        return reader.nextLine();
    }

    // Reader para Chars
    public char getCharacter(String question) {
        String strAux = getText(question);

        if (strAux.isEmpty()) {
            return ' ';
        } else {
            return strAux.charAt(0);
        }

    }

    // Imprime a pergunta 
    private void showFormattedQuestion(String question) {

        if (question == null) {
            question = "";
        }
        System.out.print(question);

    }

    public String getZoneFromUser(int tipo) {
        while (true) {
            if (tipo == 1) {
                System.out.println("Zona da missao:\n1 -> Norte\n2 -> Sul\n3 -> Este\n4 -> Oeste\n5 -> Voltar");
            }
            if (tipo == 2) {
                System.out.println("Zona da Embarcação:\n1 -> Norte\n2 -> Sul\n3 -> Este\n4 -> Oeste\n5 -> Indefinido");
            }
            int zonaMissao = this.getIntegerNumber("Escolha: ");
            switch (zonaMissao) {
                case 1:
                    return "norte";
                case 2:
                    return "sul";
                case 3:
                    return "este";
                case 4:
                    return "oeste";
                case 5:
                    return tipo == 1 ? null : "indefinido";
                default: {
                    System.out.println("Opcao inexistente");
                }
            }
        }
    }

    public String getCombustivelFromUser() {
        while (true) {
            System.out.println("Combustivel:\n1 -> Gasolina\n2 -> Diesel");
            int zonaMissao = this.getIntegerNumber("Escolha: ");
            switch (zonaMissao) {
                case 1:
                    return "gasolina";
                case 2:
                    return "diesel";
                default: {
                    System.out.println("Opção inválida");
                }
            }
        }
    }

    // Cria um novo marinheiro mas sem precisar de associar este a uma lista, tirando a global
    public Marinheiro criarMarinheiro() {
        InputReader leitor = new InputReader();
        System.out.println("CRIAR MARINHEIRO: ");
        String nome = leitor.getText("Nome: ");

        PATENTE patente = null;

        while (patente == null) {
            try {
                String patenteInput = leitor.getText("Patente: ");
                patente = PATENTE.valueOf(patenteInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Patente inválida. Tente novamente.");
            }
        }

        LocalDate dataNascimento = null;
        while (dataNascimento == null) {
            try {
                String dataInput = leitor.getText("Data de nascimento (aaaa-mm-dd): ");
                dataNascimento = LocalDate.parse(dataInput);
            } catch (Exception e) {
                System.out.println("Data inválida. Tente novamente.");
            }
        }

        Marinheiro mTemp = new Marinheiro(nome, dataNascimento, patente);
        DadosGlobais.globalListaMarinheiros.add(mTemp);

        return mTemp;
    }

    // Cria um novo marinheiro mas precisa de o associar a uma lista sem ser a global, utilizada quando se cria um tripulante diretamente no barco
    public void criarTripulante(ArrayList<Marinheiro> lista) {
        Marinheiro tripulante = criarMarinheiro();
        lista.add(tripulante);
    }

    // Verifica se existe um barco com o nome que estamos tentar colocar
    private String nomeUserGetter() {
        InputReader leitor = new InputReader();
        ArrayList<Embarcacao> lista = (ArrayList<Embarcacao>) DadosGlobais.globalListaEmbarcacoes;
        while (true) {
            var nome = leitor.getText("Nome: ");
            boolean existe = false;
            for (Embarcacao barcoIte : lista) {
                if (barcoIte.getNome().equalsIgnoreCase(nome)) {
                    System.out.println("Ja existe uma embarcacao com este nome.\nEscolha outro.");
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                return nome;
            }
        }
    }

    public static LocalDate getDataFromUser() {
        InputReader leitor = new InputReader();
        String data;
        LocalDate date;
        while (true) {
            data = leitor.getText("Data (aaaa-mm-dd): ");
            try {
                date = LocalDate.parse(data);
                break;
            } catch (Exception e) {
                System.out.println("Formato de data inválido, tente novamente!");
            }
        }
        return date;
    }

    // Barco generico
    public Embarcacao criarEmbarcacao() {
        InputReader leitor = new InputReader();
        

        System.out.println("\tNova embarcacao generica");

        var nome = nomeUserGetter();
        var marca = leitor.getText("Marca: ");
        var modelo = leitor.getText("Modelo: ");

        LocalDate date = InputReader.getDataFromUser();

        String zona = leitor.getZoneFromUser(2);
        ZONA zone = ZONA.valueOf(zona.toUpperCase());

        

        ArrayList<Marinheiro> tripulacaoNew = new ArrayList<>();

        while (true) {
            int fazerTrip = leitor.getIntegerNumber("Criar Tripulacao (1 -> Sim / 2 -> Nao): ");
            if (fazerTrip == 1) {
                criarTripulacao(tripulacaoNew, "gen");
            } else if (fazerTrip == 2) {
                break;
            } else {
                System.out.println("Opcao inesxitente");
            }
        }

        Embarcacao emb1 = new Embarcacao(nome, marca, modelo, date, zone, tripulacaoNew, true, "Generica");
        
            Porto porto = DadosGlobais.globalListaPortos.get(0);
            porto.addEmbarcacao(emb1);
        
        DadosGlobais.globalListaEmbarcacoes.add(emb1);

        return emb1;
    }

    // Barco Patrulha
    public BarcoPatrulha criarBarco() {
        InputReader leitor = new InputReader();

        

        System.out.println("\tNovo BarcoPatrulha");

        var nome = nomeUserGetter();
        var marca = leitor.getText("Marca: ");
        var modelo = leitor.getText("Modelo: ");

        LocalDate date = InputReader.getDataFromUser();

        String zona = leitor.getZoneFromUser(2);
        ZONA zone = ZONA.valueOf(zona.toUpperCase());

        Motor motor = Motor.criarMotor();

        

        ArrayList<Marinheiro> tripulacaoNew = new ArrayList<>();

        while (true) {
            int fazerTrip = leitor.getIntegerNumber("Criar Tripulacao (1 -> Sim / 2 -> Nao): ");
            if (fazerTrip == 1) {
                criarTripulacao(tripulacaoNew, "barco");
            } else if (fazerTrip == 2) {
                break;
            } else {
                System.out.println("Opcao inesxitente");
            }
        }

        BarcoPatrulha bp1 = new BarcoPatrulha(nome, marca, modelo, date, zone, motor, tripulacaoNew, true, "Barco Patrulha");
        
            Porto porto = DadosGlobais.globalListaPortos.get(0);
            porto.addEmbarcacao(bp1);
        
        DadosGlobais.globalListaEmbarcacoes.add(bp1);
        return bp1;
    }

    //Navio Suporte
    public NavioSuporte criarNavio() {
        InputReader leitor = new InputReader();

        boolean isAtracado;

        ArrayList<Motor> novosMotores = new ArrayList<>();

        System.out.println("\tNovo Navio Suporte");

        var nome = nomeUserGetter();

        var marca = leitor.getText("Marca: ");
        var modelo = leitor.getText("Modelo: ");

        LocalDate date = InputReader.getDataFromUser();

        String zona = leitor.getZoneFromUser(2);
        ZONA zone = ZONA.valueOf(zona.toUpperCase());

        var carga = leitor.getIntegerNumber("Capacidade de carga: ");
        for (int i = 0; i < 2; i++) {
            Motor motor = Motor.criarMotorNavioSuporte();
            novosMotores.add(motor);
        }

        

        ArrayList<Marinheiro> tripulacaoNew = new ArrayList<>();

        while (true) {
            int fazerTrip = leitor.getIntegerNumber("Criar Tripulacao (1 -> Sim / 2 -> Nao): ");
            if (fazerTrip == 1) {
                criarTripulacao(tripulacaoNew, "navio");
            } else if (fazerTrip == 2) {
                break;
            } else {
                System.out.println("Opcao inesxitente");
            }
        }
        NavioSuporte ns1 = new NavioSuporte(nome, marca, modelo, date, zone, novosMotores, tripulacaoNew, true, carga, "Navio Suporte");
        
            Porto porto = DadosGlobais.globalListaPortos.get(0);
            porto.addEmbarcacao(ns1);
        
        DadosGlobais.globalListaEmbarcacoes.add(ns1);
        return ns1;
    }

    // Lancha fast
    public LanchaRapida criarLancha() {
        InputReader leitor = new InputReader();

        boolean isAtracado;

        ArrayList<Motor> novosMotores = new ArrayList<>();

        System.out.println("Nova Lancha Rapida");

        var nome = nomeUserGetter();

        var marca = leitor.getText("Marca: ");
        var modelo = leitor.getText("Modelo: ");

        LocalDate date = InputReader.getDataFromUser();

        String zona = leitor.getZoneFromUser(2);
        ZONA zone = ZONA.valueOf(zona.toUpperCase());

        while (true) {
            int numMotores = leitor.getIntegerNumber("Num de motores (De 2 a 4): ");
            if (numMotores <= 4 || numMotores >= 2) {
                for (int i = 0; i < numMotores; i++) {
                    Motor motor = Motor.criarMotor();
                    novosMotores.add(motor);
                }
                break;
            } else {
                System.out.println("Numero invalido.");
            }
        }

        

        ArrayList<Marinheiro> tripulacaoNew = new ArrayList<>();

        while (true) {
            int fazerTrip = leitor.getIntegerNumber("Criar Tripulacao (1 -> Sim / 2 -> Nao): ");
            if (fazerTrip == 1) {
                criarTripulacao(tripulacaoNew, "lancha");
            } else if (fazerTrip == 2) {
                break;
            } else {
                System.out.println("Opcao inesxitente");
            }
        }

        LanchaRapida lr1 = new LanchaRapida(nome, marca, modelo, novosMotores, date, zone, tripulacaoNew, true, "Lancha Rapida");
        DadosGlobais.globalListaEmbarcacoes.add(lr1);
        
            Porto porto = DadosGlobais.globalListaPortos.get(0);
            porto.addEmbarcacao(lr1);
        
        return lr1;
    }

    public void criarTripulacao(ArrayList<Marinheiro> lista, String tipo) {
        InputReader leitor = new InputReader();

        while (true) {
            System.out.println("1 - > Criar Marinheiros\n2 - > Adicionar por nome\n");
            int criarMarinheiro = leitor.getIntegerNumber("Escolha: ");
            if (criarMarinheiro == 1) {
                int numMarinheiros = leitor.getIntegerNumber("Quantos marinheiros quer adicionar: ");
                if (tipo.equalsIgnoreCase("lancha") || tipo.equalsIgnoreCase("barco")){
                    if (numMarinheiros > 4 || numMarinheiros < 2){
                        System.out.println("So pode ter entre 2 e 4 tripulantes");
                    }
                    else {
                        for (int i = 0; i < numMarinheiros; i++) {
                            criarTripulante(lista);
                        }
                        break;
                    }
                }
                
                if (tipo.equalsIgnoreCase("navio")){
                    if (numMarinheiros > 10 || numMarinheiros < 4){
                        System.out.println("O navio tem de ter entre 4 e 10 tripulantes");
                    }
                    
                    else {
                        for (int i = 0; i < numMarinheiros; i++) {
                            criarTripulante(lista);
                        }
                        break;
                    }
                }
                
                if (tipo.equalsIgnoreCase("gen")){
                    for (int i = 0; i < numMarinheiros; i++) {
                            criarTripulante(lista);
                        }
                        break;
                }
                
                
            } else if (criarMarinheiro == 2) {
                Marinheiro ma = procurarMarinheiro();
                if (ma != null) {
                    lista.add(ma);
                }
                break;
            } else {
                System.out.println("Opcao inesxitente.");
            }

        }
    }

    public static void listaAtributos() {
        InputReader leitor = new InputReader();
        while (true) {
            System.out.println("Lista por atributos\n1 -> Marinheiros\n2 -> Embarcacoes\n3 -> Voltar");
            int chLi = leitor.getIntegerNumber("Escolha: ");
            if (chLi == 1) {
                // Marinheiro
                if (DadosGlobais.globalListaMarinheiros.isEmpty()) {
                    System.out.println("Não existem marinheiros para listar...\n");
                    break;
                }
                Marinheiro.listaOrdenadaMarinheiros();
            } else if (chLi == 2) {
                // Embarcacao
                if (DadosGlobais.globalListaEmbarcacoes.isEmpty()) {
                    System.out.println("Não existem embarcações para listar...\n");
                    break;
                }
                Embarcacao.listaOrdenadaEmbarcacoes();
            } else if (chLi == 3) {
                System.out.println("A regressar...");
                break;
            } else {
                System.out.println("opcao inesxitente");
            }
        }
    }
    
    public Marinheiro procurarMarinheiro() {
        InputReader leitor = new InputReader();
        String nome = leitor.getText("Nome do marinheiro: ");
        for (Marinheiro marinheiroProc : DadosGlobais.globalListaMarinheiros) {
            if (marinheiroProc.getNome().equalsIgnoreCase(nome)) {
                return marinheiroProc;
            }
        }

        return null;
    }

}
