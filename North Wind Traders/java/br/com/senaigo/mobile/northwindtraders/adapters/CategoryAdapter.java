package br.com.senaigo.mobile.northwindtraders.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.senaigo.mobile.northwindtraders.R;
import br.com.senaigo.mobile.northwindtraders.entities.Category;
import butterknife.BindView;

/**
 * Created by bruno on 04/04/16.
 */
public class CategoryAdapter extends BaseAdapter {

    private Context context;
    private List<Category> categories;
    private LayoutInflater inflater;

    @BindView(R.id.eCategoryId) TextView eCategoryId;

    public CategoryAdapter(Context context, List<Category> categories){
        this.context = context;
        this.categories = categories;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        Category category = categories.get(position);

        if(convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.category, parent, false);
        }
        else{
            view = convertView;
        }

        ImageView iv = (ImageView) view.findViewById(R.id.eCategoryImage);
        iv.setImageBitmap(category.getPicture());

        TextView tv = (TextView) view.findViewById(R.id.eCategoryId);
        tv.setText(category.getCategoryId().toString());

        tv = (TextView) view.findViewById(R.id.eCategoryName);
        tv.setText(category.getCategoryName());

        tv = (TextView) view.findViewById(R.id.eCategoryDescription);
        tv.setText(category.getDescription());

        return view;
    }
}
