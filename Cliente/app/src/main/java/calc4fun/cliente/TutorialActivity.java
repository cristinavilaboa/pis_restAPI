package calc4fun.cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TutorialActivity extends AppCompatActivity implements View.OnClickListener {

    Button volverMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        volverMain = (Button) findViewById(R.id.ReturnButton);
        volverMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ReturnButton:
                finish();
                break;
            default:
                break;
        }
    }
}
