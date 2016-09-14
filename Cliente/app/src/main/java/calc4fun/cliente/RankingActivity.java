package calc4fun.cliente;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;

import calc4fun.cliente.BussinesLayer.Controladores.ClientController;
import calc4fun.cliente.DataTypes.DataListaRanking;
import calc4fun.cliente.DataTypes.DataPuntosJugador;

public class RankingActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listaRanking;
    Button volverMain;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        listaRanking = (ListView) findViewById(R.id.listView);
        listaRanking.setAdapter(new RankingListAdapter(this, new DataListaRanking()));
        new RankingUpdater(this).execute();
        volverMain = (Button) findViewById(R.id.ReturnRankButton);
        volverMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ReturnRankButton:
                finish();
                break;
            default:
                break;
        }
    }

    public void updateRanking(DataListaRanking lista){
        listaRanking.setAdapter( new RankingListAdapter(this, lista));
    }

    public class RankingListAdapter extends BaseAdapter {
        DataListaRanking lista;
        Context context;
        RankingListAdapter(Context context , DataListaRanking lista){
            this.lista = lista;
            this.context=context;
        }

        @Override
        public int getCount(){
            return lista.getListaPuntos().size();
        }

        @Override
        public Object getItem(int position){
            return lista.getListaPuntos().get(position); //0 indexeeedddd?????
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View rowView = convertView;

            if (convertView == null) {
                // Create a new view into the list.
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.ranking_layout_item, parent, false);
            }

            // Set data into the view.
            TextView nickTextView = (TextView) rowView.findViewById(R.id.nick);
            TextView puntosTextView = (TextView) rowView.findViewById(R.id.puntos);

            DataPuntosJugador item = this.lista.getListaPuntos().get(position);
            nickTextView.setText(item.getNombre());
            puntosTextView.setText(String.valueOf(item.getPuntos()));

            return rowView;
        }


    }

    public class RankingUpdater extends AsyncTask<Void, Void, DataListaRanking> {

        RankingActivity activity;

        public RankingUpdater(RankingActivity activity)
        {
            this.activity = activity;
        }

        @Override
        protected DataListaRanking doInBackground(Void... params) {
            return ClientController.getInstance().VerRanking();
        }


        @Override
        protected void onPostExecute(DataListaRanking resultado) {
            if (activity!=null){
                activity.updateRanking(resultado);
            }
        }
    }
}
