package com.mycompany.projeto_poo;

/**
 *
 * @author Grupo 3
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Embarcacao {
    protected int id;
    protected static int proxId = 1000;
    protected String nome;
    protected String marca;
    protected String modelo;
    protected LocalDate data;
    protected ZONA zona;
    protected ArrayList<Marinheiro> tripulacao;
    protected boolean isAtracado;
    protected boolean isOnMission;
    protected String tipo;
    
    public Embarcacao(String nome, String marca, String modelo, LocalDate data, ZONA zona,ArrayList<Marinheiro> tripulacao, boolean isAtracado, String tipo) {
        this.id = proxId++;
        this.nome = nome;
        this.marca = marca;
        this.modelo = modelo;
        this.data = data;
        this.zona = zona;       
        this.tripulacao = tripulacao;
        this.isAtracado = isAtracado;
        this.isOnMission = false;
        this.tipo = tipo;
    }
    
    public int getID() {
        return id;
    }

    public void setID(int ID) {
        if(ID>10){
            this.id = ID;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(marca.equalsIgnoreCase(" "));
        else{
            this.nome = nome;
        }
        
    }

    public String getMarca() {
        return marca;
    }

    public boolean isIsAtracado() {
        return isAtracado;
    }

    public void setMarca(String marca) {
        if(marca.equalsIgnoreCase(" "));
        else{
            this.marca = marca;
        }
        
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if(modelo.equalsIgnoreCase(" "));
        else{
            this.modelo = modelo;
        }
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public ZONA getZona() {
        return zona;
    }

    public void setZona(ZONA zona) {
        this.zona = zona;
    }

    public ArrayList<Marinheiro> getTripulacao() {
        return tripulacao;
    }

    public void setTripulacao(ArrayList<Marinheiro> tripulacao) {
        this.tripulacao = tripulacao;
    }

    public boolean getIsAtracado() {
        return isAtracado;
    }
    
    public boolean getIsOnMission(){
        return this.isOnMission;
    }
    
    public String getTipo(){
        return this.tipo;
    }

    public void setAtracado(boolean isAtracado) {
        this.isAtracado = isAtracado;
    }

    public void showInfoEmbarcacao(Embarcacao barco){
        System.out.println("====================================");
        System.out.println("\t     INFO EMBARCACAO");
        System.out.println("====================================");
        System.out.println("Nome      : " + barco.getNome());
        System.out.println("Marca     : " + barco.getMarca());
        System.out.println("Modelo    : " + barco.getModelo());
        System.out.println("ID        : " + barco.getID());
        System.out.println("Marca     : " + barco.getMarca());
        System.out.println("Zona      : " + barco.getZona());
        System.out.println("Tipo      : " + barco.getTipo());
        System.out.println("Atracado  : " + (barco.getIsAtracado() ? "Afirmativo" : "Negativo" ));
        System.out.println("Em missao : " + (barco.getIsOnMission() ? "Afirmativo" : "Negativo" )); 
        System.out.println("Tripulacao: ");
        for (Marinheiro tripulante : this.tripulacao){
            tripulante.showInfo();
        }
        System.out.println("====================================");
    }
    
    public static ArrayList<Embarcacao> embarcacoesPorZona(ZONA zona) {
        ArrayList<Embarcacao> listaPorZona = new ArrayList<>();
        
        for (Embarcacao embarcacao : DadosGlobais.globalListaEmbarcacoes) 
            if (embarcacao.getZona() == zona) 
                listaPorZona.add(embarcacao);            
        
        if (listaPorZona.isEmpty()) 
            return null;
 
        return listaPorZona;
    }
    
    public static void adicionarEmbarcacao(Embarcacao barco){
        if (DadosGlobais.nomePorto != null){
            DadosGlobais.globalListaEmbarcacoes.add(barco);
        }
        else {
            System.out.println("Porto ainda nao criado.");
        }
    }
    
    public static void verListaEmbarcacoes() {
        InputReader leitor = new InputReader();
        ArrayList<Embarcacao> listaEmbarcacaoZona = new ArrayList<>();
        int i = 0;
        if (DadosGlobais.globalListaEmbarcacoes.isEmpty()) {
            System.out.println("Não foram adicionadas embarcações ao sistema!\n");
            return;
        }
        while(true) {
            int atracado = leitor.getIntegerNumber("Lista de embarcações atracadas? (1 -> Sim / 2 -> Nao / 3 -> Voltar): ");
            boolean isEmbAtracadas = false;
            if(atracado > 3 || atracado < 1) continue;
                if (atracado == 1) {
                    for(Embarcacao emb : DadosGlobais.globalListaEmbarcacoes) {
                        if(emb.isAtracado) isEmbAtracadas = true;                          
                    }
                    if(!isEmbAtracadas) {
                        System.out.println("Não existem embarcações atracadas para mostrar!");
                        return;
                    }
                    DadosGlobais.globalListaPortos.get(0).mostrarEmbAtracadas();           
                    break;
                }   
            
                if (atracado == 2) {
                    int isPorZona = leitor.getIntegerNumber("Lista de embarcações por zona? (1 -> Sim / 2 -> Não): ");

                    switch (isPorZona) {
                        case 1:
                            System.out.println("Selecione a zona pretendida:");
                            System.out.println("1 - NORTE");
                            System.out.println("2 - SUL");
                            System.out.println("3 - ESTE");
                            System.out.println("4 - OESTE");
                            System.out.println("5 - INDEFINIDO");

                            int zonaEscolhida = leitor.getIntegerNumber("Escolha uma opção (1-5): ");
                            ZONA zona = null;

                            switch (zonaEscolhida) {
                                case 1: 
                                    zona = ZONA.NORTE;
                                    break;
                                case 2: 
                                    zona = ZONA.SUL;
                                    break;
                                case 3: 
                                    zona = ZONA.ESTE;
                                    break;
                                case 4: 
                                    zona = ZONA.OESTE;
                                    break;
                                case 5:                                    
                                    zona = ZONA.INDEFINIDO;
                                    break;
                                default: 
                                    System.out.println("Opção inválida, tente novamente!");
                            }

                            if (zona != null) {
                                if (embarcacoesPorZona(zona) != null) {
                                    for(Embarcacao embarcacao : embarcacoesPorZona(zona)) 
                                        embarcacao.showInfoEmbarcacao(embarcacao);                                 
                                }
                                else {
                                    System.out.println("Não existem embarcações nesta zona!\n");
                                    break;
                                }                                                              
                            }
                        case 2:
                            System.out.println("Lista de todas as embarcações: ");
                            List<Embarcacao> listaOrdenada = DadosGlobais.globalListaEmbarcacoes;
                            listaOrdenada.sort(Comparator.comparing(Embarcacao :: getTipo));
                            for(Embarcacao embarcacao : listaOrdenada) 
                                embarcacao.showInfoEmbarcacao(embarcacao);
                            break;
                    }

                }


                if (atracado == 3){
                    System.out.println("A regressar...");
                    break;

                }

                else {
                    System.out.println("Opcao inesxitente");
                }
            }
    }
    public void mandarParaMissao(ZONA zona){
        this.isOnMission = true;
        
    }

    public static void editarEmbarcacao(){
        InputReader leitor = new InputReader();
        int choice = leitor.getIntegerNumber("Quer ver a lista de embarcacoes?\n 1 - Sim, 2 - Nao: ");
        if(choice == 1){
            for (Embarcacao embarcacao : DadosGlobais.globalListaEmbarcacoes){
                embarcacao.showInfoEmbarcacao(embarcacao);
            }
        }
        String nome = leitor.getText("\nNome da embarcação a editar: ");

        for (Embarcacao embarcacao : DadosGlobais.globalListaEmbarcacoes){
            if (embarcacao.getNome().equalsIgnoreCase(nome)){
                int atributo = leitor.getIntegerNumber("\n1 - Nome\n2 - Marca\n3 - Modelo\n4 - Data\nAtributo a mudar:");
                if(atributo == 1){
                    String novoNome = leitor.getText("Novo nome: ");
                    embarcacao.setNome(novoNome);
                }
                else if(atributo == 2){
                    String novaMarca = leitor.getText("Nova marca: ");
                    embarcacao.setMarca(novaMarca);                    
                }
                else if(atributo == 3){
                    String novoModelo = leitor.getText("Novo modelo: ");
                    embarcacao.setModelo(novoModelo);
                }
                else if(atributo == 4){
                    String novaData = leitor.getText("Nova data (yyyy-mm-dd): ");
                    LocalDate date = LocalDate.parse(novaData);
                    embarcacao.setData(date);                    
                }
                else{
                    System.out.println("Atributo invalido.");
                }
            }
        }   
    }
    
    
    public void setIsOnMission(boolean isOnMission) {
        this.isOnMission = isOnMission;
    }

 public static void removerEmbarcacao(){
        InputReader leitor = new InputReader();
        int choice = leitor.getIntegerNumber("Quer ver a lista de embarcacoes?\n 1 -> Sim, 2 -> Nao: ");
        if(choice == 1){
            for (Embarcacao embarcacao : DadosGlobais.globalListaEmbarcacoes){
                embarcacao.showInfoEmbarcacao(embarcacao);
            }
        }
        String nome = leitor.getText("\nNome da embarcação a remover: ");
        try {
            DadosGlobais.globalListaEmbarcacoes.removeIf(embarcacao -> embarcacao.getNome().equalsIgnoreCase(nome));
        }
        catch (Exception e) {
            System.out.println("Erro ao apagar embarcação: " + e.getMessage());                
        }
    }
    
    public static void listaOrdenadaEmbarcacoes(){
        List<Embarcacao> listaOrdenadaBarcos = DadosGlobais.globalListaEmbarcacoes;
        InputReader leitor = new InputReader();
        boolean continuar = true;
        while(continuar == true){
            System.out.println("""
                               
                               
                                   Atributo:
                                   1 -> Id
                                   2 -> Nome
                                   3 -> Marca
                                   4 -> Modelo
                                   5 -> Data de Criado
                                   6 -> Zona
                                   7 -> Tipo
                                   8 -> Voltar
                                   
                                   """);
            
            int chBar = leitor.getIntegerNumber("Escolha: ");
            switch (chBar){
                case 1:
                    // Ordenada por id
                    System.out.println("Lista ordenada por id:\n");
                    listaOrdenadaBarcos.sort(Comparator.comparing(Embarcacao :: getID));
                    for(Embarcacao embarcacao : listaOrdenadaBarcos){ 
                        embarcacao.showInfoEmbarcacao(embarcacao);
                    }
                    break;
                case 2:
                    // Ordenada por nome
                    System.out.println("Lista ordenada por nome:\n");
                    listaOrdenadaBarcos.sort(Comparator.comparing(Embarcacao :: getNome));
                    for(Embarcacao embarcacao : listaOrdenadaBarcos){ 
                        embarcacao.showInfoEmbarcacao(embarcacao);
                    }
                    break;
                case 3:
                    // Ordenada por marca
                    System.out.println("Lista ordenada por marca:\n");
                    listaOrdenadaBarcos.sort(Comparator.comparing(Embarcacao :: getMarca));
                    for(Embarcacao embarcacao : listaOrdenadaBarcos){ 
                        embarcacao.showInfoEmbarcacao(embarcacao);
                    }
                    break;
                case 4:
                    // Ordenada por modelo
                    System.out.println("Lista ordenada por modelo:\n");
                    listaOrdenadaBarcos.sort(Comparator.comparing(Embarcacao :: getModelo));
                    for(Embarcacao embarcacao : listaOrdenadaBarcos){ 
                        embarcacao.showInfoEmbarcacao(embarcacao);
                    }
                    break;
                case 5:
                    // Ordenada por data
                    System.out.println("Lista ordenada por data:\n");
                    listaOrdenadaBarcos.sort(Comparator.comparing(Embarcacao :: getData));
                    for(Embarcacao embarcacao : listaOrdenadaBarcos){ 
                        embarcacao.showInfoEmbarcacao(embarcacao);
                    }
                    break;
                case 6:
                    // Ordenada por Zona
                    System.out.println("Lista ordenada por zona:\n");
                    listaOrdenadaBarcos.sort(Comparator.comparing(Embarcacao :: getZona));
                    for(Embarcacao embarcacao : listaOrdenadaBarcos){ 
                        embarcacao.showInfoEmbarcacao(embarcacao);
                    }
                    break;
                case 7:
                    // Ordenada por tipo
                    System.out.println("Lista ordenada por tipo:\n");
                    listaOrdenadaBarcos.sort(Comparator.comparing(Embarcacao :: getTipo));
                    for(Embarcacao embarcacao : listaOrdenadaBarcos){ 
                        embarcacao.showInfoEmbarcacao(embarcacao);
                    }
                    break;
                case 8:
                    System.out.println("A regresar...");
                    continuar=false;
                    break;
                default:
                    System.out.println("Opcao inesxitente");
                 
                break;
            }
        }
    }
}
