package br.com.senaigo.indicemassacorporal.enumeradores;

import br.com.senaigo.indicemassacorporal.R;

/**
 * Created by bruno on 16/09/16.
 */
public enum EnumGenero {
    MASCULINO(R.id.rdHomem,"Masculino"),
    FEMININO(R.id.rdMulher,"Feminino");

    private final int codigo;
    private final String descricao;

    private EnumGenero(final int codigo,final String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }
    public static EnumGenero parse(final int codigo){
        for(EnumGenero genero : EnumGenero.values()){
            if(genero.getCodigo() == codigo){
                return genero;
            }
        }
        return null;
    }
}