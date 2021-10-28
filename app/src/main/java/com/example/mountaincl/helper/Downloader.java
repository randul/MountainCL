package com.example.mountaincl.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;

public class Downloader extends AsyncTask<Void,Void,String> {

    Context c;
    String urlAddress;
    ListView lv;


    public Downloader(Context c, String urlAddress, ListView lv) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(Void... params) {
        return downloadData();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);


        if(jsonData==null)
        {
            Toast.makeText(c,"Unsuccessful,No data Retrieved", Toast.LENGTH_SHORT).show();
        }else {
            //PARSE
            RequestHandler requestHandler=new RequestHandler();
            HashMap<String, String> params = new HashMap<>();

            //requestHandler.execute();

        }
    }

    private String downloadData()
    {
        //HttpURLConnection con= RequestHandler.connect(urlAddress);
//        if(con==null)
//        {
//            return null;
//        }
//
//        try
//        {
//            InputStream is=new BufferedInputStream(con.getInputStream());
//            BufferedReader br=new BufferedReader(new InputStreamReader(is));
//
//            String line;
//            StringBuffer jsonData=new StringBuffer();
//
//            while ((line=br.readLine()) != null)
//            {
//                jsonData.append(line+"n");
//            }
//
//            br.close();
//            is.close();
//
//            return jsonData.toString();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return null;
    }
}
