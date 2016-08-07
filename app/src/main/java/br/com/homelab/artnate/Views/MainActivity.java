package br.com.homelab.artnate.Views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.homelab.artnate.R;

public class MainActivity extends AppCompatActivity
{
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_relatorio_localizacao);
    }
}
