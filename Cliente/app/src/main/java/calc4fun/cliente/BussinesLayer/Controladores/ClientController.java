package calc4fun.cliente.BussinesLayer.Controladores;

import android.util.Log;

import calc4fun.cliente.DataTypes.DataProblema;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

/**
 * Created by Usuario on 02/09/2016. 1,2,3,4,5
 */
public class ClientController {
    private static String jugador = "nico_fing";
    private static int nivelInicial = 0;
    private static int id_mundo = 1;
    private static ClientController ourInstance = new ClientController();
    private RestTemplate restTemplate;

    public static ClientController getInstance() {
        return ourInstance;
    }

    private ClientController() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

    }


    public int ResponderProblema(String respuesta, int id_problema)
    {
        try {
            final String url = String.format(
                    "http://servidorgrupo8.azurewebsites.net/Servidor/responderPregunta?id_problema=%s&respuesta=%s&id_jugador=%s",
                    String.valueOf(id_problema), respuesta, jugador );
            return restTemplate.getForObject(url, int.class);
        } catch(Exception e){
            Log.e("ResponderProblema mal", e.getMessage(), e);
            return -1;
        }


    }

    public DataProblema GetProblema(){
        try {
            final String url = String.format(
                    "http://servidorgrupo8.azurewebsites.net/Servidor/siguienteProblema?nick=%s&nivel=%s&id_mundo=%s",
                    jugador, String.valueOf(nivelInicial), String.valueOf(id_mundo) );
                    nivelInicial = (nivelInicial + 1) %5;
            return restTemplate.getForObject(url, DataProblema.class);
        } catch(Exception e){
            Log.e("GetProblema anda mal", e.getMessage(), e);
            return null;
        }
    };




}
