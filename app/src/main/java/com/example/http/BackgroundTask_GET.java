package com.example.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BackgroundTask_GET extends AsyncTask<Void,Void,Void> {
    String dungdan = bai1.SREVER_NAME;
    TextView tvresult;
    String strname,strscore;
    String str;
    ProgressDialog progressDialog;
    Context context;

    public BackgroundTask_GET(TextView tvresult, String strname, String strscore, Context context) {
        this.tvresult = tvresult;
        this.strname = strname;
        this.strscore = strscore;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dungdan += "?name=" + this.strname + "&pass=" + this.strscore;
        try {
            URL url = new URL(dungdan);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            str = sb.toString();
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Sending.........");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (progressDialog.isShowing()){
            progressDialog.dismiss();

        };
        tvresult.setText(str);
    }
}
