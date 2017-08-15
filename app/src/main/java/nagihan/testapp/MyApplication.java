package nagihan.testapp;

import android.app.Application;

import java.util.List;


public class MyApplication extends Application
{

    public static List<Category> categories = null;

    public static List<News> newses=null;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
