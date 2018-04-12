package br.usjt.desenvmob.aula03.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.desenvmob.aula03.R;
import br.usjt.desenvmob.aula03.model.Chamado;
import br.usjt.desenvmob.aula03.model.Fila;
import br.usjt.desenvmob.aula03.model.ChamadoNetwork;

public class MainActivity extends Activity {

    public static final String CHAMADO = "br.usjt.desenvmob.aula03.chamados";
    public static final String FILA = "br.usjt.desenvmob.aula03.filas";
    private EditText txtFila;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtFila = (EditText) findViewById(R.id.buscar_fila);
        ctx = this;
        new DownloadJSONChamado().execute("http:// 10.71.4.155:8080/arqsw_sdesk_a2/rest/filas" ,"http:// 10.71.4.155:8080/arqsw_sdesk_a2" );
    }

    public void buscarChamados(View view) {
        String fila = txtFila.getText().toString();
        new DownloadJSONChamado().execute("http://10.71.4.155:8080/arqsw_sdesk_a2/rest/chamados");
       /* Intent intent = new Intent(this, ListarChamadosActivity.class);
        intent.putExtra(CHAMADO, fila);
        startActivity(intent);*/
    }

    private class DownloadJSONChamado extends AsyncTask<String, Void, ArrayList<Chamado>> {

        @Override
        protected ArrayList<Chamado> doInBackground(String... strings) {
            ArrayList<Chamado> chamados = new ArrayList<>();

            try {
                chamados = ChamadoNetwork.buscarChamados("http://10.71.4.155:8080/arqsw_sdesk_a2/rest/chamados");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return chamados;
        }

            protected void onPostExecute(ArrayList<Chamado> chamados) {
                Intent i = new Intent(ctx, ListarChamadosActivity.class);
                i.putExtra(CHAMADO, chamados);
                startActivity(i);
            }


        }

    private class DownloadJSONFila extends AsyncTask<String, Void, ArrayList<Fila>> {

        @Override
        protected ArrayList<Fila> doInBackground(String... strings) {
            ArrayList<Fila> filas = new ArrayList<>();

            try {
                filas = ChamadoNetwork.getFilas(strings[0], strings[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return filas;
        }
        protected void onPostExecute(ArrayList<Fila> filas) {
            Intent i = new Intent(ctx, ListarChamadosActivity.class);
            i.putExtra(FILA, filas);
            startActivity(i);
        }

    }
}

