package nagihan.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    ListView lvMenu, lvHaber;
    MenuAdapter adapter;
    NewsAdapter newsAdapter;
    CharSequence actionBarTitle;
    CharSequence appTitle;
    int categoryID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        lvMenu = (ListView) findViewById(R.id.lvmenu);
        lvHaber = (ListView) findViewById(R.id.lvhaber);

        adapter = new MenuAdapter(getApplicationContext(), MyApplication.categories);
        lvMenu.setAdapter(adapter);

        newsAdapter = new NewsAdapter(getApplicationContext(), new ArrayList<News>());
        lvHaber.setAdapter(newsAdapter);


        appTitle = getSupportActionBar().getTitle();

        final RequestQueue queue = Volley.newRequestQueue(this);


        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                categoryID = MyApplication.categories.get(position).id;
                actionBarTitle = MyApplication.categories.get(position).name;


                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://kozmopolitik.com.tr/kk" + categoryID + ".json", "", new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray array = null;
                        try {

                            MyApplication.newses = new ArrayList<News>();
                            array = response.getJSONArray("posts");

                            newsAdapter.clear();

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jobj = array.getJSONObject(i);
                                String title = jobj.getString("title");
                                String url = jobj.getString("url");
                                String img = jobj.getString("img");
                                String detail = jobj.getString("detail");
                                News news = new News(title, url, img, detail);
                                MyApplication.newses.add(news);

                                newsAdapter.addItem(news);

                            }

                            lvHaber.setAdapter(newsAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(request);

                drawerLayout.closeDrawer(lvMenu);
            }
        });


        lvHaber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                News ndetail = MyApplication.newses.get(position);

                Intent intent = new Intent(MainActivity.this, NewsDetail.class);
                intent.putExtra("title",ndetail.getTitle());
                intent.putExtra("img", ndetail.getImg());
                intent.putExtra("detail",ndetail.getDetail());
                startActivity(intent);

            }
        });


        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.opened, R.string.closed) {

            @Override
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(appTitle);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                getSupportActionBar().setTitle(actionBarTitle);

            }
        }

        ;

        toggle.syncState();

        drawerLayout.setDrawerListener(toggle);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

