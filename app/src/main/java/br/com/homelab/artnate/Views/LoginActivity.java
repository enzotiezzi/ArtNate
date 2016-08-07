
package br.com.homelab.artnate.Views;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.homelab.artnate.R;
import br.com.homelab.artnate.Session.SessionManager;

public class LoginActivity extends AppCompatActivity
{

    private int opt = 0;

    private TextView textViewSouDoador;
    private TextView textViewSouArtista;
    private Button buttonLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewSouDoador = (TextView) findViewById(R.id.textViewSouDoador);
        textViewSouArtista = (TextView) findViewById(R.id.textViewSouArtista);

        buttonLogar = (Button) findViewById(R.id.buttonLogar);

        textViewSouDoador.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                opt = 0;
                textViewSouDoador.setTextColor(Color.parseColor("#2196f3"));
                textViewSouArtista.setTextColor(Color.parseColor("#000000"));
            }
        });

        textViewSouArtista.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                opt = 1;
                textViewSouArtista.setTextColor(Color.parseColor("#2196f3"));
                textViewSouDoador.setTextColor(Color.parseColor("#000000"));
            }
        });

        buttonLogar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(opt == 0)
                {
                    new SessionManager(getApplicationContext()).setPreferences("idDoador", "1");
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
                else
                {
                    new SessionManager(getApplicationContext()).setPreferences("idArtista", "1");
                    startActivity(new Intent(LoginActivity.this, RelatorioLocalizacaoActivity.class));
                }

            }
        });
    }
}
