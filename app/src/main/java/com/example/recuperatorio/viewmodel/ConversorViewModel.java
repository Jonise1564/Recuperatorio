package com.example.recuperatorio.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recuperatorio.model.Moneda;


public class ConversorViewModel extends ViewModel {
    private final MutableLiveData<Boolean> habilitarDolares = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> habilitarEuros = new MutableLiveData<>(true);
    private final MutableLiveData<String> resultado = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();


    public LiveData<Boolean> getHabilitarDolares() {
        return habilitarDolares;
    }

    public LiveData<Boolean> getHabilitarEuros() {
        return habilitarEuros;
    }

    public LiveData<String> getResultado() {
        return resultado;
    }

    public LiveData<String> getError() {
        return error;
    }


    public void seleccionarConversion(boolean esDolaresAEuros) {
        habilitarDolares.setValue(esDolaresAEuros);
        habilitarEuros.setValue(!esDolaresAEuros);
    }
    public void convertir(String textoDolares, String textoEuros) {
        boolean esDolaresAEuros = habilitarDolares.getValue() != null && habilitarDolares.getValue();
        String textoCantidad = esDolaresAEuros ? textoDolares : textoEuros;

        if (textoCantidad == null || textoCantidad.trim().isEmpty()) {
            error.setValue("Por favor ingresa una cantidad");
            resultado.setValue("");
            return;
        }


        try {
            double cantidad = Double.parseDouble(textoCantidad);
            double valorConvertido = esDolaresAEuros
                    ? Moneda.convertirAEuros(cantidad)
                    : Moneda.convertirADolares(cantidad);

            resultado.setValue(String.format("%.2f", valorConvertido));
            error.setValue(null); // Limpia errores anteriores
        } catch (NumberFormatException e) {
            error.setValue("Cantidad inválida");
            resultado.setValue("");
        }
    }
}
