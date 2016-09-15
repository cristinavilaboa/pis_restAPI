package calc4fun.cliente;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    String laRespuesta;
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
        }
        contenido.setText(contStr);



        tutorial = (Button) findViewById(R.id.TutorialButton);
        tutorial.setEnabled(false);
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
                v.setEnabled(false);
                new responderPregunta(this).execute(new String[]{
                        laRespuesta
                });


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
            return ClientController.getInstance().getAyuda(String.valueOf(ClientController.Estado.getNivelInicial() +1));
        }

        @Override
        protected void onPostExecute(DataAyuda resultado) {
            Intent actividadTutorial = new Intent(activity, TutorialActivity.class);
            actividadTutorial.putExtra("HelpText",resultado.getAyuda());
            startActivity(actividadTutorial);
        }
    }

    public class responderPregunta extends AsyncTask<String,Void, Integer>{

        QuestionActivity activity;

        public responderPregunta(QuestionActivity activity){ this.activity = activity; }

        @Override
        protected Integer doInBackground(String... params) {
            return ClientController.getInstance().ResponderProblema(params[0],String.valueOf(ClientController.Estado.getNivelInicial() + 1)).getExperiencia();
        }

        @Override
        protected void onPostExecute(Integer experiencia){
            if (experiencia > 0){
                new PedirPreguntaEnQuestion(activity).execute();
                findViewById(R.id.AnswerText).setBackgroundColor(0xff00ff00);
            }
            else{
                findViewById(R.id.AnswerText).setBackgroundColor(0xffff0000);
                tutorial.setEnabled(true);
            }
            activity.findViewById(R.id.AnswerButton).setEnabled(true);
            ((EditText) activity.findViewById(R.id.AnswerText)).setText("");
        }
    }




    public class PedirPreguntaEnQuestion extends AsyncTask<Void, Void, DataProblema>{

        QuestionActivity activity;

        public PedirPreguntaEnQuestion(QuestionActivity activity)
        {
            this.activity = activity;
        }

        @Override
        protected DataProblema doInBackground(Void... params) {
            ClientController.Estado.incNivelInicial();
            return ClientController.getInstance().GetProblema(ClientController.Estado.getNivelInicial() );
        }


        @Override
        protected void onPostExecute(DataProblema resultado) {
            TextView contenido = (TextView) activity.findViewById(R.id.ChallengeText);
            contenido.setText(resultado.getContenido());
        }

    }
}
