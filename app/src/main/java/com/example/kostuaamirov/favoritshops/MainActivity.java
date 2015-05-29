package com.example.kostuaamirov.favoritshops;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

//import com.example.kostuaamirov.favoritshops.AsyncTask.LoaderCallbacksMy;
import com.example.kostuaamirov.favoritshops.AsyncTask.LoaderMy;
import com.example.kostuaamirov.favoritshops.AsyncTask.RequestData_Custom;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;



public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<String> {

    public Button buttonMyyy;
    public ProgressBar seekBar;
    public RequestData_Custom AsyncTask_my;
//    public LoaderCallbacksMy LoaderCB;



    //loaderCallBack override ============================================== GO

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Loader<String> loader_my = null;

        switch(id) {
            case 1:
                loader_my  = new LoaderMy(this, args);
                Log.d("LOG", "onCreateLoader: " + loader_my.hashCode());

                loader_my.forceLoad();
                break;
        }

        return loader_my;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {

        Log.i("LOG", "onLoadFinished");
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.i("LOG", "onLoaderReset");
    }

    //loaderCallBack override ================================================= END


    public class Cat {

        public String name; // имя
        public int age; // возраст
        public int color; // цвет

        // Конструктор
        public Cat() {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bndl = new Bundle();
        bndl.putString("key", "value");
        getLoaderManager().initLoader(1, bndl, this);


//        AsyncTask_my = new RequestData_Custom();
//        AsyncTask_my.execute();

        seekBar = (ProgressBar) findViewById(R.id.progressBar);

        buttonMyyy = (Button) findViewById(R.id.button1);

        buttonMyyy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {

                    //example GSON instance CLASS IN JSON
                    Cat murzik = new Cat();
                    murzik.name = "Мурзик";
                    murzik.age = 9;
                    murzik.color = Color.BLACK;

                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    Log.i("GSON", gson.toJson(murzik));

//                    //example JSON in instance CLASS
//                    String jsonText = "{\"name\":\"Мурзик\",\"color\":-16777216,\"age\":9}";
//
//                    GsonBuilder builder = new GsonBuilder();
//                    Gson gson = builder.create();
//                    Cat murzik = gson.fromJson(jsonText, Cat.class);
//                    Log.i("GSON", "Имя: " + murzik.name + "\nВозраст: " + murzik.age);

                    seekBar.setVisibility(View.VISIBLE);

                    seekBar.setProgress(0);
//                    seekBar.setVisibility(View.GONE);
                    //perform a Google search in just a few lines of code

                    Log.i("GSON", "SDADAS");

                    new DownloadImageTask((ImageView) findViewById(R.id.imageView1))
                            .execute("http://e-osetia.ru/i/logo.png");//asyncTask load img URL
                    ImageView img = (ImageView) findViewById(R.id.imageView1);
                    img.setBackgroundColor(Color.argb(255,255,255,1));


                    Integer intMyId = v.getId();

                    Toast MyToast = Toast.makeText(getApplicationContext(), intMyId.toString(), Toast.LENGTH_SHORT);
                    MyToast.show();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            }
        });
    }



//async task, loading Image of URL ========= DO
private class DownloadImageTask extends AsyncTask<String, Integer, Bitmap> {
    ImageView bmImage;



    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {


        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {

            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);


            if(mIcon11 != null) {
                publishProgress(1);
                Log.i("TESTMY", "1");
            } else {
                Log.i("TESTMY", "0" );
                publishProgress(0);
            }

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }



        return mIcon11;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        if(values[0] == 1) {
            seekBar.setVisibility(View.INVISIBLE);
            Log.i("ProgressUpdate", values[0].toString());
        }

    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
//async task, loading Image of URL ========= END


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
