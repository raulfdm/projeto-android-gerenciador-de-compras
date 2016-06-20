package br.unaerp.compras.br.unaerp.compras.utils;

/**
 * Created by raul on 12/6/2016.
 */
public class Encript {

    public static String encripta(String valor) {
        String resultado ="";
        int ascii;
        char letra;
        for (int i = 0; i < valor.length(); i++) {
            letra = valor.charAt(i);
            ascii = (int) letra;
            resultado += String.valueOf(ascii);
        }
        return resultado;
    }
}
