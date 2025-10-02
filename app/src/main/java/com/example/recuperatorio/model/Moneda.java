package com.example.recuperatorio.model;

public class Moneda {

    private static final double TASA_EUROS_A_DOLARES = 1.06;
    private static final double TASA_DOLARES_A_EUROS = 0.94;

    public static double convertirADolares(double euros) {
        return euros * TASA_EUROS_A_DOLARES;
    }

    public static double convertirAEuros(double dolares) {
        return dolares * TASA_DOLARES_A_EUROS;
    }
}
