package br.com.senaigo.mobile.northwindtraders.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.senaigo.mobile.northwindtraders.entities.Category;
import br.com.senaigo.mobile.northwindtraders.util.M;

/**
 * Created by bruno on 29/04/16.
 */
public class CategoryDAO extends SQLiteOpenHelper {

    public static final boolean DEBUG = true;
    public static final String TABELA = Category.TABELA;
    public static final String LOG_CATEGORIA = "Categoria";
    public static final int VERSION = 1;

    //Atributo que recebe uma conexão e fecha.
//    protected SQLiteDatabase db;

    public static final String CREATE_CATEGORY=
            "CREATE TABLE Category("
                    +Category.KEY_CATEGORY_ID+" integer primary key autoincrement,"
                    +Category.KEY_CATEGORY_NAME+ " text not null,"
                    +Category.KEY_CATEGORY_DESCRIPTION+" text, "
                    +Category.KEY_CATEGORY_PICTURE+" blob)";


    //Construtor que recebe o contexto da view
    public CategoryDAO(Context context){
        super(context,TABELA,null,VERSION);
    }

    public CategoryDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int  version) {
        super (context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(DEBUG){
            Log.i(LOG_CATEGORIA,"new create - Table Category");
        }

        try {
            db.execSQL(CREATE_CATEGORY);
        }catch(Exception e){
            Log.e(LOG_CATEGORIA,"Exception onCreate database." + e.getMessage());
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(DEBUG){
            String texto = "Update database from version %d to %d.";
            texto = String.format(texto,oldVersion,newVersion);
            Log.w(LOG_CATEGORIA,texto);

            //Aqui nós deletamos uma tabela se existir no banco
            //Isso ocorre quando trabalhamos no modo DEBUG
            db.execSQL("DROP TABLE IF EXISTS "+ Category.TABELA);

            //A tabela é criada
            onCreate(db);

            //Fecha o Helper
            db.close();
        }

    }

    public void onInsert(Category category){

        ContentValues values = new ContentValues();
        values.put(Category.KEY_CATEGORY_NAME,category.getCategoryName());
        values.put(Category.KEY_CATEGORY_DESCRIPTION,category.getDescription());

        /*
        * Para gravar uma imagem em um banco de dados, devemos trabalhar com a imagem
        * em Array de bytes.
        * @link http://stackoverflow.com/questions/6341776/how-to-save-bitmap-in-database
        * */

        //Pegamos o valor da variável image. Seu tipo é Bitmap;
        Bitmap image =category.getPicture();

        //Iremos criar uma referência de Stream para transferir a imagem em array de bytes
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        //Transfere o conteúdo da imagem para a referência bos (ByteArrayOutputStream)
        image.compress(Bitmap.CompressFormat.PNG,100,bos);

        //Por último converte o "Stream" bos em array de bytes;
        byte[] imageArray = bos.toByteArray();

        //Agora voltamos a preencher o Map com o array de bytes
        values.put(Category.KEY_CATEGORY_PICTURE,imageArray);

        //Assim persistimos o registro
        getWritableDatabase().insert(Category.TABELA,null,values);

    }

    public void onUpdate(Category category){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Category.KEY_CATEGORY_NAME,category.getCategoryName());
        values.put(Category.KEY_CATEGORY_DESCRIPTION,category.getDescription());
        values.put(Category.KEY_CATEGORY_PICTURE, M.ConvertPicture(category.getPicture()));

        db.update(Category.TABELA,values,Category.KEY_CATEGORY_ID+ " = ?",
                new String[]{String.valueOf(Category.KEY_CATEGORY_ID)});

    }

    public List onList(Category category){

        List<Category> categories = new ArrayList<Category>();

        //Query de consulta
        String query = "SELECT * FROM " + TABELA;

        //Aqui realizamos a consulta e o resultado é atribuído em um Cursor;
        //Um Cursor nos ajuda a navegar nos objetos retornados.
        Cursor cursor = getWritableDatabase().rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                Category item = new Category();

                item.setCategoryId(cursor.getInt(cursor.getColumnIndex(Category.KEY_CATEGORY_ID)));
                item.setCategoryName(cursor.getString(cursor.getColumnIndex(Category.KEY_CATEGORY_NAME)));
                item.setDescription(cursor.getString(cursor.getColumnIndex(Category.KEY_CATEGORY_DESCRIPTION)));

                byte[] image = cursor.getBlob(cursor.getColumnIndex(Category.KEY_CATEGORY_PICTURE));

                /**
                 * Aqui convertemos o array de bytes em um bitmap
                 * http://stackoverflow.com/questions/11613594/android-how-to-convert-byte-array-to-bitmap
                 */
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

                item.setPicture(bitmap);

                //Adiconar registros na lista
                categories.add(item);

            }while(cursor.moveToNext());
        }

        return categories;
    }

    public void onRemove(Category category){

        SQLiteDatabase db = getWritableDatabase();

        //Instrução para deletar o registro.
        //O método espera 3 campos - TABELA, CONDICAO (WHERE), VALOR (argumento)
        db.delete(Category.TABELA,Category.KEY_CATEGORY_ID,
                new String[]{String.valueOf(category.getCategoryId())});
    }


}
