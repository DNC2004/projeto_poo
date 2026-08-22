package com.mycompany.projeto_poo;

/**
 *
 * @author Grupo 3
 */

public class Motor {
    
    private int combustivel;
    private float cilindrada;
    private int potencia;
    private boolean isLigado;

    protected Motor(COMBUSTIVEL tipoCombustivel, int litrosCombustivel, float cilindrada, int potencia, boolean isLigado){
        if (combustivel>0){
            this.combustivel = litrosCombustivel;
        }
        else{
            this.combustivel = 100;
        }
        if(cilindrada>0){
            this.cilindrada = cilindrada;
        }
        else{
            this.cilindrada = 100;
        }
        if(potencia>0){
            this.potencia = potencia;
        }
        else{
            this.potencia = 100;
        }
        if(isLigado == true || isLigado == false){
            this.isLigado = isLigado;
        }
        else{
            this.isLigado = false;
        }
    }
    
    // Para usar quando se cria uma nova embarcacao
    public static Motor criarMotor(){
        InputReader leitor = new InputReader();
        System.out.println("CRIAR MOTOR: ");
        String tipoCombustivel = leitor.getCombustivelFromUser();
        int combustivel = leitor.getIntegerNumber("Combustivel (Litros) : ");
        int potencia = leitor.getIntegerNumber("Potencia: ");
        float cilindrada = leitor.getFloatNumber("Cilindrada: ");
        boolean isLigado;
        
        COMBUSTIVEL tipo = COMBUSTIVEL.valueOf(tipoCombustivel.toUpperCase());
        while(true){
            int ligado = leitor.getIntegerNumber("Ligado (1 -> Sim / 2 -> Nao): ");
            
            if (ligado == 1){
                isLigado = true;
                break;
            }
            else if (ligado == 2){
                isLigado = false;
                break;
            }
            
            else {
                System.out.println("Opcao inesxitente");
            }
        }
        
        return new Motor(tipo,combustivel,cilindrada, potencia,isLigado);
    }
    
    public static Motor criarMotorNavioSuporte(){
        InputReader leitor = new InputReader();
        System.out.println("CRIAR MOTOR: ");
        String tipoCombustivel = leitor.getCombustivelFromUser();
        int combustivel = leitor.getIntegerNumber("Combustivel (Litros): ");
        int potencia = leitor.getIntegerNumber("Potencia: ");
        boolean isLigado;
        
        if (potencia < 25000){
            potencia = 25000;
        }
        
        COMBUSTIVEL tipo = COMBUSTIVEL.valueOf(tipoCombustivel.toUpperCase());
        float cilindrada = leitor.getFloatNumber("Cilindrada: ");
        
        while(true){
            int ligado = leitor.getIntegerNumber("Ligado (1 -> Sim / 2 -> Nao): ");
            
            if (ligado == 1){
                isLigado = true;
                break;
            }
            else if (ligado == 2){
                isLigado = false;
                break;
            }
            
            else {
                System.out.println("Opcao inesxitente");
            }
        }
        
        return new Motor(tipo,combustivel,cilindrada, potencia,isLigado);
    }

    public int getCombustivel(){
        return this.combustivel;
    }

    public float getCilindrada(){
        return this.cilindrada;
    }

    public int getPotencia(){
        return this.potencia;
    }

    public boolean getIsLigado(){
        return this.isLigado;
    }

    public void setCombustivel(int combustivel){
        if (combustivel>0){
            this.combustivel = combustivel;
        }
        else{
            System.out.println("Valor invalido para combustivel, não é possivel mudar");
        }
    }

    public void setCilindrada(float cilindrada){
        if(cilindrada>0){
            this.cilindrada = cilindrada;
        }
        else{
            System.out.println("Valor invalido para cilindrada, não é possivel mudar");
        }
    }

    public void setPotencia(int potencia){
        if(potencia>0){
            this.potencia = potencia;
        }
        else{
            System.out.println("Valor invalido para potencia, não é possivel mudar");
        }
    }

    public void setIsLigado(boolean isLigado){
        if(isLigado == true || isLigado == false){
            this.isLigado = isLigado;
        }
        else{
            System.out.println("Valor invalido para estado do motor, não é possivel mudar");
        }
    }

    public void turnOnMotor(){
        this.isLigado = true;
    }

    public void turnOffMotor(){
        this.isLigado = false;
    }
    
    
}
