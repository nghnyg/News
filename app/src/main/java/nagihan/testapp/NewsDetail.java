package nagihan.testapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetail extends AppCompatActivity {
    TextView haberDetail,haberTitle;
    ImageView imageView,share;

    private final String url = "http://kozmopolitik.com.tr/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail);

        haberDetail=(TextView)findViewById(R.id.haberDetail);
        haberTitle=(TextView)findViewById(R.id.haberTitle);
        imageView =(ImageView)findViewById(R.id.imageView);
        share =(ImageView)findViewById(R.id.share);


        String title = getIntent().getStringExtra("title");
        String img = getIntent().getStringExtra("img");
        String detail = getIntent().getStringExtra("detail");

        haberTitle.setText(title);
        haberDetail.setText(Html.fromHtml(detail));

        Picasso.with(NewsDetail.this).load(url + img).into(imageView);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");
                startActivity(shareIntent);
            }
        });



    }
}
