package Controladores;


import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import Datatypes.DataListaDataProblema;
import Datatypes.DataProblema;

public interface IControladorSistemaJuego {
	
	public void avanzarJuego(String id_jugador, int id_problema, int id_mundo);
	public void CargarDatos() throws SecurityException, HeuristicMixedException, HeuristicRollbackException, RollbackException, SystemException;
}
