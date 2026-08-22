package com.mycompany.projeto_poo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Grupo 3
 */
public class DadosGlobais {
    // Lista estática compartilhada por todas as classes e em todo
    public static List<Embarcacao> globalListaEmbarcacoes = new ArrayList<>();
    public static List<Porto> globalListaPortos = new ArrayList<>();
    public static List<Marinheiro> globalListaMarinheiros = new ArrayList<>();
    
    // String de teste para testar se a funcao de carregar ficheiro funciona
    public static String conteudoCarregado;
    
    public static String nomePorto;
    
    public static int idAtual = 1000;
}
