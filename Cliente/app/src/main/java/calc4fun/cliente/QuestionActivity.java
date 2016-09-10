package calc4fun.cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.widget.Button;
import android.widget.ImageButton;

import calc4fun.cliente.BussinesLayer.Controladores.ClientController;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    Button tutorial,responder,ayuda;
    ImageButton volverMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        tutorial = (Button) findViewById(R.id.TutorialButton);
        responder = (Button) findViewById(R.id.AnswerButton);
        ayuda = (Button) findViewById(R.id.HelpButton);
        volverMain = (ImageButton) findViewById(R.id.ReturnButton);
        tutorial.setOnClickListener(this);
        responder.setOnClickListener(this);
        ayuda.setOnClickListener(this);
        volverMain.setOnClickListener(this);
        //ClientController.

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.TutorialButton:
                Intent actividadTutorial = new Intent(this,TutorialActivity.class);
                startActivity(actividadTutorial);
                break;
            case R.id.HelpButton:
                Intent actividadAyuda = new Intent(this,SendMessageActivity.class);
                startActivity(actividadAyuda);
                break;
            case R.id.ReturnButton:
                finish();
                break;
            default:
                break;
        }

    }
}
