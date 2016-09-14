package calc4fun.cliente;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;

import calc4fun.cliente.BussinesLayer.Controladores.ClientController;
import calc4fun.cliente.DataTypes.DataJugador;
import calc4fun.cliente.DataTypes.DataLogro;
import calc4fun.cliente.DataTypes.DataMundoNivel;
import calc4fun.cliente.DataTypes.DataProblema;

public class MainGo4Calcs extends AppCompatActivity implements View.OnClickListener{
    ImageButton play,profile,ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_go4_calcs);

        play = (ImageButton) findViewById(R.id.PlayButton);
        profile = (ImageButton) findViewById(R.id.ProfileButton);
        ranking = (ImageButton) findViewById(R.id.RankingButton);
        play.setOnClickListener(this);
        profile.setOnClickListener(this);
        ranking.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.PlayButton:
               new PedirPregunta(this).execute();
                break;
            case R.id.ProfileButton:
                new pedirPerfil(this).execute();
                break;
            case R.id.RankingButton:
                Intent actividadRanking = new Intent(this,RankingActivity.class);
                startActivity(actividadRanking);
            default:
                break;
        }
    }

    public class PedirPregunta extends AsyncTask<Void, Void, DataProblema>{

        AppCompatActivity activity;

        public PedirPregunta(AppCompatActivity activity)
        {
            this.activity = activity;
        }

        @Override
        protected DataProblema doInBackground(Void... params) {
            return ClientController.getInstance().GetProblema(ClientController.Estado.getNivelInicial());
        }


        @Override
        protected void onPostExecute(DataProblema resultado) {
            Intent actividadPlay = new Intent(activity, QuestionActivity.class);
            actividadPlay.putExtra("Problema", resultado.getContenido());

            actividadPlay.putExtra("IdProb",resultado.getId().toString());
            startActivity(actividadPlay);
        }

    }

    public class pedirPerfil extends AsyncTask<Void, Void, DataJugador> {
        // TIENE ERRORES, ME PARECE QUE NO LOGRA MAPEAR LAS LISTA DE NIVEL MUNDO Y DE LOGROS.
        AppCompatActivity activity;
        DataMundoNivel mundoNivel;
        DataLogro logro1;
        DataLogro logro2;

        public pedirPerfil(AppCompatActivity activity) {
            this.activity = activity;
        }


        @Override
        protected DataJugador doInBackground(Void... params) {
            return ClientController.getInstance().getPerfil();
        }

        @Override
        protected void onPostExecute(DataJugador resultado) {
            logro1 = null;
            logro2 = null;
            Intent actividadProfile = new Intent(activity, ProfileActivity.class);
            actividadProfile.putExtra("PerfilNick", resultado.getNick());
            actividadProfile.putExtra("PerfilImagen", resultado.getImagen());
            actividadProfile.putExtra("PerfilExp",resultado.getExperiencia());
            actividadProfile.putExtra("PerfilNivel",resultado.getMundosNiveles().get(0).getNivel());
            actividadProfile.putExtra("PerfilMundo",resultado.getMundosNiveles().get(0).getMundo());


            int cant_logros = 0;
            for (DataLogro logro:resultado.getLogros()) {
                actividadProfile.putExtra("logro_desc" + Integer.toString(cant_logros), logro.getDesc());
                actividadProfile.putExtra("logro_cant" + Integer.toString(cant_logros), logro.getCant());
                cant_logros+=1;
            }
            actividadProfile.putExtra("cant_logros", cant_logros);

            startActivity(actividadProfile);
        }
    }
}
