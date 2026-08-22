/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_poo;

import java.util.ArrayList;

/**
 *
 * @author joseribeiro
 */

/*Colocar aqui métodos usados múltiplas vezes. Objetivo é evitar a repetição de código desnecessariamente */
public class Helper {
    public static ArrayList<Embarcacao> embarcacoesPorZona(ZONA zona) {
        ArrayList<Embarcacao> listaPorZona = new ArrayList<>();
        
        for (Embarcacao embarcacao : DadosGlobais.globalListaEmbarcacoes) 
            if (embarcacao.getZona() == zona) 
                listaPorZona.add(embarcacao);            
        
        if (listaPorZona.isEmpty()) 
            return null;
 
        return listaPorZona;
    }
}
