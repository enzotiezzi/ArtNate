package br.com.homelab.artnate.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.Result;

import br.com.homelab.artnate.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ReaderTest extends AppCompatActivity implements ZXingScannerView.ResultHandler
{
    private final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";

    private Button buttonScanQR;

    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
    public void handleResult(Result result)
    {
        Toast.makeText(getApplicationContext(), result.getText(), Toast.LENGTH_LONG).show();
    }

    public void QrScanner()
    {
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }
}
