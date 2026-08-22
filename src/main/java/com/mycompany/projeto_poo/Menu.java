package com.mycompany.projeto_poo;

import java.util.ArrayList;

/**
 *
 * @author Grupo 3
 */
public class Menu {

    private static InputReader inputReader;

    public static void menuManutencao() {
        inputReader = new InputReader();

        OUTER:
        while (true) {
            System.out.println("\n\tModo Manutencao");
            System.out.println("1 -> Porto");
            System.out.println("2 -> Embarcacao");
            System.out.println("3 -> Marinheiro");
            System.out.println("4 -> Voltar");
            int opcao = inputReader.getIntegerNumber("Escolha: ");
            
            switch (opcao) {
                case 1:
                    {
                        System.out.println("\n\tMenu Porto");
                        System.out.println("1 -> Criar Porto");
                        System.out.println("2 -> Editar Porto");
                        System.out.println("3 -> Remover Porto");
                        System.out.println("4 -> Voltar");
                        int subOpcao = inputReader.getIntegerNumber("Escolha: ");
                        switch (subOpcao) {
                            case 1:
                                if (DadosGlobais.nomePorto == null) { // Apenas podemos criar um porto
                                    String nome = inputReader.getText("Nome do Porto: ");
                                    Porto novoPorto = new Porto(nome);
                                    // Definimos o porto para usar em todo o programa
                                    DadosGlobais.nomePorto = nome;
                                    DadosGlobais.globalListaPortos.add(novoPorto);
                                    System.out.printf("Porto \'%s\' criado com sucesso!", nome);
                                    break;
                                } else {
                                    System.out.println("Porto já criado.");
                                    break;
                                }
                            case 2:
                                if (DadosGlobais.nomePorto != null) {
                                    Porto portoAeditar = DadosGlobais.globalListaPortos.get(0);
                                    int opcaoEditPorto;
                                    while (true) {
                                        System.out.println("\n\tMenu Edição Porto");
                                        System.out.println("1 -> Editar Nome");
                                        System.out.println("2 -> Editar Zona");
                                        System.out.println("3 -> Ligar/Desligar Radar");
                                        System.out.println("4 -> Voltar");
                                        opcaoEditPorto = inputReader.getIntegerNumber("Escolha: ");
                                        if (opcao >= 1 || opcao <= 4) {
                                            break;
                                        } else {
                                            System.out.println("Opção inválida, tente novamente!");
                                            continue;
                                        }
                                    }
                                    if (opcaoEditPorto == 1) {
                                        String novoNome = inputReader.getText("Introduza o novo nome do porto: ");
                                        portoAeditar.setNome(novoNome);
                                        System.out.println("Nome do porto editado com sucesso!");
                                        break;
                                    }
                                    if (opcaoEditPorto == 2) {
                                        int i = 1;
                                        ZONA zonaAtual = portoAeditar.getZona();
                                        ZONA novaZona;
                                        while (true) {
                                            System.out.println("Zona atual : " + portoAeditar.getZona().toString());
                                            System.out.println("Zonas disponíveis: ");
                                            for (ZONA zona : ZONA.values()) {
                                                System.out.println("(" + i + ")" + zona.toString());
                                                i++;
                                            }
                                            i--;
                                            int opcaoZona = inputReader.getIntegerNumber("Introduza a zona pretendida " + "(1-" + i + "): ");
                                            
                                            switch (opcaoZona) {
                                                case 1:
                                                    portoAeditar.setZona(ZONA.NORTE);
                                                    break;
                                                case 2:
                                                    portoAeditar.setZona(ZONA.SUL);
                                                    break;
                                                case 3:
                                                    portoAeditar.setZona(ZONA.ESTE);
                                                    break;
                                                case 4:
                                                    portoAeditar.setZona(ZONA.OESTE);
                                                    break;
                                                case 5:
                                                    portoAeditar.setZona(ZONA.INDEFINIDO);
                                                    break;
                                                default:
                                                    System.out.println("Opção inválida, tente novamente...");
                                                    i = 1;
                                                    continue;
                                            }
                                            System.out.println("Zona editada com sucesso!");
                                            break;
                                        }
                                        break;
                                    }
                                    if (opcaoEditPorto == 3) {
                                        boolean isRadarLigado = portoAeditar.isIsRadarLigado();
                                        if (isRadarLigado) {
                                            System.out.println("Radar encontrava-se ligado\nA desligar radar...");
                                            portoAeditar.setIsRadarLigado(!isRadarLigado);
                                            break;
                                        } else {
                                            System.out.println("Radar encontrava-se desligado\nA ligar radar...");
                                            portoAeditar.setIsRadarLigado(!isRadarLigado);
                                            break;
                                        }
                                    }
                                    if (opcaoEditPorto == 4) {
                                        System.out.println("A voltar para o menu do Modo Manutenção");
                                        break;
                                    } else {
                                        System.out.println("Opção inválida, tente novamente!");
                                        continue;
                                    }
                                } else {
                                    System.out.println("Não foi criado o porto para poder ser editado");
                                }
                                break;
                            case 3:
                                Porto.removerPorto();
                                break;
                            case 4:
                                System.out.println("A voltar para o menu Modo Manuntencao.\n");
                                break;
                            default:
                                System.out.println("Opcao inexistente");
                        }       break;
                    }
                case 2:
                    {
                        // Para evitar criar barcos sem primeiro termos um porto
                        if (DadosGlobais.globalListaPortos.isEmpty()) {
                            System.out.println("Deseja trabalhar com embarcações porém ainda não criou o Porto.\nCrie o porto primeiro.\n");
                            String nome = inputReader.getText("Nome do Porto: ");
                            Porto novoPorto = new Porto(nome);
                            DadosGlobais.nomePorto = nome;
                            DadosGlobais.globalListaPortos.add(novoPorto);
                            System.out.printf("Porto \'%s\' criado com sucesso!", nome);
                        }       System.out.println("\n\tMenu Embarcacao");
                        System.out.println("1 -> Criar Embarcacao");
                        System.out.println("2 -> Editar Embarcacao");
                        System.out.println("3 -> Remover Embarcacao");
                        System.out.println("4 -> Voltar");
                        int subOpcao = inputReader.getIntegerNumber("Escolha: ");
                        switch (subOpcao) {
                            case 1:
                                System.out.println("1 -> Embarcacao generica");
                                System.out.println("2 -> Lancha Rapida");
                                System.out.println("3 -> Navio Suporte");
                                System.out.println("4 -> Barco Patrulha");
                                System.out.println("5 -> Voltar");
                                int escolhaEmbarcacao = inputReader.getIntegerNumber("Escolha: ");
                                
                                switch (escolhaEmbarcacao) { // As funcoes adicionam diretamente os barcos criados para a lista
                                    case 1:
                                        inputReader.criarEmbarcacao();
                                        break;
                                        
                                    case 2:
                                        inputReader.criarLancha();
                                        break;
                                        
                                    case 3:
                                        inputReader.criarNavio();
                                        break;
                                        
                                    case 4:
                                        inputReader.criarBarco();
                                        break;
                                        
                                    case 5:
                                        break;
                                        
                                    default:
                                        System.out.println("Opcao inesxitente");
                                }
                                break;
                            case 2:
                                // Editar embarcações
                                Embarcacao.editarEmbarcacao();
                                break;
                            case 3:
                                // Remover embarcações
                                Embarcacao.removerEmbarcacao();
                                break;

                            case 4:
                                break;
                                
                            default:
                                System.out.println("Opcao inexistente");
                        }       break;
                    }
                case 3:
                    {
                        System.out.println("\n\tMenu Marinheiro");
                        System.out.println("1 -> Criar Marinheiro");
                        System.out.println("2 -> Editar Marinheiro");
                        System.out.println("3 -> Remover Marinheiro");
                        System.out.println("4 -> Voltar");
                        int subOpcao = inputReader.getIntegerNumber("Escolha: ");
                        switch (subOpcao) {
                            case 1:
                                inputReader.criarMarinheiro();
                                break;
                            case 2:
                                Marinheiro.editarMarinheiro();
                                break;
                            case 3:
                                Marinheiro.removerMarinheiro();
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("Opcao inexistente");
                        }       break;
                    }
                case 4:
                    break OUTER;
                default:
                    System.out.println("Opcao inexistente");
                    break;
            }
        }
    }

    public static void menuUtilizacao() {
        inputReader = new InputReader();
        boolean existeEmbarcacaoValida = false;
        boolean embEncontrada = false;
        OUTER:
        while (true) {
            System.out.println("\n\tModo Utilizacao");
            System.out.println("1 -> Enviar missoes");
            System.out.println("2 -> Terminar missoes");
            System.out.println("3 -> Ver listas");
            System.out.println("4 -> Ficheiros");
            System.out.println("5 -> Voltar");
            int opcao = inputReader.getIntegerNumber("Escolha: ");
            switch (opcao) {
                case 1:
                    System.out.println("Tipos de missoes:\n1 -> Perseguicao e Captura\n2 -> Apoio\n3 -> Procura e Salvamento\n4 -> Voltar");
                    while (true) {
                        String zona;
                        int tipoMissao = inputReader.getIntegerNumber("Escolha: ");
                        String zonaAAAA = inputReader.getZoneFromUser(1);
                        if (zonaAAAA == null) {
                            break;
                        } else {
                            ZONA zonaTemp = ZONA.valueOf(zonaAAAA.toUpperCase());
                            switch (tipoMissao) {
                                case 1:
                                    for (Embarcacao embarcacao : DadosGlobais.globalListaEmbarcacoes) {
                                        if (embarcacao instanceof LanchaRapida && embarcacao.isIsAtracado()) {
                                            existeEmbarcacaoValida = true;
                                        }
                                    }
                                    if (existeEmbarcacaoValida) Porto.enviarMissoes("perseguicao", zonaTemp);
                                    else System.out.println("Não existem embarcações válidas para esta missão.");
                                    break;
                                    
                                
                                case 2:
                                    for (Embarcacao embarcacao : DadosGlobais.globalListaEmbarcacoes) {
                                        if (embarcacao instanceof NavioSuporte && embarcacao.isIsAtracado()) {
                                            existeEmbarcacaoValida = true;
                                        }
                                    }
                                    if (existeEmbarcacaoValida) Porto.enviarMissoes("apoio", zonaTemp);
                                    else System.out.println("Não existem embarcações válidas para esta missão.");
                                    break;
                                case 3:
                                    for (Embarcacao embarcacao : DadosGlobais.globalListaEmbarcacoes) {
                                        if (embarcacao instanceof BarcoPatrulha && embarcacao.isIsAtracado()) {
                                            existeEmbarcacaoValida = true;
                                        }
                                    }
                                    if (existeEmbarcacaoValida) Porto.enviarMissoes("procura", zonaTemp);
                                    else System.out.println("Não existem embarcações válidas para esta missão.");
                                    break;
                                case 4:
                                    System.out.println("A regressar ao menu...");
                                    break;
                                    
                                default:
                                    System.out.println("Opcao inesxitente!");
                                    break;
                            }
                            break;
                        }
                    }   break;
                
                case 2:
                        ArrayList<Embarcacao> embsEmMissao = new ArrayList<>();
                       for (Embarcacao embarcacao : DadosGlobais.globalListaEmbarcacoes) {
                           if (embarcacao.getIsOnMission()) {
                               embsEmMissao.add(embarcacao);
                           }
                       }

                       if (embsEmMissao.isEmpty()) {
                           System.out.println("Não existem embarcações em missão!");
                           break;
                       }

                       System.out.println("Embarcações em missão atualmente:\n");
                       for (Embarcacao embEmMissao : embsEmMissao) {
                           embEmMissao.showInfoEmbarcacao(embEmMissao);
                       }
                       try {
                           String nomeEmbATerminarMissao = inputReader.getText("Introduza o nome da embarcação em que pretende terminar a missão e que regresse ao Porto: ");
                           for (Embarcacao embEmMissao : embsEmMissao) {
                               //Compara nome das embarcações com o nome introduzido
                               if (nomeEmbATerminarMissao.equals(embEmMissao.getNome())) {
                                   embEmMissao.setAtracado(true);
                                   embEmMissao.setIsOnMission(false);
                                   embEncontrada = true;
                                   break;
                               }
                           }
                           if (!embEncontrada) {
                               throw new Exception("Nome inválido");
                           }
                       } catch (Exception e) {
                           System.out.println("Nome de embarcação inválido.\n");
                           break;
                       }
                       System.out.println("Missão terminada com sucesso!\nEmbarcação a voltar ao Porto...\n");
                   break;

                
                case 3:
                    {
                        System.out.println("\n\tMenu Listas");
                        System.out.println("1 -> Lista Embarcacoes");
                        System.out.println("2 -> Lista Marinheiros");
                        System.out.println("3 -> Lista atributos");
                        System.out.println("4 -> Voltar");
                        int subOpcao = inputReader.getIntegerNumber("Escolha: ");
                        switch (subOpcao) {
                            case 1:
                                Embarcacao.verListaEmbarcacoes();
                                break;
                            case 2:
                                //  Ver lista marinheiros
                                Marinheiro.verListaMarinheiros();
                                break;
                            case 3:
                                // Ver lista por atributos
                                InputReader.listaAtributos();
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("Opcao inexistente");
                        }       break;
                    }
                case 4:
                    {
                        //Objeto vazio para se poder usar as funções da classe
                        FileHandler f = new FileHandler();
                        System.out.println("\n\tMenu Ficheiros");
                        System.out.println("1 -> Guardar informacoes");
                        System.out.println("2 -> Carregar informacoes");
                        System.out.println("3 -> Exportar lista deembarcacoes");
                        System.out.println("4 -> Voltar");
                        int subOpcao = inputReader.getIntegerNumber("Escolha: ");
                         switch (subOpcao) {
                            case 1 -> // Guardar informacoes num ficheiro
                                f.guardarFicheiro();
                            case 2 -> // Carregar apartir de um ficheiro (Ainda por funcionar top)
                                f.carregarFicheiro();
                            case 3 -> // Exportar lista de embarcacoes
                                f.exportarEmbarcacoes();
                            case 4 -> {
                    }
                            default -> System.out.println("Opcao inexistente");
                        }
                
                    }
                case 5:
                    break OUTER;
                default:
                    System.out.println("Opcao inexistente");
                    break;
            }
        }
    }

}
