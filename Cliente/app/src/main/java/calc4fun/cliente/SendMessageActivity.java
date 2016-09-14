package calc4fun.cliente;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import calc4fun.cliente.BussinesLayer.Controladores.ClientController;

public class SendMessageActivity extends AppCompatActivity implements View.OnClickListener {

    Button enviar,volverMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        enviar = (Button) findViewById(R.id.SendButton);
        volverMain = (Button) findViewById(R.id.CancelButton);
        enviar.setOnClickListener(this);
        volverMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.CancelButton:
                finish();
                break;
            case R.id.SendButton:
                if (!enviar.getText().toString().isEmpty()){
                    new EnviarMensaje(this).execute(enviar.getText().toString());
                }
                break;
            default:
                break;
        }
    }

    public class EnviarMensaje extends AsyncTask<String,Void, Void> {

        SendMessageActivity activity;

        public EnviarMensaje(SendMessageActivity activity){ this.activity = activity; }

        @Override
        protected Void doInBackground(String... params) {
            ClientController.getInstance().enviarMensaje(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void nada){
            ((TextView) (activity.findViewById(R.id.editMessageText))).setText("");
        }
    }



}
