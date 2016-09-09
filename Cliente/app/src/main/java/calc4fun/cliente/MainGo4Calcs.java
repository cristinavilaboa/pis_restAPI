package calc4fun.cliente;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

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
                Intent actividadPlay = new Intent(this,QuestionActivity.class);
                startActivity(actividadPlay);
                break;
            case R.id.ProfileButton:
                Intent actividadProfile = new Intent(this,ProfileActivity.class);
                startActivity(actividadProfile);
                break;
            case R.id.RankingButton:
                Intent actividadRanking = new Intent(this,RankingActivity.class);
                startActivity(actividadRanking);
            default:
                break;
        }
    }
}
