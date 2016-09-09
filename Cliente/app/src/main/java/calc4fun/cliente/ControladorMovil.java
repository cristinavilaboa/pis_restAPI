package calc4fun.cliente;

/**
 * Created by tperaza on 8/9/2016.
 */
public class ControladorMovil {
    private static ControladorMovil ourInstance = new ControladorMovil();

    public static ControladorMovil getInstance() {
        return ourInstance;
    }

    private ControladorMovil() {
    }
}
