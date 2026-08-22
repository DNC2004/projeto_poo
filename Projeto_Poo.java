
package com.mycompany.projeto_poo;

/**
 *
 * @author Gprupo 3
 */
public class Projeto_Poo {

    public static void main(String[] args) {
        InputReader scanner = new InputReader();
        String pergunta = "Escolha: ";
        int modo;
        while (true){
            System.out.println("\n\tMenu: ");
            System.out.println("1 -> Modo Manutencao");
            System.out.println("2 -> Modo Utilizacao");
            System.out.println("3 -> Encerrar o programa");

            modo = scanner.getIntegerNumber(pergunta);

            if (modo == 1){
                System.out.println("A entrar no modo de manutencao...");
                Menu.menuManutencao();
            }
            
            else if (modo == 2){
                System.out.println("A entrar no modo de utilizacao...");
                Menu.menuUtilizacao();
            }
            
            else if (modo == 3){
                System.out.println("A encerrar o programa...");
                break;
            }
            
            else {
                System.out.println("Opcao inexistente");
            }
        }
    }
}
