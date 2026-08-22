package com.mycompany.projeto_poo;

import java.util.ArrayList;

/**
 *
 * @author Grupo 3
 */
public class Porto implements Radar {

    private String nome;
    private ArrayList<Embarcacao> listaEmbarcacoes;
    private int nTotalMissoes;
    private ZONA zona;
    private boolean isRadarLigado;

    public String getNome() {
        return nome;
    }

    public ZONA getZona() {
        return zona;
    }

    public boolean isIsRadarLigado() {
        return isRadarLigado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setZona(ZONA zona) {
        this.zona = zona;
    }

    public void setIsRadarLigado(boolean isRadarLigado) {
        this.isRadarLigado = isRadarLigado;
    }

    public Porto(String nome) {
        if (nome == null || nome.isEmpty()) {
            this.nome = "null";
        } else {
            this.nome = nome;
        }
        this.listaEmbarcacoes = new ArrayList<Embarcacao>();
        this.nTotalMissoes = 0;
        this.zona = ZONA.INDEFINIDO;
        this.isRadarLigado = false;

    }

    public void mostrarEmbAtracadas() {
        int i = 0;
        System.out.println("====================================");
        System.out.println("\t     INFO Embarcacoes");
        System.out.println("====================================");
        for (Embarcacao embarcacao : listaEmbarcacoes) {
            if (embarcacao.getIsAtracado()) {
                embarcacao.showInfoEmbarcacao(embarcacao);
            }
        }
        System.out.println("====================================");
    }

    public void addEmbarcacao(Embarcacao novaEmbarcacao) {
        listaEmbarcacoes.add(novaEmbarcacao);
    }

    //Por nome 
    public void removeEmbarcacao(Embarcacao novaEmbarcacao) {
        for (int i = 0; i < listaEmbarcacoes.size(); i++) {
            if (listaEmbarcacoes.get(i).getNome().equalsIgnoreCase(novaEmbarcacao.getNome())) {
                listaEmbarcacoes.remove(i);
            }
        }
    }

    public ArrayList<Embarcacao> getListaEmbarcacoes() {
        return this.listaEmbarcacoes;
    }

    public static void enviarMissoes(String tipo, ZONA zona) {
        ArrayList<Embarcacao> listaBarcos = (ArrayList<Embarcacao>) DadosGlobais.globalListaEmbarcacoes;

        switch (tipo) {
            case "perseguicao":
                for (Embarcacao barco : listaBarcos) {
                    if (barco instanceof LanchaRapida && barco.getZona() == zona) {
                        barco.mandarParaMissao(zona);
                    }
                }
                break;
            case "apoio":
                // navio suporte
                for (Embarcacao barco : listaBarcos) {
                    if (barco instanceof NavioSuporte && barco.getZona() == zona) {
                        barco.mandarParaMissao(zona);
                    }
                }
                break;
            case "procura":
                // barcos patrulha
                for (Embarcacao barco : listaBarcos) {
                    if (barco instanceof BarcoPatrulha && barco.getZona() == zona) {
                        barco.mandarParaMissao(zona);
                    }
                }
                break;
        }
        System.out.println("Embarcação enviada para missão!");
    }

    @Override
    public void ligarRadar() {
        if (!this.isRadarLigado) {
            this.isRadarLigado = true;
        }
    }

    @Override
    public void desligarRadar() {
        if (this.isRadarLigado) {
            this.isRadarLigado = false;
        }
    }

    @Override
    public ArrayList<Embarcacao> carregarEmbarcacoes(ArrayList<Embarcacao> lista) {
        return lista;
    }

    @Override
    public ArrayList<Embarcacao> detetarEmbarcacoes(ZONA zona) {
        ArrayList<Embarcacao> listaBarcos = (ArrayList<Embarcacao>) DadosGlobais.globalListaEmbarcacoes;
        if (this.isRadarLigado) {
            ArrayList<Embarcacao> listaZona = new ArrayList<>();
            for (Embarcacao embarcacao : listaBarcos) {
                if (embarcacao.getZona() == zona) {
                    listaZona.add(embarcacao);
                }
            }
            return listaZona;
        } else {
            System.out.println("Radar desligado, nenhuma embarcação foi detetada!");
            return null;
        }
    }

    public static void removerPorto() {
        DadosGlobais.globalListaPortos.remove(0);
        DadosGlobais.nomePorto = null;
        System.out.println("Porto removido com sucesso!\n");
    }

}
