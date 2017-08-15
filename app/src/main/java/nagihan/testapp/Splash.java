package nagihan.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://kozmopolitik.com.tr/kategorijson.json", "", new Response.Listener<JSONObject>() {
            @Override
                public void onResponse(JSONObject response) {

                JSONArray array = null;
                try {
                    MyApplication.categories = new ArrayList<>();
                    array = response.getJSONArray("posts");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jo = array.getJSONObject(i);
                        int id = jo.getInt("kat_id");
                        String name = jo.getString("kat_name");
                        String desc = jo.getString("kat_desc");
                        String url = jo.getString("kat_url");
                        Category category = new Category(id, name, desc, url);
                        MyApplication.categories.add(category);
                    }
                    startMainActivity();
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

}

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }

    public void startMainActivity() {
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timerThread.start();
    }

}
