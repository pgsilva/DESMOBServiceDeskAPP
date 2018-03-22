package br.usjt.desenvmob.aula03.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.usjt.desenvmob.aula03.R;

/**
 * Created by arqdsis on 21/03/2018.
 */

public class ChamadoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Chamado> chamados;

    public ChamadoAdapter(Context context, ArrayList<Chamado> chamados) {
        this.context = context;
        this.chamados = chamados;
    }

    @Override
    public int getCount() {
        return chamados.size();
    }

    @Override
    public Object getItem(int position) {
        return chamados.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        View view = null;
            if(view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.linha_chamado, parent, false);
                ImageView figura  = (ImageView) view.findViewById(R.id.imagem_fila);
                TextView numero = (TextView) view.findViewById(R.id.numero_status_chamado);
                TextView datas = (TextView) view.findViewById(R.id.abertura_fechamento_chamado);
                TextView descricao = (TextView) view.findViewById(R.id.descricao_chamado);
                ViewHolder v = new ViewHolder();
                v.setData(datas);
                v.setDescricao(descricao);
                v.setImagem(figura);
                v.setNumero(numero);
            }


        Chamado chamado = chamados.get(position);
        ViewHolder v = (ViewHolder) view.getTag();

        v.getImagem().setImageDrawable(Util.getDrawableDinamic(context, chamado.getFila().getFigura()));
        v.getNumero().setText(String.format("numero: %d - status: %s", chamado.getNumero(), chamado.getStatus()));
        v.getData().setText(String.format("abertura: %tD - fechamento: %tD", chamado.getDataAbertura(), chamado.getDataFechamento()));
        v.getDescricao().setText(chamado.getDescricao());

        return view;
    }
}
