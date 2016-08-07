package br.com.homelab.artnate.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import br.com.homelab.artnate.Barcode.IntentIntegrator;
import br.com.homelab.artnate.Barcode.IntentResult;
import br.com.homelab.artnate.Http.HttpClientHelper;
import br.com.homelab.artnate.Http.ICallback;
import br.com.homelab.artnate.Models.Artista;
import br.com.homelab.artnate.Models.Doacao;
import br.com.homelab.artnate.R;
import br.com.homelab.artnate.Session.SessionManager;
import br.com.homelab.artnate.Utilities.Utils;

public class HomeActivity extends AppCompatActivity
{
    private Context context;

    private ListView listViewAoVivo;
    private ListView listViewFavoritos;
    private ListView listViewHistorico;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        context = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                IntentIntegrator scanIntegrator = new IntentIntegrator(HomeActivity.this);
                scanIntegrator.initiateScan();
            }
        });


        listViewAoVivo = (ListView) findViewById(R.id.listViewAoVivo);
        listViewFavoritos = (ListView) findViewById(R.id.listViewFavoritos);
        listViewHistorico = (ListView) findViewById(R.id.listViewHistorico);

        listViewAoVivo.setOnItemClickListener(clickListener);
        listViewFavoritos.setOnItemClickListener(clickListener);

        carregarAoVivo();
        carregarFavoritos();
        carregarHistorico();

    }

    AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            Artista a = (Artista) parent.getAdapter().getItem(position);

            Intent i = new Intent(context, PerfilActivity.class);

            i.putExtra("artista", new Gson().toJson(a));

            startActivity(i);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (scanningResult != null)
        {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            // TODO: pegar artista com o codigo de barras

            String url = Utils.buildURL(getApplicationContext(), "geral/aovivo");

            HttpClientHelper.sendRequest(context, "get", url, new ICallback()
            {
                @Override
                public void onRequestEnd(int statusCode, Throwable t, String response)
                {
                    if (statusCode == 200 && t == null)
                    {
                        Artista[] artistas = new Gson().fromJson(response, Artista[].class);

                        listViewAoVivo.setAdapter(new ArrayAdapter<Artista>(context, android.R.layout.simple_spinner_dropdown_item, artistas));
                    }
                }
            }, null);
        }
    }

    private void carregarAoVivo()
    {
        String url = Utils.buildURL(getApplicationContext(), "geral/aovivo");

        HttpClientHelper.sendRequest(context, "get", url, new ICallback()
        {
            @Override
            public void onRequestEnd(int statusCode, Throwable t, String response)
            {
                if (statusCode == 200 && t == null)
                {
                    Artista[] artistas = new Gson().fromJson(response, Artista[].class);

                    listViewAoVivo.setAdapter(new ArrayAdapter<Artista>(context, android.R.layout.simple_spinner_dropdown_item, artistas));
                }
            }
        }, null);
    }

    private void carregarFavoritos()
    {
        int idDoador = Integer.parseInt(new SessionManager(context).getPreferences("idDoador"));

        String url = Utils.buildURL(getApplicationContext(), "geral/artistasPreferidos");

        HttpClientHelper.sendRequest(context, "post", url, new ICallback()
        {
            @Override
            public void onRequestEnd(int statusCode, Throwable t, String response)
            {
                if (statusCode == 200 && t == null)
                {
                    Artista[] artistas = new Gson().fromJson(response, Artista[].class);

                    listViewFavoritos.setAdapter(new ArrayAdapter<Artista>(context, android.R.layout.simple_spinner_dropdown_item, artistas));
                }
            }
        }, idDoador);
    }

    private void carregarHistorico()
    {
        int idDoador = Integer.parseInt(new SessionManager(context).getPreferences("idDoador"));

        String url = Utils.buildURL(getApplicationContext(), "geral/historicoDeDoacoesFeitas");

        HttpClientHelper.sendRequest(context, "post", url, new ICallback()
        {
            @Override
            public void onRequestEnd(int statusCode, Throwable t, String response)
            {
                if (statusCode == 200 && t == null)
                {
                    Doacao[] doacoes = new Gson().fromJson(response, Doacao[].class);

                    listViewHistorico.setAdapter(new ArrayAdapter<Doacao>(context, android.R.layout.simple_spinner_dropdown_item, doacoes));
                }
            }
        }, idDoador);
    }



}
