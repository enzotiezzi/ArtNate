package br.com.homelab.artnate.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import br.com.homelab.artnate.R;

/**
 * Created by enzo on 07/08/2016.
 */
public class CheckinDialog extends Dialog
{
    public CheckinDialog(Context context)
    {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.content_checkin);

        TextView textViewCancelarShow = (TextView) findViewById(R.id.textViewCancelarShow);
        textViewCancelarShow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });
    }
}
