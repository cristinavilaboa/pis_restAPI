package BussinesLayer;

/**
 * Created by Usuario on 02/09/2016.
 */
public class ClientController {
    private static ClientController ourInstance = new ClientController();

    public static ClientController getInstance() {
        return ourInstance;
    }

    private ClientController() {
    }
}
