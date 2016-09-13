package calc4fun.cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TutorialActivity extends AppCompatActivity implements View.OnClickListener {

    Button volverMain;
    TextView tutorial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        tutorial = (TextView) findViewById(R.id.LearnText);
        String contStr = "No hay contenido";
        Bundle dataFromMenu = getIntent().getExtras();
        if(dataFromMenu != null){
            contStr = dataFromMenu.getString("HelpText");
        }
        tutorial.setText(contStr);
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
