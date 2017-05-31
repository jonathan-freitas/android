package br.com.senaigo.mobile.northwindtraders.activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.senaigo.mobile.northwindtraders.R;
import br.com.senaigo.mobile.northwindtraders.adapters.CategoryAdapter;
import br.com.senaigo.mobile.northwindtraders.entities.Category;
import br.com.senaigo.mobile.northwindtraders.persistence.CategoryDAO;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.id;
import static br.com.senaigo.mobile.northwindtraders.R.id.listView;

/**
 * Created by bruno on 04/04/16.
 */
public class HomeActivity extends AppCompatActivity {


    //ButterKnife - injeção de dependência
    //@BindView(R.id.txtCategoryId) public EditText txtCategoryId;
    //@BindView(R.id.txtCategoryName) public EditText txtCategoryName;
    //@BindView(R.id.txtCategoryDescription) public EditText txtCategoryDescription;
    @BindView(R.id.btnCategory) public Button btnCreateCategory;

    //Inicializando
    EditText txtCategoryId;
    EditText txtCategoryName;
    EditText txtCategoryDescription;

    protected Category category;
    protected List<Category> categories;

    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        categories = new ArrayList<Category>();
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);

    }

    protected Category populateList(){

        //nome_txt = (EditText) findViewById(R.id.nome_txt);
        txtCategoryId = (EditText) findViewById(R.id.txtCategoryId);
        txtCategoryName = (EditText) findViewById(R.id.txtCategoryName);
        txtCategoryDescription = (EditText) findViewById(R.id.txtCategoryDescription);

        Category category = new Category();
        category.setCategoryId(Integer.parseInt(txtCategoryId.getText().toString()));
        category.setCategoryName(txtCategoryName.getText().toString());
        category.setDescription(txtCategoryDescription.getText().toString());
        category.setPicture(BitmapFactory.decodeResource(getResources(), R.drawable.student_1));
        return category;
    }


    @OnClick(R.id.btnCategory) public void getCategory(View view) {

        ButterKnife.bind(this, view);
//        Intent it = new Intent(this, CategoryActivity.class);
//        categories.add(populateList());
//        it.putParcelableArrayListExtra("categories", (ArrayList<? extends Parcelable>) categories);
//        startActivity(it);

//        categories.add(populateList());
//        ListView listView = (ListView) findViewById(R.id.listView);
//        listView.setAdapter(new CategoryAdapter(this, categories));

        CategoryDAO categoryDAO = new CategoryDAO(this);
        categoryDAO.onInsert(populateList());

        categories = categoryDAO.onList(null);

        //fecha a conexao com o sqllite
        categoryDAO.close();

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CategoryAdapter(this, categories));

        //Intent it = new Intent(this, CategoryActivity.class);
        //it.putParcelableArrayListExtra("categories", (ArrayList<? extends Parcelable>) categoryDAO.onList(null));

        //startActivity(it);
    }

//    //Metodo para editar um elemento da ListView
    public void update(View view) {

        ListView listView = (ListView)findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"texteeeee",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
