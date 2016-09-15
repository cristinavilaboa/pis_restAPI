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
                Intent actividadProfile = new Intent(this, ProfileActivity.class);
                startActivity(actividadProfile);
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


}
