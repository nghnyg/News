package nagihan.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends BaseAdapter {

    private TextView textView;
    private ImageView img;
    List<News> newses;
    private Context context;
    private final String url = "http://kozmopolitik.com.tr/";


    public NewsAdapter(Context context, List<News> newses) {
        this.context = context;
        this.newses = newses;
    }

    @Override
    public int getCount() {
        return newses.size();
    }

    @Override
    public Object getItem(int position) {
        return newses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 1000 + position;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {

            view = LayoutInflater.from(context).inflate(R.layout.news_item, null);

            textView = (TextView) view.findViewById(R.id.textView);
            textView.setText(MyApplication.newses.get(position).title);

            img = (ImageView) view.findViewById(R.id.img);

            Picasso.with(context).load(url + MyApplication.newses.get(position).getImg()).into(img);

            return view;
        }

        return view;

    }

    public void clear() {      //Listview i doldurma başlamadan önce boşaltması için.
        if (newses != null) {
            newses.clear();
            notifyDataSetChanged();

        }

    }

    public void addItem(News news) {     //Listview deki itemları ekliyor.Adateri her defasında oluşturmaması için yaptım.
        if (newses == null)
            newses = new ArrayList<>();
        newses.add(news);
        notifyDataSetChanged();


    }
}
