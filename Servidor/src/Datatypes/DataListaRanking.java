/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datatypes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cristina
 */
public class DataListaRanking {
    private List<DataPuntosJugador> listaPuntos=new ArrayList<>();

    public DataListaRanking(List<DataPuntosJugador> listaPuntos) {
        this.listaPuntos=listaPuntos;
    }
    
    public List<DataPuntosJugador> getListaPuntos() {
        return listaPuntos;
    }

    
    
}
