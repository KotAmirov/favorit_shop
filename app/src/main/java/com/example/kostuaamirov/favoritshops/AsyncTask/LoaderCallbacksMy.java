package com.example.kostuaamirov.favoritshops.AsyncTask;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by kostuaamirov on 28.05.15.
 */
public class LoaderCallbacksMy extends Activity implements LoaderManager.LoaderCallbacks<String> {


    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Loader<String> loader_my = null;

        switch(id) {
            case 1:
                loader_my  = new LoaderMy(this, args);

                Log.d("LOG", "onCreateLoader11111111: " + loader_my.hashCode());
                break;
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {

        Log.i("LOG", "onLoadFinished");
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.i("LOG", "onLoaderReset");
    }

}
