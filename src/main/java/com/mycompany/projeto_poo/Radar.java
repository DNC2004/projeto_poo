package com.mycompany.projeto_poo;
import java.util.ArrayList;

/**
 *
 * @author Grupo 3
 */

public interface Radar{
    boolean isLigado = false;

    public void ligarRadar();
    
    public void desligarRadar();
    
    public ArrayList<Embarcacao> carregarEmbarcacoes(ArrayList<Embarcacao> lista);
    
    public ArrayList<Embarcacao> detetarEmbarcacoes(ZONA zona);
    
}
