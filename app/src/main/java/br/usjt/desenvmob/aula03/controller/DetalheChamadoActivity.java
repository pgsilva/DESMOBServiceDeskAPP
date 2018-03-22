package br.usjt.desenvmob.aula03.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import br.usjt.desenvmob.aula03.R;
import br.usjt.desenvmob.aula03.model.Chamado;

public class DetalheChamadoActivity extends Activity {

    TextView txtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_chamado);
        txtNome = (TextView) findViewById(R.id.chamado_nome);
        Intent intent = getIntent();
        Chamado ch =(Chamado)intent.getSerializableExtra(ListarChamadosActivity.CHAMADO);
        txtNome.setText(ch.getDescricao());
    }
}
