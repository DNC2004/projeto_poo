package com.mycompany.projeto_poo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Grupo 3
 */

public class BarcoPatrulha extends Embarcacao implements Radar {
    private boolean isHolofote;
    private boolean isRadarLigado;
    private Motor motor;
    private String tipo;
    
    
    public BarcoPatrulha(String nome, String marca, String modelo, LocalDate data, ZONA zona, Motor motor, ArrayList<Marinheiro> tripulacao, boolean isAtracado, String tipo) {
        super(nome, marca, modelo, data, zona,tripulacao, isAtracado, tipo);
        this.isHolofote = false;
        this.isRadarLigado = false;
        this.motor = motor;
        
    }

    public void setHolofote(boolean holofote) {
        isHolofote = holofote;
    }

    public boolean isisHolofote() {
        return isHolofote;
    }
    
    @Override
    public void mandarParaMissao(ZONA zona){
        super.isOnMission = true;
        ligarRadar();
        setHolofote(true);
        super.isAtracado = false;
        detetarEmbarcacoes(zona);
        
    }
    
    @Override
    public void ligarRadar() {
        if (!this.isRadarLigado) this.isRadarLigado = true;
    }

    @Override
    public void desligarRadar() {
        if (this.isRadarLigado) this.isRadarLigado = false;
    }
    
    public Motor getMotor(){
        return motor;
    }
    
    public void setMotor(Motor motor){
        this.motor = motor;
    }

    @Override
    public ArrayList<Embarcacao> carregarEmbarcacoes(ArrayList<Embarcacao> lista) {
        return null;
    }
    
    @Override
    public ArrayList<Embarcacao> detetarEmbarcacoes(ZONA zona) {
        ArrayList<Embarcacao> listaBarcos = (ArrayList<Embarcacao>) DadosGlobais.globalListaEmbarcacoes;
        if (this.isRadarLigado) {
            ArrayList<Embarcacao> listaZona = new ArrayList<>();
            for(Embarcacao embarcacao : listaBarcos){
                if (embarcacao.getZona() == zona)  listaZona.add(embarcacao);
            }
            return listaZona;
        }
        else {
            System.out.println("Radar desligado, nenhuma embarcação foi detetada!");
            return null;
        }
    }
}
