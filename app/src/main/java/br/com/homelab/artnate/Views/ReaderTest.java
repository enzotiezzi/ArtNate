package br.com.homelab.artnate.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.homelab.artnate.Barcode.IntentIntegrator;
import br.com.homelab.artnate.Barcode.IntentResult;
import br.com.homelab.artnate.R;

public class ReaderTest extends AppCompatActivity
{
    private Button buttonScanQR;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader_test);

        buttonScanQR = (Button) findViewById(R.id.buttonScanQR);

        buttonScanQR.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (scanningResult != null)
        {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            Toast.makeText(getApplicationContext(), scanContent, Toast.LENGTH_LONG).show();
        }
    }
}
