package br.com.senaigo.indicemassacorporal.atividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.text.Normalizer;

import br.com.senaigo.indicemassacorporal.R;
import br.com.senaigo.indicemassacorporal.helper.FormularioHelper;

public class CalculoImcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_imc);
    }

    public void calcular(View view) {
        FormularioHelper helper = new FormularioHelper(this);
        helper.calcularIMC();
    }
}
