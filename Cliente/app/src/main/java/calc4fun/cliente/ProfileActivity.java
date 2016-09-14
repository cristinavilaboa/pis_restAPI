package calc4fun.cliente;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    Button returnMain;
    TextView nickName, exp, mundoNivel1, mundoNivel2, logro1, logro2;
    ImageView imagen;
    Bundle dataFromMenu;
    String contStr, contStr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Cargando nickName
        nickName = (TextView) findViewById(R.id.NickView);
        Bundle dataFromMenu = getIntent().getExtras();
        String contStr = "No hay contenido";
        if(dataFromMenu != null){
            contStr = dataFromMenu.getString("PerfilNick");
        }
        nickName.setText(contStr);

        //Cargando Exp
        exp = (TextView) findViewById(R.id.ExpView);
        contStr = "No hay contenido";
        if(dataFromMenu != null){
            contStr = dataFromMenu.getString("PerfilExp");
        }
        exp.setText(contStr);

        //Cargando MundoNivel
        mundoNivel1 = (TextView) findViewById(R.id.MundoNivel1View);
        mundoNivel2 = (TextView) findViewById(R.id.MundoNivel2View);
        contStr = "No hay Contenido";
        contStr2 = "No hay Contenido";
        if (dataFromMenu != null){
            contStr = dataFromMenu.getString("PerfilMundo");
            contStr2 = String.valueOf(dataFromMenu.getInt("PerfilNivel"));
        }
        mundoNivel1.setText("Mundo:" + contStr);
        mundoNivel2.setText("Nivel:" + contStr2);


        //Cargar Logros
        logro1 = (TextView) findViewById(R.id.Logro1View);
        logro2 = (TextView) findViewById(R.id.Logro2View);
        contStr = "No hay Contenido";
        contStr2 = "";
        if (dataFromMenu != null){
            contStr = String.valueOf(dataFromMenu.getInt("logro_cant0"));
            contStr2 = dataFromMenu.getString("logro_desc0");
        }
        logro1.setText("Cantidad: " +contStr + "  Descripción" + contStr2);
        contStr = "No hay Contenido";
        contStr2 = "";
        if (dataFromMenu != null){
            contStr = String.valueOf(dataFromMenu.getInt("logro_cant1"));
            contStr2 = dataFromMenu.getString("logro_desc1");
        }
        logro2.setText("Cantidad: " +contStr + "  Descripción: " + contStr2);

        returnMain = (Button) findViewById(R.id.BackButton);
        returnMain.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BackButton:
                finish();
                break;
            default:
                break;
        }
    }
}
