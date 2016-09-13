package calc4fun.cliente.DataTypes;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by tperaza on 12/9/2016.
 */
public class DataListaRanking {
    private List<DataPuntosJugador> listaPuntos=new ArrayList<>();

    public DataListaRanking(){

    }

    public DataListaRanking(List<DataPuntosJugador> listaPuntos) {
        this.listaPuntos=listaPuntos;
    }

    public List<DataPuntosJugador> getListaPuntos() {
        return listaPuntos;
    }

}
