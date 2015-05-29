package com.example.kostuaamirov.favoritshops.AsyncTask;

import android.content.Context;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by kostuaamirov on 28.05.15.
 */
public class LoaderMy extends Loader<String> {

    RequestData_Custom requestData_Custom;

    public LoaderMy(Context context, Bundle args) {
        super(context);

        Log.d("LOG", hashCode() + " create LoaderMy");

    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.d("LOG", hashCode() + " onStartLoading");
        if(takeContentChanged()) {//checked status flag equally the true, if data edited, then start onForceLoad
           forceLoad();
        }
    }

    @Override
    protected boolean onCancelLoad() {
        Log.d("LOG", hashCode() + " onCancelLoad");
        return super.onCancelLoad();
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        Log.d("LOG", hashCode() + " onForceLoad");

        if(requestData_Custom != null)
            requestData_Custom.cancel(true);

        requestData_Custom = new RequestData_Custom();
        requestData_Custom.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "asdadsasd" );

//        if (getTimeTask != null)
//            getTimeTask.cancel(true);
//        getTimeTask = new GetTimeTask();
//        getTimeTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, format);//start AsyncTask
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        Log.d("LOG", hashCode() + " onStopLoading");
    }

    @Override
    protected void onAbandon() {
        super.onAbandon();
    }

    @Override
    protected void onReset() {
        super.onReset();
    }




}
