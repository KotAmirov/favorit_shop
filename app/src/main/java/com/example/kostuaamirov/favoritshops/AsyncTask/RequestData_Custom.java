package com.example.kostuaamirov.favoritshops.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kostuaamirov on 26.05.15.
 */
public class RequestData_Custom extends AsyncTask<String, Void, Void> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... params) {
        String adasd = params[0];
        Log.i("LOG", adasd);
        try {
            // Construct data
            String data = URLEncoder.encode("key1", "UTF-8") + "=" + URLEncoder.encode("1111", "UTF-8");
            data += "&" + URLEncoder.encode("key2", "UTF-8") + "=" + URLEncoder.encode("2222", "UTF-8");

            // Send data
            URL url = new URL("http://ob.e-osetia.ru/index.php/main/device/get_ad_one?id=1696&view=1");
            URLConnection conn = url.openConnection();
            // If you invoke the method setDoOutput(true) on the URLConnection, it will always use the POST method.
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line = null;
            String response = "";
            while ((line = rd.readLine()) != null) {
                response += line;
                Log.i("RESPLINE", line);
            }


//            String jsonText = "{\"name\":\"Мурзик\",\"color\":-16777216,\"age\":9}";
//
//            GsonBuilder builder = new GsonBuilder();
//            Gson gson = builder.create();
//            Cat murzik = gson.fromJson(jsonText, Cat.class);
//            Log.i("GSON", "Имя: " + murzik.name + "\nВозраст: " + murzik.age);


            Log.i("RESP", response);

              wr.close();
            rd.close();
        }
        catch (Exception e) {
        }


//        publishProgress(1);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}


