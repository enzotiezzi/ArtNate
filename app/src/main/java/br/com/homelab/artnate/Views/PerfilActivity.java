package br.com.homelab.artnate.Views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import br.com.homelab.artnate.Dialogs.DoacaoDialog;
import br.com.homelab.artnate.Http.HttpClientHelper;
import br.com.homelab.artnate.Http.ICallback;
import br.com.homelab.artnate.Models.Artista;
import br.com.homelab.artnate.Models.Checkin;
import br.com.homelab.artnate.R;
import br.com.homelab.artnate.Utilities.Utils;

public class PerfilActivity extends AppCompatActivity
{
    private Context context;
    private Artista a;

    private TextView textViewNome;
    private TextView textViewTipoMusica;

    private TextView textViewEstado;
    private Button buttonFazerDoacao;

    private ListView listViewUltimas;
    private ListView listViewFrequentes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        context = this;

        a = new Artista();
        a = new Gson().fromJson(getIntent().getStringExtra("artista"), Artista.class);

        textViewNome = (TextView) findViewById(R.id.textViewNome);
        textViewTipoMusica = (TextView) findViewById(R.id.textViewTipoMusica);

        textViewEstado = (TextView) findViewById(R.id.textViewEstado);
        buttonFazerDoacao = (Button) findViewById(R.id.buttonFazerDoacao);
        listViewUltimas = (ListView) findViewById(R.id.listViewUltimas);
        listViewFrequentes = (ListView) findViewById(R.id.listViewFrequentes);

        textViewNome.setText(a.getNomeArtistico());
        textViewTipoMusica.setText(a.getOcupacao() + " - " + a.getArtes());


        buttonFazerDoacao.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DoacaoDialog doacaoDialog = new DoacaoDialog(context);
                doacaoDialog.show();
                doacaoDialog.setDoarPara(a.getNomeArtistico());
                doacaoDialog.setIdArtista(a.getIdInterno());
                Window window = doacaoDialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });

        carregarLocais(a.getIdInterno());
    }

    private void carregarLocais(int id)
    {
        String url = Utils.buildURL(getApplicationContext(), "geral/ultimosCheckins");

        HttpClientHelper.sendRequest(context, "post", url, new ICallback()
        {
            @Override
            public void onRequestEnd(int statusCode, Throwable t, String response)
            {
                if (statusCode == 200 && t == null)
                {
                    Checkin[] checkins = new Gson().fromJson(response, Checkin[].class);

                    listViewUltimas.setAdapter(new ArrayAdapter<Checkin>(context, android.R.layout.simple_spinner_dropdown_item, checkins));
                    listViewFrequentes.setAdapter(new ArrayAdapter<Checkin>(context, android.R.layout.simple_spinner_dropdown_item, checkins));
                }
            }
        }, id);
    }
}
