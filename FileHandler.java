package com.mycompany.projeto_poo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Grupo 3
 */

public class FileHandler {
    InputReader leitor = new InputReader();
    
    public FileHandler(){};
    
    // Função para criar ficheiros
    public void criarFicheiro(){
        String  nomeFicheiro = leitor.getText("Nome do ficheiro: ");
        String fullNomeFicheiro = ficheiroHasTxt(nomeFicheiro);
        
        System.out.println("A criar ficheiro: " + fullNomeFicheiro);
        
        if(!procurarFicheiro(fullNomeFicheiro)){ // Certifica-se de que o ficheiro não existe
            try {
                File ficheiro = new File(fullNomeFicheiro);
                ficheiro.createNewFile();
                System.out.println("Ficheiro criado com sucesso!");

            }catch (IOException e){
                System.out.println("Erro ao criar o ficheiro: " + e.getMessage());
            }  
        
        }
        
        else {
            System.out.println("Ficheiro ja existente");
        }
    }
    
    // Verifica se o nome inserido tem ou não o 'txt' (Pode ser adaptada para outro tipo de ficheiros)
    private String ficheiroHasTxt(String nomeFicheiro){
        String typeValid = ".txt";
        String typeCheck = "";
        
        if (nomeFicheiro.length() > 5){
            typeCheck = nomeFicheiro.substring(nomeFicheiro.length() - 4);
        }
        
        if (!typeValid.equals(typeCheck)){
            nomeFicheiro = nomeFicheiro + typeValid;
        }
        return nomeFicheiro;
    }
    
    // Procura o ficheiro na diretoria atual
    private boolean procurarFicheiro(String nomeFicheiro){
        File diretoriaAtual = new File(".");
        File[] ficheiros = diretoriaAtual.listFiles();
        
        if (ficheiros != null){
            for(File ficheiro : ficheiros){
                if (ficheiro.getName().equalsIgnoreCase(nomeFicheiro)){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public String convertMarinheirosToString(ArrayList<Marinheiro> lista) {
        StringBuilder textoFinal = new StringBuilder();

        for (Marinheiro mar : lista) {
            // Extract attributes from the Marinheiro (Sailor)
            String nome = mar.getNome();
            PATENTE patente = mar.getPatente();
            LocalDate nasceu = mar.getDataNascimento();
            int id = mar.getId();

            // Format the sailor's data
            textoFinal.append("---marinheiro---")
                      .append("\nNome: ").append(nome)
                      .append("\nData de nascimento: ").append(nasceu)
                      .append("\nPatente: ").append(patente)
                      .append("\nId: ").append(id)
                      .append("\n")
                      .append("\n---marinheiro---end---\n");
        }

        return textoFinal.toString();
    }

    public String convertBarcosToString(ArrayList<Embarcacao> lista) {
        StringBuilder textoFinal = new StringBuilder();

        // Iterate through each boat
        for (Embarcacao bar : lista) {
            // Extract boat attributes
            int id = bar.getID();
            String nome = bar.getNome();
            String marca = bar.getMarca();
            String modelo = bar.getModelo();
            LocalDate data = bar.getData();
            boolean atracado = bar.getIsAtracado();
            ZONA zona = bar.getZona();
            ArrayList<Marinheiro> trip = bar.getTripulacao(); // Crew of the boat
            String tipo = bar.getTipo();

            // Write boat data inside {}
            textoFinal.append("---embarcacao---\n");
            textoFinal.append("Id: ").append(id).append("\n");
            textoFinal.append("Nome: ").append(nome).append("\n");
            textoFinal.append("Marca: ").append(marca).append("\n");
            textoFinal.append("Modelo: ").append(modelo).append("\n");
            textoFinal.append("Data criado: ").append(data).append("\n");
            textoFinal.append("Zona: ").append(zona).append("\n");
            textoFinal.append("Tipo: ").append(tipo).append("\n");
            textoFinal.append("Atracado: ").append(atracado ? "Afirmativo" : "Negativo").append("\n");

            // Format and add the crew (tripulantes)
            String tripulacao = convertMarinheirosToString(trip);
            textoFinal.append("Tripulacao: valor\n").append(tripulacao);
            
            textoFinal.append("\n---embarcacao---end---\n");

            // End the boat section
            textoFinal.append("\n");
        }

        return textoFinal.toString();
    }

    public void guardarFicheiro() {
        // Get the global lists for sailors and boats
        ArrayList<Marinheiro> listaMarinheiros = (ArrayList<Marinheiro>) DadosGlobais.globalListaMarinheiros;
        ArrayList<Embarcacao> listaBarcosOrdenada = (ArrayList<Embarcacao>) DadosGlobais.globalListaEmbarcacoes;

        // Sort the boats by type
        listaBarcosOrdenada.sort(Comparator.comparing(Embarcacao::getTipo));

        // Get the file name
        String nomeFicheiro = leitor.getText("Nome do ficheiro: ");
        String trueNomeFicheiro = ficheiroHasTxt(nomeFicheiro);

        // Check if the file exists
        if (procurarFicheiro(trueNomeFicheiro)) {
            try (FileWriter escritor = new FileWriter(trueNomeFicheiro, true)) {

                // Write the harbor (port) name at the top
                escritor.write("Nome do porto: " + DadosGlobais.nomePorto + "\n");

                // Write the boats' data
                escritor.write("Informações das embarcações:\n");
                String textoBarcos = convertBarcosToString(listaBarcosOrdenada);
                escritor.write(textoBarcos);

                // Write the sailors' section
                escritor.write("|\n");
                escritor.write("\nInformações dos marinheiros:\n");
                String textoMarinheiros = convertMarinheirosToString(listaMarinheiros);
                escritor.write(textoMarinheiros);

                // Print success message
                System.out.println("Conteúdo guardado com sucesso no ficheiro: " + trueNomeFicheiro);

            } catch (IOException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } else {
            // Handle case where the file doesn't exist
            System.out.println("Ficheiro inexistente na directoria atual.");
            System.out.println("Quer criar o ficheiro?\n1 -> Sim\n2 -> No");
            int ch = leitor.getIntegerNumber("Escolha: ");

            if (ch == 1) {
                criarFicheiro();  // Create the file if the user agrees
            }
        }
    }

    // Carregar texto de um ficheiro V1 (Ver melhor opção para conseguir utilizar o texto no resto do programa)
    public void carregarFicheiro() {
        String nomeFicheiro = leitor.getText("Nome do ficheiro: ");
        String trueNomeFicheiro = ficheiroHasTxt(nomeFicheiro);

        String texto = "";

        if (procurarFicheiro(trueNomeFicheiro)) {

            try (BufferedReader br = new BufferedReader(new FileReader(trueNomeFicheiro))) {
                ArrayList<Marinheiro> marinheiros = new ArrayList<>();
                String line;
                StringBuilder conteudo = new StringBuilder();

                // Read file line by line
                while ((line = br.readLine()) != null) {
                    conteudo.append(line).append("\n");
                }

                texto = conteudo.toString();

                // Extract "Nome do porto" from the first line
                Pattern padraoNomePorto = Pattern.compile("Nome do porto:\\s*(.*)");
                Matcher matcherNomePorto = padraoNomePorto.matcher(texto);

                if (matcherNomePorto.find()) {
                    String nomePorto = matcherNomePorto.group(1).trim();
                    Porto novoPorto = new Porto(nomePorto);
                    DadosGlobais.nomePorto = nomePorto;
                    DadosGlobais.globalListaPortos.add(novoPorto);
                } else {
                    System.out.println("Aviso: Nome do porto não encontrado no ficheiro.");
                }

                // Padrão para capturar embarcações e seus tripulantes
                Pattern padraoEmbarcacao = Pattern.compile("---embarcacao---\\n(.*?)(?=(---embarcacao---end---|$))", Pattern.DOTALL);
                Matcher matcherEmbarcacao = padraoEmbarcacao.matcher(texto);

                while (matcherEmbarcacao.find()) {
                    String textoEmbarcacao = matcherEmbarcacao.group(1);
                    convertStringToBarcosComTripulantes(textoEmbarcacao); // Converte embarcações e tripulantes
                }
                System.out.println(DadosGlobais.globalListaMarinheiros);
                // Padrão para capturar marinheiros globais
                Pattern padraoMarinheiros = Pattern.compile("---marinheiro---\\n(.*?)(?=(---marinheiro---end---|$))", Pattern.DOTALL);
                Matcher matcherMarinheiros = padraoMarinheiros.matcher(texto);

                while (matcherMarinheiros.find()) {
                    String textoMarinheiro = matcherMarinheiros.group(1);
                    convertStringToMarinheiros(marinheiros, textoMarinheiro); // Converte marinheiros
                }
                System.out.println(DadosGlobais.globalListaMarinheiros);
                System.out.println("Conteúdo do ficheiro carregado com sucesso!");

            } catch (IOException e) {
                System.out.println("Erro ao carregar o ficheiro: " + e.getMessage());
            }
        } else {
            System.out.println("Ficheiro não encontrado na diretoria atual.");
        }
    }


    
    // Converte a string de barcos que recebe quando se carrega o ficheiro e converte-os em objetos do tipo barco
    public void convertStringToBarcosComTripulantes(String texto) {
        String[] embarcacoes = texto.split("\n\n"); // Dividir por cada embarcação

        for (String barcoTexto : embarcacoes) {
            if (barcoTexto.trim().isEmpty()) continue;

            int id = 0;
            String nome = "";
            String marca = "";
            String modelo = "";
            LocalDate data = null;
            ZONA zona = null;
            String tipo = "";
            Boolean atracado = false;
            ArrayList<Marinheiro> tripulacao = new ArrayList<>();

            String[] linhas = barcoTexto.split("\n");
            boolean isTripulacaoSection = false; // Flag to track the "Tripulacao" section
            StringBuilder tripulacaoTexto = new StringBuilder();

            for (String linha : linhas) {
                if (linha.trim().isEmpty()) continue;
                
                if (isTripulacaoSection) {
                    // Collect all subsequent tripulante lines
                    tripulacaoTexto.append(linha).append("\n");
                } else {
                    String[] partes = linha.split(": ");
                    if (partes.length < 2) continue;

                    String chave = partes[0].trim();
                    String valor = partes[1].trim();

                    switch (chave) {
                        case "Id":
                            id = Integer.parseInt(valor);
                            break;
                        case "Nome":
                            nome = valor;
                            break;
                        case "Marca":
                            marca = valor;
                            break;
                        case "Modelo":
                            modelo = valor;
                            break;
                        case "Data criado":
                            data = LocalDate.parse(valor);
                            break;
                        case "Zona":
                            zona = ZONA.valueOf(valor.toUpperCase());
                            break;
                        case "Tipo":
                            tipo = valor;
                            break;
                        case "Atracado":
                            atracado = valor.equalsIgnoreCase("Afirmativo");
                            break;
                        case "Tripulacao":
                            isTripulacaoSection = true; // Switch to tripulacao section
                            break;
                    }
                }
            }
            

            // Now process the tripulacao text within the current embarcacao
            if (tripulacaoTexto.length() > 0) {
                // Convert collected tripulacao text into Marinheiro objects
                Pattern padraoMarinheiros = Pattern.compile("---marinheiro---\\n(.*?)(?=(---marinheiro---end---|$))", Pattern.DOTALL);
                Matcher matcherMarinheiros = padraoMarinheiros.matcher(tripulacaoTexto.toString());

                while (matcherMarinheiros.find()) {
                    String textoMarinheiro = matcherMarinheiros.group(1).trim();
                    convertStringToMarinheiros(tripulacao, textoMarinheiro); // Convert marinheiros
                }
            }
            // Criar e adicionar embarcação com tripulantes
            Embarcacao embarcacao = new Embarcacao(nome, marca, modelo, data, zona, tripulacao, atracado, tipo);
            DadosGlobais.globalListaEmbarcacoes.add(embarcacao);
            Porto porto = DadosGlobais.globalListaPortos.get(0);
            porto.addEmbarcacao(embarcacao);
        }
    }


    // Converte a string de Marinheiros que recebe quando se carrega o ficheiro e converte-os em objetos do tipo marinheiro
    public ArrayList<Marinheiro> convertStringToMarinheiros(ArrayList<Marinheiro> lista, String texto) {
        String[] marinheiros = texto.split("\n\n");
        for (String marinheiroTexto : marinheiros) {
            if (marinheiroTexto.trim().isEmpty()) continue;

            String nome = "";
            PATENTE patente = null;
            LocalDate nasceu = null;
            int id = 0;
            int idAtualizada;

            String[] atributos = marinheiroTexto.split("\n");
            for (String atributo : atributos) {
                String[] partes = atributo.split(": ");
                if (partes.length < 2) continue;

                String chave = partes[0].trim();
                String valor = partes[1].trim();

                switch (chave) {
                    case "Nome":
                        nome = valor;
                        break;
                    case "Data de nascimento":
                        nasceu = LocalDate.parse(valor);
                        break;
                    case "Patente":
                        patente = PATENTE.valueOf(valor.toUpperCase());
                        break;
                    case "Id":
                        id = Integer.parseInt(valor);
                        break;
                }
            }

            // Criar marinheiro e adicionar à lista
            Marinheiro marinheiro = new Marinheiro(nome, nasceu, patente);
            Marinheiro.atualizarIdValor(id, 1);
            
            if (!DadosGlobais.globalListaMarinheiros.contains(marinheiro)) {
                DadosGlobais.globalListaMarinheiros.add(marinheiro);
            }
            lista.add(marinheiro);
        }
        return lista;
    }


    
    // Exportar lista de embarcacoes para um ficheiro, o que muda da guardar ficheiro é que esta exporta só
    // a lista de embarcações invés de tudo
    public void exportarEmbarcacoes(){
        String nomeFicheiro = leitor.getText("Nome do ficheiro: ");
        String trueNomeFicheiro = ficheiroHasTxt(nomeFicheiro);
        
        if(procurarFicheiro(trueNomeFicheiro)){
            
            try (FileWriter escritor = new FileWriter(trueNomeFicheiro, true)){    
                ArrayList<Embarcacao> lista = (ArrayList<Embarcacao>) DadosGlobais.globalListaEmbarcacoes;
                String texto = convertBarcosToString(lista);
                
                escritor.write("Lista de barcos exportada:\n");
                escritor.write("{\n");
                escritor.write(texto);
                escritor.write("\n}");
                System.out.println("Conteudo guardado com sucesso no ficheiro: " + trueNomeFicheiro);
                
            } catch (IOException e){
                System.out.println("Erro: " + e.getMessage());
            }
        }
        
        else {
            System.out.println("Ficheiro inesxitente na directoria atual.");
        }
        
        System.out.println("Quer criar o ficheiro?\n1 -> Sim\n2 -> No");
            int ch = leitor.getIntegerNumber("Escolha: ");
            
            if (ch == 1){
                criarFicheiro();
            }
            else {}
    }
}
