package com.mycompany.projeto_poo;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 *
 *
 * @author Grupo 3
 */
public class Marinheiro {

    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private PATENTE patente;
    int carregado = 0;

    protected Marinheiro(String nome, LocalDate dataNascimento, PATENTE patente) {
        this.id = DadosGlobais.idAtual++;
        
        this.dataNascimento = dataNascimento;

        // Validação da patente
        if (dataNascimento.getYear() > 1989 && patente == PATENTE.OFICIAL) {
            this.patente = PATENTE.ERRO;
        } else {
            this.patente = patente;
        }

        // Validação do nome
        if (nome == null || nome.isBlank()) {
            this.nome = "INDEFINIDO";
        } else {
            this.nome = nome;
        }
    }

    public void setNome(String nome) {
        if (nome.equalsIgnoreCase(" ")); else {
            this.nome = nome;
        }
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setPatente(PATENTE patente) {
        this.patente = patente;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public PATENTE getPatente() {
        return patente;
    }
    
    public int calcIdade(){
        return (int) ChronoUnit.YEARS.between(this.dataNascimento, LocalDate.now());
    }

    public void showInfo() {
        System.out.println("====================================");
        System.out.println("\t  INFO MARINHEIRO");
        System.out.println("====================================");
        System.out.println("Nome:               " + this.nome);
        System.out.println("Data de Nascimento: " + this.dataNascimento);
        System.out.println("Idade:              " + this.calcIdade());
        System.out.println("Patente:            " + this.patente);
        System.out.println("ID:                 " + this.id);
        System.out.println("====================================");
    }
    
    
    public static void verListaMarinheiros() {
        if(DadosGlobais.globalListaMarinheiros.isEmpty()) {
            System.out.println("Não existem marinheiros para mostrar!");
            return;
        }
        
        System.out.println("Lista de marinheiros: ");
        List<Marinheiro> listaOrdenadaMarinheiros = DadosGlobais.globalListaMarinheiros;
        listaOrdenadaMarinheiros.sort(Comparator.comparing(Marinheiro::getNome));

        for (Marinheiro marinheiro : DadosGlobais.globalListaMarinheiros) {
            marinheiro.showInfo();
        }
    }

    // Precisa de ser otimizada (Principalmente a parte de editar o marinheiro em si)
    public static void editarMarinheiro() {
        InputReader leitor = new InputReader();
        String nome = leitor.getText("Nome do marinheiro a editar: ");

        for (Marinheiro marinheiro : DadosGlobais.globalListaMarinheiros) {
            if (marinheiro.getNome().equalsIgnoreCase(nome)) {
                int atributo = leitor.getIntegerNumber("\n1 - Nome\n2 - Data de Nascimento\n3 - Patente\nAtributo a mudar:");
                if (atributo == 1) {
                    String newname = leitor.getText("Novo nome: ");
                    marinheiro.setNome(newname);
                }
                if (atributo == 2) {
                    String data = leitor.getText("Nova data de nascimento (aaaa-mm-dd): ");
                    LocalDate date = LocalDate.parse(data);
                    marinheiro.setDataNascimento(date);
                }
                if (atributo == 3) {
                    String patenteInit = leitor.getText("Nova patente: ");
                    PATENTE patente = PATENTE.valueOf(patenteInit.toUpperCase());
                    marinheiro.setPatente(patente);
                } else {
                    System.out.println("Atributo nao existe");
                }
            }
        }

    }

    public static void removerMarinheiro() {
        InputReader leitor = new InputReader();
        String nome = leitor.getText("Nome do marinheiro a remover: ");

        for (Marinheiro marinheiro : DadosGlobais.globalListaMarinheiros) {
            if (marinheiro.getNome().equalsIgnoreCase(nome)) {
                DadosGlobais.globalListaMarinheiros.remove(marinheiro);
            }
        }
    }

    public static void listaOrdenadaMarinheiros() {
        List<Marinheiro> listaOrdenadaMarinheiros = DadosGlobais.globalListaMarinheiros;
        InputReader leitor = new InputReader();
        boolean continuar = true;
        while (continuar == true) {
            System.out.println("\nAtributo:\n1 -> Id\n2 -> Nome\n3 -> Patente\n4 ->Data de Nascimento\n5 -> Voltar");
            int chAtr = leitor.getIntegerNumber("Escolha: ");
            switch (chAtr) {
                case 1:
                    // Ordenada por id
                    System.out.println("Lista por id:\n");
                    listaOrdenadaMarinheiros.sort(Comparator.comparing(Marinheiro::getId));
                    for (Marinheiro mari : listaOrdenadaMarinheiros) {
                        mari.showInfo();
                    }
                    break;

                case 2:
                    // Ordenada por Nome
                    System.out.println("Lista por nome:\n");
                    listaOrdenadaMarinheiros.sort(Comparator.comparing(Marinheiro::getNome));
                    for (Marinheiro mari : listaOrdenadaMarinheiros) {
                        mari.showInfo();
                    }
                    break;

                case 3:
                    // Ordenada por patente
                    System.out.println("Lista por patente:\n");
                    listaOrdenadaMarinheiros.sort(Comparator.comparing(Marinheiro::getPatente));
                    for (Marinheiro mari : listaOrdenadaMarinheiros) {
                        mari.showInfo();
                    }
                    break;

                case 4:
                    // Ordenada por data de nascimento
                    System.out.println("Lista por data nascimento:\n");
                    listaOrdenadaMarinheiros.sort(Comparator.comparing(Marinheiro::getDataNascimento));
                    for (Marinheiro mari : listaOrdenadaMarinheiros) {
                        mari.showInfo();
                    }
                    break;

                case 5:
                    continuar = false;
                    System.out.println("A regressar...");
                    break;
                default:
                    System.out.println("Opcao inesxitente");
            }
        }
    }
    
    public static void atualizarIdValor(int valorNovo, int carregado){
        if (carregado == 1){
            DadosGlobais.idAtual = valorNovo;
        }
        else {
            DadosGlobais.idAtual = 1000;
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Marinheiro marinheiro = (Marinheiro) obj;
        return id == marinheiro.id; // Compare based on unique ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Ensure hashCode uses the same field used in equals
    }

}




