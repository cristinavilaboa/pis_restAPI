package calc4fun.cliente.BussinesLayer.Controladores;

import android.util.Log;

import calc4fun.cliente.DataTypes.DataAyuda;
import calc4fun.cliente.DataTypes.DataExperiencia;
import calc4fun.cliente.DataTypes.DataJugador;
import calc4fun.cliente.DataTypes.DataListaRanking;
import calc4fun.cliente.DataTypes.DataProblema;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Usuario on 02/09/2016. 1,2,3,4,5
 */
public class ClientController {
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static String baseUrl = "pepefiestaplaya";//servidorgrupo8
    private static String jugador = "nico_fing";

    public static class Estado{
        private static int nivelInicial = 0;
        public static int getNivelInicial(){
            return nivelInicial;
        }
        public static void incNivelInicial(){
            nivelInicial = (nivelInicial + 1 )% 5 ;
        }
    }
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

    public DataListaRanking VerRanking() {
        try {
            final String url =
                    "http://" +baseUrl +".azurewebsites.net/Servidor/verranking";
            return restTemplate.getForObject(url, DataListaRanking.class);
        } catch(Exception e){
            Log.e("ResponderProblema mal", e.getMessage(), e);
            return null;
        }
    }


    public DataExperiencia ResponderProblema(String respuesta, String id_problema)
    {
        try {
            final String url = String.format(
                    "http://" +baseUrl +".azurewebsites.net/Servidor/responderPregunta?id_problema=%s&respuesta=%s&id_jugador=%s",
                    id_problema, respuesta, jugador );
            return restTemplate.getForObject(url, DataExperiencia.class);
        } catch(Exception e){
            Log.e("ResponderProblema mal", e.getMessage(), e);
            return null;
        }


    }

    public DataProblema GetProblema(int nivelAnterior){
        try {
            final String url = String.format(
                    "http://" +baseUrl +".azurewebsites.net/Servidor/siguienteProblema?nick=%s&nivel=%s&id_mundo=%s",
                    jugador, String.valueOf(nivelAnterior), String.valueOf(id_mundo) );
            return restTemplate.getForObject(url, DataProblema.class);
        } catch(Exception e){
            Log.e("Error:Problem Not Found", e.getMessage(), e);
            return null;
        }
    }

    public DataJugador getPerfil(){
        try {
            final String url = String.format(
                    "http://" +baseUrl +".azurewebsites.net/Servidor/verperfil?nick=%s",
                    jugador);
            return restTemplate.getForObject(url, DataJugador.class);
        } catch(Exception e){
            Log.e("Error:Profile Not Found", e.getMessage(), e);
            return null;
        }
    }


    public DataAyuda getAyuda(String id_problem){
        try {
            final String url = String.format(
                    "http://" +baseUrl +".azurewebsites.net/Servidor/getayuda?id_pregunta=%s",
                    id_problem);
            return restTemplate.getForObject(url, DataAyuda.class);
        } catch(Exception e){
            Log.e("Error:Help Not Found", e.getMessage(), e);
            return null;
        }
    }

    public void enviarMensaje(String texto){
        try{
            final String url = String.format(
                    "http://" +baseUrl +".azurewebsites.net/Servidor/enviarmensaje?id_problema=%s&mensaje=%s&fecha=%s&asunto=%s",
                    String.valueOf(Estado.getNivelInicial()),
                    texto,
                    dateFormat.format(new Date()),
                    "asunto");


        }catch(Exception e){
            Log.e("Error:Message not sent", e.getMessage(), e);
        }

    }

}
