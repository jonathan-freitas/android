package br.com.senaigo.indicemassacorporal.helper;

import android.graphics.Color;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import br.com.senaigo.indicemassacorporal.R;
import br.com.senaigo.indicemassacorporal.atividades.CalculoImcActivity;
import br.com.senaigo.indicemassacorporal.enumeradores.EnumGenero;

/**
 * Created by bruno on 16/09/16.
 */
public class FormularioHelper {

    private EditText txtAltura;
    private EditText txtPeso;
    private TextView labelResultadoImc;
    private RadioGroup rdGrupo;
    private ImageView imgImc;

    private CalculoImcActivity activity;

    public FormularioHelper(final CalculoImcActivity activity) {

        //Captura o campo altura
        txtAltura = (EditText) activity.findViewById(R.id.txtAltura);

        //Captura os dados do campo peso;
        txtPeso = (EditText) activity.findViewById(R.id.txtPeso);

        //Pega a opcao selecionado homem ou mulher
        rdGrupo = (RadioGroup)activity.findViewById(R.id.rdGrupo);

        //Captura o valor do radio selecionado.
        //rdOpcaoSelecionada = (RadioButton)activity.findViewById(rdGrupo.getCheckedRadioButtonId());

        //Apresentação dos dados informados;
        labelResultadoImc = (TextView) activity.findViewById(R.id.labelResultadoImc);

        //Imagem
        imgImc = (ImageView) activity.findViewById(R.id.imgImc);

        this.activity = activity;


    }

    public void calcularIMC() {

        //Converte os dados de String para o tipo preterido
        int altura = Integer.parseInt(txtAltura.getText().toString());
        double peso = Double.parseDouble(txtPeso.getText().toString());

        //Converter altura em metros
        double alturaConvertida = (double)altura/100;

        //Calcular o IMC
        double imc = peso/(Math.pow(alturaConvertida,2));

        regraPesoIdeal(alturaConvertida);

        //
        EnumGenero genero = EnumGenero.parse(rdGrupo.getCheckedRadioButtonId());

        switch(genero){

            case MASCULINO:
                regraImcHomem(imc);
                break;

            case FEMININO:
                regraImcMulher(imc);
                break;
        }
    }

    public void regraPesoIdeal(final double altura){

        double imcMinimo = 18.5;
        double imcMaximo = 25;

        double pesoMinimo = imcMinimo*(Math.pow(altura,2));
        double pesoMaximo = imcMaximo*(Math.pow(altura,2));


        String pesoIdeal = activity.getString(R.string.peso_ideal, pesoMinimo, pesoMaximo);

        labelResultadoImc.setText(pesoIdeal+"\n");
    }

    public void regraImcHomem(final double imc){

        String dados = String.format("IMC: %.2f\n",imc);

        String pesoIdeal = labelResultadoImc.getText().toString()+"\n";

        if(imc<20.7){
            labelResultadoImc.setText(R.string.abaixo_peso);
            labelResultadoImc.setTextColor(Color.YELLOW);
            imgImc.setImageResource(R.drawable.ic_abaixo_peso);
        }
        else if(imc<26.4){
            labelResultadoImc.setText(R.string.peso_normal);
            labelResultadoImc.setTextColor(Color.BLUE);
            imgImc.setImageResource(R.drawable.ic_peso_normal);
        }else if(imc<27.8){
            labelResultadoImc.setText(R.string.acima_peso);
            labelResultadoImc.setTextColor(Color.rgb(255,165,0));
            imgImc.setImageResource(R.drawable.ic_marginalmente_acima_peso_ideal);
        }else if(imc <31.1){
            labelResultadoImc.setText(R.string.acima_peso_ideal);
            labelResultadoImc.setTextColor(Color.MAGENTA);
            imgImc.setImageResource(R.drawable.ic_acima_peso_ideal);
        }else {
            labelResultadoImc.setText(R.string.obeso);
            labelResultadoImc.setTextColor(Color.RED);
            imgImc.setImageResource(R.drawable.ic_obeso);
        }

        labelResultadoImc.setText(pesoIdeal+dados+labelResultadoImc.getText());
        labelResultadoImc.setTextSize(20);

    }

    public void regraImcMulher(final double imc){

        String dados = String.format("IMC: %.2f\n",imc);

        String pesoIdeal = labelResultadoImc.getText().toString()+"\n";

        if(imc<19.1){
            labelResultadoImc.setText(R.string.abaixo_peso);
            labelResultadoImc.setTextColor(Color.YELLOW);
            imgImc.setImageResource(R.drawable.ic_abaixo_peso);
        }
        else if(imc<25.8){
            labelResultadoImc.setText(R.string.peso_normal);
            labelResultadoImc.setTextColor(Color.BLUE);
            imgImc.setImageResource(R.drawable.ic_peso_normal);
        }
        else if(imc<27.3){
            labelResultadoImc.setText(R.string.acima_peso);
            labelResultadoImc.setTextColor(Color.rgb(255,165,0));
            imgImc.setImageResource(R.drawable.ic_marginalmente_acima_peso_ideal);
        }
        else if(imc<32.3){
            labelResultadoImc.setText(R.string.acima_peso_ideal);
            labelResultadoImc.setTextColor(Color.MAGENTA);
            imgImc.setImageResource(R.drawable.ic_acima_peso_ideal);
        }
        else{
            labelResultadoImc.setText(R.string.obeso);
            labelResultadoImc.setTextColor(Color.RED);
            imgImc.setImageResource(R.drawable.ic_obeso);
        }

        labelResultadoImc.setText(pesoIdeal+dados+labelResultadoImc.getText());
        labelResultadoImc.setTextSize(20);
    }
}
