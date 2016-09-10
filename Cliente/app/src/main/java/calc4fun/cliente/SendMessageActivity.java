package calc4fun.cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
            default:
                break;
        }
    }
}
