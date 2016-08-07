package br.com.homelab.artnate.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import br.com.homelab.artnate.Http.HttpClientHelper;
import br.com.homelab.artnate.Http.ICallback;
import br.com.homelab.artnate.Models.Artista;
import br.com.homelab.artnate.Models.EfetuarDoacaoInput;
import br.com.homelab.artnate.R;
import br.com.homelab.artnate.Session.SessionManager;
import br.com.homelab.artnate.Utilities.Utils;

/**
 * Created by enzo on 07/08/2016.
 */
public class DoacaoDialog extends Dialog
{
    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;

    private TextView textViewDoarPara;
    private Button buttonFazerDoacao;
    private Button buttonCancelar;

    private int idArtista;

    public DoacaoDialog(Context context)
    {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_valor_doacao);

        radioGroup1 = (RadioGroup) findViewById(R.id.radiogroup1);
        radioGroup2 = (RadioGroup) findViewById(R.id.radiogroup2);

        radioGroup1.setOnCheckedChangeListener(listener1);
        radioGroup2.setOnCheckedChangeListener(listener2);

        radioGroup1.clearCheck();
        radioGroup2.clearCheck();

        textViewDoarPara = (TextView) findViewById(R.id.textViewDoarPara);
        buttonCancelar = (Button) findViewById(R.id.buttonCancelar);
        buttonFazerDoacao = (Button) findViewById(R.id.buttonRealizarDoacao);

        buttonCancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });

        buttonFazerDoacao.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int chkId1 = radioGroup1.getCheckedRadioButtonId();
                int chkId2 = radioGroup2.getCheckedRadioButtonId();
                int realCheck = chkId1 == -1 ? chkId2 : chkId1;

                RadioButton rb = (RadioButton) findViewById(realCheck);

                EfetuarDoacaoInput efetuarDoacaoInput = new EfetuarDoacaoInput();

                int idDoador = Integer.parseInt(new SessionManager(getContext()).getPreferences("idDoador"));

                efetuarDoacaoInput.setIdContribuidor(idDoador);
                efetuarDoacaoInput.setIdArtista(idArtista);
                efetuarDoacaoInput.setValor(rb.getText().toString());

                String url = Utils.buildURL(getContext(), "geral/efetuarDoacao");

                HttpClientHelper.sendRequest(getContext(), "post", url, new ICallback()
                {
                    @Override
                    public void onRequestEnd(int statusCode, Throwable t, String response)
                    {
                        if (statusCode == 200 && t == null)
                        {
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Sucesso")
                                    .setMessage("Doação feita com sucesso")
                                    .setNeutralButton("ok", null)
                                    .show();

                            dismiss();
                        }
                    }
                }, efetuarDoacaoInput);
            }
        });
    }

    public void setDoarPara(String para)
    {
        textViewDoarPara.setText(textViewDoarPara.getText().toString() + para);
    }

    public void setIdArtista(int id)
    {
        idArtista = id;
    }

    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroup2.setOnCheckedChangeListener(null);
                radioGroup2.clearCheck();
                radioGroup2.setOnCheckedChangeListener(listener2);
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroup1.setOnCheckedChangeListener(null);
                radioGroup1.clearCheck();
                radioGroup1.setOnCheckedChangeListener(listener1);
            }
        }
    };
}
