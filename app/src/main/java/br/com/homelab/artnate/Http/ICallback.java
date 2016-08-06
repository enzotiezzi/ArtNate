package br.com.homelab.artnate.Http;

/**
 * Created by enzo on 30/05/2016.
 */
public interface ICallback
{
    void onRequestEnd(int statusCode, Throwable t, String response);
}
