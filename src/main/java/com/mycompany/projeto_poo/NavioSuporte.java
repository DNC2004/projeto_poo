package com.mycompany.projeto_poo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Grupo 3
 */

public class NavioSuporte extends Embarcacao implements Radar {
    private boolean isHolofote;
    private boolean isRadarLigado;
    private float capacidadeCarga;
    ArrayList <Motor> motores = new ArrayList<>();
    
    
    public NavioSuporte(String nome, String marca, String modelo, LocalDate data, ZONA zona, ArrayList<Motor> motor, ArrayList<Marinheiro> tripulacao, boolean isAtracado,int carga, String tipo) {
        
        super(nome, marca,modelo,data,zona,tripulacao,isAtracado, tipo);
        this.motores = motor;        
        this.isHolofote = false;
        this.capacidadeCarga = carga;
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
        if (this.isRadarLigado != true){
            this.isRadarLigado = true;
        }
    }

    @Override
    public void desligarRadar() {
        if (this.isRadarLigado != false){
            this.isRadarLigado = false;
        }
    }
    
    public ArrayList<Motor> getMotores(){
        return motores;
    }
    
    public void setMotores(ArrayList<Motor> motores){
        this.motores = motores;
    }
    
    public float getCapacidadeCarga(){
        return capacidadeCarga;
    }
    
    public void setCarga(float capacidadeCarga){
        this.capacidadeCarga = capacidadeCarga;
    }

    @Override
    public ArrayList<Embarcacao> carregarEmbarcacoes(ArrayList<Embarcacao> lista) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
