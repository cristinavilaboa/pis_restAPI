package calc4fun.cliente;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    Button returnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        returnMain = (Button) findViewById(R.id.BackButton);
        returnMain.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BackButton:
                Intent actividadBack = new Intent(this, MainGo4Calcs.class);
                startActivity(actividadBack);
                break;
            default:
                break;
        }
    }
}
