package br.com.homelab.artnate.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import br.com.homelab.artnate.R;

/**
 * Created by enzo on 07/08/2016.
 */
public class BarcodeDialog extends Dialog
{
    private String codigo = "";

    public BarcodeDialog(Context context)
    {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.content_codigobarras);

        TextView textViewCancelar = (TextView) findViewById(R.id.textViewCancelar);
        textViewCancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }
}
