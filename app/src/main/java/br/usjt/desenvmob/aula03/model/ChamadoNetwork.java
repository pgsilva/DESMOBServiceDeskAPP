package br.usjt.desenvmob.aula03.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by arqdsis on 28/03/2018.
 */

public class ChamadoNetwork {
   private static final DateFormat formater  = new SimpleDateFormat("dd-MM-yyyy");


    public static ArrayList<Chamado> buscarChamados(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        ArrayList<Chamado> chamados = new ArrayList<>();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();

        try{
            JSONArray lista = new JSONArray(json);

            for(int i=0; i < lista.length(); i++){
                JSONObject item = (JSONObject)lista.get(i);
                Chamado chamado = new Chamado();
                chamado.setNumero(item.getInt("numero"));
                chamado.setStatus(item.getString("status"));
                String sDataAbertura = (item.getString("dataAbertura"));
                String sDataFechamento = (item.getString("dataFechamento"));
                try {
                    chamado.setDataAbertura((Date)formater.parse(sDataAbertura));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    chamado.setDataFechamento((Date)formater.parse(sDataFechamento));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                JSONObject filaitem = item.getJSONObject("fila");
                Fila fila = new Fila();
                fila.setId((filaitem.getInt("id")));
                fila.setNome((filaitem.getString("nome")));
                fila.setFigura((filaitem.getString("nm_figura")));
                chamado.setFila(fila);
                chamados.add(chamado);
            }

        }catch (JSONException e){
            e.printStackTrace();
            throw new IOException(e);
        }
        return chamados;
    }

}
