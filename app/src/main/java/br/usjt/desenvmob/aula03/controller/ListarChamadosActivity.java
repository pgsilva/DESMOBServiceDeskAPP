package br.usjt.desenvmob.aula03.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.desenvmob.aula03.R;
import br.usjt.desenvmob.aula03.model.Chamado;
import br.usjt.desenvmob.aula03.model.ChamadoAdapter;
import br.usjt.desenvmob.aula03.model.ChamadoNetwork;
import br.usjt.desenvmob.aula03.model.Data;

public class ListarChamadosActivity extends Activity{

    public static final String CHAMADO = "br.usjt.desenvmob.aula03.chamado";
    ArrayList<Chamado> chamados;
    ListView listView;
    Activity contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_chamados);
        final Intent intent = getIntent();
        chamados =(ArrayList<Chamado>)intent.getSerializableExtra(MainActivity.CHAMADO);

        /*
             String nomeFila = intent.getStringExtra(MainActivity.FILA);
            try {
            chamados = ChamadoNetwork.buscarChamados("http://10.0.2.2:8080/arqsw_sdesk_a4_remasterized/rest/chamados");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        listView = (ListView) findViewById(R.id.lista_chamados);
        ChamadoAdapter adapter = new ChamadoAdapter(this, chamados);
        listView.setAdapter(adapter);
        contexto = this;
        listView.setOnItemClickListener(
            new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Chamado chamado = chamados.get(position);
                    Intent intent1 = new Intent(contexto, DetalheChamadoActivity.class);
                    intent1.putExtra(CHAMADO, chamado);
                    startActivity(intent1);
                }
            }
        );
    }

}
