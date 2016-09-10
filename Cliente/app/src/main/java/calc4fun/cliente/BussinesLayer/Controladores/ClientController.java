package calc4fun.cliente.BussinesLayer.Controladores;

/**
 * Created by Usuario on 02/09/2016. 1,2,3,4,5
 */
public class ClientController {
    private static int current = 0;
    private static ClientController ourInstance = new ClientController();

    public static ClientController getInstance() {
        return ourInstance;
    }

    private ClientController() {

    }


}
