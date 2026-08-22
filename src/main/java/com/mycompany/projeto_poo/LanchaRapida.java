package com.mycompany.projeto_poo;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Grupo 3
 */

public class LanchaRapida extends Embarcacao{
    protected boolean isHolofote;
    ArrayList <Motor> motores = new ArrayList<>();
   
    public LanchaRapida(String nome, String marca, String modelo, ArrayList<Motor> motor, LocalDate data, ZONA zona,ArrayList<Marinheiro> tripulacao, boolean isAtracado, String tipo) {
        
        super(nome, marca,modelo,data,zona,tripulacao,isAtracado, tipo);
        this.motores = motor;
        this.isHolofote = false;
        
    }
    
    public boolean getIsHolofote() {
      return isHolofote;
    }

    public void setHolofote(boolean holofote) {
      this.isHolofote = holofote;
    }
    
    @Override
    public void mandarParaMissao(ZONA zona){
        super.isOnMission = true;
        setHolofote(true);
        super.isAtracado = false;
        
    }
    
    public ArrayList<Motor> getMotores(){
        return motores;
    }
    
    public void setMotores(ArrayList<Motor> motores){
        this.motores = motores;
    }
  }
