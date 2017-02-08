package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.gmgn.myapplication.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by gmgn on 2/6/2017.
 */

public class EndpointsAsyncTask extends AsyncTask<OnJokeReceivedListener, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private OnJokeReceivedListener listener;
    WifiManager wm ;
    String ip;

    public EndpointsAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(OnJokeReceivedListener... params) {
        if(myApiService == null) {  // Only do this once
            //wm = (WifiManager)context.getSystemService(WIFI_SERVICE);
          //  ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    //http://api.icndb.com/jokes
                    //http://10.0.2.2:8080/_ah/api/
                    //joketeller-158011
                    //builditbigger-157911

                    .setRootUrl("http://10.0.3.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver
            myApiService = builder.build();
        }
        listener = params[0];
        try {
            return myApiService.sayHi("joke").execute().getData();

        } catch (IOException e) {
            return e.getMessage();
        }
    }
    @Override
    protected void onPostExecute(String result) {
        listener.onReceived(result);
    }
}
