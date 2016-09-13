package calc4fun.cliente;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import calc4fun.cliente.BussinesLayer.Controladores.ClientController;
import calc4fun.cliente.DataTypes.DataAyuda;
import calc4fun.cliente.DataTypes.DataProblema;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    Button tutorial,responder,ayuda;
    ImageButton volverMain;
    String id_Problema, laRespuesta;
    EditText respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        TextView contenido = (TextView) findViewById(R.id.ChallengeText);
        String contStr = "No hay contenido";
        Bundle dataFromMenu = getIntent().getExtras();
        if(dataFromMenu != null){
            contStr = dataFromMenu.getString("Problema");
            id_Problema = dataFromMenu.getString("IdProb");
        }
        contenido.setText(id_Problema + "-" + contStr);



        tutorial = (Button) findViewById(R.id.TutorialButton);
        responder = (Button) findViewById(R.id.AnswerButton);
        ayuda = (Button) findViewById(R.id.HelpButton);
        volverMain = (ImageButton) findViewById(R.id.ReturnButton);
        tutorial.setOnClickListener(this);
        responder.setOnClickListener(this);
        ayuda.setOnClickListener(this);
        volverMain.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.TutorialButton:
                new pedirTutorial(this).execute();
                break;
            case R.id.HelpButton:
                Intent actividadAyuda = new Intent(this,SendMessageActivity.class);
                startActivity(actividadAyuda);
                break;
            case R.id.ReturnButton:
                finish();
                break;
            case R.id.AnswerButton:
                respuesta = (EditText) findViewById(R.id.AnswerText);
                laRespuesta = respuesta.getText().toString();

            default:
                break;
        }

    }

    public class pedirTutorial extends AsyncTask<String, Void, DataAyuda>{
        // El JSON tiene problemas para parsear el string de la explicacion.
        AppCompatActivity activity;

        public pedirTutorial(AppCompatActivity activity)
        {
            this.activity = activity;
        }

        @Override
        protected DataAyuda doInBackground(String... params) {
            return ClientController.getInstance().getAyuda(id_Problema);
        }

        @Override
        protected void onPostExecute(DataAyuda resultado) {
            Intent actividadTutorial = new Intent(activity, TutorialActivity.class);
            actividadTutorial.putExtra("HelpText",resultado.getAyuda());
            startActivity(actividadTutorial);
        }
    }

    /*public class responderPregunta extends AsyncTask<String,Void, Integer>{

        AppCompatActivity activity;

        public responderPregunta(AppCompatActivity activity){ this.activity = activity; }

        @Override
        protected Integer doInBackground(String... params) {
            return ClientController.getInstance().ResponderProblema(laRespuesta,id_Problema);
        }
    }*/
}
