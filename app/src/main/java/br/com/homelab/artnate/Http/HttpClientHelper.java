package br.com.homelab.artnate.Http;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.homelab.artnate.R;


/**
 * Created by enzo on 30/05/2016.
 */
public class HttpClientHelper
{
    public static void sendRequest(final Context context, final String method, final String url, final ICallback onResponse, final Object body)
    {
        (new AsyncTask<String, Void, String>()
        {
            private ProgressDialog progressDialog;
            private int status;
            private boolean error = false;

            @Override
            protected void onPreExecute()
            {
                progressDialog = new ProgressDialog(context);
                progressDialog.setTitle(context.getResources().getString(R.string.wait));
                progressDialog.show();
            }

            @Override
            protected String doInBackground(String... params)
            {
                String response = "";
                try
                {
                    switch (method.toUpperCase())
                    {
                        case "GET":
                            response = httpGet(url);
                            break;
                        case "POST":
                            response = httpPost(url, body);
                            break;
                        case "PUT":
                            response = httpPut(url, body);
                            break;
                        case "DELETE":
                            response = httpDelete(url, body);
                            break;
                    }

                    return response;
                }
                catch (Throwable t)
                {
                    error = true;
                    onResponse.onRequestEnd(500, t, context.getResources().getString(R.string.generic_error));
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String response)
            {
                if(!error)
                {
                    if(response != null)
                        onResponse.onRequestEnd(200, null, response);
                }

                progressDialog.dismiss();
            }
        }).execute();
    }

    private static String httpGet(String url)
    {
        try
        {
            URL _url = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoInput(true);

            String json = readInputStream(connection.getInputStream());

            return json;
        } catch (Throwable t)
        {
            return null;
        }
    }

    private static String httpPost(String url, Object body)
    {
        try
        {
            URL _url = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            String json = new Gson().toJson(body);
            writeOutputStream(connection.getOutputStream(), json);
            String response = readInputStream(connection.getInputStream());

            return response;
        } catch (Throwable t)
        {
            return null;
        }
    }

    private static String httpPut(String url, Object body)
    {
        try
        {
            URL _url = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setChunkedStreamingMode(url.getBytes().length);
            connection.setDoOutput(true);

            String json = new Gson().toJson(body);
            writeOutputStream(connection.getOutputStream(), json);
            String response = readInputStream(connection.getInputStream());

            return response;
        } catch (Throwable t)
        {
            return null;
        }
    }

    private static String httpDelete(String url, Object body)
    {
        try
        {
            URL _url = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setChunkedStreamingMode(url.getBytes().length);
            connection.setDoOutput(true);

            String json = new Gson().toJson(body);
            writeOutputStream(connection.getOutputStream(), json);
            String response = readInputStream(connection.getInputStream());

            return response;
        } catch (Throwable t)
        {
            return null;
        }
    }

    private static String readInputStream(InputStream stream)
    {
        try
        {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(stream));

            String json = "";
            String linha = "";

            while((linha = bReader.readLine()) != null)
                json += linha;
            bReader.close();
            stream.close();

            return json;
        } catch (Throwable t)
        {
            return null;
        }

    }

    private static void writeOutputStream(OutputStream stream, String json) throws Exception
    {
        BufferedWriter bWriter = new BufferedWriter(
                new OutputStreamWriter(stream));
        bWriter.write(json);
        bWriter.flush();
        bWriter.close();
        stream.close();
    }
}
