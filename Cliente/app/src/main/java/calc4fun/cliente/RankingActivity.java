package calc4fun.cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class RankingActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listaRanking;
    Button volverMain;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        volverMain = (Button) findViewById(R.id.ReturnRankButton);
        volverMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ReturnRankButton:
                Intent actividadVolver = new Intent(this,MainGo4Calcs.class);
                startActivity(actividadVolver);
                break;
            default:
                break;
        }
    }
}
