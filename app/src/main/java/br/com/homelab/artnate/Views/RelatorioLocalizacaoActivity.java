package br.com.homelab.artnate.Views;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import br.com.homelab.artnate.Dialogs.BarcodeDialog;
import br.com.homelab.artnate.Dialogs.CheckinDialog;
import br.com.homelab.artnate.R;

public class RelatorioLocalizacaoActivity extends AppCompatActivity
{
    private Context context;

    private ImageView imageViewDialogBarcode;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_localizacao);

        context = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CheckinDialog checkinDialog = new CheckinDialog(context);
                checkinDialog.show();
                Window window = checkinDialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });

        imageViewDialogBarcode = (ImageView) findViewById(R.id.imageViewBarcode);
        imageViewDialogBarcode.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                BarcodeDialog barcodeDialog = new BarcodeDialog(context);
                barcodeDialog.show();
                Window window = barcodeDialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });
    }

}
