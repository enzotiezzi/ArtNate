package br.com.homelab.artnate.Utilities;

import android.content.Context;

import br.com.homelab.artnate.R;

/**
 * Created by Usuário on 22/06/2016.
 */
public class Utils
{
    public static String buildURL(Context context, String rota)
    {
        String url = "";

        String serverString = context.getResources().getString(R.string.SERVER_URL);

        url = String.format("%s/%s", serverString, rota);

        return url;
    }
}
