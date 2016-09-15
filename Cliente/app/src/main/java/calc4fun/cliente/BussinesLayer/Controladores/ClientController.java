package calc4fun.cliente.BussinesLayer.Controladores;

import android.util.Log;

import calc4fun.cliente.DataTypes.DataAyuda;
import calc4fun.cliente.DataTypes.DataExperiencia;
import calc4fun.cliente.DataTypes.DataJugador;
import calc4fun.cliente.DataTypes.DataListaRanking;
import calc4fun.cliente.DataTypes.DataProblema;
import calc4fun.cliente.DataTypes.DataEstadoMensaje;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Usuario on 02/09/2016. 1,2,3,4,5
 */
public class ClientController {
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static String baseUrl = "servidorgrupo8.azurewebsites.net";//"10.0.2.2:8080";   //"servidorgrupo8.azurewebsites.net"; <- usar esta direccion para localhost
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
            return restTemplate.getForObject(
                    UriComponentsBuilder.fromUriString("http://" +baseUrl)
                            .path("/Servidor/verranking")
                            .build().toUriString()
                    , DataListaRanking.class);
        } catch(Exception e){
            Log.e("ResponderProblema mal", e.getMessage(), e);
            return null;
        }
    }


    public DataExperiencia ResponderProblema(String respuesta, String id_problema)
    {

        try {
            return restTemplate.getForObject(
                    UriComponentsBuilder.fromUriString("http://" +baseUrl)
                            .path("/Servidor/responderPregunta")
                            .queryParam("id_problema", id_problema )
                            .queryParam("respuesta", respuesta)
                            .queryParam("id_jugador", jugador)
                            .build().toUriString()
                    , DataExperiencia.class);
        } catch(Exception e){
            Log.e("ResponderProblema mal", e.getMessage(), e);
            return null;
        }


    }

    public DataProblema GetProblema(int nivelAnterior){
        try {
            return restTemplate.getForObject(
                    UriComponentsBuilder.fromUriString("http://" +baseUrl)
                            .path("/Servidor/siguienteProblema")
                            .queryParam("nick", jugador )
                            .queryParam("nivel", nivelAnterior)
                            .queryParam("id_mundo", id_mundo)
                            .build().toUriString()
                    , DataProblema.class);
        } catch(Exception e){
            Log.e("Error:Problem Not Found", e.getMessage(), e);
            return null;
        }
    }

    public DataJugador getPerfil(){
        try {
            return restTemplate.getForObject(
                    UriComponentsBuilder.fromUriString("http://" +baseUrl)
                            .path("/Servidor/verperfil")
                            .queryParam("nick", jugador )
                            .build().toUriString(), DataJugador.class);
        } catch(Exception e){
            Log.e("Error:Profile Not Found", e.getMessage(), e);
            return null;
        }
    }


    public DataAyuda getAyuda(String id_problem){
        try {
            return restTemplate.getForObject(
                    UriComponentsBuilder.fromUriString("http://" +baseUrl)
                            .path("/Servidor/getayuda")
                            .queryParam("id_pregunta", id_problem )
                            .build().toUriString(), DataAyuda.class);
        } catch(Exception e){
            Log.e("Error:Help Not Found", e.getMessage(), e);
            return null;
        }
    }

    public DataEstadoMensaje enviarMensaje(String texto){
        try{
            return restTemplate.getForObject(
                    UriComponentsBuilder.fromUriString("http://" +baseUrl)
                            .path("/Servidor/enviarmensaje")
                            .queryParam("id_problema",  String.valueOf(Estado.getNivelInicial() +1) )
                            .queryParam("fecha", dateFormat.format(new Date()) )
                            .queryParam("mensaje", URLEncoder.encode(texto, "UTF-8") )
                            .queryParam("asunto", "Mensaje de Ayuda" )
                            .build().toUriString(),DataEstadoMensaje.class);


        }catch(Exception e){
            Log.e("Error:Message not sent", e.getMessage(), e);
            return null;
        }

    }

}
