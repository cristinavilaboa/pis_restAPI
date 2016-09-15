package calc4fun.cliente;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import calc4fun.cliente.DataTypes.DataEstadoMensaje;

import calc4fun.cliente.BussinesLayer.Controladores.ClientController;

public class SendMessageActivity extends AppCompatActivity implements View.OnClickListener {

    Button enviar,volverMain;
    EditText mensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        enviar = (Button) findViewById(R.id.SendButton);
        volverMain = (Button) findViewById(R.id.CancelButton);
        mensaje = (EditText) findViewById(R.id.editMessageText);
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
                if (!mensaje.getText().toString().isEmpty()){
                    new EnviarMensaje(this).execute(mensaje.getText().toString());
                }
                break;
            default:
                break;
        }
    }

    public class EnviarMensaje extends AsyncTask<String,Void, DataEstadoMensaje> {

        SendMessageActivity activity;

        public EnviarMensaje(SendMessageActivity activity){ this.activity = activity; }

        @Override
        protected DataEstadoMensaje doInBackground(String... params) {
             return ClientController.getInstance().enviarMensaje(params[0]);

        }

        @Override
        protected void onPostExecute(DataEstadoMensaje mens){
            ((TextView) (activity.findViewById(R.id.editMessageText))).setText("");
        }
    }



}
