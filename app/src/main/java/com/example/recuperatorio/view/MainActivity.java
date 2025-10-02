package com.example.recuperatorio.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.recuperatorio.R;
import com.example.recuperatorio.databinding.ActivityMainBinding;
import com.example.recuperatorio.viewmodel.ConversorViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ConversorViewModel vistaModelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vistaModelo = new ViewModelProvider(this).get(ConversorViewModel.class);

        // Observadores
        vistaModelo.getHabilitarDolares().observe(this, binding.campoDolares::setEnabled);
        vistaModelo.getHabilitarEuros().observe(this, binding.campoEuros::setEnabled);
        vistaModelo.getResultado().observe(this, binding.campoResultado::setText);




        // Eventos delegados
        binding.radioDolaresAEuros.setOnClickListener(v -> vistaModelo.seleccionarConversion(true));
        binding.radioEurosADolares.setOnClickListener(v -> vistaModelo.seleccionarConversion(false));
        binding.botonConvertir.setOnClickListener(v -> {
            vistaModelo.convertir(
                    binding.campoDolares.getText().toString(),
                    binding.campoEuros.getText().toString()
            );
        });
    }
}