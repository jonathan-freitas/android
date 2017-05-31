package br.com.senaigo.indicemassacorporal.v1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import br.com.senaigo.indicemassacorporal.R;

/**
 * Calculo IMC
 * IMC é uma sigla utilizada para Índice de Massa Corporal, que é uma medida utilizada para medir a obesidade.
 * O cálculo do IMC é feito dividindo o peso (em quilogramas) pela altura (em metros) ao quadrado
 *
 */
public class CalculoImc extends AppCompatActivity {

    private EditText txtAltura;
    private EditText txtPeso;
    private TextView labelResultadoImc;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
    }

    public void calcular(final View view) {

        //Captura os dados da tela
        txtAltura = (EditText) findViewById(R.id.txtAltura);
        txtPeso = (EditText) findViewById(R.id.txtPeso);
        labelResultadoImc = (TextView) findViewById(R.id.labelResultadoImc);

        //Converte os dados de String para o tipo preterido
        int altura;
        double peso;
        altura = Integer.parseInt(txtAltura.getText().toString());
        peso = Double.parseDouble(txtPeso.getText().toString());

        //Converter altura em metros
        double alturaConvertida = (double)altura/100;

        //Pega a opcao selecionado homem ou mulher
        RadioGroup grupo = (RadioGroup)findViewById(R.id.rdGrupo);
        RadioButton botao = (RadioButton)this.findViewById(grupo.getCheckedRadioButtonId());
        String valorSelecionado = botao.getText().toString();

        //Calcular o IMC
        double imc = peso/(Math.pow(alturaConvertida,2));

        //Regras para homem
        String condicao = "";
        if("homem".equals(valorSelecionado.toLowerCase())){
            if(imc<20.7){
                condicao = "abaixo do peso.";
                labelResultadoImc.setTextColor(Color.YELLOW);
            }
            else if(imc<26.4){
                condicao = "no peso normal.";
                labelResultadoImc.setTextColor(Color.BLUE);
            }else if(imc<27.8){
                condicao = "marginalmente acima do peso.";
                //Laranja
                labelResultadoImc.setTextColor(Color.rgb(255,165,0));
            }else if(imc <31.1){
                condicao = "acima do peso ideal.";
                labelResultadoImc.setTextColor(Color.MAGENTA);
            }else {
                condicao="obeso";
                labelResultadoImc.setTextColor(Color.RED);
            }
            //regra mulher
        }else{
            if(imc<19.1){
                condicao="abaixo do peso.";
                labelResultadoImc.setTextColor(Color.YELLOW);
            }
            else if(imc<25.8){
                condicao="no peso normal.";
                labelResultadoImc.setTextColor(Color.BLUE);
            }
            else if(imc<27.3){
                condicao="marginalmente acima do peso.";
                labelResultadoImc.setTextColor(Color.rgb(255,165,0));
            }
            else if(imc<32.3){
                condicao="acima do peso ideal.";
                labelResultadoImc.setTextColor(Color.MAGENTA);
            }
            else{
                condicao="obesa";
                labelResultadoImc.setTextColor(Color.RED);
            }
        }

        String dados = String.format("Tipo: %s\n Condicao: %s\n IMC:%.2f",valorSelecionado,condicao,imc);
        labelResultadoImc.setText(dados);
        labelResultadoImc.setTextSize(14);

    }
}
