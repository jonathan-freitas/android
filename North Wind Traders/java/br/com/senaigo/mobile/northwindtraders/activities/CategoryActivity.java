package br.com.senaigo.mobile.northwindtraders.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.senaigo.mobile.northwindtraders.R;
import br.com.senaigo.mobile.northwindtraders.adapters.CategoryAdapter;
import br.com.senaigo.mobile.northwindtraders.entities.Category;
import br.com.senaigo.mobile.northwindtraders.persistence.CategoryDAO;

/**
 * Created by bruno on 04/04/16.
 *
 * @see <a href="https://github.com/JakeWharton/butterknife">Butter Knife</a>
 * @see <a href="https://github.com/square/picasso">Picasso</a>
 */
public class CategoryActivity extends AppCompatActivity {

    private ArrayList<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        categories = getIntent().getParcelableArrayListExtra("categories");

        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new CategoryAdapter(this, categories));
    }
}
