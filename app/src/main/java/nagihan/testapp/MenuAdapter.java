package nagihan.testapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MenuAdapter extends BaseAdapter {

    private TextView txtTitle;
    List<Category>categories;
    private Context ctx;

    public MenuAdapter(Context context, List<Category> categories) {
        this.ctx = context;
        this.categories= this.categories;
    }

    @Override
    public int getCount() {
        return MyApplication.categories.size();
    }

    @Override
    public Object getItem(int position) {
        return MyApplication.categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {

            view = LayoutInflater.from(ctx).inflate(R.layout.menu_item, null);

            txtTitle = (TextView) view.findViewById(R.id.txt_title);
            txtTitle.setText(MyApplication.categories.get(position).name);

            return view;
        }

        return null;
    }
}
