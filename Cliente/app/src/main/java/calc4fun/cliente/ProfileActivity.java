package calc4fun.cliente;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import calc4fun.cliente.BussinesLayer.Controladores.ClientController;
import calc4fun.cliente.DataTypes.DataJugador;
import calc4fun.cliente.DataTypes.DataListaRanking;
import calc4fun.cliente.DataTypes.DataLogro;
import calc4fun.cliente.DataTypes.DataMundoNivel;
import calc4fun.cliente.DataTypes.DataPuntosJugador;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    Button returnMain;
    TextView nickName, exp, mundoNivel1, mundoNivel2, logro1, logro2;
    ImageView imagen;
    Bundle dataFromMenu;
    String contStr, contStr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        new pedirPerfil(this).execute();


        returnMain = (Button) findViewById(R.id.BackButton);
        returnMain.setOnClickListener(this);
    }

    public class ProfileListAdapter extends BaseAdapter {
        List<DataLogro> lista;
        Context context;
        ProfileListAdapter(Context context , List<DataLogro> lista){
            this.lista = lista;
            this.context=context;
        }

        @Override
        public int getCount(){
            return lista.size();
        }

        @Override
        public Object getItem(int position){
            return lista.get(position); //0 indexeeedddd?????
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
                rowView = inflater.inflate(R.layout.layout, parent, false);
            }

            // Set data into the view.
            TextView descriptionText = (TextView) rowView.findViewById(R.id.descriptionBadge);

            DataLogro item = this.lista.get(position);
            descriptionText.setText(item.getDesc() + "  x" + String.valueOf(item.getCant()));


            return rowView;
        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BackButton:
                finish();
                break;
            default:
                break;
        }
    }

    public class pedirPerfil extends AsyncTask<Void, Void, DataJugador> {
        // TIENE ERRORES, ME PARECE QUE NO LOGRA MAPEAR LAS LISTA DE NIVEL MUNDO Y DE LOGROS.
        ProfileActivity activity;
        DataMundoNivel mundoNivel;
        DataLogro logro1;
        DataLogro logro2;

        public pedirPerfil(ProfileActivity activity) {
            this.activity = activity;
        }


        @Override
        protected DataJugador doInBackground(Void... params) {
            return ClientController.getInstance().getPerfil();
        }

        @Override
        protected void onPostExecute(DataJugador resultado) {
            List<DataLogro> lista =resultado.getLogros();
            ((TextView)activity.findViewById(R.id.NickView)).setText("Nick: " +resultado.getNick());
            ((TextView)activity.findViewById(R.id.ExpView)).setText("Experiencia: " +String.valueOf(resultado.getExperiencia()));
            ((TextView)activity.findViewById(R.id.mundonivel)).setText("Mundo: " + resultado.getMundosNiveles().get(0).getMundo() + " Nivel: " + String.valueOf(ClientController.Estado.getNivelInicial()) );
            ((ListView)activity.findViewById(R.id.listaperfil)).setAdapter(new ProfileListAdapter(activity, lista));




        }
    }
}
