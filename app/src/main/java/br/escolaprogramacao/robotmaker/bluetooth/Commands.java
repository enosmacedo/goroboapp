package br.escolaprogramacao.robotmaker.bluetooth;

public class Commands {
    private static Commands uniqueInstance;
    public String COMANDO_FRENTE = "C";
    public String COMANDO_TRAS = "A";
    public String COMANDO_DIREITA = "E";
    public String COMANDO_ESQUERDA = "G";
    public String COMANDO_PARE = "S";
    public String COMANDO_MUDAR_MODULO = "X";
    public String COMANDO_DEBUG = "C";
    public String COMANDO_AUMENTAR_VELOCIDADE = "N";
    public String COMANDO_DIMINUIR_VELOCIDADE = "M";
    public String COMANDO_PRINT = "P";

    private Commands() {
    }

    public static synchronized Commands getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new Commands();

        return uniqueInstance;
    }
}
